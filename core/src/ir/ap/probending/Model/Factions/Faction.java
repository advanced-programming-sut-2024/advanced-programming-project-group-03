package ir.ap.probending.Model.Factions;

import com.badlogic.gdx.graphics.Texture;
import ir.ap.probending.Model.Leader;

import java.util.ArrayList;

public class Faction {
    protected ArrayList<Leader> leaders;
    protected Texture texture;

    public Faction() {
        leaders = new ArrayList<>();
    }

    public Faction clone() {
        try {
            return (Faction) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Leader> getLeaderArray() {
        return leaders;
    }

    public void addLeader(Leader leader) {
        leaders.add(leader);
    }

    public void removeLeader(Leader leader) {
        leaders.remove(leader);
    }

    public Texture getTexture() {
        return texture;
    }

}
