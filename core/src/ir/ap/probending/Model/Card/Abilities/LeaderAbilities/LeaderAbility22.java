package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;

public class LeaderAbility22 extends Ability {
    @Override
    public void executeAbility(Card card) {
        Game.getGame().setLoseHalfInBadWeatherActivated(true);
        Game.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
