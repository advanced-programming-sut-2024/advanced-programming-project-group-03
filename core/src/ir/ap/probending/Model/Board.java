package ir.ap.probending.Model;

import ir.ap.probending.Model.Card.Card;

import java.util.ArrayList;

public class Board {
    private Card commander;
    private ArrayList<Card> closeCombat = new ArrayList<>();
    private ArrayList<Card> ranged = new ArrayList<>();
    private ArrayList<Card> siege = new ArrayList<>();
    private Card special;
    private ArrayList<Card> burntCards = new ArrayList<>();

    public Card getCommander() {
        return commander;
    }

    public void setCommander(Card commander) {
        this.commander = commander;
    }

    public ArrayList<Card> getCloseCombat() {
        return closeCombat;
    }

    public void setCloseCombat(ArrayList<Card> closeCombat) {
        this.closeCombat = closeCombat;
    }

    public ArrayList<Card> getRanged() {
        return ranged;
    }

    public void setRanged(ArrayList<Card> ranged) {
        this.ranged = ranged;
    }

    public ArrayList<Card> getSiege() {
        return siege;
    }

    public void setSiege(ArrayList<Card> siege) {
        this.siege = siege;
    }

    public Card getSpecial() {
        return special;
    }

    public void setSpecial(Card special) {
        this.special = special;
    }

    public ArrayList<Card> getBurntCards() {
        return burntCards;
    }

    public void setBurntCards(ArrayList<Card> burntCards) {
        this.burntCards = burntCards;
    }
}
