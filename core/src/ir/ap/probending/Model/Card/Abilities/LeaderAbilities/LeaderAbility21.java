package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.Game;

public class LeaderAbility21 extends Ability {
    @Override
    public void executeAbility(Card card) {
        Game.getGame().getGameBoard().getPlayer1().getDeck().addAll(Game.getGame().getGameBoard().getPlayer1().getBurntCards());
        Game.getGame().getGameBoard().getPlayer1().getBurntCards().clear();
        Game.getGame().getGameBoard().getPlayer2().getDeck().addAll(Game.getGame().getGameBoard().getPlayer2().getBurntCards());
        Game.getGame().getGameBoard().getPlayer2().getBurntCards().clear();

        Game.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
