package ir.ap.probending.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import ir.ap.probending.ProBending;

public class MainMenuScreen implements Screen {
    private ProBending game;
    private SpriteBatch batch;
    private Stage stage;
    VideoPlayer videoPlayer;
    VideoPlayer videoPlayer2;
    private boolean hasPlayedIntro = false;

    public MainMenuScreen(ProBending game) {
        this.game = game;
        this.batch = game.batch;
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.addActor(MainMenuScreenController.getMainMenuController().getTable());
        LoginScreenController.getLoginController().handleLoginButtons(game);
        ForgetPasswordController.getForgetPasswordController().handleMainMenuButtons(game);
        SignInScreenController.getSignInController().handleSignInMenuButtons(game);
        PickQuestionMenuController.getPickQuestionMenuController().handlePickQuestionMenuButtons(game);
        ProfileScreenController.getProfileController().handleProfileButtons(game);
        MainMenuScreenController.getMainMenuController().handleMainMenuButtons(game);

        videoPlayer = VideoPlayerCreator.createVideoPlayer();
        videoPlayer.setOnCompletionListener(new VideoPlayer.CompletionListener() {
            @Override
            public void onCompletionListener(FileHandle fileHandle) {
                try {
                    videoPlayer.play(Gdx.files.internal("1.webm"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        try {
            videoPlayer.play(Gdx.files.internal("1.webm"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        videoPlayer2 = VideoPlayerCreator.createVideoPlayer();
        videoPlayer2.setOnCompletionListener(new VideoPlayer.CompletionListener() {
            @Override
            public void onCompletionListener(FileHandle fileHandle) {
                try {
                    hasPlayedIntro = true;
                    videoPlayer2.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        try {
            videoPlayer2.play(Gdx.files.internal("video.webm"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(1, 1, 1, 1);
        videoPlayer.update();
        if (!hasPlayedIntro){
            videoPlayer2.update();
        }
        batch.begin();
        Texture videoTexture = videoPlayer.getTexture();
        if (videoTexture != null) {
            //MainMenuController.getMainMenuController().getBackgroundImage().setDrawable(GameAssetManager.getGameAssetManager().getSkin().newDrawable("white", new Sprite(videoTexture).getColor()));
            //batch.draw(videoTexture, 320, 0, 1280, 720);
            MainMenuScreenController.getMainMenuController().setBackgroundImage(new Image(videoTexture));
            MainMenuScreenController.getMainMenuController().getBackgroundImage().setPosition(0, 0);
            MainMenuScreenController.getMainMenuController().getBackgroundImage().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            stage.addActor(MainMenuScreenController.getMainMenuController().getBackgroundImage());
            MainMenuScreenController.getMainMenuController().getBackgroundImage().toBack();
        }
        Texture videoTexture2 = videoPlayer2.getTexture();
        if (videoTexture2 != null) {
            batch.draw(videoTexture2, 0, 0, 1920, 1080);
        }
        stage.draw();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }

    //getters and setters


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
