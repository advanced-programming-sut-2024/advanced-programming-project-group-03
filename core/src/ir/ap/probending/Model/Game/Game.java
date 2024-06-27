package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.GameMaster;

public class Game {
    private static Game game = new Game();
    private Player currentPlayer;
    private GameBoard gameBoard;
    private boolean isCardPlayedThisRound = false;
    private Game() {
    }
    public void startGame() {
        //set a gameboard
        gameBoard = new GameBoard(new Player(GameMaster.getGameMaster().getLoggedInUser1()) , new Player(GameMaster.getGameMaster().getGuestUser2()) , new Board() , new Board());

        // Set players
        currentPlayer = gameBoard.getPlayer1();

        // give cards to players
        gameBoard.getPlayer1().addCardsToDeck(PreGame.getPreGame().getDeckCards());
        selectRandomCardsAndFactionForPlayer2();

        //give 10 random cards to each player
        for (int i = 0; i < 10; i++) {
            gameBoard.getPlayer1().drawCard();
            gameBoard.getPlayer2().drawCard();
        }

        //add hand cards of player1 to view
        setUpHandView(gameBoard.getPlayer1());

        //set leaders and factions
        //TODO

        //setup views that are dependent to gameboard
        setupViewsThatAreDependentToGameBoard();
    }

    public void endTurn() {
        if (currentPlayer.equals(gameBoard.getPlayer1()) && !currentPlayer.isPassedThisRound()) {
            if (!isCardPlayedThisRound) {
                currentPlayer.setPassedThisRound(true);
                GameUIController.getGameUIController().showPassForPlayer1();
            }
            else {
                currentPlayer.setPassedThisRound(false);
                isCardPlayedThisRound = false;
            }

            currentPlayer = gameBoard.getPlayer2();
            setUpHandView(gameBoard.getPlayer2());
        }
        else if (currentPlayer.equals(gameBoard.getPlayer2()) && !currentPlayer.isPassedThisRound()){
            if (!isCardPlayedThisRound) {
                currentPlayer.setPassedThisRound(true);
                GameUIController.getGameUIController().showPassForPlayer2();
            }
            else {
                currentPlayer.setPassedThisRound(false);
                isCardPlayedThisRound = false;
            }

            currentPlayer = gameBoard.getPlayer1();
            setUpHandView(gameBoard.getPlayer1());
        }

        //check if both players have passed this set
        if (gameBoard.getPlayer1().isPassedThisRound() && gameBoard.getPlayer2().isPassedThisRound()) {
            //TODO end set

            if (gameBoard.getPlayer1Board().getCloseCombatPowerSum() > gameBoard.getPlayer2Board().getCloseCombatPowerSum())
                GameUIController.getGameUIController().showSetEndDialog(gameBoard.getPlayer1().getUser().getUsername() + " won this set");
            else if (gameBoard.getPlayer1Board().getCloseCombatPowerSum() < gameBoard.getPlayer2Board().getCloseCombatPowerSum())
                GameUIController.getGameUIController().showSetEndDialog(gameBoard.getPlayer2().getUser().getUsername() + " won this set");
            else
                GameUIController.getGameUIController().showSetEndDialog("Draw");


            //start new set
            gameBoard.getPlayer1().setPassedThisRound(false);
            gameBoard.getPlayer2().setPassedThisRound(false);
            GameUIController.getGameUIController().hidePassForPlayer1();
            GameUIController.getGameUIController().hidePassForPlayer2();
        }
    }

    public void playCard(Card card) {
        isCardPlayedThisRound = true;

        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            if (currentPlayer.getHand().get(i).getName().equals(card.getName())) {
                currentPlayer.getHand().remove(i);
                break;
            }
        }

        switch (card.getPlayingRow()){
            case 0:
                if (currentPlayer.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().addCardToSiege(card);
                else
                    gameBoard.getPlayer2Board().addCardToSiege(card);
                break;
            case 1:
                if (currentPlayer.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().addCardToRanged(card);
                else
                    gameBoard.getPlayer2Board().addCardToRanged(card);
                break;
            case 2:
                if (currentPlayer.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().addCardToCloseCombat(card);
                else
                    gameBoard.getPlayer2Board().addCardToCloseCombat(card);
                break;
            case 6:
                Game.getGame().getGameBoard().addSpellCard(card);
                break;
        }

        setUpHandView(currentPlayer);
    }

    private void selectRandomCardsAndFactionForPlayer2() {
        gameBoard.getPlayer2().addCardsToDeck(PreGame.getPreGame().getDeckCards());//TODO change this to a different deck
        //random cards from random faction and random leader for player2 TODO
    }

    public int getCurrentTurn(){
        if (currentPlayer.equals(gameBoard.getPlayer1()))
            return 1;
        else
            return 2;
    }

    private Player decideWinner() {
        //TODO
        return null;
    }

    //views
    private void setupViewsThatAreDependentToGameBoard() {
        GameUIController.getGameUIController().addUsernameLabels();
    }
    private void setUpHandView(Player player){
        //add hand cards of player1 to view
        GameUIController.getGameUIController().getPlayerHandTable().clearChildren();
        int cardInRowCount = 0;
        for (int i = 0; i < player.getHand().size(); i++) {
            player.getHand().get(i).getSprite().setSize(150, 300);
            GameUIController.getGameUIController().getPlayerHandTable().add(player.getHand().get(i).clone2());
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
