package ir.ap.probending.Model.Card.Abilities.LeaderAbilities;

import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.Game;

public class LeaderAbility1 extends Ability {
    @Override
    public void executeAbility(Card card) {
        if (Game.getGame().getCurrentTurn() == 1){
            for (Card card1 : Game.getGame().getGameBoard().getPlayer1().getDeck()){
                if (card1.getName().equals("Fog")){
                    Game.getGame().playCard(card1 , Game.getGame().getGameBoard().getPlayer1());
                    Game.getGame().getGameBoard().getPlayer1().getDeck().remove(card1);
                    break;
                }
            }
        }
        else {
            for (Card card1 : Game.getGame().getGameBoard().getPlayer2().getDeck()){
                if (card1.getName().equals("Fog")){
                    Game.getGame().playCard(card1 , Game.getGame().getGameBoard().getPlayer2());
                    Game.getGame().getGameBoard().getPlayer2().getDeck().remove(card1);
                    break;
                }
            }
        }
        Game.getGame().getCurrentPlayer().setPlayedLeaderAbility(true);
        GameUIController.getGameUIController().hideLeaderAbilityButton();
    }
}
