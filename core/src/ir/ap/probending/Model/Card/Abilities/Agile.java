package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;

public class Agile extends Ability {
    public void executeAbility(Card card) {
        card.setPlayingRow(3);
    }
}
