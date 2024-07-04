package ir.ap.probending.Model;

import Server.FriendRequest;
import ir.ap.probending.Model.Data.GameHistory;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password;
    private String email;
    private String loginNumber = "";
    private boolean registerConfirmed = false;
    private int questionIndex;
    private String questionAnswer;
    private ArrayList<GameHistory> gameHistories;
    private int gamePlayedCount;
    private int gameWonCount;
    private int gameLostCount;
    private String nickname;
    private int score;
    private int rank;
    private ArrayList<FriendRequest> sentFriendRequests;
    private HashMap<String, Boolean> friends;
    private ArrayList<FriendRequest> receivedFriendRequests;
    private boolean isPlaying = false;
    private boolean isOnline = false;
    private boolean hasLoggedIn = false;
    public User(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.questionIndex = 0;
        this.questionAnswer = "";
        gameHistories = new ArrayList<>();
        gamePlayedCount = 0;
        gameWonCount = 0;
        gameLostCount = 0;
        score = 0;
        rank = 0;
        sentFriendRequests = new ArrayList<>();
    }

    public User() {
        gamePlayedCount = 0;
        gameWonCount = 0;
        gameLostCount = 0;
        score = 0;
        rank = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }


    public ArrayList<GameHistory> getGameHistories() {
        return gameHistories;
    }

    public void setGameHistories(ArrayList<GameHistory> gameHistories) {
        this.gameHistories = gameHistories;
    }

    public void addGameHistory(GameHistory gameHistory) {
        gameHistories.add(gameHistory);
    }

    public void addQuestionAnswer(int question, String answer) {
        questionAnswer = answer;
        questionIndex = question;
    }

    public void removeGameHistory(GameHistory gameHistory) {
        gameHistories.remove(gameHistory);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGamePlayedCount() {
        return gamePlayedCount;
    }

    public void setGamePlayedCount(int gamePlayedCount) {
        this.gamePlayedCount = gamePlayedCount;
    }

    public int getGameWonCount() {
        return gameWonCount;
    }

    public void setGameWonCount(int gameWonCount) {
        this.gameWonCount = gameWonCount;
    }

    public int getGameLostCount() {
        return gameLostCount;
    }

    public void setGameLostCount(int gameLostCount) {
        this.gameLostCount = gameLostCount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(String loginNumber) {
        this.loginNumber = loginNumber;
    }

    public boolean isRegisterConfirmed() {
        return registerConfirmed;
    }

    public void setRegisterConfirmed(boolean registerConfirmed) {
        this.registerConfirmed = registerConfirmed;
    }
    public void addFriendRequest(FriendRequest friendRequest) {
        sentFriendRequests.add(friendRequest);
    }
    public FriendRequest getFriendRequestById(int id) {
        for (FriendRequest friendRequest : sentFriendRequests) {
            if (friendRequest.getId() == id) {
                return friendRequest;
            }
        }
        return null;
    }
    public ArrayList<FriendRequest> getSentFriendRequests() {
        return sentFriendRequests;
    }
    public void addFriend(String friend) {
        friends.put(friend, false);
    }

    public HashMap<String, Boolean> getFriends() {
        return friends;
    }

    public boolean getHasLoggedIn() {
        return hasLoggedIn;
    }

    public void setHasLoggedIn(boolean hasLoggedIn) {
        this.hasLoggedIn = hasLoggedIn;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

}
