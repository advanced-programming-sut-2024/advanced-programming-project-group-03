package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Frost;
import ir.ap.probending.Model.Card.Abilities.Morale;
import ir.ap.probending.Model.Card.Card;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private ArrayList<Card> closeCombat = new ArrayList<>();
    private ArrayList<Card> ranged = new ArrayList<>();
    private ArrayList<Card> siege = new ArrayList<>();
    private Card commander7;
    private Card commander8;
    private Card commander9;
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

        if (doesCloseCombatHaveCommander()){
            int sumOfHeroPower = 0;
            for (Card card : closeCombat) {
                if (card.isHero()){
                    sumOfHeroPower += card.getPower();
                    sum -= card.getPower();
                }
            }

            sum *= 2;
            sum += sumOfHeroPower;
        }
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

        if (doesRangedHaveCommander()){
            int sumOfHeroPower = 0;
            for (Card card : ranged) {
                if (card.isHero()){
                    sumOfHeroPower += card.getPower();
                    sum -= card.getPower();
                }
            }

            sum *= 2;
            sum += sumOfHeroPower;
        }
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

        if (doesSiegeHaveCommander()){
            int sumOfHeroPower = 0;
            for (Card card : siege) {
                if (card.isHero()){
                    sumOfHeroPower += card.getPower();
                    sum -= card.getPower();
                }
            }

            sum *= 2;
            sum += sumOfHeroPower;
        }
        return sum ;
    }

    public int getTotalPower() {
        return getCloseCombatPowerSum() + getRangedPowerSum() + getSiegePowerSum();
    }

    public void clearBoard() {
        closeCombat.clear();
        ranged.clear();
        siege.clear();
        commander7 = null;
        commander8 = null;
        commander9 = null;
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

    public boolean doesSiegeHaveCommander(){
        return commander7 != null;
    }

    public boolean doesRangedHaveCommander(){
        return commander8 != null;
    }

    public boolean doesCloseCombatHaveCommander(){
        return commander9 != null;
    }

    //getters and setters
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

    public Card getCommander7() {
        return commander7;
    }

    public void setCommander7(Card commander7) {
        this.commander7 = commander7;
    }

    public Card getCommander8() {
        return commander8;
    }

    public void setCommander8(Card commander8) {
        this.commander8 = commander8;
    }

    public Card getCommander9() {
        return commander9;
    }

    public void setCommander9(Card commander9) {
        this.commander9 = commander9;
    }
}
