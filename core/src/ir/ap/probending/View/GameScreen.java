package ir.ap.probending.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ir.ap.probending.Control.*;
import ir.ap.probending.ProBending;

public class GameScreen implements Screen {

    private ProBending game;
    private SpriteBatch batch;
    private Stage stage;

    public GameScreen(ProBending game) {
        this.game = game;
        this.batch = game.batch;
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.addActor(GameUIController.getGameUIController().getTable());
        GameUIController.getGameUIController().handlePreGameController(game);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        stage.draw();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
            GameUIController.getGameUIController().chatWindowToggle();
        }
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
