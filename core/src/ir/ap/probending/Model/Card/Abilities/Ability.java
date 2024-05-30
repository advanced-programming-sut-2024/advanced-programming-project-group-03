package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;

public abstract class Ability {

    public Ability clone () {
        return new Morale(); //TODO: implement clone method
    }

    public abstract void executeAbility(Card card);
}
