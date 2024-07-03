package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;
import ir.ap.probending.Model.Game.Player;

public class Morale extends Ability{
    public void executeAbility(Card card) {
        Player player = Game.getGame().getCurrentPlayer();

        if(player.equals(Game.getGame().getGameBoard().getPlayer1())){
            switch (card.getPlayingRow()){
                case 2:
                    Game.getGame().getGameBoard().getPlayer1Board().setMoraleBoostCloseCombat(Game.getGame().getGameBoard().getPlayer1Board().getMoraleBoostCloseCombat() + 1);
                    break;
                case 1:
                    Game.getGame().getGameBoard().getPlayer1Board().setMoraleBoostRanged(Game.getGame().getGameBoard().getPlayer1Board().getMoraleBoostRanged() + 1);
                    break;
                case 0:
                    Game.getGame().getGameBoard().getPlayer1Board().setMoraleBoostSiege(Game.getGame().getGameBoard().getPlayer1Board().getMoraleBoostSiege() + 1);
                    break;
            }
        }
        else{
            switch (card.getPlayingRow()){
                case 2:
                    Game.getGame().getGameBoard().getPlayer2Board().setMoraleBoostCloseCombat(Game.getGame().getGameBoard().getPlayer2Board().getMoraleBoostCloseCombat() + 1);
                    break;
                case 1:
                    Game.getGame().getGameBoard().getPlayer2Board().setMoraleBoostRanged(Game.getGame().getGameBoard().getPlayer2Board().getMoraleBoostRanged() + 1);
                    break;
                case 0:
                    Game.getGame().getGameBoard().getPlayer2Board().setMoraleBoostSiege(Game.getGame().getGameBoard().getPlayer2Board().getMoraleBoostSiege() + 1);
                    break;
            }
        }
    }
}
