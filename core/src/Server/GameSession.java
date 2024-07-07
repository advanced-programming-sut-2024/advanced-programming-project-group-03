package Server;

import com.google.gson.Gson;
import ir.ap.probending.Model.Game.PreGame;
import ir.ap.probending.Model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;

import static java.lang.Thread.sleep;

public class GameSession extends Thread{
    private static ArrayList<Socket> sockets = new ArrayList<>();
    private static GameSession instance = new GameSession();
    static DataOutputStream log;


    synchronized public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            new GameSession().start();
            System.out.println("hi");
            while (true) {
                Socket socket = serverSocket.accept();
                synchronized(sockets) {
                    instance.sockets.add(socket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public static GameSession getInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }

//    public static void runGameSession() throws IOException {
//        new RunGameSession().start();
//    }
//
//    static class RunGameSession extends Thread {
//        @Override
//        public void run() {
//            main(null);
//        }
//    }

//    static class RunSendMessage extends Thread {
//        @Override
//        public void run() {
//            try {
//                sendMessage();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }


    synchronized public void run() {
        try {
            Socket socket2 = new Socket("localhost", 5000);
            log = new DataOutputStream(socket2.getOutputStream());
            System.out.println("hi");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            ArrayList<Socket> deletedSockets = new ArrayList<>();
            synchronized(sockets) {
                socketFor:
                for (Socket socket : sockets) {

                    try {
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        String message = dataInputStream.readUTF();
                        if (message != null && !message.equals("")) {
                            System.out.println(message);
                            log.writeUTF(message);
                            log.flush();
                            GameInfo gameInfo2 = GameInfo.getGameInfo(socket);
                            if (gameInfo2 != null) {
                                if (message.startsWith("pregame ")) {
                                    if (gameInfo2.getFirstPlayerSocket().equals(socket)) {
                                        if (gameInfo2.getFirstPlayerPreGame() != null) {
                                            continue;
                                        }
                                        try {
                                            String serializedPreGame = message.substring(8);
                                            PreGame preGame = deserializePreGame(serializedPreGame);
                                            gameInfo2.setFirstPlayerPreGame(preGame);
                                            if (gameInfo2.getSecondPlayerPreGame() != null) {
                                                gameInfo2.setupGameBoard();
                                            }
                                        } catch (IOException | ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    } else if (gameInfo2.getSecondPlayerSocket().equals(socket)) {
                                        if (gameInfo2.getSecondPlayerPreGame() != null) {
                                            continue;
                                        }
                                        try {
                                            String serializedPreGame = message.substring(8);
                                            PreGame preGame = deserializePreGame(serializedPreGame);
                                            gameInfo2.setSecondPlayerPreGame(preGame);
                                            if (gameInfo2.getFirstPlayerPreGame() != null) {
                                                gameInfo2.setupGameBoard();
                                            }
                                        } catch (IOException | ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                if (message.equals("AreUsersReady")) {
                                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                                    if (gameInfo2.isGameStarted()) {
                                        dataOutputStream.writeUTF("yes");
                                        dataOutputStream.flush();
                                    } else {
                                        dataOutputStream.writeUTF("no");
                                        dataOutputStream.flush();
                                    }
                                    continue;
                                }

                                if (message.equals("getGameBoard")) {
                                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                                    dataOutputStream.writeUTF(new Gson().toJson(gameInfo2.getGameBoard()));
                                    dataOutputStream.flush();
                                    continue;
                                }

                                if (gameInfo2.getFirstPlayerSocket().equals(socket) && !gameInfo2.getFirstPlayer().getUsername().equals(message)) {
                                    DataOutputStream dataOutputStream = new DataOutputStream(gameInfo2.getSecondPlayerSocket().getOutputStream());
                                    dataOutputStream.writeUTF(message);
                                    dataOutputStream.flush();
                                    continue;
                                } else if (gameInfo2.getSecondPlayerSocket().equals(socket) && !gameInfo2.getSecondPlayer().getUsername().equals(message)) {
                                    DataOutputStream dataOutputStream = new DataOutputStream(gameInfo2.getFirstPlayerSocket().getOutputStream());
                                    dataOutputStream.writeUTF(message);
                                    dataOutputStream.flush();
                                    continue;
                                }
                            } else {
                                for (GameInfo gameInfo : GameInfo.getGameInfos()) {

                                    if (gameInfo.getFirstPlayer().getUsername().equals(message) && gameInfo.getFirstPlayerSocket() == null) {
                                        gameInfo.setFirstPlayerSocket(socket);
                                        continue socketFor;
                                    } else if (gameInfo.getSecondPlayer().getUsername().equals(message) && gameInfo.getFirstPlayerSocket() == null) {
                                        gameInfo.setSecondPlayerSocket(socket);
                                        continue socketFor;
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        deletedSockets.add(socket);
                    }
                }
                for (Socket socket : deletedSockets) {
                    instance.sockets.remove(socket);
                    GameInfo.getGameInfos().removeIf(gameInfo -> gameInfo.getFirstPlayerSocket().equals(socket) || gameInfo.getSecondPlayerSocket().equals(socket));
                }
            }
        }
    }

    public void serverStart(User firstUser, User secondUser) {
        new GameInfo(firstUser, secondUser, null, null);
    }

    public static PreGame deserializePreGame(String serializedPreGame) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(serializedPreGame);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (PreGame) objectInputStream.readObject();
        }
    }


}
