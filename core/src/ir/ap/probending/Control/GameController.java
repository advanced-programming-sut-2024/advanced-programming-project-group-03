package ir.ap.probending.Control;

import ir.ap.probending.Model.Game.Board;
import ir.ap.probending.Model.Game.GameBoard;
import ir.ap.probending.Model.Game.Player;
import ir.ap.probending.View.GameUIController;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Card.CardObjects;
import ir.ap.probending.Model.Data.GameHistory;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Data.SaveUser;
import ir.ap.probending.Model.Factions.Faction;
import ir.ap.probending.Model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class GameController {
    private static GameController gameController = new GameController();
    private Player currentPlayer;
    private GameBoard gameBoard;
    private int currentSet = 1;
    private boolean isCardPlayedThisRound = false;
    private int vetoCount = 0;
    private boolean isRestoreCardRandomlyActivated = false;
    private boolean isLoseHalfInBadWeatherActivated = false;
    private Player summonAvengerPlayer = null;
    private GameHistory gameHistory;
    private GameHistory gameHistory2;
    private GameController() {
    }
    public void startGame() {
        //set a gameboard
        gameBoard = new GameBoard(new Player(GameMaster.getGameMaster().getLoggedInUser1()) , new Player(GameMaster.getGameMaster().getLoggedInUser2()) , new Board() , new Board());
        resetGameSettings();
        GameController.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Earth" , new ArrayList<>() , new ArrayList<>()));
        GameController.getGame().getGameBoard().getPlayer2Board().setFaction(new Faction("Earth" , new ArrayList<>() , new ArrayList<>()));


        // give cards to players
        gameBoard.getPlayer1().addCardsToDeck(PreGameController.getPreGame().getDeckCards());


        //give 10 random cards to each player
        for (int i = 0; i < 10; i++) {
            gameBoard.getPlayer1().drawCard();
        }


        // Set players
        currentPlayer = decideFirstTurn();


        //setup new game history
        Date date = new Date();
        String dateString = date.toString();
        gameHistory = new GameHistory(gameBoard.getPlayer2().getUser().getUsername() , dateString , "" , "0" , "0");

        gameHistory2 = new GameHistory(gameBoard.getPlayer1().getUser().getUsername() , dateString , "" , "0" , "0");
    }

    public void startGameWithoutScreen(){
        //set a gameboard
        gameBoard = new GameBoard(new Player(GameMaster.getGameMaster().getLoggedInUser1()) , new Player(GameMaster.getGameMaster().getLoggedInUser2()) , new Board() , new Board());
        resetGameSettings();

        // give cards to players
        gameBoard.getPlayer1().addCardsToDeck(PreGameController.getPreGame().getDeckCards());
        gameBoard.getPlayer1Board().setFaction(new Faction("Water" , new ArrayList<>() , new ArrayList<>()));
        gameBoard.getPlayer2Board().setFaction( new Faction("Fire", new ArrayList<>(), new ArrayList<>()));

        //give 10 random cards to each player
        for (int i = 0; i < 10; i++) {
            gameBoard.getPlayer1().drawCard();
            gameBoard.getPlayer2().drawCard();
        }

        currentPlayer = gameBoard.getPlayer1();

        //setup new game history
        Date date = new Date();
        String dateString = date.toString();
        gameHistory = new GameHistory(gameBoard.getPlayer2().getUser().getUsername() , dateString , "" , "0" , "0");

        gameHistory2 = new GameHistory(gameBoard.getPlayer1().getUser().getUsername() , dateString , "" , "0" , "0");
    }

    public void endTurn(Boolean viewUpdate) {
        if (currentPlayer.equals(gameBoard.getPlayer1()) && !currentPlayer.isPassedThisRound()) {
            if (!isCardPlayedThisRound) {
                currentPlayer.setPassedThisRound(true);
                if (viewUpdate)
                    GameUIController.getGameUIController().showPassForPlayer1();
            }
            else {
                currentPlayer.setPassedThisRound(false);
                isCardPlayedThisRound = false;
            }

            if (!gameBoard.getPlayer2().isPassedThisRound()){
                currentPlayer = gameBoard.getPlayer2();
                if (viewUpdate)
                    GameUIController.getGameUIController().setUpHandView(gameBoard.getPlayer2());
            }
            else {
                currentPlayer = gameBoard.getPlayer1();
                if (viewUpdate)
                    GameUIController.getGameUIController().setUpHandView(gameBoard.getPlayer1());
            }
        }
        else if (currentPlayer.equals(gameBoard.getPlayer2()) && !currentPlayer.isPassedThisRound()){
            if (!isCardPlayedThisRound) {
                currentPlayer.setPassedThisRound(true);
                if (viewUpdate)
                    GameUIController.getGameUIController().showPassForPlayer2();
            }
            else {
                currentPlayer.setPassedThisRound(false);
                isCardPlayedThisRound = false;
            }

            if (!gameBoard.getPlayer1().isPassedThisRound()){
                currentPlayer = gameBoard.getPlayer1();
                if (viewUpdate)
                    GameUIController.getGameUIController().setUpHandView(gameBoard.getPlayer1());
            }
            else {
                currentPlayer = gameBoard.getPlayer2();
                if (viewUpdate)
                    GameUIController.getGameUIController().setUpHandView(gameBoard.getPlayer2());
            }
        }


        if (viewUpdate) {
            GameUIController.getGameUIController().setCurrentTurnPlayerUsername(currentPlayer.getUser().getUsername() + " 's turn");
            GameUIController.getGameUIController().updateRows();
            if (currentPlayer.isPlayedLeaderAbility()){
                GameUIController.getGameUIController().hideLeaderAbilityButton();
            }
            else {
                GameUIController.getGameUIController().showLeaderAbilityButton();
            }
        }

        //check if both players have passed this set
        if (gameBoard.getPlayer1().isPassedThisRound() && gameBoard.getPlayer2().isPassedThisRound()) {
            gameHistory.getMyScoresInRounds().add(String.valueOf(gameBoard.getPlayer1Board().getTotalPower()));
            gameHistory.getEnemyScoresInRounds().add(String.valueOf(gameBoard.getPlayer2Board().getTotalPower()));

            gameHistory2.getMyScoresInRounds().add(String.valueOf(gameBoard.getPlayer2Board().getTotalPower()));
            gameHistory2.getEnemyScoresInRounds().add(String.valueOf(gameBoard.getPlayer1Board().getTotalPower()));

            if (decideWinner() != null){
                if (viewUpdate)
                    GameUIController.getGameUIController().showSetEndDialog(Objects.requireNonNull(decideWinner()).getUser().getUsername() + " won this set");
                Objects.requireNonNull(decideWinner()).setSetsWon(Objects.requireNonNull(decideWinner()).getSetsWon() + 1);
                if (getPlayersFaction(Objects.requireNonNull(decideWinner())).getFactionName().equals("Water")){
                    Objects.requireNonNull(decideWinner()).drawCard();
                }
            }
            else{
                if (viewUpdate)
                    GameUIController.getGameUIController().showSetEndDialog("Draw");
                gameBoard.getPlayer1().setSetsWon(gameBoard.getPlayer1().getSetsWon() + 1);
                gameBoard.getPlayer2().setSetsWon(gameBoard.getPlayer2().getSetsWon() + 1);
            }
        }
    }

    public void startNewSet(boolean viewUpdate) {
        declareWinner();
        currentSet++;
        gameBoard.getPlayer1().setPassedThisRound(false);
        gameBoard.getPlayer2().setPassedThisRound(false);
        gameBoard.setSpyDoublePowerActivated(false);
        isCardPlayedThisRound = false;
        if (viewUpdate){
            depositCardToBurntCards(true);
            GameUIController.getGameUIController().setClickedCard(null);
            GameUIController.getGameUIController().hidePassForPlayer1();
            GameUIController.getGameUIController().hidePassForPlayer2();
        }
        else {
            depositCardToBurntCards(false);
        }
        if (gameBoard.getPlayer1().getSetsWon() > gameBoard.getPlayer2().getSetsWon()){
            currentPlayer = gameBoard.getPlayer2();
        }
        else {
            currentPlayer = gameBoard.getPlayer1();
        }

        if (viewUpdate){
            GameUIController.getGameUIController().setUpHandView(currentPlayer);
            GameUIController.getGameUIController().setCurrentTurnPlayerUsername(currentPlayer.getUser().getUsername() + " 's turn");
            updatePowerLabelsNumbers();
            updateSetWonLabels();
        }
        gameBoard.getPlayer1Board().setCommander7Played(false);
        gameBoard.getPlayer1Board().setCommander8Played(false);
        gameBoard.getPlayer1Board().setCommander9Played(false);
        isRestoreCardRandomlyActivated = false;
        isLoseHalfInBadWeatherActivated = false;

        if (viewUpdate){
            if (currentPlayer.isPlayedLeaderAbility()){
                GameUIController.getGameUIController().hideLeaderAbilityButton();
            }
            else {
                GameUIController.getGameUIController().showLeaderAbilityButton();
            }
            GameUIController.getGameUIController().updateRows();

            if(summonAvengerPlayer != null) {
                playCard(CardObjects.getEvilDruk(), summonAvengerPlayer);
            }
        }
    }

    public void playCard(Card card , int row , boolean viewUpdate) {
        isCardPlayedThisRound = true;

        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
            if (currentPlayer.getHand().get(i).getName().equals(card.getName())) {
                currentPlayer.getHand().remove(i);
                break;
            }
        }
        Card newCard = null;
        if (viewUpdate){
            newCard = card.clone4();
        }
        else {
            newCard = card;
        }

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
                GameController.getGame().getGameBoard().addSpellCard(newCard);
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
            try {
                card.getAbility().executeAbility(newCard);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        if (viewUpdate){
            updatePowerLabelsNumbers();
            GameUIController.getGameUIController().updateRows();
            endTurn(true);
        }
        else {
            endTurn(false);
        }

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
                gameBoard.addSpellCard(card);
                break;
        }

        if (card.getAbility() != null){
            card.getAbility().executeAbility(card);
        }

        updatePowerLabelsNumbers();
        GameUIController.getGameUIController().updateRows();
    }

    public int getCurrentTurn(){
        if (currentPlayer.equals(gameBoard.getPlayer1()))
            return 1;
        else
            return 2;
    }

    public Player decideWinner() {
        if (gameBoard.getPlayer1Board().getTotalPower() > gameBoard.getPlayer2Board().getTotalPower())
            return gameBoard.getPlayer1();
        else if (gameBoard.getPlayer1Board().getTotalPower() < gameBoard.getPlayer2Board().getTotalPower())
            return gameBoard.getPlayer2();
        else if (getPlayersFaction(gameBoard.getPlayer1()).getFactionName().equals("Air") && getPlayersFaction(gameBoard.getPlayer2()).getFactionName().equals("Air"))
            return null;
        else if (getPlayersFaction(gameBoard.getPlayer1()).getFactionName().equals("Air"))
            return gameBoard.getPlayer1();
        else if (getPlayersFaction(gameBoard.getPlayer2()).getFactionName().equals("Air"))
            return gameBoard.getPlayer2();
        else
            return null;
    }

    public Player decideFirstTurn(){
        if (gameBoard.getPlayer1Board().getFaction().getFactionName().equals("Earth") && gameBoard.getPlayer2Board().getFaction().getFactionName().equals("Earth"))
            return gameBoard.getPlayer1();
        else if (gameBoard.getPlayer1Board().getFaction().getFactionName().equals("Earth"))
            return gameBoard.getPlayer1();
        else if (gameBoard.getPlayer2Board().getFaction().getFactionName().equals("Earth"))
            return gameBoard.getPlayer2();
        else
            return gameBoard.getPlayer1();
    }

    public void updatePowerLabelsNumbers(){
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

    public void depositCardToBurntCards(boolean updateView){
        Card player1KeepCard1 = null;
        Card player1KeepCard2 = null;
        Card player2KeepCard1 = null;
        Card player2KeepCard2 = null;

        ArrayList<Card> burntCards1 = new ArrayList<>();
        for (Card card : gameBoard.getPlayer1Board().getSiege()) {
            burntCards1.add(card);
        }
        for (Card card : gameBoard.getPlayer1Board().getRanged()) {
            burntCards1.add(card);
        }
        for (Card card : gameBoard.getPlayer1Board().getCloseCombat()) {
            burntCards1.add(card);
        }
        if (gameBoard.getPlayer1Board().getFaction().getFactionName().equals("Fire") && burntCards1.size() > 0){
            int random = new Random().nextInt(burntCards1.size());
            gameBoard.getPlayer1().addCardToHand(burntCards1.get(random));
            burntCards1.remove(random);
        }
        if (getPlayersFaction(gameBoard.getPlayer1()).getFactionName().equals("Air") && burntCards1.size() > 1 && currentSet == 3){
            int random = new Random().nextInt(burntCards1.size());
            player1KeepCard1 = burntCards1.get(random);
            burntCards1.remove(random);
            random = new Random().nextInt(burntCards1.size());
            player1KeepCard2 = burntCards1.get(random);
            burntCards1.remove(random);
        }
        for (Card card : burntCards1) {
            gameBoard.getPlayer1().addCardToBurntCards(card);
        }

        ArrayList<Card> burntCards2 = new ArrayList<>();
        for (Card card : gameBoard.getPlayer2Board().getSiege()) {
            burntCards2.add(card);
        }
        for (Card card : gameBoard.getPlayer2Board().getRanged()) {
            burntCards2.add(card);
        }
        for (Card card : gameBoard.getPlayer2Board().getCloseCombat()) {
            burntCards2.add(card);
        }
        if (getPlayersFaction(gameBoard.getPlayer2()).getFactionName().equals("Fire") && burntCards2.size() > 0){
            int random = new Random().nextInt(burntCards2.size());
            gameBoard.getPlayer2().addCardToHand(burntCards2.get(random));
            burntCards2.remove(random);
        }
        if (getPlayersFaction(gameBoard.getPlayer2()).getFactionName().equals("Air") && burntCards2.size() > 1 && currentSet == 3){
            int random = new Random().nextInt(burntCards2.size());
            player2KeepCard1 = burntCards2.get(random);
            burntCards2.remove(random);
            random = new Random().nextInt(burntCards2.size());
            player2KeepCard2 = burntCards2.get(random);
            burntCards2.remove(random);
        }
        for (Card card : burntCards2) {
            gameBoard.getPlayer2().addCardToBurntCards(card);
        }
       if (updateView){
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
           if (player1KeepCard1 != null){
               playCard(player1KeepCard1 , gameBoard.getPlayer1());
           }
           if (player1KeepCard2 != null){
               playCard(player1KeepCard2 , gameBoard.getPlayer1());
           }
           if (player2KeepCard1 != null){
               playCard(player2KeepCard1 , gameBoard.getPlayer2());
           }
           if (player2KeepCard2 != null){
               playCard(player2KeepCard2 , gameBoard.getPlayer2());
           }
       }
    }

    public Player getOtherPlayer(){
        if (currentPlayer.equals(gameBoard.getPlayer1()))
            return gameBoard.getPlayer2();
        else
            return gameBoard.getPlayer1();
    }

    private void declareWinner(){

        User user1 = GameMaster.getGameMaster().getLoggedInUser1();
        User user2 = GameMaster.getGameMaster().getLoggedInUser2();

        if (gameBoard.getPlayer1().getSetsWon() > gameBoard.getPlayer2().getSetsWon() && gameBoard.getPlayer1().getSetsWon() == 2){
            GameUIController.getGameUIController().showGameEndDialog(gameBoard.getPlayer1().getUser().getUsername() + " won the game");
            gameHistory.setWinner(gameBoard.getPlayer1().getUser().getUsername());
            user1.setGameWonCount(user1.getGameWonCount() + 1);

            gameHistory.setMyFinalScore(String.valueOf(gameBoard.getPlayer1().getSetsWon()));
            gameHistory.setEnemyFinalScore(String.valueOf(gameBoard.getPlayer2().getSetsWon()));

            user1.getGameHistories().add(gameHistory);
            user1.setGamePlayedCount(user1.getGamePlayedCount() + 1);
            user1.setScore(user1.getScore() + new Random().nextInt(100) + 1);
            SaveUser.updateUser(user1);

            gameHistory2.setWinner(gameBoard.getPlayer1().getUser().getUsername());
            user2.setGameLostCount(user2.getGameLostCount() + 1);

            gameHistory2.setMyFinalScore(String.valueOf(gameBoard.getPlayer2().getSetsWon()));
            gameHistory2.setEnemyFinalScore(String.valueOf(gameBoard.getPlayer1().getSetsWon()));

            user2.getGameHistories().add(gameHistory2);
            user2.setGamePlayedCount(user2.getGamePlayedCount() + 1);
            user2.setScore(user2.getScore() + new Random().nextInt(100) + 1);
            SaveUser.updateUser(user2);
        }
        else if (gameBoard.getPlayer1().getSetsWon() < gameBoard.getPlayer2().getSetsWon() && gameBoard.getPlayer2().getSetsWon() == 2){
            GameUIController.getGameUIController().showGameEndDialog(gameBoard.getPlayer2().getUser().getUsername() + " won the game");
            gameHistory.setWinner(gameBoard.getPlayer2().getUser().getUsername());
            user1.setGameLostCount(user1.getGameLostCount() + 1);


            gameHistory.setMyFinalScore(String.valueOf(gameBoard.getPlayer1().getSetsWon()));
            gameHistory.setEnemyFinalScore(String.valueOf(gameBoard.getPlayer2().getSetsWon()));

            user1.getGameHistories().add(gameHistory);
            user1.setGamePlayedCount(user1.getGamePlayedCount() + 1);
            user1.setScore(user1.getScore() + new Random().nextInt(100) + 1);
            SaveUser.updateUser(user1);

            gameHistory2.setWinner(gameBoard.getPlayer2().getUser().getUsername());
            user2.setGameWonCount(user2.getGameWonCount() + 1);

            gameHistory2.setMyFinalScore(String.valueOf(gameBoard.getPlayer2().getSetsWon()));
            gameHistory2.setEnemyFinalScore(String.valueOf(gameBoard.getPlayer1().getSetsWon()));

            user2.getGameHistories().add(gameHistory2);
            user2.setGamePlayedCount(user2.getGamePlayedCount() + 1);
            user2.setScore(user2.getScore() + new Random().nextInt(100) + 1);
            SaveUser.updateUser(user2);
        }
        else if (gameBoard.getPlayer1().getSetsWon() == 2 && gameBoard.getPlayer2().getSetsWon() == 2){
            GameUIController.getGameUIController().showGameEndDialog("Draw");
            gameHistory.setWinner("Draw");


            gameHistory.setMyFinalScore(String.valueOf(gameBoard.getPlayer1().getSetsWon()));
            gameHistory.setEnemyFinalScore(String.valueOf(gameBoard.getPlayer2().getSetsWon()));

            user1.getGameHistories().add(gameHistory);
            user1.setGamePlayedCount(user1.getGamePlayedCount() + 1);
            user1.setScore(user1.getScore() + new Random().nextInt(100) + 1);
            SaveUser.updateUser(user1);

            gameHistory2.setWinner("Draw");

            gameHistory2.setMyFinalScore(String.valueOf(gameBoard.getPlayer2().getSetsWon()));
            gameHistory2.setEnemyFinalScore(String.valueOf(gameBoard.getPlayer1().getSetsWon()));

            user2.getGameHistories().add(gameHistory2);
            user2.setGamePlayedCount(user2.getGamePlayedCount() + 1);
            user2.setScore(user2.getScore() + new Random().nextInt(100) + 1);
            SaveUser.updateUser(user2);
        }
    }

    public void resetGameSettings(){
        gameBoard.getPlayer1().getHand().clear();
        gameBoard.getPlayer1().getDeck().clear();
        gameBoard.getPlayer1().getBurntCards().clear();
        gameBoard.getPlayer1().setSetsWon(0);
        gameBoard.getPlayer1().setPassedThisRound(false);
        gameBoard.getPlayer1().setPlayedLeaderAbility(false);
        gameBoard.getPlayer2().getHand().clear();
        gameBoard.getPlayer2().getDeck().clear();
        gameBoard.getPlayer2().getBurntCards().clear();
        gameBoard.getPlayer2().setSetsWon(0);
        gameBoard.getPlayer2().setPassedThisRound(false);
        gameBoard.getPlayer2().setPlayedLeaderAbility(false);
        gameBoard.getPlayer1Board().clearBoard();
        gameBoard.getPlayer2Board().clearBoard();
        gameBoard.getSpellCards().clear();
        currentSet = 1;
        isCardPlayedThisRound = false;
        vetoCount = 0;
        isRestoreCardRandomlyActivated = false;
        isLoseHalfInBadWeatherActivated = false;
    }

    public Faction getPlayersFaction(Player player){
        if (player.equals(gameBoard.getPlayer1())){
            return gameBoard.getPlayer1Board().getFaction();
        }
        else {
            return gameBoard.getPlayer2Board().getFaction();
        }
    }
    //getters and setters

    public static GameController getGame() {
        return gameController;
    }

    public static void setGame(GameController gameController) {
        GameController.gameController = gameController;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
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

    public boolean isRestoreCardRandomlyActivated() {
        return isRestoreCardRandomlyActivated;
    }

    public void setRestoreCardRandomlyActivated(boolean restoreCardRandomlyActivated) {
        isRestoreCardRandomlyActivated = restoreCardRandomlyActivated;
    }

    public boolean isLoseHalfInBadWeatherActivated() {
        return isLoseHalfInBadWeatherActivated;
    }

    public void setLoseHalfInBadWeatherActivated(boolean loseHalfInBadWeatherActivated) {
        isLoseHalfInBadWeatherActivated = loseHalfInBadWeatherActivated;
    }

    public Player getSummonAvengerPlayer() {
        return summonAvengerPlayer;
    }

    public void setSummonAvengerPlayer(Player summonAvengerPlayer) {
        this.summonAvengerPlayer = summonAvengerPlayer;
    }


    public int getCurrentSet() {
        return currentSet;
    }

    public void setCurrentSet(int i) {
        currentSet = i;
    }
}
