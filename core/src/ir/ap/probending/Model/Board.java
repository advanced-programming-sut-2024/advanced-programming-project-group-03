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

    public Card getCard(int i, int j) {
        if (i == 0) {
            return closeCombat.get(j);
        } else if (i == 1) {
            return ranged.get(j);
        } else if (i == 2) {
            return siege.get(j);
        } else {
            return null;
        }
    }

    public ArrayList<Card> getRow(int i) {
        if (i == 0) {
            return closeCombat;
        } else if (i == 1) {
            return ranged;
        } else if (i == 2) {
            return siege;
        } else {
            return null;
        }
    }

    public Integer[] findCard(Card card) {
        Integer[] result = new Integer[2];
        for (int i = 0; i < 3; i++) {
            if (closeCombat.contains(card)) {
                result[0] = 0;
                result[1] = closeCombat.indexOf(card);
                return result;
            } else if (ranged.contains(card)) {
                result[0] = 1;
                result[1] = ranged.indexOf(card);
                return result;
            } else if (siege.contains(card)) {
                result[0] = 2;
                result[1] = siege.indexOf(card);
                return result;
            }
        }
        return null;
    }

    public boolean hasCard (Card card) {
        return closeCombat.contains(card) || ranged.contains(card) || siege.contains(card);
    }

    public void addCard(Card card) {
        if (card.getPlayingRow() == 0) {
            siege.add(card);
        } else if (card.getPlayingRow() == 1) {
            ranged.add(card);
        } else if (card.getPlayingRow() == 2) {
            closeCombat.add(card);
        }
    }

    public void removeCard(Card card) {
        if (card.getPlayingRow() == 0) {
            siege.remove(card);
        } else if (card.getPlayingRow() == 1) {
            ranged.remove(card);
        } else if (card.getPlayingRow() == 2) {
            closeCombat.remove(card);
        }
    }
}
