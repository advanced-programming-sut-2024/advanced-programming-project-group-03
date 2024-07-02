package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;

public class LeaderAbility12 extends Ability {
    @Override
    public void executeAbility(Card card) {
        if (Game.getGame().getCurrentTurn() == 1) {
            Game.getGame().getGameBoard().getPlayer1Board().setCommander8Played(true);
        }
        else {
            Game.getGame().getGameBoard().getPlayer2Board().setCommander8Played(true);
        }
        GameUIController.getGameUIController().updateRows();
        Game.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
