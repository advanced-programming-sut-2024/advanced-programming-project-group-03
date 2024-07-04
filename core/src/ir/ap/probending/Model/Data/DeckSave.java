package ir.ap.probending.Model.Data;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Factions.Faction;

import java.util.ArrayList;

public class DeckSave {
    private ArrayList<String> deckCards;
    private String playerFaction;

    public DeckSave(ArrayList<String> deckCards, String playerFaction) {
        this.deckCards = deckCards;
        this.playerFaction = playerFaction;
    }

    public DeckSave() {
    }

    public ArrayList<String> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(ArrayList<String> deckCards) {
        this.deckCards = deckCards;
    }

    public String getPlayerFaction() {
        return playerFaction;
    }

    public void setPlayerFaction(String playerFaction) {
        this.playerFaction = playerFaction;
    }

}
