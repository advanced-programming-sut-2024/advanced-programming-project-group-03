package ir.ap.probending.Model.Data;

import java.util.ArrayList;

public class GameHistory {
    private String enemyUsername;
    private String Date;
    private String winner;
    private ArrayList<String> enemyScoresInRounds;
    private ArrayList<String> myScoresInRounds;
    private String EnemyFinalScore;
    private String MyFinalScore;

    public GameHistory(String enemyUsername, String date, String winner, String enemyFinalScore, String myFinalScore) {
        this.enemyUsername = enemyUsername;
        Date = date;
        this.winner = winner;
        this.enemyScoresInRounds = new ArrayList<>();
        this.myScoresInRounds = new ArrayList<>();
        EnemyFinalScore = enemyFinalScore;
        MyFinalScore = myFinalScore;
    }

    //getters and setters


    public String getEnemyUsername() {
        return enemyUsername;
    }

    public void setEnemyUsername(String enemyUsername) {
        this.enemyUsername = enemyUsername;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public ArrayList<String> getEnemyScoresInRounds() {
        return enemyScoresInRounds;
    }

    public void setEnemyScoresInRounds(ArrayList<String> enemyScoresInRounds) {
        this.enemyScoresInRounds = enemyScoresInRounds;
    }

    public ArrayList<String> getMyScoresInRounds() {
        return myScoresInRounds;
    }

    public void setMyScoresInRounds(ArrayList<String> myScoresInRounds) {
        this.myScoresInRounds = myScoresInRounds;
    }

    public String getEnemyFinalScore() {
        return EnemyFinalScore;
    }

    public void setEnemyFinalScore(String enemyFinalScore) {
        EnemyFinalScore = enemyFinalScore;
    }

    public String getMyFinalScore() {
        return MyFinalScore;
    }

    public void setMyFinalScore(String myFinalScore) {
        MyFinalScore = myFinalScore;
    }
}
