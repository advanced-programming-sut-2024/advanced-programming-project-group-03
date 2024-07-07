package Server;

import com.google.gson.Gson;
import ir.ap.probending.Model.Game.PreGame;
import ir.ap.probending.Model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class GameSession extends Thread {
    private static final List<Socket> sockets = new ArrayList<>();
    private static GameSession instance = new GameSession();
    private static DataOutputStream log;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            instance.start();  // Start the GameSession thread
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                synchronized (sockets) {
                    sockets.add(socket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized GameSession getInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }

    @Override
    public void run() {
        try {
            Socket logSocket = new Socket("localhost", 5000);
            log = new DataOutputStream(logSocket.getOutputStream());
            System.out.println("Logging started");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            List<Socket> deletedSockets = new ArrayList<>();
            synchronized (sockets) {
                for (Socket socket : sockets) {
                    try {
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        String message = dataInputStream.readUTF();
                        if (message != null && !message.isEmpty()) {
                            System.out.println(message);
                            log.writeUTF(message);
                            log.flush();
                            processMessage(socket, message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        deletedSockets.add(socket);
                    }
                }
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                sockets.removeAll(deletedSockets);
                for (Socket socket : deletedSockets) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void processMessage(Socket socket, String message) throws IOException {
        GameInfo gameInfo = GameInfo.getGameInfo(socket);
        if (gameInfo != null) {
            if (message.startsWith("pregame ")) {
                handlePreGameMessage(socket, message, gameInfo);
            } else if (message.equals("AreUsersReady")) {
                handleAreUsersReadyMessage(socket, gameInfo);
            } else if (message.equals("getGameBoard")) {
                handleGetGameBoardMessage(socket, gameInfo);
            } else {
                forwardMessageToOpponent(socket, message, gameInfo);
            }
        } else {
            assignSocketToGameInfo(socket, message);
        }
    }

    private void handlePreGameMessage(Socket socket, String message, GameInfo gameInfo) throws IOException {
        try {
            String serializedPreGame = message.substring(8);
            PreGame preGame = deserializePreGame(serializedPreGame);
            if (gameInfo.getFirstPlayerSocket().equals(socket)) {
                if (gameInfo.getFirstPlayerPreGame() == null) {
                    gameInfo.setFirstPlayerPreGame(preGame);
                }
            } else if (gameInfo.getSecondPlayerSocket().equals(socket)) {
                if (gameInfo.getSecondPlayerPreGame() == null) {
                    gameInfo.setSecondPlayerPreGame(preGame);
                }
            }
            if (gameInfo.getFirstPlayerPreGame() != null && gameInfo.getSecondPlayerPreGame() != null) {
                gameInfo.setupGameBoard();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleAreUsersReadyMessage(Socket socket, GameInfo gameInfo) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        if (gameInfo.isGameStarted()) {
            dataOutputStream.writeUTF("yes");
        } else {
            dataOutputStream.writeUTF("no");
        }
        dataOutputStream.flush();
    }

    private void handleGetGameBoardMessage(Socket socket, GameInfo gameInfo) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(new Gson().toJson(gameInfo.getGameBoard()));
        dataOutputStream.flush();
    }

    private void forwardMessageToOpponent(Socket socket, String message, GameInfo gameInfo) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(
                gameInfo.getFirstPlayerSocket().equals(socket) ?
                        gameInfo.getSecondPlayerSocket().getOutputStream() :
                        gameInfo.getFirstPlayerSocket().getOutputStream());
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
    }

    private void assignSocketToGameInfo(Socket socket, String message) {
        for (GameInfo gameInfo : GameInfo.getGameInfos()) {
            if (gameInfo.getFirstPlayer().getUsername().equals(message) && gameInfo.getFirstPlayerSocket() == null) {
                gameInfo.setFirstPlayerSocket(socket);
                return;
            } else if (gameInfo.getSecondPlayer().getUsername().equals(message) && gameInfo.getSecondPlayerSocket() == null) {
                gameInfo.setSecondPlayerSocket(socket);
                return;
            }
        }
    }

    public static PreGame deserializePreGame(String serializedPreGame) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(serializedPreGame);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (PreGame) objectInputStream.readObject();
        }
    }
}
