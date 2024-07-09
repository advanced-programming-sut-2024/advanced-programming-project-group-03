package ir.ap.probending.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ir.ap.probending.Model.Data.MusicMaster;
import ir.ap.probending.Model.Factions.FactionObjects;
import ir.ap.probending.Control.PreGame;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.ProBending;

public class MainMenuScreenController {
    private final static MainMenuScreenController MAIN_MENU_SCREEN_CONTROLLER = new MainMenuScreenController();
    private final Table table = new Table();
    private Image backgroundImage = new Image(new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getBackground())));
    private final TextButton playButton = new TextButton("Play", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton signInButton = new TextButton("Login", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton profileButton = new TextButton("Profile", GameAssetManager.getGameAssetManager().getSkin());
    private final Label gameTitle = new Label("Pro Bending", GameAssetManager.getGameAssetManager().getSkin() , "title");
    private final Label creatorsInfo = new Label("Created by: Kiarash Shojaei & Amin Koohi & Nima Hekmati", GameAssetManager.getGameAssetManager().getSkin() );

    private MainMenuScreenController(){
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();

        table.add(playButton).fillX();
        table.row().pad(10, 0, 10, 0);

        table.addActor(signInButton);
        signInButton.setPosition(1390, Gdx.graphics.getHeight() - 50 - signInButton.getHeight());

        table.addActor(profileButton);
        profileButton.setPosition(1600, Gdx.graphics.getHeight() - 50 - signInButton.getHeight());

        table.addActor(gameTitle);
        gameTitle.setPosition(50, Gdx.graphics.getHeight() - 50 - gameTitle.getHeight());

        table.addActor(creatorsInfo);
        creatorsInfo.setPosition(50, 50);

        //musics
        MusicMaster.getInstance().playBgMusicMenu();
    }

    private void playButton(ProBending game){
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().dispose();
                game.setScreen(ScreenMasterSetting.getInstance().getPreGameScreen());
                if (PreGameScreenController.getPreGameController().isUsedBackButton())
                    PreGameScreenController.setPreGameController();
                PreGameScreenController.getPreGameController().handlePreGameController(game);
                PreGame.getPreGame().changeFaction(FactionObjects.WATER.getFaction().clone() , true);
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
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(LoginScreenController.getLoginController().getTable());
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
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(ProfileScreenController.getProfileController().getTable());

                ProfileScreenController.getProfileController().updateLabels();
            }
        });
    }

    public void handleMainMenuButtons(ProBending game) {
        playButton(game);
        signInButton(game);
        profileButton(game);
    }

    //getters and setters

    public static MainMenuScreenController getMainMenuController(){
        return MAIN_MENU_SCREEN_CONTROLLER;
    }

    public Actor getTable() {
        return table;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
