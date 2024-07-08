package ir.ap.probending.Model.Factions;

import com.badlogic.gdx.graphics.Texture;
import ir.ap.probending.Model.Card.Card;

import java.util.ArrayList;
import java.util.Random;

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

    public Faction(String factionName , ArrayList<Card> leaders, ArrayList<Card> cards) {
        this.factionName = factionName;
        this.leaders = new ArrayList<>(leaders);
        this.cards = new ArrayList<>(cards);
    }

    public static Faction getRandomFaction() {
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

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }
}
