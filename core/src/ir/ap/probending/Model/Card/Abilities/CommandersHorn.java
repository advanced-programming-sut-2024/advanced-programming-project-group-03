package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Game.Board;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;
import ir.ap.probending.Model.Game.GameBoard;

public class CommandersHorn extends Ability{
    public void executeAbility(Card card) {
        Board player1Board = Game.getGame().getGameBoard().getPlayer1Board();
        Board player2Board = Game.getGame().getGameBoard().getPlayer2Board();
        int row;
        if (player1Board.hasCard(card)) {
            row = player1Board.findCard(card)[0];
            doublePower(player1Board, row);
        } else if (player2Board.hasCard(card)) {
            row = player2Board.findCard(card)[0];
            doublePower(player2Board, row);
        }
    }

    private void doublePower(Board playerBoard, int row) {
        for (Card card : playerBoard.getRow(row))
            card.setPower(card.getPower() * 2);
    }
}
