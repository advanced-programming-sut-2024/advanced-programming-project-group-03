package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Data.MusicMaster;
import ir.ap.probending.Model.Factions.FactionObjects;
import ir.ap.probending.Model.Game.PreGame;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.ProBending;

public class MainMenuController {
    private final static MainMenuController mainMenuController = new MainMenuController(true);
    private final static MainMenuController unloggedMainMenuController = new MainMenuController(false);
    private final Table table = new Table();
    private final Image backgroundImage = new Image(new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getBackground())));
    private final TextButton playButton = new TextButton("Play", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton signInButton = new TextButton("Login", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton profileButton = new TextButton("Profile", GameAssetManager.getGameAssetManager().getSkin());

    private MainMenuController(boolean logged) {
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();
        table.addActor(backgroundImage);
        backgroundImage.setFillParent(true);
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //put image bakcground

        if (logged) {
            table.add(playButton).fillX();
            table.row().pad(10, 0, 10, 0);
        }

        table.addActor(signInButton);
        signInButton.setPosition(50, Gdx.graphics.getHeight() - 50 - signInButton.getHeight());

        if (logged) {
            table.addActor(profileButton);
            profileButton.setPosition(100, 200);
        }
        //musics
        MusicMaster.getInstance().playBgMusicMenu();
    }

    private void playButton(ProBending game){
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                game.setScreen(ScreenMasterSetting.getInstance().getPreGameScreen());
                PreGame.getPreGame().changeFaction(FactionObjects.WATER.getFaction().clone());
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
        if (GameMaster.getGameMaster().getLoggedInUser1().getUsername().equals("Guest1") || !GameMaster.getGameMaster().getLoggedInUser1().getHasLoggedIn())
            return unloggedMainMenuController;
        else
            return mainMenuController;
    }

    public Actor getTable() {
        return table;
    }

}
