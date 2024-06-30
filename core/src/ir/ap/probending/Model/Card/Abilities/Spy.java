package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;
import ir.ap.probending.Model.Game.GameBoard;

public class Spy extends Ability {
    public void executeAbility(Card card) {
        GameBoard gameBoard = Game.getGame().getGameBoard();
        if (gameBoard.getPlayer1Board().getCloseCombat().contains(card)) {
            gameBoard.getPlayer2Board().addCardToCloseCombat(card);
            gameBoard.getPlayer1Board().removeCardFromCloseCombat(card);
        }
        else if (gameBoard.getPlayer1Board().getRanged().contains(card)) {
            gameBoard.getPlayer2Board().addCardToRanged(card);
            gameBoard.getPlayer1Board().removeCardFromRanged(card);
        }
        else if (gameBoard.getPlayer1Board().getSiege().contains(card)) {
            gameBoard.getPlayer2Board().addCardToSiege(card);
            gameBoard.getPlayer1Board().removeCardFromSiege(card);
        }
        else if (gameBoard.getPlayer2Board().getCloseCombat().contains(card)) {
            gameBoard.getPlayer1Board().addCardToCloseCombat(card);
            gameBoard.getPlayer2Board().removeCardFromCloseCombat(card);
        }
        else if (gameBoard.getPlayer2Board().getRanged().contains(card)) {
            gameBoard.getPlayer1Board().addCardToRanged(card);
            gameBoard.getPlayer2Board().removeCardFromRanged(card);
        }
        else if (gameBoard.getPlayer2Board().getSiege().contains(card)) {
            gameBoard.getPlayer1Board().addCardToSiege(card);
            gameBoard.getPlayer2Board().removeCardFromSiege(card);
        }

        Game.getGame().getCurrentPlayer().drawCard();
        Game.getGame().getCurrentPlayer().drawCard();
    }
}
