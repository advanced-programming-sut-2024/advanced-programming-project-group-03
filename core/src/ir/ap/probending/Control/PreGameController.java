package ir.ap.probending.Control;

import ir.ap.probending.View.PreGameScreenController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.DeckSave;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Data.SaveUser;
import ir.ap.probending.Model.Factions.Faction;
import ir.ap.probending.Model.Factions.FactionObjects;
import ir.ap.probending.Model.User;

import java.util.ArrayList;
import java.util.Random;

public class PreGameController {
    private static PreGameController preGameController = new PreGameController();
    private Faction playerFaction;
    private Card selectedLeader;
    private ArrayList<Card> deckCards = new ArrayList<>();
    private ArrayList<Card> storageCards = new ArrayList<>();

    public void changeFaction(Faction faction , boolean viewUpdate){
        playerFaction = faction;
        deckCards.clear();
        storageCards.clear();
        ArrayList<Card> factionCards = faction.getCardArray();
        //sort the faction cards arraylist by their power
        factionCards.sort((card1, card2) -> card2.getPower() - card1.getPower());
        storageCards.addAll(factionCards);
        selectedLeader = faction.getLeaderArray().get(0);
        if (viewUpdate){
            PreGameScreenController.getPreGameController().refreshPregame();
        }
    }

    public void saveDeckToFile(String location){
         ArrayList<String> deckCards = new ArrayList<>();
         for (Card card : this.deckCards){
             deckCards.add(card.getName());
         }
         SaveUser.saveDeckToJson(location, deckCards , playerFaction.getFactionName());
    }

    public void saveDeckToDB(){
        ArrayList<String> deckCards = new ArrayList<>();
        for (Card card : this.deckCards){
            deckCards.add(card.getName());
        }
        DeckSave deckSave = new DeckSave(deckCards, playerFaction.getFactionName());

        GameMaster.getGameMaster().getLoggedInUser1().setDeckSave(deckSave);
        SaveUser.updateUser(GameMaster.getGameMaster().getLoggedInUser1());
    }


    public void setOpponentPlayer(User user){
        if (user != null){
            GameMaster.getGameMaster().setLoggedInUser2(user);
        }
    }

    public Card getCardByNameFromStorage(String name){
        for (Card card : storageCards){
            if (card.getName().equals(name)){
                return card;
            }
        }
        return null;
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

    public static PreGameController getPreGame() {
        return preGameController;
    }

    public static void setPreGame(PreGameController preGameController) {
        PreGameController.preGameController = preGameController;
    }

    public Card getSelectedLeader() {
        return selectedLeader;
    }

    public void setSelectedLeader(Card selectedLeader) {
        this.selectedLeader = selectedLeader;
    }

    public static PreGameController getPreGameController() {
        return preGameController;
    }

    public static void setPreGameController(PreGameController preGameController) {
        PreGameController.preGameController = preGameController;
    }
}
