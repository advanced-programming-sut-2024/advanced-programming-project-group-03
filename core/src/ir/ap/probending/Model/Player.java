package ir.ap.probending.Model;

import ir.ap.probending.Model.Card.Card;

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
        if (deck.size() > 0) {
            hand.add(deck.get(0));
            deck.remove(0);
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
