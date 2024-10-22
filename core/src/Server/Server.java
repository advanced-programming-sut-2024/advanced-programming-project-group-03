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
import java.util.Random;

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
                System.out.println(message);
                switch (command) {
                    case "signup":{
                        if (messageParts.length != 5)
                            response = "Invalid input";
                        else
                            response = signUp(messageParts[1], messageParts[2], messageParts[3], messageParts[4]);
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;}
                    case "login":{
                        if (messageParts.length != 3)
                            response = "Invalid input";
                        else
                            response = login(messageParts[1], messageParts[2]);
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;}
                    case "sendLoginEmail":{
                        response = sendLoginEmail();
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;}
                    case "sendSignupEmail":{
                        response = sendSignupEmail();
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;}
                    case "confirm":{
                        response = receiveToken(messageParts[1]);
                        System.out.println(response);
                        break;}
                    case "getUser":{
                        User user = getUser();
                        Gson gson = new Gson();
                        System.out.println(response);
                        String userJson = gson.toJson(user);
                        dataOutputStream.writeUTF(userJson);
                        dataOutputStream.flush();
                        break;}
                    case "saveUser":{
                        String userString = message.substring(9);
                        Gson gson1 = new Gson();
                        User user1 = gson1.fromJson(userString, User.class);
                        saveUser(user1);
                        dataOutputStream.writeUTF("");
                        dataOutputStream.flush();
                        break;}
                    case "searchUser":{
                        String username = messageParts[1];
                        User requestedUser = getUserByUsername(username);
                        if(requestedUser == null){
                            dataOutputStream.writeUTF("User not found");
                            dataOutputStream.flush();
                            break;
                        }
                        Gson gson2 = new Gson();
                        String userJson1 = gson2.toJson(requestedUser);
                        dataOutputStream.writeUTF(userJson1);
                        dataOutputStream.flush();
                        break;}
                    case "addFriend": {
                        String senderUsername = messageParts[1];
                        String receiverUsername = messageParts[2];
                        User sender = getUserByUsername(senderUsername);
                        User receiver = getUserByUsername(receiverUsername);
                        FriendRequest friendRequest = new FriendRequest(sender, receiver);
                        dataOutputStream.writeUTF("Friend request sent");
                        dataOutputStream.flush();
                        break;
                    }
                    case "acceptFriend": {
                        String senderUsername = messageParts[1];
                        String receiverUsername = messageParts[2];
                        int friendRequestId = Integer.parseInt(messageParts[3]);
                        User sender = getUserByUsername(senderUsername);
                        User receiver = getUserByUsername(receiverUsername);
                        FriendRequest friendRequest = receiver.getFriendRequestById(friendRequestId);
                        friendRequest.setState("accepted");
                        receiver.addFriend(sender);
                        sender.addFriend(receiver);
                        dataOutputStream.writeUTF("Friend request accepted");
                        dataOutputStream.flush();
                        break;
                    }
                    case "rejectFriend": {
                        String senderUsername = messageParts[1];
                        String receiverUsername = messageParts[2];
                        int friendRequestId = Integer.parseInt(messageParts[3]);
                        User sender = getUserByUsername(senderUsername);
                        User receiver = getUserByUsername(receiverUsername);
                        FriendRequest friendRequest = receiver.getFriendRequestById(friendRequestId);
                        friendRequest.setState("rejected");
                        dataOutputStream.writeUTF("Friend request rejected");
                        dataOutputStream.flush();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized String signUp(String username, String password, String email, String nickname) {
        if (users == null) {
            User user = new User(username, password, email, nickname);
            users.add(user);
            saveUsersToFile();

            return "Signed up successfully.";
        }

        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            return "Username already exists";
        } else if (users.stream().anyMatch(user -> user.getEmail().equals(email))) {
            return "Email already exists";
        } else if (users.stream().anyMatch(user -> user.getNickname().equals(nickname))) {
            return "Nickname already exists";
        } else {
            User user = new User(username, password, email, nickname);
            users.add(user);
            currentUser = user;
            saveUsersToFile();

            return "Signed up successfully.";
        }
    }

    private synchronized String login(String username, String password) {
        if (users == null) {
            return "User not found";
        }

        User user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
        if (user == null) {
            return "User not found";
        } else if (!user.getPassword().equals(password)) {
            return "Incorrect password";
        } else if (user.getEmail().equals("probendingavatar@gmail.com") && !user.isRegisterConfirmed()) {
            return "Email not confirmed";
        } else {
            currentUser = user;
            return "Sending email confirmation code";
        }
    }

    private User getUser() {
        return currentUser;
    }

    private void saveUser(User user) {
        if (user.getUsername().equals(currentUser.getUsername())) {
            currentUser = user;
        }
        users.removeIf(u -> u.getUsername().equals(user.getUsername()));
        users.add(user);
        saveUsersToFile();
    }

    private String sendLoginEmail() {
        if (currentUser == null) {
            return "Email not found";
        }
        if (currentUser.getEmail().equals("probendingavatar@gmail.com")) {
            Random random = new Random();
            int confirmationCode = 100000 + random.nextInt(900000);
            String confirmationCodeStr = String.valueOf(confirmationCode);
            currentUser.setLoginNumber(confirmationCodeStr);
            MailSender mailSender = new MailSender();
            try {
                mailSender.sendEmail(currentUser.getEmail(), "Pro Bending Login Confirmation", "Your confirmation code is: " + confirmationCodeStr);
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed to send email";
            }
            return confirmationCodeStr;
        }
        return "";
    }

    private String sendSignupEmail() {
        if (currentUser == null) {
            return "User not found";
        }
        if (currentUser.getEmail().equals("probendingavatar@gmail.com")) {
            // Construct the confirmation link with the token
            String confirmationLink = "http://localhost:8080/confirm?token=" + currentUser.getUsername();

            // Send the confirmation email
            MailSender mailSender = new MailSender();
            try {
                mailSender.sendEmail(currentUser.getEmail(), "Sign Up Confirmation", "Please click the link below to confirm your email:\n" + confirmationLink);
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed to send email";
            }
        }

        return "Confirmation email sent successfully";
    }

    public static String receiveToken(String token) {
        String response = "Received token: " + token;
        users.stream().filter(user -> user.getUsername().equals(token)).findFirst().ifPresent(user -> user.setRegisterConfirmed(true));
        return response;
    }
    public static User getUserByUsername(String username){
        for(User user:users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    private static void loadUsersFromFile() {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (Reader reader = new FileReader(USERS_FILE)) {
            Gson gson = new Gson();
            Type userListType = new TypeToken<ArrayList<User>>() {
            }.getType();
            users = gson.fromJson(reader, userListType);
            if (users == null) {
                users = new ArrayList<>();
            }
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
