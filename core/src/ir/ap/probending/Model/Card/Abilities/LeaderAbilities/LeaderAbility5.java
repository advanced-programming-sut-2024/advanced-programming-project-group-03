package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;

public class LeaderAbility5 extends Ability {
    @Override
    public void executeAbility(Card card) {
        if (Game.getGame().getCurrentTurn() == 1){
            Card strongestSiege = Game.getGame().getGameBoard().getPlayer2Board().getStrongestRanged();
            if (Game.getGame().getGameBoard().getPlayer2Board().getRangedPowerSum() > 10){
                Game.getGame().getGameBoard().getPlayer2Board().removeCardFromRanged(strongestSiege);
                Game.getGame().getGameBoard().getPlayer2().addCardToBurntCards(strongestSiege);
            }
        }
        else {
            Card strongestSiege = Game.getGame().getGameBoard().getPlayer1Board().getStrongestRanged();
            if (Game.getGame().getGameBoard().getPlayer1Board().getRangedPowerSum() > 10){
                Game.getGame().getGameBoard().getPlayer1Board().removeCardFromRanged(strongestSiege);
                Game.getGame().getGameBoard().getPlayer1().addCardToBurntCards(strongestSiege);
            }
        }

        GameUIController.getGameUIController().updateRows();
    }
}
