package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;

public class Clear extends Ability {
    @Override
    public void executeAbility(Card card) {
        Game.getGame().getGameBoard().getSpellCards().clear();
    }
}
