package Server;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Game.Board;
import ir.ap.probending.Model.Game.GameBoard;
import ir.ap.probending.Model.Game.Player;
import ir.ap.probending.Model.Game.PreGame;
import ir.ap.probending.Model.User;

import java.net.Socket;
import java.util.ArrayList;

public class GameInfo {
    private static ArrayList<GameInfo> gameInfos = new ArrayList<>();
    private User firstUser = null;
    private User secondUser = null;
    private Socket firstPlayerSocket = null;
    private Socket secondPlayerSocket = null;
    private PreGame firstPlayerPreGame = null;
    private PreGame secondPlayerPreGame = null;
    private GameBoard gameBoard = null;
    private boolean isGameStarted = false;

    public GameInfo(User firstUser, User secondUser, Socket firstPlayerSocket, Socket secondPlayerSocket) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.firstPlayerSocket = firstPlayerSocket;
        this.secondPlayerSocket = secondPlayerSocket;
        gameInfos.add(this);
    }

    public User getFirstPlayer() {
        return firstUser;
    }

    public void setFirstPlayer(User firstPlayer) {
        this.firstUser = firstPlayer;
    }

    public User getSecondPlayer() {
        return secondUser;
    }

    public void setSecondPlayer(User secondPlayer) {
        this.secondUser = secondPlayer;
    }

    public Socket getFirstPlayerSocket() {
        return firstPlayerSocket;
    }

    public void setFirstPlayerSocket(Socket firstPlayerSocket) {
        this.firstPlayerSocket = firstPlayerSocket;
    }

    public Socket getSecondPlayerSocket() {
        return secondPlayerSocket;
    }

    public void setSecondPlayerSocket(Socket secondPlayerSocket) {
        this.secondPlayerSocket = secondPlayerSocket;
    }

    public static ArrayList<GameInfo> getGameInfos() {
        return gameInfos;
    }

    public static GameInfo getGameInfo(Socket socket) {
        for (GameInfo gameInfo : gameInfos) {
            if (gameInfo.getFirstPlayerSocket() == socket || gameInfo.getSecondPlayerSocket() == socket) {
                return gameInfo;
            }
        }
        return null;
    }

    public PreGame getFirstPlayerPreGame() {
        return firstPlayerPreGame;
    }

    public void setFirstPlayerPreGame(PreGame firstPlayerPreGame) {
        this.firstPlayerPreGame = firstPlayerPreGame;
    }

    public PreGame getSecondPlayerPreGame() {
        return secondPlayerPreGame;
    }

    public void setSecondPlayerPreGame(PreGame secondPlayerPreGame) {
        this.secondPlayerPreGame = secondPlayerPreGame;
    }

    public void setupGameBoard() {
        //set a gameboard
        gameBoard = new GameBoard(new Player(firstUser) , new Player(secondUser) , new Board() , new Board());

        // give cards to players
        gameBoard.getPlayer1().addCardsToDeck(firstPlayerPreGame.getDeckCards());
        gameBoard.getPlayer2().addCardsToDeck(secondPlayerPreGame.getDeckCards());

        //give 10 random cards to each player
        for (int i = 0; i < 10; i++) {
            gameBoard.getPlayer1().drawCard();
            gameBoard.getPlayer2().drawCard();
        }

        //veto cards
//        GameUIController.getGameUIController().activateCardListWindow();
        ArrayList<Card> vetoCards = new ArrayList<>();
        for (Card card : gameBoard.getPlayer1().getHand()) {
            vetoCards.add(card.clone5());
        }
//        GameUIController.getGameUIController().addCardsToCardListWindow(vetoCards);

        //add hand cards of player1 to view
//        setUpHandView(gameBoard.getPlayer1());

        //set leaders and factions
        gameBoard.getPlayer1Board().setLeader(firstPlayerPreGame.getSelectedLeader().clone6());
        gameBoard.getPlayer1Board().setFaction(firstPlayerPreGame.getPlayerFaction());
        gameBoard.getPlayer2Board().setLeader(secondPlayerPreGame.getSelectedLeader().clone6());
        gameBoard.getPlayer2Board().setFaction(secondPlayerPreGame.getPlayerFaction());
        isGameStarted = true;

//        GameUIController.getGameUIController().addLeadersToLeaderTable1(gameBoard.getPlayer1Board().getLeader());
//        GameUIController.getGameUIController().addLeadersToLeaderTable2(gameBoard.getPlayer2Board().getLeader());
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }
}
