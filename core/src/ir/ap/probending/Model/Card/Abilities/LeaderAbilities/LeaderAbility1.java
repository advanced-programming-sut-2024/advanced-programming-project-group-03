package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;

public class LeaderAbility1 extends Ability {
    @Override
    public void executeAbility(Card card) {
        if (GameController.getGame().getCurrentTurn() == 1){
            for (Card card1 : GameController.getGame().getGameBoard().getPlayer1().getDeck()){
                if (card1.getName().equals("Fog")){
                    GameController.getGame().playCard(card1 , GameController.getGame().getGameBoard().getPlayer1() , true);
                    GameController.getGame().getGameBoard().getPlayer1().getDeck().remove(card1);
                    break;
                }
            }
        }
        else {
            for (Card card1 : GameController.getGame().getGameBoard().getPlayer2().getDeck()){
                if (card1.getName().equals("Fog")){
                    GameController.getGame().playCard(card1 , GameController.getGame().getGameBoard().getPlayer2() , true);
                    GameController.getGame().getGameBoard().getPlayer2().getDeck().remove(card1);
                    break;
                }
            }
        }
        GameController.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
