package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ir.ap.probending.Model.Factions.FactionObjects;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.ProBending;

public class MainMenuController {
    private final static MainMenuController mainMenuController = new MainMenuController();
    private final Table table = new Table();
    private final TextButton playButton = new TextButton("Play", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton signInButton = new TextButton("Login", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton profileButton = new TextButton("Profile", GameAssetManager.getGameAssetManager().getSkin());

    private MainMenuController() {
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();
        table.addActor(GameAssetManager.getGameAssetManager().getBackground());

        table.add(playButton).fillX();
        table.row().pad(10, 0, 10, 0);

        table.addActor(signInButton);
        signInButton.setPosition(50, Gdx.graphics.getHeight() - 50 - signInButton.getHeight());

        table.addActor(profileButton);
        profileButton.setPosition(100, 200);

    }

    private void playButton(ProBending game){
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                game.setScreen(ScreenMasterSetting.getInstance().getPreGameScreen());
                PreGameController.getPreGameController().getPreGame().changeFaction(FactionObjects.WATER.getFaction().clone());
            }
        });
    }

    private void signInButton(ProBending game){
        signInButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(LoginController.getLoginController().getTable());
            }
        });
    }

    private void profileButton(ProBending game){
        profileButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(ProfileController.getProfileController().getTable());

                ProfileController.getProfileController().updateLabels();
            }
        });
    }

    public void handleMainMenuButtons(ProBending game) {
        playButton(game);
        signInButton(game);
        profileButton(game);
    }

    //getters and setters

    public static MainMenuController getMainMenuController(){
        return mainMenuController;
    }

    public Actor getTable() {
        return table;
    }
}
