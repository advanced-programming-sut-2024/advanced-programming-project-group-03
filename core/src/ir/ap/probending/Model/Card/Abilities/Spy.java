package ir.ap.probending.Model.Card.Abilities;

import com.badlogic.gdx.Game;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.GameBoard;

public class Spy extends Ability {
    public void executeAbility(Card card) {
        if (GameBoard.getPlayer1Board().hasCard(card)) {
            GameBoard.getPlayer1Board().addCard(card);
            GameBoard.getPlayer1Board().removeCard(card);
        } else {
            GameBoard.getPlayer2Board().addCard(card);
            GameBoard.getPlayer2Board().removeCard(card);
        }
    }
}
