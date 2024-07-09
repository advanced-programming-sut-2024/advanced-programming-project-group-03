package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

public class LeaderAbility10 extends Ability {
    @Override
    public void executeAbility(Card card) {
        GameController.getGame().getGameBoard().setSpyDoublePowerActivated(true);
    }
}
