package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Game.Board;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.GameBoard;

public class TightBonds extends Ability{
    public void executeAbility(Card card) {
        Board player1Board = GameBoard.getPlayer1Board();
        Board player2Board = GameBoard.getPlayer2Board();

        if (player1Board.hasCard(card)) {
            tightBond(player1Board, card);
        } else {
            tightBond(player2Board, card);
        }
    }

    private void tightBond(Board playerBoard, Card card) {
        Integer[] position = playerBoard.findCard(card);
        int row = position[0];
        int column = position[1];
        int power = card.getPower();
        boolean hasCardNextTo = false;
        Card neighbourCard;
        if ((neighbourCard = playerBoard.getCard(row, column - 1)) != null && neighbourCard.getName().equals(card.getName())) {
            hasCardNextTo = true;
            neighbourCard.setPower(neighbourCard.getPower() * 2);
        }
        if ((neighbourCard = playerBoard.getCard(row, column + 1)) != null && neighbourCard.getName().equals(card.getName())) {
            hasCardNextTo = true;
            neighbourCard.setPower(neighbourCard.getPower() * 2);
        }
        if (hasCardNextTo) {
            card.setPower(power * 2);
        }
    }
}
