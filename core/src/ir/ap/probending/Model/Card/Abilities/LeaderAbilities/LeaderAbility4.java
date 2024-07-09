package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

public class LeaderAbility4 extends Ability {
    @Override
    public void executeAbility(Card card) {
        if (GameController.getGame().getCurrentTurn() == 1){
            Card strongestSiege = GameController.getGame().getGameBoard().getPlayer2Board().getStrongestSiege();
            if (GameController.getGame().getGameBoard().getPlayer2Board().getSiegePowerSum() > 10){
                GameController.getGame().getGameBoard().getPlayer2Board().removeCardFromSiege(strongestSiege);
                GameController.getGame().getGameBoard().getPlayer2().addCardToBurntCards(strongestSiege);
            }
        }
        else {
            Card strongestSiege = GameController.getGame().getGameBoard().getPlayer1Board().getStrongestSiege();
            if (GameController.getGame().getGameBoard().getPlayer1Board().getSiegePowerSum() > 10){
                GameController.getGame().getGameBoard().getPlayer1Board().removeCardFromSiege(strongestSiege);
                GameController.getGame().getGameBoard().getPlayer1().addCardToBurntCards(strongestSiege);
            }
        }

        GameUIController.getGameUIController().updateRows();
        GameController.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
