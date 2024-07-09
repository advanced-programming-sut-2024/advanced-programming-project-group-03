package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Control.GameController;
import ir.ap.probending.Model.Game.Player;

public class Morale extends Ability{
    public void executeAbility(Card card) {
        Player player = GameController.getGame().getCurrentPlayer();

        if(player.equals(GameController.getGame().getGameBoard().getPlayer1())){
            switch (card.getPlayingRow()){
                case 2:
                    GameController.getGame().getGameBoard().getPlayer1Board().setMoraleBoostCloseCombat(GameController.getGame().getGameBoard().getPlayer1Board().getMoraleBoostCloseCombat() + 1);
                    break;
                case 1:
                    GameController.getGame().getGameBoard().getPlayer1Board().setMoraleBoostRanged(GameController.getGame().getGameBoard().getPlayer1Board().getMoraleBoostRanged() + 1);
                    break;
                case 0:
                    GameController.getGame().getGameBoard().getPlayer1Board().setMoraleBoostSiege(GameController.getGame().getGameBoard().getPlayer1Board().getMoraleBoostSiege() + 1);
                    break;
            }
        }
        else{
            switch (card.getPlayingRow()){
                case 2:
                    GameController.getGame().getGameBoard().getPlayer2Board().setMoraleBoostCloseCombat(GameController.getGame().getGameBoard().getPlayer2Board().getMoraleBoostCloseCombat() + 1);
                    break;
                case 1:
                    GameController.getGame().getGameBoard().getPlayer2Board().setMoraleBoostRanged(GameController.getGame().getGameBoard().getPlayer2Board().getMoraleBoostRanged() + 1);
                    break;
                case 0:
                    GameController.getGame().getGameBoard().getPlayer2Board().setMoraleBoostSiege(GameController.getGame().getGameBoard().getPlayer2Board().getMoraleBoostSiege() + 1);
                    break;
            }
        }
    }
}
