package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameSession {
    private HashMap<Socket, Socket> gameSessions = new HashMap<>();
    private ArrayList<Socket> sockets = new ArrayList<>();
    private static GameSession instance;


    public static void main() {
        try {
            ServerSocket serverSocket = new ServerSocket(6000);
            while (true) {
                Socket socket = serverSocket.accept();
                instance.gameSessions.put(socket, null);
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

    private static void sendMessage() {
        while (true) {
            ArrayList<Socket> deletedSockets = new ArrayList<>();
            for (Socket socket : instance.sockets) {
                try {
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    String message = dataInputStream.readUTF();
                    if (instance.gameSessions.containsKey(socket)) {
                        DataOutputStream dataOutputStream = new DataOutputStream(instance.gameSessions.get(socket).getOutputStream());
                        dataOutputStream.writeUTF(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    deletedSockets.add(socket);
                }
            }
            for (Socket socket : deletedSockets) {
                instance.sockets.remove(socket);
                instance.gameSessions.remove(socket);
            }
        }
    }
}
