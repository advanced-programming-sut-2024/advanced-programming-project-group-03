package ir.ap.probending.Model.Game;

import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.GameMaster;

import java.util.ArrayList;
import java.util.Objects;

public class Game {
    private static Game game = new Game();
    private Player currentPlayer;
    private GameBoard gameBoard;
    private int currentSet = 1;
    private boolean isCardPlayedThisRound = false;
    private int vetoCount = 0;
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

        //veto cards
        GameUIController.getGameUIController().activateCardListWindow();
        ArrayList<Card> vetoCards = new ArrayList<>();
        for (Card card : gameBoard.getPlayer1().getHand()) {
            vetoCards.add(card.clone5());
        }
        GameUIController.getGameUIController().addCardsToCardListWindow(vetoCards);

        //add hand cards of player1 to view
        setUpHandView(gameBoard.getPlayer1());

        //set leaders and factions
        gameBoard.getPlayer1Board().setLeader(PreGame.getPreGame().getSelectedLeader().clone6());
        gameBoard.getPlayer1Board().setFaction(PreGame.getPreGame().getPlayerFaction());
        gameBoard.getPlayer2Board().setLeader(PreGame.getPreGame().getSelectedLeader().clone6());//TODO change this to a random leader
        gameBoard.getPlayer2Board().setFaction(PreGame.getPreGame().getPlayerFaction());//TODO

        GameUIController.getGameUIController().addLeadersToLeaderTable1(gameBoard.getPlayer1Board().getLeader());
        GameUIController.getGameUIController().addLeadersToLeaderTable2(gameBoard.getPlayer2Board().getLeader());

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
        GameUIController.getGameUIController().updateRows();

