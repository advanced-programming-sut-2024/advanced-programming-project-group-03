package ir.ap.probending.Model.Card.Abilities;

import ir.ap.probending.Model.Game.Board;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Game.Game;
import ir.ap.probending.Model.Game.GameBoard;
import ir.ap.probending.Model.Game.Player;

public class TightBonds extends Ability{

    @Override
    public void executeAbility(Card card) {
        Player currentPlayer = Game.getGame().getCurrentPlayer();
        boolean isFound = false;
        if (currentPlayer.equals(Game.getGame().getGameBoard().getPlayer1())) {
            switch (card.getPlayingRow()){
                case 0:
                    for (Card cardInRow : Game.getGame().getGameBoard().getPlayer1Board().getSiege()) {
                        if (cardInRow.getName().equals(card.getName()) && cardInRow != card){
                            cardInRow.setPower(cardInRow.getPower() * 2);
                            isFound = true;
                        }
                    }
                    break;
                case 1:
                    for (Card cardInRow : Game.getGame().getGameBoard().getPlayer1Board().getRanged()) {
                        if (cardInRow.getName().equals(card.getName()) && cardInRow != card){
                            cardInRow.setPower(cardInRow.getPower() * 2);
                            isFound = true;
                        }
                    }
                    break;
                case 2:
                    for (Card cardInRow : Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat()) {
                        if (cardInRow.getName().equals(card.getName()) && cardInRow != card){
                            cardInRow.setPower(cardInRow.getPower() * 2);
                            isFound = true;
                        }
                    }
                    break;
            }
        }
        else {
            switch (card.getPlayingRow()){
                case 0:
                    for (Card cardInRow : Game.getGame().getGameBoard().getPlayer2Board().getSiege()) {
                        if (cardInRow.getName().equals(card.getName()) && cardInRow != card){
                            cardInRow.setPower(cardInRow.getPower() * 2);
                            isFound = true;
                        }
                    }
                    break;
                case 1:
                    for (Card cardInRow : Game.getGame().getGameBoard().getPlayer2Board().getRanged()) {
                        if (cardInRow.getName().equals(card.getName()) && cardInRow != card){
                            cardInRow.setPower(cardInRow.getPower() * 2);
                            isFound = true;
                        }
                    }
                    break;
                case 2:
                    for (Card cardInRow : Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat()) {
                        if (cardInRow.getName().equals(card.getName()) && cardInRow != card){
                            cardInRow.setPower(cardInRow.getPower() * 2);
                            isFound = true;
                        }
                    }
                    break;
            }
        }

        if (isFound)
            card.setPower(card.getPower() * 2);
    }
}
