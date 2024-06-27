package Server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ir.ap.probending.Model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    private static final String USERS_FILE = "users.json";
    private static List<User> users = new ArrayList<>();
    private Socket socket;
    private User currentUser = null;

    public Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String message = dataInputStream.readUTF().trim();
                if (message.equals("exit")) {
                    break;
                }
                String[] messageParts = message.split(" ");
                String command = messageParts[0];
                String response = "";
                switch (command) {
                    case "signup":
                        response = signUp(messageParts[1], messageParts[2], messageParts[3], messageParts[4]);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized String signUp(String username, String password, String email, String nickname) {
        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            return "Username already exists";
        } else if (users.stream().anyMatch(user -> user.getEmail().equals(email))) {
            return "Email already exists";
        } else if (users.stream().anyMatch(user -> user.getNickname().equals(nickname))) {
            return "Nickname already exists";
        } else {
            User user = new User(username, password, email, nickname);
            users.add(user);
            saveUsersToFile();
            return "Signed up successfully";
        }
    }

    private static void loadUsersFromFile() {
        try (Reader reader = new FileReader(USERS_FILE)) {
            Gson gson = new Gson();
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            users = gson.fromJson(reader, userListType);
        } catch (FileNotFoundException e) {
            System.out.println("Users file not found. Starting with an empty user list.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveUsersToFile() {
        try (Writer writer = new FileWriter(USERS_FILE)) {
            Gson gson = new Gson();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadUsersFromFile();

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true) {
                Socket socket = serverSocket.accept();
                Server server = new Server(socket);
                server.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
