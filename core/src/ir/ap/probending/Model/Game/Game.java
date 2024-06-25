package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Data.GameMaster;

public class Game {
    public static void startGame() {
        // Set players
        GameBoard.setPlayer1(new Player(GameMaster.getGameMaster().getLoggedInUser1()));
        GameBoard.setPlayer2(new Player(GameMaster.getGameMaster().getGuestUser2()));

        //set boards
        GameBoard.setPlayer1Board(new Board());
        GameBoard.setPlayer2Board(new Board());

        // give cards to players
        GameBoard.getPlayer1().addCardsToDeck(PreGame.getPreGame().getDeckCards());
        GameBoard.getPlayer2().addCardsToDeck(PreGame.getPreGame().getDeckCards());

        //give 10 random cards to each player
        for (int i = 0; i < 10; i++) {
            GameBoard.getPlayer1().drawCard();
            GameBoard.getPlayer2().drawCard();
        }

        //add hand cards of player1 to view
        int cardInRowCount = 0;
        for (int i = 0; i < GameBoard.getPlayer1().getHand().size(); i++) {
            GameBoard.getPlayer1Board().addCardToHand(GameBoard.getPlayer1().getHand().get(i).clone2());
            GameBoard.getPlayer1Board().getHand().get(i).getSprite().setSize(150, 300);
            GameUIController.getGameUIController().getPlayerHandTable().add(GameBoard.getPlayer1Board().getHand().get(i)).pad(0);
            cardInRowCount++;
            if (cardInRowCount == 5) {
                GameUIController.getGameUIController().getPlayerHandTable().row();
                cardInRowCount = 0;
            }
        }

        //set leaders and factions
        //TODO

    }
}
