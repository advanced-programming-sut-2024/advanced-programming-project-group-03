package ir.ap.probending.Model;

import ir.ap.probending.ProBending;
import ir.ap.probending.View.GameScreen;
import ir.ap.probending.View.MainMenuScreen;
import ir.ap.probending.View.PreGameScreen;

public class ScreenMasterSetting {
    private static ScreenMasterSetting instance = new ScreenMasterSetting();
    private ProBending game;
    private MainMenuScreen mainMenuScreen;
    private PreGameScreen preGameScreen;
    private GameScreen gameScreen;


    //getters and setters

    public void setGame(ProBending game) {
        this.game = game;
    }

    public ProBending getGame() {
        return game;
    }

    public static ScreenMasterSetting getInstance() {
        return instance;
    }

    public static void setInstance(ScreenMasterSetting instance) {
        ScreenMasterSetting.instance = instance;
    }

    public MainMenuScreen getMainMenuScreen() {
        return mainMenuScreen;
    }

    public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
        this.mainMenuScreen = mainMenuScreen;
    }

    public PreGameScreen getPreGameScreen() {
        return preGameScreen;
    }

    public void setPreGameScreen(PreGameScreen preGameScreen) {
        this.preGameScreen = preGameScreen;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

}
