package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;
import ir.ap.probending.Model.Game.GameBoard;

public class Spy extends Ability {
    public void executeAbility(Card card) {
        if (Game.getGame().getGameBoard().getPlayer1Board().hasCard(card)) {
            Game.getGame().getGameBoard().getPlayer1Board().addCard(card);
            Game.getGame().getGameBoard().getPlayer1Board().removeCard(card);
        } else {
            Game.getGame().getGameBoard().getPlayer2Board().addCard(card);
            Game.getGame().getGameBoard().getPlayer2Board().removeCard(card);
        }
    }
}
