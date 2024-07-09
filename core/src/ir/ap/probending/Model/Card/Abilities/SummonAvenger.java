package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

public class SummonAvenger extends Ability{
    public void executeAbility(Card card) {
        GameController.getGame().setSummonAvengerPlayer(GameController.getGame().getCurrentPlayer());
    }
}
