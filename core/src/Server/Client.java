package Server;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private Socket gamesocket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private DataInputStream gameDataInputStream;
    private DataOutputStream gameDataOutputStream;

    public String communicate(String message) {
        this.sendMessage(message);
        if (message.equals("exit")) {
            this.closeConnection();
            return null;
        }
        return this.receiveMessage();
    }

    public boolean establishConnection(String address, int port) {
        try {
            socket = new Socket(address, port);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            gamesocket = new Socket(address, 5050);
            gameDataInputStream = new DataInputStream(gamesocket.getInputStream());
            gameDataOutputStream = new DataOutputStream(gamesocket.getOutputStream());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void sendMessage(String message) {
        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGameMessage(String message) {
        try {
            gameDataOutputStream.writeUTF(message);
            gameDataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String receiveMessage() {
        try {
            return dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String receiveGameMessage() {
        try {
            return gameDataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void closeConnection() {
        try {
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
