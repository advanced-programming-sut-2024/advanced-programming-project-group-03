package ir.ap.probending.Control;

import ir.ap.probending.View.PreGameController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.DeckSave;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Data.SaveUser;
import ir.ap.probending.Model.Factions.Faction;
import ir.ap.probending.Model.Factions.FactionObjects;
import ir.ap.probending.Model.User;

import java.util.ArrayList;
import java.util.Random;

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

    public void setOpponentPlayer(User user){
        if (user != null){
            GameMaster.getGameMaster().setLoggedInUser2(user);
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

    public Faction getRandomFaction() {
        int random = new Random().nextInt(4);
        switch (random){
            case 0:
                return FactionObjects.getFactionByName("Fire");
            case 1:
                return FactionObjects.getFactionByName("Water");
            case 2:
                return FactionObjects.getFactionByName("Earth");
            case 3:
                return FactionObjects.getFactionByName("Air");
            default:
                return FactionObjects.getFactionByName("Fire");
        }
    }

    public Card getRandomLeader(Faction faction) {
        return faction.getLeaderArray().get(new Random().nextInt(faction.getLeaderArray().size()));
    }

    public ArrayList<Card> getRandomDeckCards(Faction faction) {
        ArrayList<Card> deckCards = new ArrayList<>();
        ArrayList<Card> factionCards = new ArrayList<>(faction.getCardArray());
        factionCards.sort((card1, card2) -> card2.getPower() - card1.getPower());
        for (int i = 0; i < 20; i++){
            int random = new Random().nextInt(factionCards.size());
            deckCards.add(factionCards.get(random));
            factionCards.remove(random);
        }
        return deckCards;
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
