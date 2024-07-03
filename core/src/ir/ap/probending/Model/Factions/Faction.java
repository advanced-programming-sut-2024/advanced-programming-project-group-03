package ir.ap.probending.Model.Factions;

import com.badlogic.gdx.graphics.Texture;
import ir.ap.probending.Model.Card.Card;

import java.util.ArrayList;

public class Faction {
    private String factionName;
    private ArrayList<Card> leaders;
    private ArrayList<Card> cards;
    private Texture texture;

    public Faction(String factionName, Texture texture , ArrayList<Card> leaders, ArrayList<Card> cards) {
        this.factionName = factionName;
        this.texture = texture;
        this.leaders = new ArrayList<>(leaders);
        this.cards = new ArrayList<>(cards);
    }

    public Faction clone() {
        return new Faction(factionName, texture, leaders, cards);
    }

    public ArrayList<Card> getLeaderArray() {
        return leaders;
    }

    public void addLeader(Card leader) {
        leaders.add(leader);
    }

    public void removeLeader(Card leader) {
        leaders.remove(leader);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public ArrayList<Card> getCardArray() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

}
