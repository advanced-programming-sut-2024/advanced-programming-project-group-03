package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.Game;
import ir.ap.probending.Model.Card.Abilities.*;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Factions.Faction;

import java.util.ArrayList;

public class Board {
    private ArrayList<Card> closeCombat = new ArrayList<>();
    private ArrayList<Card> ranged = new ArrayList<>();
    private ArrayList<Card> siege = new ArrayList<>();
    private Card commander7;
    private Card commander8;
    private Card commander9;
    private boolean commander7Played = false;
    private boolean commander8Played = false;
    private boolean commander9Played = false;
    private Card leader;
    private Faction faction;
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
                    if (Game.getGame().isLoseHalfInBadWeatherActivated()){
                        sum += card.getPower() / 2;
                    }
                    else {
                        sum += 1;
                    }
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

        if (Game.getGame().getGameBoard().isSpyDoublePowerActivated()){
            for (Card card : closeCombat) {
                if (card.getAbility() instanceof Spy){
                    sum += card.getPower();
                }
            }
        }
        return sum ;
    }

    public int getRangedPowerSum() {
        int sum = 0;
        if (Game.getGame().getGameBoard().isFogPlayed() || Game.getGame().getGameBoard().isStormPlayed()){
            for (Card card : ranged) {
                if (!card.isHero()){
                    if (Game.getGame().isLoseHalfInBadWeatherActivated()){
                        sum += card.getPower() / 2;
                    }
                    else {
                        sum += 1;
                    }
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

        if (Game.getGame().getGameBoard().isSpyDoublePowerActivated()){
            for (Card card : ranged) {
                if (card.getAbility() instanceof Spy){
                    sum += card.getPower();
                }
            }
        }
        return sum ;
    }

    public int getSiegePowerSum() {
        int sum = 0;
        if (Game.getGame().getGameBoard().isRainPlayed() || Game.getGame().getGameBoard().isStormPlayed()){
            for (Card card : siege) {
                if (!card.isHero()){
                    if (Game.getGame().isLoseHalfInBadWeatherActivated()){
                        sum += card.getPower() / 2;
                    }
                    else {
                        sum += 1;
                    }
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

        if (Game.getGame().getGameBoard().isSpyDoublePowerActivated()){
            for (Card card : siege) {
                if (card.getAbility() instanceof Spy){
                    sum += card.getPower();
                }
            }
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
        return (commander7 != null && commander7.getAbility() instanceof CommandersHorn) || commander7Played;
    }

    public boolean doesRangedHaveCommander(){
        return (commander8 != null && commander8.getAbility() instanceof CommandersHorn) || commander8Played;
    }

    public boolean doesCloseCombatHaveCommander(){
        return (commander9 != null && commander9.getAbility() instanceof CommandersHorn) || commander9Played;
    }

    public boolean doesSiegeHaveMardroeme(){
        for (Card card : siege) {
            if (card.getAbility() instanceof Mardroeme){
                return true;
            }
        }
        return false;
    }

    public boolean doesRangedHaveMardroeme(){
        for (Card card : ranged) {
            if (card.getAbility() instanceof Mardroeme){
                return true;
            }
        }
        return false;
    }

    public boolean doesCloseCombatHaveMardroeme(){
        for (Card card : closeCombat) {
            if (card.getAbility() instanceof Mardroeme){
                return true;
            }
        }
        return false;
    }


    public void playLeaderAbility(){
        if (leader != null){
            leader.getAbility().executeAbility(leader);
        }
    }

    public int getCardsRow(Card card){
        if (closeCombat.contains(card)){
            return 2;
        }
        else if (ranged.contains(card)){
            return 1;
        }
        else if (siege.contains(card)){
            return 0;
        }
        return 6;
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

    public Card getLeader() {
        return leader;
    }

    public void setLeader(Card leader) {
        this.leader = leader;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public boolean isCommander7Played() {
        return commander7Played;
    }

    public void setCommander7Played(boolean commander7Played) {
        this.commander7Played = commander7Played;
    }

    public boolean isCommander8Played() {
        return commander8Played;
    }

    public void setCommander8Played(boolean commander8Played) {
        this.commander8Played = commander8Played;
    }

    public boolean isCommander9Played() {
        return commander9Played;
    }

    public void setCommander9Played(boolean commander9Played) {
        this.commander9Played = commander9Played;
    }

    public Card getStrongestSiege() {
        Card strongestSiege = null;
        int maxPower = 0;
        for (Card card : siege) {
            if (card.getPower() > maxPower){
                maxPower = card.getPower();
                strongestSiege = card;
            }
        }
        return strongestSiege;
    }

    public Card getStrongestRanged() {
        Card strongestRanged = null;
        int maxPower = 0;
        for (Card card : ranged) {
            if (card.getPower() > maxPower){
                maxPower = card.getPower();
                strongestRanged = card;
            }
        }
        return strongestRanged;
    }

    public Card getStrongestCloseCombat() {
        Card strongestCloseCombat = null;
        int maxPower = 0;
        for (Card card : closeCombat) {
            if (card.getPower() > maxPower){
                maxPower = card.getPower();
                strongestCloseCombat = card;
            }
        }
        return strongestCloseCombat;
    }
}
