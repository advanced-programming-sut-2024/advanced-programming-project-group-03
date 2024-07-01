package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Frost;
import ir.ap.probending.Model.Card.Abilities.Morale;
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
    private int moraleBoostCloseCombat = 0;
    private int moraleBoostRanged = 0;
    private int moraleBoostSiege = 0;

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
        if (Game.getGame().getGameBoard().isFrostPlayed()){
            for (Card card : closeCombat) {
                if (!card.isHero()){
                    sum += 1;
                }
                else {
                    sum += card.getPower();
                }
            }
        }
        else {
            for (Card card : closeCombat) {
                sum += card.getPower();
            }
        }
        sum += (closeCombat.size() - moraleBoostCloseCombat - getCloseCombatHeroCount() ) * moraleBoostCloseCombat;
        return sum ;
    }

    public int getRangedPowerSum() {
        int sum = 0;
        if (Game.getGame().getGameBoard().isFogPlayed() || Game.getGame().getGameBoard().isStormPlayed()){
            for (Card card : ranged) {
                if (!card.isHero()){
                    sum += 1;
                }
                else {
                    sum += card.getPower();
                }
            }
        }
        else{
            for (Card card : ranged) {
                sum += card.getPower();
            }
        }
        sum += (ranged.size() - moraleBoostRanged - getRangedHeroCount() ) * moraleBoostRanged;
        return sum ;
    }

    public int getSiegePowerSum() {
        int sum = 0;
        if (Game.getGame().getGameBoard().isRainPlayed() || Game.getGame().getGameBoard().isStormPlayed()){
            for (Card card : siege) {
                if (!card.isHero()){
                    sum += 1;
                }
                else {
                    sum += card.getPower();
                }
            }
        }
        else {
            for (Card card : siege) {
                sum += card.getPower();
            }
        }
        sum += (siege.size() - moraleBoostSiege - getSiegeHeroCount() ) * moraleBoostSiege;
        return sum ;
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

    private int getCloseCombatHeroCount(){
        int count = 0;
        for (Card card : closeCombat) {
            if (card.isHero() && !(card.getAbility() instanceof Morale))
                count++;
        }
        return count;
    }

    private int getRangedHeroCount(){
        int count = 0;
        for (Card card : ranged) {
            if (card.isHero() && !(card.getAbility() instanceof Morale))
                count++;
        }
        return count;
    }

    private int getSiegeHeroCount(){
        int count = 0;
        for (Card card : siege) {
            if (card.isHero() && !(card.getAbility() instanceof Morale))
                count++;
        }
        return count;
    }

    public ArrayList<Card> getAllCards(){
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.addAll(closeCombat);
        allCards.addAll(ranged);
        allCards.addAll(siege);
        return allCards;
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

    public int getMoraleBoostCloseCombat() {
        return moraleBoostCloseCombat;
    }

    public void setMoraleBoostCloseCombat(int moraleBoostCloseCombat) {
        this.moraleBoostCloseCombat = moraleBoostCloseCombat;
    }

    public int getMoraleBoostRanged() {
        return moraleBoostRanged;
    }

    public void setMoraleBoostRanged(int moraleBoostRanged) {
        this.moraleBoostRanged = moraleBoostRanged;
    }

    public int getMoraleBoostSiege() {
        return moraleBoostSiege;
    }

    public void setMoraleBoostSiege(int moraleBoostSiege) {
        this.moraleBoostSiege = moraleBoostSiege;
    }
}
