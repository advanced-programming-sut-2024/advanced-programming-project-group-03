package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

public class LeaderAbility21 extends Ability {
    @Override
    public void executeAbility(Card card) {
        GameController.getGame().getGameBoard().getPlayer1().getDeck().addAll(GameController.getGame().getGameBoard().getPlayer1().getBurntCards());
        GameController.getGame().getGameBoard().getPlayer1().getBurntCards().clear();
        GameController.getGame().getGameBoard().getPlayer2().getDeck().addAll(GameController.getGame().getGameBoard().getPlayer2().getBurntCards());
        GameController.getGame().getGameBoard().getPlayer2().getBurntCards().clear();

        GameController.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
