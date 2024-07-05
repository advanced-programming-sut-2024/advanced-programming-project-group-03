package Server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ir.ap.probending.Model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Server extends Thread {
    private static final String USERS_FILE = "users.json";
    private static List<User> users = new ArrayList<>();
    public Socket socket;
    private User currentUser = null;

    public Server(Socket socket) throws IOException {
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
                    case "signup":
                        if (messageParts.length != 5)
                            response = "Invalid input";
                        else
                            response = signUp(messageParts[1], messageParts[2], messageParts[3], messageParts[4]);
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                    case "login":
                        if (messageParts.length != 3)
                            response = "Invalid input";
                        else
                            response = login(messageParts[1], messageParts[2]);
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                    case "sendLoginEmail":
                        response = sendLoginEmail();
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                    case "sendSignupEmail":
                        response = sendSignupEmail();
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                    case "confirm":
                        response = receiveToken(messageParts[1]);
                        System.out.println(response);
                        break;
                    case "getUser":
                        String userJson;
                        if (currentUser != null) {
                            User user = getUser();
                            Gson gson = new Gson();
                            System.out.println(response);
                            userJson = gson.toJson(user);
                        } else {
                            userJson = "User not found";
                        }
                        dataOutputStream.writeUTF(userJson);
                        dataOutputStream.flush();
                        break;
                    case "saveUser":
                        String userString = message.substring(9);
                        Gson gson1 = new Gson();
                        User user1 = gson1.fromJson(userString, User.class);
                        saveUser(user1);
                        dataOutputStream.writeUTF("");
                        dataOutputStream.flush();
                        break;
                    case "searchUser":
                        String username = messageParts[1];
                        User requestedUser = getUserByUsername(username);
                        if (requestedUser == null) {
                            dataOutputStream.writeUTF("User not found");
                            dataOutputStream.flush();
                            break;
                        }
                        Gson gson2 = new Gson();
                        String userJson1 = gson2.toJson(requestedUser);
                        dataOutputStream.writeUTF(userJson1);
                        dataOutputStream.flush();
                        break;
                    case "addFriend":
                        String senderUsername = messageParts[1];
                        String receiverUsername = messageParts[2];
                        User sender = getUserByUsername(senderUsername);
                        User receiver = getUserByUsername(receiverUsername);
                        FriendRequest friendRequest = new FriendRequest(sender.getUsername(),receiver.getUsername());
                        sender.addSentFriendRequest(friendRequest);
                        receiver.addReceivedFriendRequest(friendRequest);
                        dataOutputStream.writeUTF("Friend request sent");
                        dataOutputStream.flush();
                        break;
                    case "acceptFriend":
                        senderUsername = messageParts[1];
                        receiverUsername = messageParts[2];
                        int friendRequestId = Integer.parseInt(messageParts[3]);
                        sender = getUserByUsername(senderUsername);
                        receiver = getUserByUsername(receiverUsername);
                        friendRequest = receiver.getFriendRequestById(friendRequestId);
                        friendRequest.setState("accepted");
                        //check if they are already friends
                        if (!sender.getFriends().containsKey(receiverUsername)) {
                            sender.addFriend(receiverUsername);
                            receiver.addFriend(senderUsername);
                        }
                        dataOutputStream.writeUTF("Friend request accepted");
                        dataOutputStream.flush();
                        break;
                    case "rejectFriend":
                        senderUsername = messageParts[1];
                        receiverUsername = messageParts[2];
                        friendRequestId = Integer.parseInt(messageParts[3]);
                        sender = getUserByUsername(senderUsername);
                        receiver = getUserByUsername(receiverUsername);
                        friendRequest = receiver.getFriendRequestById(friendRequestId);
                        friendRequest.setState("rejected");
                        dataOutputStream.writeUTF("Friend request rejected");
                        dataOutputStream.flush();
                        break;
                    case "changeUsername":
                        response = changeUsername(messageParts[1]);
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                    case "changeEmail":
                        response = changeEmail(messageParts[1]);
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                    case "changeNickname":
                        response = changeNickname(messageParts[1]);
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                    case "changePassword":
                        response = changePassword(messageParts[1]);
                        System.out.println(response);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                    case "sendGameRequest":
                        if (messageParts.length == 1)
                            response = "Username is empty";
                        else
                            response = sendGameRequest(messageParts[1]);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                    case "acceptGameRequest":
                        if (messageParts.length == 1)
                            response = "Username is empty";
                        else
                            response = acceptGameRequest(messageParts[1]);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                    case "rejectGameRequest":
                        if (messageParts.length == 1)
                            response = "Username is empty";
                        else
                            response = rejectGameRequest(messageParts[1]);
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (currentUser != null) {
                currentUser.setOnline(false);
                currentUser.setPlaying(false);
            }
            saveUsersToFile();
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
            if (currentUser != null && currentUser != user)
                currentUser.setOnline(false);
            currentUser = user;
            saveUsersToFile();
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

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private String changeUsername(String newUsername) {
        if (users.stream().anyMatch(user -> user.getUsername().equals(newUsername))) {
            return "Username already exists";
        } else {
            String oldUsername = currentUser.getUsername();
            currentUser.setUsername(newUsername);
            for (User user : users) {
                for (FriendRequest friendRequest : user.getSentFriendRequests()) {
                    if (friendRequest.getReceiver().equals(oldUsername)) {
                        friendRequest.setReceiver(newUsername);
                    }
                }
                if (user.getFriends().containsKey(oldUsername)) {
                    boolean hasSendGameInvitation = user.getFriends().get(oldUsername);
                    user.getFriends().remove(oldUsername);
                    user.getFriends().put(newUsername, hasSendGameInvitation);
                }

            }
            saveUsersToFile();
            return "Username changed successfully";
        }
    }

    private String changeEmail(String newEmail) {
        if (users.stream().anyMatch(user -> user.getEmail().equals(newEmail))) {
            return "Email already exists";
        } else {
            currentUser.setEmail(newEmail);
            saveUsersToFile();
            return "Email changed successfully";
        }
    }

    private String sendGameRequest(String receiverUsername) {
        User receiver = getUserByUsername(receiverUsername);
        if (receiver == null) {
            return "User not found";
        } else if (receiver.isPlaying()) {
            return "User is already in a game";
        } else if (!receiver.isOnline()) {
            return "User is offline";
        } else if (!receiver.getFriends().containsKey(currentUser.getUsername())) {
            return "User is not your friend";
        } else {
            receiver.getFriends().put(currentUser.getUsername(), true);
            return "Game request sent";
        }
    }

    private String acceptGameRequest(String senderUsername) {
        User sender = getUserByUsername(senderUsername);
        if (sender == null) {
            return "User not found";
        } else if (!sender.getFriends().containsKey(currentUser.getUsername())) {
            return "User is not your friend";
        } else if (!currentUser.getFriends().get(senderUsername)) {
            return "User has not sent you a game request";
        } else if (sender.isPlaying()) {
            return "User is already in a game";
        } else if (!sender.isOnline()) {
            return "User is offline";
        } else {
            // Start the game session
            GameSession.getInstance().serverStart(sender, currentUser);
            sender.setPlaying(true);
            currentUser.setPlaying(true);
            return "Game request accepted";
        }
    }

    private String rejectGameRequest(String senderUsername) {
        User sender = getUserByUsername(senderUsername);
        if (sender == null) {
            return "User not found";
        } else if (!sender.getFriends().containsKey(currentUser.getUsername())) {
            return "User is not your friend";
        } else if (!currentUser.getFriends().get(senderUsername)) {
            return "User has not sent you a game request";
        } else {
            currentUser.getFriends().put(senderUsername, false);
            return "Game request rejected";
        }
    }

    private static Object[] getAliveServers() {
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parentGroup;
        while ((parentGroup = rootGroup.getParent()) != null) {
            rootGroup = parentGroup;
        }

        int noOfThreads = rootGroup.activeCount();
        Thread[] threads = new Thread[noOfThreads + 10]; // add a small buffer
        int count = rootGroup.enumerate(threads);
        return Arrays.stream(threads, 0, count)
                .filter(Thread::isAlive)
                .filter(thread -> thread instanceof Server)
                .map(thread -> (Server) thread)
                .toArray();
    }

    private String changeNickname(String newNickname) {
        if (users.stream().anyMatch(user -> user.getNickname().equals(newNickname))) {
            return "Nickname already exists";
        } else {
            currentUser.setNickname(newNickname);
            saveUsersToFile();
            return "Nickname changed successfully";
        }
    }

    private String changePassword(String newPassword) {
        currentUser.setPassword(newPassword);
        saveUsersToFile();
        return "Password changed successfully";
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
