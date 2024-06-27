package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Card;

import java.util.ArrayList;

public class GameBoard {
    private Board player1Board;
    private Player player1;
    private Board player2Board;
    private Player player2;
    private ArrayList<Card> spellCards = new ArrayList<>();

    public GameBoard(Player player1, Player player2 , Board player1Board, Board player2Board) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Board = player1Board;
        this.player2Board = player2Board;
    }

    public Board getPlayer1Board() {
        if (player1Board == null)
            player1Board = new Board();
        return player1Board;
    }

    public Board getPlayer2Board() {
        if (player2Board == null)
            player2Board = new Board();
        return player2Board;
    }

    public void addSpellCard(Card card) {
        spellCards.add(card);
    }

    public void removeSpellCard(Card card) {
        spellCards.remove(card);
    }

    //getters and setters

    public  Player getPlayer1() {
        return player1;
    }

    public  void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public  Player getPlayer2() {
        return player2;
    }

    public  void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setPlayer1Board(Board player1Board) {
        this.player1Board = player1Board;
    }

    public void setPlayer2Board(Board player2Board) {
        this.player2Board = player2Board;
    }

    public ArrayList<Card> getSpellCards() {
        return spellCards;
    }

    public void setSpellCards(ArrayList<Card> spellCards) {
        this.spellCards = spellCards;
    }
}
