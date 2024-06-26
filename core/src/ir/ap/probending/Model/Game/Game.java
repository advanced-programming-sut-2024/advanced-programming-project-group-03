package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.GameMaster;

public class Game {
    private static Game game = new Game();
    private Player currentPlayer;
    private GameBoard gameBoard;
    private Game() {
    }
    public void startGame() {
        //set a gameboard
        gameBoard = new GameBoard(new Player(GameMaster.getGameMaster().getLoggedInUser1()) , new Player(GameMaster.getGameMaster().getGuestUser2()) , new Board() , new Board());

        // Set players
        currentPlayer = gameBoard.getPlayer1();

        // give cards to players
        gameBoard.getPlayer1().addCardsToDeck(PreGame.getPreGame().getDeckCards());
        gameBoard.getPlayer2().addCardsToDeck(PreGame.getPreGame().getDeckCards());//TODO change this to a different deck

        //give 10 random cards to each player
        for (int i = 0; i < 10; i++) {
            gameBoard.getPlayer1().drawCard();
            gameBoard.getPlayer2().drawCard();
        }

        //add hand cards of player1 to view
        int cardInRowCount = 0;
        for (int i = 0; i < gameBoard.getPlayer1().getHand().size(); i++) {
            gameBoard.getPlayer1().getHand().get(i).getSprite().setSize(150, 300);
            GameUIController.getGameUIController().getPlayerHandTable().add(gameBoard.getPlayer1().getHand().get(i).clone2());
            cardInRowCount++;
            if (cardInRowCount == 5) {
                GameUIController.getGameUIController().getPlayerHandTable().row();
                cardInRowCount = 0;
            }
        }

        //set leaders and factions
        //TODO

    }

    public void endTurn() {
        //TODO
    }

    public void playCard(Card card) {
        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            if (currentPlayer.getHand().get(i).getName().equals(card.getName())) {
                currentPlayer.getHand().remove(i);
                break;
            }
        }

        GameUIController.getGameUIController().getPlayerHandTable().clearChildren();
        int cardInRowCount = 0;
        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            currentPlayer.getHand().get(i).getSprite().setSize(150, 300);
            GameUIController.getGameUIController().getPlayerHandTable().add(currentPlayer.getHand().get(i).clone2());
            cardInRowCount++;
            if (cardInRowCount == 5) {
                GameUIController.getGameUIController().getPlayerHandTable().row();
                cardInRowCount = 0;
            }
        }
    }

    //getters and setters

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Game.game = game;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }


}
