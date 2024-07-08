package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.Game;

public class LeaderAbility11 extends Ability {
    @Override
    public void executeAbility(Card card) {
        if (Game.getGame().getCurrentTurn() == 1){
            Card strongestSiege = Game.getGame().getGameBoard().getPlayer2Board().getStrongestCloseCombat();
            if (Game.getGame().getGameBoard().getPlayer2Board().getCloseCombatPowerSum() > 10){
                Game.getGame().getGameBoard().getPlayer2Board().removeCardFromCloseCombat(strongestSiege);
                Game.getGame().getGameBoard().getPlayer2().addCardToBurntCards(strongestSiege);
            }
        }
        else {
            Card strongestSiege = Game.getGame().getGameBoard().getPlayer1Board().getStrongestCloseCombat();
            if (Game.getGame().getGameBoard().getPlayer1Board().getCloseCombatPowerSum() > 10){
                Game.getGame().getGameBoard().getPlayer1Board().removeCardFromCloseCombat(strongestSiege);
                Game.getGame().getGameBoard().getPlayer1().addCardToBurntCards(strongestSiege);
            }
        }

        GameUIController.getGameUIController().updateRows();
        Game.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
