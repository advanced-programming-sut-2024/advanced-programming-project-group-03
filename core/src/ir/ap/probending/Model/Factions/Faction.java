package ir.ap.probending.Model.Factions;

import com.badlogic.gdx.graphics.Texture;
import ir.ap.probending.Model.Card.Card;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Faction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String factionName;
    private ArrayList<Card> leaders;
    private ArrayList<Card> cards;
    private transient Texture texture;

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

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        // Handle texture and sprite serialization if needed
        // For example, serialize texture path if applicable
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        // Handle texture and sprite deserialization if needed
        // For example, reinitialize texture from path if applicable
    }
}
