package ir.ap.probending.Model.LeaderAbilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Leader;

public abstract class LeaderAbility {

    public LeaderAbility clone () {
        try {
            return (LeaderAbility) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract void execute(Leader Leader);
}
