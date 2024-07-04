package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.PreGameController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Card.CardObjects;
import ir.ap.probending.Model.Data.DeckSave;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Data.SaveUser;
import ir.ap.probending.Model.Factions.Faction;
import ir.ap.probending.Model.Factions.FactionObjects;

import java.util.ArrayList;

public class PreGame {
    private static PreGame preGame = new PreGame();
    private Faction playerFaction;
    private Card selectedLeader;
    private ArrayList<Card> deckCards = new ArrayList<>();
    private ArrayList<Card> storageCards = new ArrayList<>();

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
        try {
            ArrayList<String> deckCards = new ArrayList<>();
            for (Card card : this.deckCards){
                deckCards.add(card.getName());
            }
            SaveUser.saveDeckToJson(location, deckCards , playerFaction.getFactionName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDeckToDB(){
        try {
            ArrayList<String> deckCards = new ArrayList<>();
            for (Card card : this.deckCards){
                deckCards.add(card.getName());
            }
            DeckSave deckSave = new DeckSave(deckCards, playerFaction.getFactionName());

            GameMaster.getGameMaster().getLoggedInUser1().setDeckSave(deckSave);
            SaveUser.updateUser(GameMaster.getGameMaster().getLoggedInUser1());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDeckFromFile(String location){
        try {
            DeckSave deckSave = SaveUser.loadDeckFromJson(location);
            ArrayList<String> cards = new ArrayList<>();
            if (deckSave.getDeckCards() != null){
                cards = new ArrayList<>(deckSave.getDeckCards());
            }
            if (deckSave.getPlayerFaction() != null){
                changeFaction(FactionObjects.getFactionByName(deckSave.getPlayerFaction()));
            }
            for (String card : cards){
                if (getCardByNameFromStorage(card) != null){
                    deckCards.add(getCardByNameFromStorage(card));
                    storageCards.remove(getCardByNameFromStorage(card));
                }
            }
            PreGameController.getPreGameController().refreshDeckTable();
            PreGameController.getPreGameController().refreshLabels();
            PreGameController.getPreGameController().refreshStorageTable();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDeckFromDB(){
        try {
            DeckSave deckSave = GameMaster.getGameMaster().getLoggedInUser1().getDeckSave();
            ArrayList<String> cards = new ArrayList<>();
            if (deckSave.getDeckCards() != null){
                cards = new ArrayList<>(deckSave.getDeckCards());
            }
            if (deckSave.getPlayerFaction() != null){
                changeFaction(FactionObjects.getFactionByName(deckSave.getPlayerFaction()));
            }
            for (String card : cards){
                if (getCardByNameFromStorage(card) != null){
                    deckCards.add(getCardByNameFromStorage(card));
                    storageCards.remove(getCardByNameFromStorage(card));
                }
            }
            PreGameController.getPreGameController().refreshDeckTable();
            PreGameController.getPreGameController().refreshLabels();
            PreGameController.getPreGameController().refreshStorageTable();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Card getCardByNameFromStorage(String name){
        for (Card card : storageCards){
            if (card.getName().equals(name)){
                return card;
            }
        }
        return null;
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
