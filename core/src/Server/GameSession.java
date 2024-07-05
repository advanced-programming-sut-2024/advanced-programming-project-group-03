package Server;

import ir.ap.probending.Model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameSession {
    private ArrayList<Socket> sockets = new ArrayList<>();
    private static GameSession instance = new GameSession();




    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            while (true) {
                Socket socket = serverSocket.accept();
                instance.sockets.add(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameSession getInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }

    public void runGameSession() {
        new RunGameSession().start();
        new RunSendMessage().start();
    }

    class RunGameSession extends Thread {
        @Override
        public void run() {
            main(null);
        }
    }

    class RunSendMessage extends Thread {
        @Override
        public void run() {
            sendMessage();
        }
    }


    private static void sendMessage() {
        while (true) {
            ArrayList<Socket> deletedSockets = new ArrayList<>();
            socketFor:
            for (Socket socket : instance.sockets) {
                try {
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    String message = dataInputStream.readUTF();
                    if (message != null && !message.equals("")) {
                        for (GameInfo gameInfo : GameInfo.getGameInfos()) {
                            if (gameInfo.getFirstPlayerSocket().equals(socket) && !gameInfo.getFirstPlayer().getUsername().equals(message)) {
                                DataOutputStream dataOutputStream = new DataOutputStream(gameInfo.getSecondPlayerSocket().getOutputStream());
                                dataOutputStream.writeUTF(message);
                                continue socketFor;
                            } else if (gameInfo.getSecondPlayerSocket().equals(socket) && !gameInfo.getSecondPlayer().getUsername().equals(message)) {
                                DataOutputStream dataOutputStream = new DataOutputStream(gameInfo.getFirstPlayerSocket().getOutputStream());
                                dataOutputStream.writeUTF(message);
                                continue socketFor;
                            } else if (gameInfo.getFirstPlayer().getUsername().equals(message) && gameInfo.getFirstPlayerSocket() == null) {
                                gameInfo.setFirstPlayerSocket(socket);
                                continue socketFor;
                            } else if (gameInfo.getSecondPlayer().getUsername().equals(message) && gameInfo.getFirstPlayerSocket() == null) {
                                gameInfo.setSecondPlayerSocket(socket);
                                continue socketFor;
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

    public void serverStart(User firstUser, User secondUser) {
        new GameInfo(firstUser, secondUser, null, null);
    }
}
