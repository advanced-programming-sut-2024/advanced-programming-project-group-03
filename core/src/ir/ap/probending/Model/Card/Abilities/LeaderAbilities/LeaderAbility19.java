package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

public class LeaderAbility19 extends Ability {
    @Override
    public void executeAbility(Card card) {
        Card opponentsCard = GameController.getGame().getOtherPlayer().getBurntCards().remove(0);
        GameController.getGame().getCurrentPlayer().addCardToHand(opponentsCard);
        GameController.getGame().setUpHandView(GameController.getGame().getCurrentPlayer());

        GameController.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
