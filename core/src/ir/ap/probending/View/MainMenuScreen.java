package ir.ap.probending.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ir.ap.probending.Control.*;
import ir.ap.probending.Model.Data.SecurityQuestions;
import ir.ap.probending.ProBending;

public class MainMenuScreen implements Screen {
    private ProBending game;
    private SpriteBatch batch;
    private Stage stage;

    public MainMenuScreen(ProBending game) {
        this.game = game;
        this.batch = game.batch;
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.addActor(MainMenuController.getMainMenuController().getTable());
        MainMenuController.getMainMenuController().handleMainMenuButtons(game);
        LoginController.getLoginController().handleLoginButtons(game);
        ForgetPasswordController.getForgetPasswordController().handleMainMenuButtons(game);
        SignInController.getSignInController().handleSignInMenuButtons(game);
        PickQuestionMenuController.getPickQuestionMenuController().handlePickQuestionMenuButtons(game);

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
