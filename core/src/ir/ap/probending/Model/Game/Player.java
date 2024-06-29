package ir.ap.probending.Model.Game;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.User;

import java.util.ArrayList;

public class Player {
    private User user;
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> burntCards = new ArrayList<>();
    private boolean isPassedThisRound = false;
    private int setsWon = 0;

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

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void removeCardFromHand(Card card) {
        hand.remove(card);
    }

    public void addCardToBurntCards(Card card) {
        burntCards.add(card);
    }

    public void removeCardFromBurntCards(Card card) {
        burntCards.remove(card);
    }

    public void removeCardFromDeckCards(Card card){
        deck.remove(card);
    }

    public void removeCardFromDeckCards(ArrayList<Card> card){
        deck.removeAll(card);
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

    public ArrayList<Card> getBurntCards() {
        return burntCards;
    }

    public void setBurntCards(ArrayList<Card> burntCards) {
        this.burntCards = burntCards;
    }

    public boolean isPassedThisRound() {
        return isPassedThisRound;
    }

    public void setPassedThisRound(boolean passedThisRound) {
        isPassedThisRound = passedThisRound;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }


}
