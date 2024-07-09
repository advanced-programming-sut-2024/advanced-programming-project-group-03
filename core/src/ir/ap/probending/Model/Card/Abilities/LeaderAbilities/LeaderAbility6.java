package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

public class LeaderAbility6 extends Ability {
    @Override
    public void executeAbility(Card card) {
        if (GameController.getGame().getCurrentTurn() == 1) {
            GameController.getGame().getGameBoard().getPlayer1Board().setCommander9Played(true);
        }
        else {
            GameController.getGame().getGameBoard().getPlayer2Board().setCommander9Played(true);
        }
        GameUIController.getGameUIController().updateRows();
        GameController.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
