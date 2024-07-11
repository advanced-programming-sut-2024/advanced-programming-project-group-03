package Server;

import com.google.gson.Gson;
import ir.ap.probending.Model.Game.Game;
import ir.ap.probending.Model.Game.GameBoard;
import ir.ap.probending.Model.Game.PreGame;
import ir.ap.probending.Model.Message;
import ir.ap.probending.Model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameSession extends Thread {
    private static final List<ClientHandler> clients = new ArrayList<>();
    private static GameSession instance = new GameSession();
    private static DataOutputStream log;
    private static DataInputStream logIn;
    private static String playedCard = null;
    private static int playedCardRow = -1;
    private static boolean medic = false;
    private static String medicCard = null;
    private static int medicRow = -1;
    private static boolean hasPassed = false;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            instance.start();
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                synchronized (clients) {
                    clients.add(clientHandler);
                }
                clientHandler.start();
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
            } else if (message.startsWith("setGame ")) {
                handleSetGameMessage(message, gameInfo);
                System.out.println("setGameBoard message");
            } else if (message.startsWith("startGame")) {
                serverStart(message.split(" ")[1], message.split(" ")[2]);
            } else if (message.startsWith("playCard")) {
                handlePlayCardMessage(message, gameInfo);
            } else if (message.startsWith("playCardMedic")) {
                handlePlayCardMedicMessage(message, gameInfo);
            } else if (message.startsWith("isOpponentPlayedCard")) {
                handleIsOpponentPlayedCard(message);
            } else if (message.startsWith("sendMessage ")) {
                Message message1 = new Gson().fromJson(message.substring(11), Message.class);
                addMessage(message1, socket);
            } else if (message.equals("getMessages")) {
                dataOutputStream.writeUTF(new Gson().toJson(gameInfo.getMessages()));
                dataOutputStream.flush();
            } else if (message.equals("getGames")) {
                handleGetGamesMessage();
                System.out.println("getGames message");
            } else if (message.startsWith("pass")) {
                hasPassed = true;
            } else {
                assignSocketToGameInfo(socket, message);
                System.out.println("socket assigned to gameInfo");
            }
        }

        synchronized private void handlePlayCardMedicMessage(String message, GameInfo gameInfo) throws IOException {
            System.out.println(message);
            System.out.println("----------------------------------");
            String cardname = message.split(" ")[1];
            medic = true;
            Pattern pattern = Pattern.compile("playCardMedic (?<cardname>.*) (?<cardrow>\\d+) (?<medicCard>.*) (?<medicRow>\\d+)");
            Matcher matcher = pattern.matcher(message);
            matcher.matches();
            cardname = matcher.group("cardname");
            medicCard = matcher.group("medicCard");
            playedCard = cardname;
            playedCardRow = Integer.parseInt(matcher.group("cardrow"));
            medicCard = matcher.group("medicCard");
            medicRow = Integer.parseInt(matcher.group("medicRow"));
            medic=true;
        }

        synchronized private void handleGetGamesMessage() throws IOException {
            ArrayList<GameInfo> gameInfos = GameInfo.getGameInfos();
            if (gameInfos.isEmpty()) {
                dataOutputStream.writeUTF("no games");
                dataOutputStream.flush();
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
                    objectOutputStream.writeObject(gameInfos);
                }
                dataOutputStream.writeUTF(Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()));
                dataOutputStream.flush();
            }
        }

        synchronized private void handleSetGameMessage(String message, GameInfo gameInfo) throws IOException {
            String serializedGameBoard = message.substring(8);
            Game game = deserializeGame(serializedGameBoard);
            gameInfo.setGame(game);
        }

        private Game deserializeGame(String serializedGameBoard) {
            byte[] data = Base64.getDecoder().decode(serializedGameBoard);
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
                return (Game) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        synchronized private void handleIsOpponentPlayedCard(String message) throws IOException {
            System.out.println("kir ---------------------------kir kir ----");
            if(medic==true){
                forwardMessageToOpponent(socket, "playCardMedic " + playedCard + " " + playedCardRow + " " + medicCard + " " + medicRow, GameInfo.getGameInfo(socket));
                System.out.println("playCardMedic " + playedCard + " " + playedCardRow + " " + medicCard + " " + medicRow);
                playedCard = null;
                medic = false;
            }
            else if (playedCard != null) {
                forwardMessageToOpponent(socket, "playCard " + playedCard + " " + playedCardRow, GameInfo.getGameInfo(socket));
                System.out.println("playCard " + playedCard + " " + playedCardRow);
                playedCard = null;
            } else if (hasPassed) {
                forwardMessageToOpponent(socket, "pass", GameInfo.getGameInfo(socket));
                hasPassed = false;
            } else {
                forwardMessageToOpponent(socket, "no", GameInfo.getGameInfo(socket));
            }
            dataOutputStream.flush();
        }

        synchronized private void handlePlayCardMessage(String message, GameInfo gameInfo) throws IOException {
            System.out.println(message);
            System.out.println("----------------------------------");
            String cardname = message.split(" ")[1];
            for (int i = 2; i < message.split(" ").length - 1; i++) {
                cardname += " " + message.split(" ")[i];
            }
            System.out.println(cardname);
            playedCard = cardname;
            playedCardRow = Integer.parseInt(message.split(" ")[message.split(" ").length - 1]);
        }

        synchronized private void handlePreGameMessage(Socket socket, String message, GameInfo gameInfo) throws
                IOException {
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

        synchronized private void forwardMessageToOpponent(Socket socket, String message, GameInfo gameInfo) throws
                IOException {
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

    public static void addMessage(Message message, Socket socket) {
        GameInfo gameInfo = GameInfo.getGameInfo(socket);
        gameInfo.addMessage(message);
    }
}
