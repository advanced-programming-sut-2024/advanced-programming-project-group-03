package ir.ap.probending.Model.Game;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.User;

import java.util.ArrayList;

public class Player {
    private User user;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;

    public Player(User user) {
        this.user = user;
        deck = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public void drawCard() {
        //TODO chooses random card from deck
        if (deck.size() > 0) {
            hand.add(deck.get(0));
            deck.remove(0);
        }
    }

    public void addCardsToDeck(ArrayList<Card> cards) {
        deck.addAll(cards);
    }

    //getters and setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