        //check if both players have passed this set
        if (gameBoard.getPlayer1().isPassedThisRound() && gameBoard.getPlayer2().isPassedThisRound()) {
            //TODO end set

            if (decideWinner() != null){
                GameUIController.getGameUIController().showSetEndDialog(Objects.requireNonNull(decideWinner()).getUser().getUsername() + " won this set");
                Objects.requireNonNull(decideWinner()).setSetsWon(Objects.requireNonNull(decideWinner()).getSetsWon() + 1);
            }
            else{
                GameUIController.getGameUIController().showSetEndDialog("Draw");
                gameBoard.getPlayer1().setSetsWon(gameBoard.getPlayer1().getSetsWon() + 1);
                gameBoard.getPlayer2().setSetsWon(gameBoard.getPlayer2().getSetsWon() + 1);
            }
        }
    }

    public void startNewSet() {
        depositCardToBurntCards();
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
        updatePowerLabelsNumbers();
        updateSetWonLabels();
        Game.getGame().getGameBoard().getPlayer1Board().setCommander7Played(false);
        Game.getGame().getGameBoard().getPlayer1Board().setCommander8Played(false);
        Game.getGame().getGameBoard().getPlayer1Board().setCommander9Played(false);
    }

    public void playCard(Card card , int row) {
        isCardPlayedThisRound = true;

        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            if (currentPlayer.getHand().get(i).getName().equals(card.getName())) {
                currentPlayer.getHand().remove(i);
                break;
            }
        }

        Card newCard = card.clone4();

        switch (row){
            case 0:
                if (currentPlayer.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().addCardToSiege(newCard);
                else
                    gameBoard.getPlayer2Board().addCardToSiege(newCard);
                break;
            case 1:
                if (currentPlayer.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().addCardToRanged(newCard);
                else
                    gameBoard.getPlayer2Board().addCardToRanged(newCard);
                break;
            case 2:
                if (currentPlayer.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().addCardToCloseCombat(newCard);
                else
                    gameBoard.getPlayer2Board().addCardToCloseCombat(newCard);
                break;
            case 6:
                Game.getGame().getGameBoard().addSpellCard(newCard);
                break;
            case 7:
                if (currentPlayer.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().setCommander7(newCard);
                break;
            case 8:
                if (currentPlayer.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().setCommander8(newCard);
                break;
           case 9:
                if (currentPlayer.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().setCommander9(newCard);
                break;
           case 10:
                if (currentPlayer.equals(gameBoard.getPlayer2()))
                    gameBoard.getPlayer2Board().setCommander9(newCard);
                break;
           case 11:
                if (currentPlayer.equals(gameBoard.getPlayer2()))
                    gameBoard.getPlayer2Board().setCommander8(newCard);
                break;
           case 12:
                if (currentPlayer.equals(gameBoard.getPlayer2()))
                    gameBoard.getPlayer2Board().setCommander7(newCard);
                break;
        }

        if (card.getAbility() != null){
            card.getAbility().executeAbility(newCard);
        }

        updatePowerLabelsNumbers();
        GameUIController.getGameUIController().updateRows();
        endTurn();

    }

    public void playCard(Card card , Player player) {
        isCardPlayedThisRound = true;

        switch (card.getPlayingRow()){
            case 0:
                if (player.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().addCardToSiege(card);
                else
                    gameBoard.getPlayer2Board().addCardToSiege(card);
                break;
            case 1:
                if (player.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().addCardToRanged(card);
                else
                    gameBoard.getPlayer2Board().addCardToRanged(card);
                break;
            case 2:
                if (player.equals(gameBoard.getPlayer1()))
                    gameBoard.getPlayer1Board().addCardToCloseCombat(card);
                else
                    gameBoard.getPlayer2Board().addCardToCloseCombat(card);
                break;
            case 6:
                Game.getGame().getGameBoard().addSpellCard(card);
                break;
        }

        if (card.getAbility() != null){
            card.getAbility().executeAbility(card);
        }

        updatePowerLabelsNumbers();
        GameUIController.getGameUIController().updateRows();
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

    private void updatePowerLabelsNumbers(){
        GameUIController.getGameUIController().setPlayer1CloseCombatPowerSum(gameBoard.getPlayer1Board().getCloseCombatPowerSum());
        GameUIController.getGameUIController().setPlayer1RangedPowerSum(gameBoard.getPlayer1Board().getRangedPowerSum());
        GameUIController.getGameUIController().setPlayer1SiegePowerSum(gameBoard.getPlayer1Board().getSiegePowerSum());
        GameUIController.getGameUIController().setPlayer2CloseCombatPowerSum(gameBoard.getPlayer2Board().getCloseCombatPowerSum());
        GameUIController.getGameUIController().setPlayer2RangedPowerSum(gameBoard.getPlayer2Board().getRangedPowerSum());
        GameUIController.getGameUIController().setPlayer2SiegePowerSum(gameBoard.getPlayer2Board().getSiegePowerSum());
        GameUIController.getGameUIController().setPlayer1TotalPowerSum(gameBoard.getPlayer1Board().getTotalPower());
        GameUIController.getGameUIController().setPlayer2TotalPowerSum(gameBoard.getPlayer2Board().getTotalPower());
    }

    private void updateSetWonLabels(){
        GameUIController.getGameUIController().setPlayer1SetWon(gameBoard.getPlayer1().getSetsWon());
        GameUIController.getGameUIController().setPlayer2SetWon(gameBoard.getPlayer2().getSetsWon());
    }

    private void depositCardToBurntCards(){
        for (Card card : gameBoard.getPlayer1Board().getSiege()) {
            gameBoard.getPlayer1().addCardToBurntCards(card);
        }
        for (Card card : gameBoard.getPlayer1Board().getRanged()) {
            gameBoard.getPlayer1().addCardToBurntCards(card);
        }
        for (Card card : gameBoard.getPlayer1Board().getCloseCombat()) {
            gameBoard.getPlayer1().addCardToBurntCards(card);
        }
        for (Card card : gameBoard.getPlayer2Board().getSiege()) {
            gameBoard.getPlayer2().addCardToBurntCards(card);
        }
        for (Card card : gameBoard.getPlayer2Board().getRanged()) {
            gameBoard.getPlayer2().addCardToBurntCards(card);
        }
        for (Card card : gameBoard.getPlayer2Board().getCloseCombat()) {
            gameBoard.getPlayer2().addCardToBurntCards(card);
        }
    }

    public Player getOtherPlayer(){
        if (currentPlayer.equals(gameBoard.getPlayer1()))
            return gameBoard.getPlayer2();
        else
            return gameBoard.getPlayer1();
    }

    //views
    private void setupViewsThatAreDependentToGameBoard() {
        GameUIController.getGameUIController().addUsernameLabels();
    }
    public void setUpHandView(Player player){
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
        gameBoard.getSpellCards().clear();
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

    public int getVetoCount() {
        return vetoCount;
    }

    public void setVetoCount(int vetoCount) {
        this.vetoCount = vetoCount;
    }
}
