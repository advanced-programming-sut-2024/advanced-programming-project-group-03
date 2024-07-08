

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import ir.ap.probending.Control.Game;
import ir.ap.probending.Model.Card.Abilities.Morale;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.ProBending;
import ir.ap.probending.View.GameUIController;
import ir.ap.probending.View.PreGameScreen;
import org.junit.*;
import org.junit.Assert;
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
        Game.getGame().depositCardToBurntCards();
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer1().getBurntCards().size(), 0);
        Assert.assertEquals(Game.getGame().getGameBoard().getPlayer2().getBurntCards().size(), 0);
    }
}
