package Server;

import ir.ap.probending.Model.User;

import java.net.Socket;
import java.util.ArrayList;

public class GameInfo {
    private static ArrayList<GameInfo> gameInfos = new ArrayList<>();
    private User firstPlayer = null;
    private User secondPlayer = null;
    private Socket firstPlayerSocket = null;
    private Socket secondPlayerSocket = null;

    public GameInfo(User firstPlayer, User secondPlayer, Socket firstPlayerSocket, Socket secondPlayerSocket) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.firstPlayerSocket = firstPlayerSocket;
        this.secondPlayerSocket = secondPlayerSocket;
        gameInfos.add(this);
    }

    public User getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(User firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public User getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(User secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Socket getFirstPlayerSocket() {
        return firstPlayerSocket;
    }

    public void setFirstPlayerSocket(Socket firstPlayerSocket) {
        this.firstPlayerSocket = firstPlayerSocket;
    }

    public Socket getSecondPlayerSocket() {
        return secondPlayerSocket;
    }

    public void setSecondPlayerSocket(Socket secondPlayerSocket) {
        this.secondPlayerSocket = secondPlayerSocket;
    }

    public static ArrayList<GameInfo> getGameInfos() {
        return gameInfos;
    }
}
