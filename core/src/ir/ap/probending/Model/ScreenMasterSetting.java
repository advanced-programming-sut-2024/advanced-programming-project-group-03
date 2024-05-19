package ir.ap.probending.Model;

import ir.ap.probending.ProBending;
import ir.ap.probending.View.MainMenuScreen;

public class ScreenMasterSetting {
    private static ScreenMasterSetting instance = new ScreenMasterSetting();
    private ProBending game;
    private MainMenuScreen mainMenuScreen;


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
}
