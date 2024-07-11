package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Data.MusicMaster;
import ir.ap.probending.Model.Factions.FactionObjects;
import ir.ap.probending.Model.Game.Game;
import ir.ap.probending.Model.Game.GameBoard;
import ir.ap.probending.Model.Game.PreGame;
import ir.ap.probending.Model.Message;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.ProBending;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainMenuController {
    private final static MainMenuController mainMenuController = new MainMenuController();
    private final Table table = new Table();
    private final Image backgroundImage = new Image(new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getBackground())));
    private final TextButton playButton = new TextButton("Play", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton signInButton = new TextButton("Login", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton profileButton = new TextButton("Profile", GameAssetManager.getGameAssetManager().getSkin());
    private final Window gameWindow = new Window("", GameAssetManager.getGameAssetManager().getSkin());
    private final Table gameTable = new Table();
    private final ScrollPane gameScrollPane = new ScrollPane(gameTable);
    private ScrollPane.ScrollPaneStyle gameScrollPaneStyle;
    private ScrollPane.ScrollPaneStyle scrollPaneStyle;
    private final TextButton backButton = new TextButton("Back", GameAssetManager.getGameAssetManager().getSkin());


    private MainMenuController() {
        scrollPaneStyle = GameAssetManager.getGameAssetManager().getSkin().get("default", ScrollPane.ScrollPaneStyle.class);

        gameWindow.add(gameScrollPane).fillX().expandX().fillY().expandY();
        gameWindow.row();

        gameWindow.add(backButton).fillX().expandX();
        table.addActor(gameWindow);
        gameWindow.setVisible(false);

        gameWindow.setSize(1920, 1080);
        gameWindow.setPosition(Gdx.graphics.getWidth() / 2 - gameWindow.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gameWindow.getHeight() / 2);
        gameWindow.toFront();

        gameScrollPane.setScrollingDisabled(false, false);
        gameScrollPane.setScrollbarsVisible(true);
        gameScrollPane.setFadeScrollBars(false);
        gameScrollPane.setSmoothScrolling(true);
        gameScrollPane.setScrollBarPositions(false, true);
        gameScrollPaneStyle = new ScrollPane.ScrollPaneStyle(scrollPaneStyle);
        gameScrollPane.setStyle(gameScrollPaneStyle);

        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();
        table.addActor(backgroundImage);
        backgroundImage.setFillParent(true);
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //put image bakcground


        table.add(playButton).fillX();
        table.row().pad(10, 0, 10, 0);

        table.addActor(signInButton);
        signInButton.setPosition(50, Gdx.graphics.getHeight() - 50 - signInButton.getHeight());

        table.addActor(profileButton);
        profileButton.setPosition(100, 200);

        //musics
        MusicMaster.getInstance().playBgMusicMenu();
    }

    private void setBackButton(ProBending game){
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameWindow.setVisible(false);
            }
        });
    }

    private void playButton(ProBending game){
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // see if user is logged in
                if (GameMaster.getGameMaster().getLoggedInUser1().getUsername().equals("Guest1") || !GameMaster.getGameMaster().getLoggedInUser1().getHasLoggedIn()) {
                    return;
                }
//                game.getScreen().dispose();
//                game.setScreen(ScreenMasterSetting.getInstance().getPreGameScreen());
//                PreGame.getPreGame().changeFaction(FactionObjects.WATER.getFaction().clone());
                gameWindow.setVisible(true);

                String response = ProBending.client.gameCommunicate("getGames");
                if (response.equals("no games")) {
                    return;
                }
                Gson gson = new Gson();
                Type gameBoardArrayList = new TypeToken<ArrayList<GameBoard>>() {
                }.getType();
                ArrayList<GameBoard> games = gson.fromJson(response, gameBoardArrayList);

                gameTable.clear();
                for (GameBoard gameBoard : games) {
                    TextButton gameButton = new TextButton(gameBoard.getPlayer1().getUser().getUsername() + " VS " + gameBoard.getPlayer2().getUser().getUsername(), GameAssetManager.getGameAssetManager().getSkin());
                    gameButton.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            String response = ProBending.client.gameCommunicate("joinGame " + gameBoard.getPlayer1().getUser().getUsername());
                            Gson gson = new Gson();
                            Game game2 = gson.fromJson(response, Game.class);
                            Game.setGame(game2);
                            game.getScreen().dispose();
                            game.setScreen(ScreenMasterSetting.getInstance().getGameScreen());

                        }
                    });
                    gameTable.add(gameButton).fillX().expandX();
                    gameTable.row();
                }
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
                if (GameMaster.getGameMaster().getLoggedInUser1().getUsername().equals("Guest1") || !GameMaster.getGameMaster().getLoggedInUser1().getHasLoggedIn()) {
                    return;
                }
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(ProfileController.getProfileController().getTable());

                ProfileController.getProfileController().updateLabels();
            }
        });
    }

    public void handleMainMenuButtons(ProBending game) {
        setBackButton(game);
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
