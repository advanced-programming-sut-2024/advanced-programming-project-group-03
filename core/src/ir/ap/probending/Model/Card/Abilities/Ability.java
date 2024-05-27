package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;

public abstract class Ability {

    public Ability clone () {
        try {
            return (Ability) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract void executeAbility(Card card);
}
