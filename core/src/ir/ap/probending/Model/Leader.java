package ir.ap.probending.Model;

import ir.ap.probending.Model.Factions.Faction;
import ir.ap.probending.Model.LeaderAbilities.LeaderAbility;

public class Leader {
    private Faction faction;
    private String name;
     private LeaderAbility leaderAbility;

    public Leader(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Faction getFaction() {
        return faction;
    }

    public void executeLeaderAbility(){
        leaderAbility.execute(this);
    }
}
