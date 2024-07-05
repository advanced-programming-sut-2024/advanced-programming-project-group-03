package ir.ap.probending.Control;

import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Factions.FactionObjects;
import ir.ap.probending.Model.Game.PreGame;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.ProBending;
import ir.ap.probending.View.MainMenuScreen;
import ir.ap.probending.View.PreGameScreen;

public class GameStartController extends Thread {
    private static ProBending game;

    @Override
    public void run() {
        while (true) {
            String response = ProBending.client.communicate("isUserInGame");
            if (response.equals("yes")) {
                ProBending.client.sendGameMessage(GameMaster.getGameMaster().getLoggedInUser1().getUsername());
                while (GameMaster.getGameMaster().getLoggedInUser1().isPlaying()) {
                    try {
                        if (game.getScreen() instanceof MainMenuScreen || game.getScreen() instanceof PreGameScreen)
                            game.getScreen().dispose();
                            game.setScreen(ScreenMasterSetting.getInstance().getPreGameScreen());
                            PreGame.getPreGame().changeFaction(FactionObjects.WATER.getFaction().clone());                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void setGame(ProBending game) {
        GameStartController.game = game;
    }
}
