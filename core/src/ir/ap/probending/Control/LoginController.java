package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ir.ap.probending.Model.GameAssetManager;
import ir.ap.probending.ProBending;
import ir.ap.probending.Model.ScreenMasterSetting;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoginController {
    private static LoginController loginController = new LoginController();
    private Table table = new Table();

    private final TextField usernameField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField passwordField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton loginButton = new TextButton("Login", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton forgetPasswordButton = new TextButton("Forgot Password", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton signUpButton = new TextButton("Don't have an account", GameAssetManager.getGameAssetManager().getSkin());

    private LoginController() {
        table.addActor(GameAssetManager.getGameAssetManager().getBackground());
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();

        table.row().pad(10, 0, 10, 0);
        table.add(usernameField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(passwordField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(loginButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(forgetPasswordButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(signUpButton).fillX();
        
    }

    private void setForgetPasswordButton(ProBending game){
        forgetPasswordButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(ForgetPasswordController.getForgetPasswordController().getTable());
            }
        });
    }

    public void handleLoginButtons(ProBending game) {
        setForgetPasswordButton(game);
    }

    //getters and setters

    public static LoginController getLoginController(){
        return loginController;
    }

    public Actor getTable() {
        return table;
    }
}
