package ir.ap.probending.Model;

import ir.ap.probending.Model.Card.Card;

import java.util.ArrayList;

public class PreGame {
    private ArrayList<Card> deckCards = new ArrayList<>();
    private ArrayList<Card> storageCards = new ArrayList<>();

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
}
