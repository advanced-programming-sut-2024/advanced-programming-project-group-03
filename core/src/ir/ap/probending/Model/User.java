package ir.ap.probending.Model;

import ir.ap.probending.Model.Data.GameHistory;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String email;
    private HashMap<Integer, String> questionAnswer;
    private ArrayList<GameHistory> gameHistories;
    private int gamePlayedCount;
    private int gameWonCount;
    private int gameLostCount;
    private String nickname;
    private int score;
    private int rank;

    public User(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        questionAnswer = new HashMap<>();
        gameHistories = new ArrayList<>();
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

    public HashMap<Integer, String> getQuestionAnswer() {
        return questionAnswer;
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
        questionAnswer.put(question, answer);
    }

    public void removeQuestionAnswer(int question) {
        questionAnswer.remove(question);
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

    public void setQuestionAnswer(HashMap<Integer, String> questionAnswer) {
        this.questionAnswer = questionAnswer;
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
}
