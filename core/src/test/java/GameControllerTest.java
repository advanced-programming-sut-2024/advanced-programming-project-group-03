

import ir.ap.probending.Control.GameController;
import ir.ap.probending.Control.MenuController;
import ir.ap.probending.Control.PreGameController;
import ir.ap.probending.Model.Card.Abilities.Morale;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.GameHistory;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Factions.Faction;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.Model.User;
import ir.ap.probending.ProBending;
import org.junit.*;
import org.junit.Assert;

import java.util.ArrayList;

public class GameControllerTest {
    @Test
    public void isGameInitialized() {
        Assert.assertNotNull(GameController.getGame());
    }

    @Test
    public void testStartGame() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        GameController.getGame().startGameWithoutScreen();
        GameController.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Earth" , new ArrayList<>() , new ArrayList<>()));
        GameController.getGame().getGameBoard().getPlayer2Board().setFaction(new Faction("Water" , new ArrayList<>() , new ArrayList<>()));
        GameController.getGame().startGame();
        Assert.assertNotNull(GameController.getGame().getGameBoard());
        Assert.assertNotNull(GameController.getGame().getGameBoard().getPlayer1());
        Assert.assertNotNull(GameController.getGame().getGameBoard().getPlayer2());
        Assert.assertNotNull(GameController.getGame().getGameBoard().getPlayer1Board());
        Assert.assertNotNull(GameController.getGame().getGameBoard().getPlayer2Board());
        Assert.assertEquals(GameController.getGame().getVetoCount() , 0);
    }

    @Test
    public void testGetCurrentTurn() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        GameController.getGame().startGameWithoutScreen();
        Assert.assertEquals(GameController.getGame().getCurrentTurn(), 1);
    }

    @Test
    public void testGetOtherPlayer() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        GameController.getGame().startGameWithoutScreen();
        Assert.assertEquals(GameController.getGame().getOtherPlayer(), GameController.getGame().getGameBoard().getPlayer2());
    }

    @Test
    public void testPlayCard() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        GameController.getGame().startGameWithoutScreen();
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        Assert.assertEquals(GameController.getGame().decideWinner() , GameController.getGame().getGameBoard().getPlayer1());
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1Board().getSiege().get(0).getName(), "test");
    }

    @Test
    public void testEndTurn() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        GameController.getGame().startGameWithoutScreen();
        GameController.getGame().endTurn(false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        Assert.assertEquals(GameController.getGame().getCurrentTurn(), 2);
        Assert.assertEquals(GameController.getGame().decideWinner() , GameController.getGame().getGameBoard().getPlayer2());
    }

    @Test
    public void testDepositIntoBurnt(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        GameController.getGame().startGameWithoutScreen();
        GameController.getGame().getGameBoard().getPlayer2Board().setFaction(GameController.getGame().getGameBoard().getPlayer1Board().getFaction());
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        GameController.getGame().endTurn(false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 1);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 1);

        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 1 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 1 , false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 2);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 4);

        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 2), 2 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 2), 2 , false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 3);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 9);

        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 1 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 1 , false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 4);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 16);

        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 7 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 7 , false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 5);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 23);

        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 8 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 8 , false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 6);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 30);

        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 2), 9 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 2), 9 , false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 7);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 37);

        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 6 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 6 , false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 8);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 44);

        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 10 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 10 , false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 9);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 51);

        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 11 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 11 , false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 10);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 58);

        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 12 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 12 , false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 11);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 65);
    }

    @Test
    public void testDepositIntoBurnt2(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        GameController.getGame().startGameWithoutScreen();
        GameController.getGame().getGameBoard().getPlayer2Board().setFaction(new Faction("Air" , new ArrayList<>() , new ArrayList<>()));
        GameController.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Air" , new ArrayList<>() , new ArrayList<>()));

        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 5 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 1 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 4 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 2 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 3 , false);
        GameController.getGame().endTurn(false);
        GameController.getGame().endTurn(false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 5 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 1 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 4 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 2 , false);
        GameController.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 3 , false);
        GameController.getGame().endTurn(false);
        GameController.getGame().endTurn(false);
        GameController.getGame().depositCardToBurntCards(false);
        GameController.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(GameController.getGame().getCurrentSet() , 1);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 6);
        Assert.assertEquals(GameController.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 6);

        GameController.getGame().startNewSet(false);
        GameController.getGame().startNewSet(false);

        GameController.getGame().getGameBoard().getPlayer2Board().setFaction(new Faction("Fire" , new ArrayList<>() , new ArrayList<>()));
        GameController.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Fire" , new ArrayList<>() , new ArrayList<>()));
        GameController.getGame().depositCardToBurntCards(false);
    }

    @Test
    public void testEndGame(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        GameController.getGame().startGameWithoutScreen();
        GameController.getGame().endTurn(false);
        GameController.getGame().endTurn(false);
        Assert.assertNotNull(GameController.getGame().getGameBoard());
        Assert.assertNotNull(GameController.getGame().getGameBoard().getPlayer1());
        Assert.assertNotNull(GameController.getGame().getGameBoard().getPlayer2());
        Assert.assertNotNull(GameController.getGame().getGameBoard().getPlayer1Board());
        Assert.assertNotNull(GameController.getGame().getGameBoard().getPlayer2Board());
        Assert.assertEquals(GameController.getGame().getVetoCount() , 0);
        Assert.assertEquals(GameController.getGame().getCurrentPlayer().getSetsWon() , 1);
        Assert.assertEquals(GameController.getGame().getOtherPlayer().getSetsWon() , 1);
    }

    @Test
    public void testDecideFirst(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        GameController.getGame().startGameWithoutScreen();
        Assert.assertNotNull(GameController.getGame().decideFirstTurn());

        GameController.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Earth" , new ArrayList<>() , new ArrayList<>()));
        Assert.assertEquals(GameController.getGame().decideFirstTurn() , GameController.getGame().getGameBoard().getPlayer1());


        GameController.getGame().getGameBoard().getPlayer2Board().setFaction(new Faction("Earth" , new ArrayList<>() , new ArrayList<>()));
        GameController.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Water" , new ArrayList<>() , new ArrayList<>()));
        Assert.assertEquals(GameController.getGame().decideFirstTurn() , GameController.getGame().getGameBoard().getPlayer2());

        GameController.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Earth" , new ArrayList<>() , new ArrayList<>()));
        Assert.assertEquals(GameController.getGame().decideFirstTurn() , GameController.getGame().getGameBoard().getPlayer1());
    }

    @Test
    public void testGettersAndSetters(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        GameController.getGame().startGameWithoutScreen();
        GameController.getGame().setVetoCount(10);
        Assert.assertEquals(GameController.getGame().getVetoCount() , 10);
        GameController.getGame().setLoseHalfInBadWeatherActivated(true);
        Assert.assertTrue(GameController.getGame().isLoseHalfInBadWeatherActivated());
        GameController.getGame().setRestoreCardRandomlyActivated(true);
        Assert.assertTrue(GameController.getGame().isRestoreCardRandomlyActivated());
        GameController.getGame().setGameBoard(null);
        Assert.assertNull(GameController.getGame().getGameBoard());
        GameController.getGame().setCurrentSet(10);
        Assert.assertEquals(GameController.getGame().getCurrentSet() , 10);
        GameController.getGame().setSummonAvengerPlayer(null);
        Assert.assertNull(GameController.getGame().getSummonAvengerPlayer());

    }

    @Test
    public void testDeckCard() {
        PreGameController preGameController = new PreGameController();

        Assert.assertNotNull(preGameController);
    }

    @Test
    public void testStorage() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        PreGameController preGameController = new PreGameController();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));

        preGameController.changeFaction(new Faction("Earth", cards, cards) , false);
        Assert.assertEquals(3, preGameController.getStorageCards().size());

    }

    @Test
    public void saveTest() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        PreGameController preGameController = new PreGameController();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));

        preGameController.changeFaction(new Faction("Earth", cards, cards) , false);

        preGameController.setDeckCards(cards);

        preGameController.saveDeckToFile("kir.json");
        Assert.assertEquals(3, preGameController.getDeckCards().size());

        preGameController.saveDeckToDB();
        GameMaster.getGameMaster().setLoggedInUser1(new User());
        preGameController.saveDeckToDB();

    }

    @Test
    public void setOpponentPlayerTest() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        PreGameController preGameController = new PreGameController();
        preGameController.setOpponentPlayer(new User());
        Assert.assertNotNull((GameMaster.getGameMaster().getLoggedInUser2()));
    }

    @Test
    public void getCardNameFromStorageTest() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        PreGameController preGameController = new PreGameController();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        preGameController.changeFaction(new Faction("Earth", cards, cards) , false);
        preGameController.setStorageCards(cards);
        preGameController.setDeckCards(cards);

        Assert.assertNotNull(preGameController.getCardByNameFromStorage("test"));
        Assert.assertNull(preGameController.getCardByNameFromStorage("ewrwerwer"));
        Assert.assertNotNull(preGameController.getRandomDeckCards(new Faction("Earth", cards, cards)));
        Assert.assertNotNull(preGameController.getRandomLeader(new Faction("Earth", cards, cards)));

    }

    @Test
    public void testGettersAndSettersPregame(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        PreGameController preGameController = new PreGameController();
        preGameController.setDeckCards(new ArrayList<>());
        Assert.assertNotNull(preGameController.getDeckCards());
        preGameController.setStorageCards(new ArrayList<>());
        Assert.assertNotNull(preGameController.getStorageCards());
        preGameController.setPlayerFaction(new Faction("Earth", new ArrayList<>(), new ArrayList<>()));
        Assert.assertNotNull(preGameController.getPlayerFaction());
        preGameController.setSelectedLeader(new Card(new Morale() , "test", "test" , 10 , false, 0));
        Assert.assertNotNull(preGameController.getSelectedLeader());
        preGameController.setPreGame(new PreGameController());
        Assert.assertNotNull(PreGameController.getPreGame());
        Assert.assertNotNull(preGameController.getSelectedLeader());
    }

    @Test
    public void testMenu(){
        Assert.assertNotNull(MenuController.getMenu());
        MenuController.setMenu(null);
        Assert.assertNotNull(MenuController.getMenu());
    }

    @Test
    public void testForgetPasswordMethods(){
        Assert.assertNull(MenuController.getMenu().submitUsernameForForgetPassword(new ArrayList<>()  , "test"));
        ArrayList<User> users = new ArrayList<>();
        User user = new User();
        user.setUsername("test");
        users.add(user);
        Assert.assertEquals(MenuController.getMenu().submitUsernameForForgetPassword(users , "test") , user);
    }

    @Test
    public void testProfileMenuMethods() {
        User user = new User();
        user.setScore(10);
        User user2 = new User();
        user2.setScore(20);
        User user3 = new User();
        user2.setScore(5);
        User user4 = new User();
        user2.setScore(3);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        Assert.assertEquals(MenuController.getMenu().sortUsersByScore(users).get(0).getScore(), 10);

        GameMaster.getGameMaster().setLoggedInUser1(user);
        user.setUsername("test");
        user.setGameHistories(new ArrayList<>());
        user2.setGameHistories(new ArrayList<>());
        GameHistory gameHistory = new GameHistory();
        gameHistory.setDate("2020-10-10");
        user.getGameHistories().add(gameHistory);
        users.add(user2);
        user2.getGameHistories().add(gameHistory);

        Assert.assertEquals(MenuController.getMenu().getDates(users).get(0), "2020-10-10");

    }
}
