package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;

public class LeaderAbility19 extends Ability {
    @Override
    public void executeAbility(Card card) {
        Card opponentsCard = Game.getGame().getOtherPlayer().getBurntCards().remove(0);
        Game.getGame().getCurrentPlayer().addCardToHand(opponentsCard);
        Game.getGame().setUpHandView(Game.getGame().getCurrentPlayer());

        Game.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
