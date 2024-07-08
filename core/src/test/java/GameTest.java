

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import ir.ap.probending.Control.Game;
import ir.ap.probending.Control.PreGame;
import ir.ap.probending.Model.Card.Abilities.Morale;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Factions.Faction;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.Model.User;
import ir.ap.probending.ProBending;
import ir.ap.probending.View.GameUIController;
import ir.ap.probending.View.PreGameScreen;
import org.junit.*;
import org.junit.Assert;

import java.util.ArrayList;

public class GameTest {
    @Test
    public void isGameInitialized() {
        Assert.assertNotNull(Game.getGame());
    }

    @Test
    public void testStartGame() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        Game.getGame().startGameWithoutScreen();
        Assert.assertNotNull(Game.getGame().getGameBoard());
        Assert.assertNotNull(Game.getGame().getGameBoard().getPlayer1());
        Assert.assertNotNull(Game.getGame().getGameBoard().getPlayer2());
        Assert.assertNotNull(Game.getGame().getGameBoard().getPlayer1Board());
        Assert.assertNotNull(Game.getGame().getGameBoard().getPlayer2Board());
        Assert.assertEquals(Game.getGame().getVetoCount() , 0);
    }

    @Test
    public void testGetCurrentTurn() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        Game.getGame().startGameWithoutScreen();
        Assert.assertEquals(Game.getGame().getCurrentTurn(), 1);
    }

    @Test
    public void testGetOtherPlayer() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        Game.getGame().startGameWithoutScreen();
        Assert.assertEquals(Game.getGame().getOtherPlayer(), Game.getGame().getGameBoard().getPlayer2());
    }

    @Test
    public void testPlayCard() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        Game.getGame().startGameWithoutScreen();
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        Assert.assertEquals(Game.getGame().decideWinner() , Game.getGame().getGameBoard().getPlayer1());
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1Board().getSiege().get(0).getName(), "test");
    }

    @Test
    public void testEndTurn() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        Game.getGame().startGameWithoutScreen();
        Game.getGame().endTurn(false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        Assert.assertEquals(Game.getGame().getCurrentTurn(), 2);
        Assert.assertEquals(Game.getGame().decideWinner() , Game.getGame().getGameBoard().getPlayer2());
    }

    @Test
    public void testDepositIntoBurnt(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        Game.getGame().startGameWithoutScreen();
        Game.getGame().getGameBoard().getPlayer2Board().setFaction(Game.getGame().getGameBoard().getPlayer1Board().getFaction());
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        Game.getGame().endTurn(false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 1);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 1);

        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 1 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 1 , false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 2);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 4);

        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 2), 2 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 2), 2 , false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 3);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 9);

        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 1 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 1 , false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 4);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 16);

        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 7 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 1), 7 , false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 5);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 23);

        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 8 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 8 , false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 6);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 30);

        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 2), 9 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 2), 9 , false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 7);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 37);

        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 6 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 6 , false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 8);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 44);

        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 10 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 10 , false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 9);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 51);

        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 11 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 11 , false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 10);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 58);

        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 12 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 6), 12 , false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 11);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 65);
    }

    @Test
    public void testDepositIntoBurnt2(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        Game.getGame().startGameWithoutScreen();
        Game.getGame().getGameBoard().getPlayer2Board().setFaction(new Faction("Air" , new ArrayList<>() , new ArrayList<>()));
        Game.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Air" , new ArrayList<>() , new ArrayList<>()));

        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 5 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 1 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 4 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 2 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 3 , false);
        Game.getGame().endTurn(false);
        Game.getGame().endTurn(false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 0 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 5 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 1 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 4 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 2 , false);
        Game.getGame().playCard(new Card(new Morale() , "test", "test" , 10 , false, 0), 3 , false);
        Game.getGame().endTurn(false);
        Game.getGame().endTurn(false);
        Game.getGame().depositCardToBurntCards(false);
        Game.getGame().depositCardToBurntCards(false);
        Assert.assertEquals(Game.getGame().getCurrentSet() , 1);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 6);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 6);

        Game.getGame().startNewSet(false);
        Game.getGame().startNewSet(false);

        Game.getGame().getGameBoard().getPlayer2Board().setFaction(new Faction("Fire" , new ArrayList<>() , new ArrayList<>()));
        Game.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Fire" , new ArrayList<>() , new ArrayList<>()));
        Game.getGame().depositCardToBurntCards(false);
    }

    @Test
    public void testEndGame(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        Game.getGame().startGameWithoutScreen();
        Game.getGame().endTurn(false);
        Game.getGame().endTurn(false);
        Assert.assertNotNull(Game.getGame().getGameBoard());
        Assert.assertNotNull(Game.getGame().getGameBoard().getPlayer1());
        Assert.assertNotNull(Game.getGame().getGameBoard().getPlayer2());
        Assert.assertNotNull(Game.getGame().getGameBoard().getPlayer1Board());
        Assert.assertNotNull(Game.getGame().getGameBoard().getPlayer2Board());
        Assert.assertEquals(Game.getGame().getVetoCount() , 0);
        Assert.assertEquals(Game.getGame().getCurrentPlayer().getSetsWon() , 1);
        Assert.assertEquals(Game.getGame().getOtherPlayer().getSetsWon() , 1);
    }

    @Test
    public void testDecideFirst(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        Game.getGame().startGameWithoutScreen();
        Assert.assertNotNull(Game.getGame().decideFirstTurn());

        Game.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Earth" , new ArrayList<>() , new ArrayList<>()));
        Assert.assertEquals(Game.getGame().decideFirstTurn() , Game.getGame().getGameBoard().getPlayer1());


        Game.getGame().getGameBoard().getPlayer2Board().setFaction(new Faction("Earth" , new ArrayList<>() , new ArrayList<>()));
        Game.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Water" , new ArrayList<>() , new ArrayList<>()));
        Assert.assertEquals(Game.getGame().decideFirstTurn() , Game.getGame().getGameBoard().getPlayer2());

        Game.getGame().getGameBoard().getPlayer1Board().setFaction(new Faction("Earth" , new ArrayList<>() , new ArrayList<>()));
        Assert.assertEquals(Game.getGame().decideFirstTurn() , Game.getGame().getGameBoard().getPlayer1());
    }

    @Test
    public void testGettersAndSetters(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        Game.getGame().startGameWithoutScreen();
        Game.getGame().setVetoCount(10);
        Assert.assertEquals(Game.getGame().getVetoCount() , 10);
        Game.getGame().setLoseHalfInBadWeatherActivated(true);
        Assert.assertTrue(Game.getGame().isLoseHalfInBadWeatherActivated());
        Game.getGame().setRestoreCardRandomlyActivated(true);
        Assert.assertTrue(Game.getGame().isRestoreCardRandomlyActivated());
        Game.getGame().setGameBoard(null);
        Assert.assertNull(Game.getGame().getGameBoard());
        Game.getGame().setCurrentSet(10);
        Assert.assertEquals(Game.getGame().getCurrentSet() , 10);
        Game.getGame().setSummonAvengerPlayer(null);
        Assert.assertNull(Game.getGame().getSummonAvengerPlayer());

    }

    @Test
    public void testDeckCard() {
        PreGame preGame = new PreGame();

        Assert.assertNotNull(preGame);
    }

    @Test
    public void testStorage() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        PreGame preGame = new PreGame();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));

        preGame.changeFaction(new Faction("Earth", cards, cards) , false);
        Assert.assertEquals(3, preGame.getStorageCards().size());

    }

    @Test
    public void saveTest() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        PreGame preGame = new PreGame();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));
        cards.add(new Card(new Morale() , "test", "test" , 10 , false, 0));

        preGame.changeFaction(new Faction("Earth", cards, cards) , false);

        preGame.setDeckCards(cards);

        preGame.saveDeckToFile("kir.json");
        Assert.assertEquals(3, preGame.getDeckCards().size());

        preGame.saveDeckToDB();
        GameMaster.getGameMaster().setLoggedInUser1(new User());
        preGame.saveDeckToDB();

    }

    @Test
    public void setOpponentPlayerTest() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        PreGame preGame = new PreGame();
        preGame.setOpponentPlayer(new User());
        Assert.assertNotNull((GameMaster.getGameMaster().getLoggedInUser2()));
    }

    @Test
    public void getCardNameFromStorageTest() {
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        PreGame preGame = new PreGame();
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
        preGame.changeFaction(new Faction("Earth", cards, cards) , false);
        preGame.setStorageCards(cards);
        preGame.setDeckCards(cards);

        Assert.assertNotNull(preGame.getCardByNameFromStorage("test"));
        Assert.assertNull(preGame.getCardByNameFromStorage("ewrwerwer"));
        Assert.assertNotNull(preGame.getRandomDeckCards(new Faction("Earth", cards, cards)));
        Assert.assertNotNull(preGame.getRandomLeader(new Faction("Earth", cards, cards)));

    }

    @Test
    public void testGettersAndSettersPregame(){
        ProBending proBending = new ProBending();
        ScreenMasterSetting.getInstance().setGame(proBending);
        PreGame preGame = new PreGame();
        preGame.setDeckCards(new ArrayList<>());
        Assert.assertNotNull(preGame.getDeckCards());
        preGame.setStorageCards(new ArrayList<>());
        Assert.assertNotNull(preGame.getStorageCards());
        preGame.setPlayerFaction(new Faction("Earth", new ArrayList<>(), new ArrayList<>()));
        Assert.assertNotNull(preGame.getPlayerFaction());
        preGame.setSelectedLeader(new Card(new Morale() , "test", "test" , 10 , false, 0));
        Assert.assertNotNull(preGame.getSelectedLeader());
        preGame.setPreGame(new PreGame());
        Assert.assertNotNull(PreGame.getPreGame());
        Assert.assertNotNull(preGame.getSelectedLeader());
    }
}
