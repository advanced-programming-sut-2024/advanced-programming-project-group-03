package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.Game;

public class SummonAvenger extends Ability{
    public void executeAbility(Card card) {
        Game.getGame().setSummonAvengerPlayer(Game.getGame().getCurrentPlayer());
    }
}
