package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.PreGameController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Card.CardObjects;
import ir.ap.probending.Model.Factions.Faction;

import java.io.Serializable;
import java.util.ArrayList;

public class PreGame implements Serializable {
    private static PreGame preGame = new PreGame();
    private Faction playerFaction;
    private Card selectedLeader;
    private ArrayList<Card> deckCards = new ArrayList<>();
    private ArrayList<Card> storageCards = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public void changeFaction(Faction faction) {
        playerFaction = faction;
        deckCards.clear();
        storageCards.clear();
        ArrayList<Card> factionCards = faction.getCardArray();
        //sort the faction cards arraylist by their power
        factionCards.sort((card1, card2) -> card2.getPower() - card1.getPower());
        storageCards.addAll(factionCards);
        selectedLeader = faction.getLeaderArray().get(0);
        PreGameController.getPreGameController().refreshLabels();
        PreGameController.getPreGameController().refreshStorageTable();
        PreGameController.getPreGameController().refreshDeckTable();
    }

    public void saveDeckToFile(String location){
        //TODO save deck to file
    }

    public void saveDeckToDB(){
        //TODO save deck to database
    }

    public void loadDeckFromFile(String location){
        //TODO load deck from file
    }

    public void loadDeckFromDB(){
        //TODO load deck from database
    }

    //getters and setters

    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(ArrayList<Card> deckCards) {
        this.deckCards = deckCards;
    }

    public ArrayList<Card> getStorageCards() {
        return storageCards;
    }

    public void setStorageCards(ArrayList<Card> storageCards) {
        this.storageCards = storageCards;
    }

    public Faction getPlayerFaction() {
        return playerFaction;
    }

    public void setPlayerFaction(Faction playerFaction) {
        this.playerFaction = playerFaction;
    }

    public static PreGame getPreGame() {
        return preGame;
    }

    public static void setPreGame(PreGame preGame) {
        PreGame.preGame = preGame;
    }

    public Card getSelectedLeader() {
        return selectedLeader;
    }

    public void setSelectedLeader(Card selectedLeader) {
        this.selectedLeader = selectedLeader;
    }
}
