package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

public class Clear extends Ability {
    @Override
    public void executeAbility(Card card) {
        GameController.getGame().getGameBoard().getSpellCards().clear();
    }
}
