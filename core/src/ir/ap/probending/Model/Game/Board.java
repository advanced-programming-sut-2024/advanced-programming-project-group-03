package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Card;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Card commanderCloseCombat;
    private Card commanderRanged;
    private Card commanderSiege;
    private ArrayList<Card> closeCombat = new ArrayList<>();
    private ArrayList<Card> ranged = new ArrayList<>();
    private ArrayList<Card> siege = new ArrayList<>();

    public void addCardToCloseCombat(Card card) {
        closeCombat.add(card);
    }

    public void addCardToRanged(Card card) {
        ranged.add(card);
    }

    public void addCardToSiege(Card card) {
        siege.add(card);
    }

    public void removeCardFromCloseCombat(Card card) {
        closeCombat.remove(card);
    }

    public void removeCardFromRanged(Card card) {
        ranged.remove(card);
    }

    public void removeCardFromSiege(Card card) {
        siege.remove(card);
    }

    public void addCommanderToCloseCombat(Card card) {
        commanderCloseCombat = card;
    }

    public void addCommanderToRanged(Card card) {
        commanderRanged = card;
    }

    public void addCommanderToSiege(Card card) {
        commanderSiege = card;
    }

    public void removeCommanderFromCloseCombat() {
        commanderCloseCombat = null;
    }

    public void removeCommanderFromRanged() {
        commanderRanged = null;
    }

    public void removeCommanderFromSiege() {
        commanderSiege = null;
    }

    public int getCloseCombatPowerSum() {
        int sum = 0;
        for (Card card : closeCombat) {
            sum += card.getPower();
        }
        return sum;
    }

    public int getRangedPowerSum() {
        int sum = 0;
        for (Card card : ranged) {
            sum += card.getPower();
        }
        return sum;
    }

    public int getSiegePowerSum() {
        int sum = 0;
        for (Card card : siege) {
            sum += card.getPower();
        }
        return sum;
    }

    public int getTotalPower() {
        return getCloseCombatPowerSum() + getRangedPowerSum() + getSiegePowerSum();
    }

    public void clearBoard() {
        closeCombat.clear();
        ranged.clear();
        siege.clear();
        commanderCloseCombat = null;
        commanderRanged = null;
        commanderSiege = null;
    }

    //getters and setters
    public Card getCommanderCloseCombat() {
        return commanderCloseCombat;
    }

    public void setCommanderCloseCombat(Card commanderCloseCombat) {
        this.commanderCloseCombat = commanderCloseCombat;
    }

    public Card getCommanderRanged() {
        return commanderRanged;
    }

    public void setCommanderRanged(Card commanderRanged) {
        this.commanderRanged = commanderRanged;
    }

    public Card getCommanderSiege() {
        return commanderSiege;
    }

    public void setCommanderSiege(Card commanderSiege) {
        this.commanderSiege = commanderSiege;
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
}
