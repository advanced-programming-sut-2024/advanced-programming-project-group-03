package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.GameMaster;

import java.util.Objects;

public class Game {
    private static Game game = new Game();
    private Player currentPlayer;
    private GameBoard gameBoard;
    private int currentSet = 1;
    private boolean isCardPlayedThisRound = false;
    private Game() {
    }
    public void startGame() {
        //set a gameboard
        gameBoard = new GameBoard(new Player(GameMaster.getGameMaster().getLoggedInUser1()) , new Player(GameMaster.getGameMaster().getGuestUser2()) , new Board() , new Board());

        // Set players
        currentPlayer = gameBoard.getPlayer1();
        GameUIController.getGameUIController().setCurrentTurnPlayerUsername(currentPlayer.getUser().getUsername() + " 's turn");

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

        GameUIController.getGameUIController().setCurrentTurnPlayerUsername(currentPlayer.getUser().getUsername() + " 's turn");

        //check if both players have passed this set
        if (gameBoard.getPlayer1().isPassedThisRound() && gameBoard.getPlayer2().isPassedThisRound()) {
            //TODO end set

            if (decideWinner() != null)
                GameUIController.getGameUIController().showSetEndDialog(Objects.requireNonNull(decideWinner()).getUser().getUsername() + " won this set");
            else
                GameUIController.getGameUIController().showSetEndDialog("Draw");
        }
    }

    public void startNewSet() {
        currentSet++;
        GameUIController.getGameUIController().setClickedCard(null);
        clearBoard();
        gameBoard.getPlayer1().setPassedThisRound(false);
        gameBoard.getPlayer2().setPassedThisRound(false);
        isCardPlayedThisRound = false;
        setUpHandView(gameBoard.getPlayer1());
        GameUIController.getGameUIController().hidePassForPlayer1();
        GameUIController.getGameUIController().hidePassForPlayer2();
        if (currentSet % 2 == 0){
            currentPlayer = gameBoard.getPlayer2();
        }
        else {
            currentPlayer = gameBoard.getPlayer1();
        }
        setUpHandView(currentPlayer);
        GameUIController.getGameUIController().setCurrentTurnPlayerUsername(currentPlayer.getUser().getUsername() + " 's turn");
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
        if (gameBoard.getPlayer1Board().getTotalPower() > gameBoard.getPlayer2Board().getTotalPower())
            return gameBoard.getPlayer1();
        else if (gameBoard.getPlayer1Board().getTotalPower() < gameBoard.getPlayer2Board().getTotalPower())
            return gameBoard.getPlayer2();
        else
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

    public void clearBoard() {
        gameBoard.getPlayer1Board().clearBoard();
        gameBoard.getPlayer2Board().clearBoard();
        GameUIController.getGameUIController().getRow0Table().clearChildren();
        GameUIController.getGameUIController().getRow1Table().clearChildren();
        GameUIController.getGameUIController().getRow2Table().clearChildren();
        GameUIController.getGameUIController().getRow3Table().clearChildren();
        GameUIController.getGameUIController().getRow4Table().clearChildren();
        GameUIController.getGameUIController().getRow5Table().clearChildren();
        GameUIController.getGameUIController().getSpellRowTable().clearChildren();
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
