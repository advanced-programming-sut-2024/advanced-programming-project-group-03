package Server;

import com.google.gson.Gson;
import ir.ap.probending.Model.Game.GameBoard;
import ir.ap.probending.Model.Game.PreGame;
import ir.ap.probending.Model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class GameSession extends Thread {
    private static final List<ClientHandler> clients = new ArrayList<>();
    private static GameSession instance = new GameSession();
    private static DataOutputStream log;
    private static DataInputStream logIn;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            instance.start();  // Start the GameSession thread
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                synchronized (clients) {
                    clients.add(clientHandler);
                }
                clientHandler.start();  // Start a new thread for each client
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
            logIn = new DataInputStream(logSocket.getInputStream());
            System.out.println("Logging started");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            synchronized (clients) {
                clients.removeIf(client -> !client.isAlive());  // Remove dead clients
            }
        }
    }

    public static PreGame deserializePreGame(String serializedPreGame) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(serializedPreGame);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (PreGame) objectInputStream.readObject();
        }
    }

    static class ClientHandler extends Thread {
        private final Socket socket;
        private final DataInputStream dataInputStream;
        private final DataOutputStream dataOutputStream;

        ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String message = dataInputStream.readUTF();
                    if (message != null && !message.isEmpty()) {
                        System.out.println(message);
                        synchronized (log) {
                            log.writeUTF(message);
                            log.flush();
                        }
                        System.out.println("processing message: " + message);
                        processMessage(socket, message);
                        System.out.println("message processed: " + message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (clients) {
                    clients.remove(this);
                }
            }
        }

        synchronized private void processMessage(Socket socket, String message) throws IOException {
            GameInfo gameInfo = GameInfo.getGameInfo(socket);
            if (message.startsWith("pregame ")) {
                System.out.println("pregame message");
                handlePreGameMessage(socket, message, gameInfo);
            } else if (message.equals("AreUsersReady")) {
                System.out.println("areUsersReady message");
                handleAreUsersReadyMessage(gameInfo);
            } else if (message.equals("getGameBoard")) {
                handleGetGameBoardMessage(gameInfo);
                System.out.println("getGameBoard message");
            } else if (message.startsWith("startGame")) {
                serverStart(message.split(" ")[1], message.split(" ")[2]);
            } else {
                assignSocketToGameInfo(socket, message);
                System.out.println("socket assigned to gameInfo");
            }
        }

        synchronized private void handlePreGameMessage(Socket socket, String message, GameInfo gameInfo) throws IOException {
            try {
                String serializedPreGame = message.substring(8);
                PreGame preGame = deserializePreGame(serializedPreGame);
                System.out.println("handlePreGameMessage");
                if (gameInfo.getFirstPlayerSocket().equals(socket)) {
                    if (gameInfo.getFirstPlayerPreGame() == null) {
                        gameInfo.setFirstPlayerPreGame(preGame);
                        System.out.println("first player pregame set");
                    }
                } else if (gameInfo.getSecondPlayerSocket().equals(socket)) {
                    if (gameInfo.getSecondPlayerPreGame() == null) {
                        gameInfo.setSecondPlayerPreGame(preGame);
                        System.out.println("second player pregame set");
                    }
                }
                if (gameInfo.getFirstPlayerPreGame() != null && gameInfo.getSecondPlayerPreGame() != null) {
                    System.out.println("beginning setup");
                    gameInfo.setupGameBoard();
                    System.out.println("setup complete");
                }
                System.out.println("preGameMessage handled");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        synchronized private void handleAreUsersReadyMessage(GameInfo gameInfo) throws IOException {
            if (gameInfo.isGameStarted()) {
                dataOutputStream.writeUTF("yes");
                System.out.println("yes");
            } else {
                dataOutputStream.writeUTF("no");
                System.out.println("no");
            }
            dataOutputStream.flush();
        }

        synchronized private void handleGetGameBoardMessage(GameInfo gameInfo) throws IOException {
            dataOutputStream.writeUTF(serializeGetBoard(gameInfo.getGameBoard()));
            dataOutputStream.flush();
        }

        synchronized private void forwardMessageToOpponent(Socket socket, String message, GameInfo gameInfo) throws IOException {
            DataOutputStream opponentOutputStream = new DataOutputStream(
                    gameInfo.getFirstPlayerSocket().equals(socket) ?
                            gameInfo.getSecondPlayerSocket().getOutputStream() :
                            gameInfo.getFirstPlayerSocket().getOutputStream());
            opponentOutputStream.writeUTF(message);
            opponentOutputStream.flush();
        }

        synchronized private void assignSocketToGameInfo(Socket socket, String message) {
            String username = message.trim();
            System.out.println("Assigning socket to game info for username: " + username);
            for (GameInfo gameInfo : GameInfo.getGameInfos()) {
                System.out.println("Checking game info with users: " + gameInfo.getFirstPlayer().getUsername() + " and " + gameInfo.getSecondPlayer().getUsername());
                if (gameInfo.getFirstPlayer().getUsername().equals(username) && gameInfo.getFirstPlayerSocket() == null) {
                    gameInfo.setFirstPlayerSocket(socket);
                    System.out.println("Assigned socket to first player: " + username);
                    return;
                } else if (gameInfo.getSecondPlayer().getUsername().equals(username) && gameInfo.getSecondPlayerSocket() == null) {
                    gameInfo.setSecondPlayerSocket(socket);
                    System.out.println("Assigned socket to second player: " + username);
                    return;
                }
            }
            System.out.println("No matching game info found for username: " + username);
        }

    }

    public static void serverStart(String firstUsername, String secondUsername) throws IOException {
        Gson gson = new Gson();
        log.writeUTF("searchUser " + firstUsername);
        log.flush();
        User firstUser = gson.fromJson(logIn.readUTF(), User.class);
        log.writeUTF("searchUser " + secondUsername);
        log.flush();
        User secondUser = gson.fromJson(logIn.readUTF(), User.class);
        GameInfo gameInfo = new GameInfo(firstUser, secondUser, null, null);
    }

    public static String serializeGetBoard(GameBoard gameBoard) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(gameBoard);
        }
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
}
