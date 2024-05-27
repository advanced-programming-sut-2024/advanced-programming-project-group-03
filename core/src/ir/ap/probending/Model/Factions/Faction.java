package ir.ap.probending.Model.Factions;

import ir.ap.probending.Model.Leader;

import java.util.ArrayList;

public abstract class Faction {
    protected ArrayList<Leader> leaders;

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

}
