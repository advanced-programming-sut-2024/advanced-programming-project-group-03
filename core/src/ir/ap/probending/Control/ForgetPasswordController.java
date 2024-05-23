package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.Model.GameAssetManager;
import ir.ap.probending.ProBending;
import jdk.jpackage.internal.Log;

public class ForgetPasswordController {
    private static ForgetPasswordController forgetPasswordController = new ForgetPasswordController();
    private Table table = new Table();
    private final TextField usernameField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton submitUsername = new TextButton("Submit Username", GameAssetManager.getGameAssetManager().getSkin());
    private final SelectBox<String> questionSelector = new SelectBox<>(GameAssetManager.getGameAssetManager().getSkin());
    private final TextField answerField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField newPasswordField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton changePassword = new TextButton("Change Password", GameAssetManager.getGameAssetManager().getSkin());
    private ForgetPasswordController() {
        table.addActor(GameAssetManager.getGameAssetManager().getBackground());
        table.setFillParent(true);
        table.center();
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());

        table.row().pad(10, 0, 10, 0);
        table.add(usernameField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(submitUsername).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(questionSelector).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(answerField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(newPasswordField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(changePassword).fillX();


        questionSelector.setDisabled(true);
        answerField.setDisabled(true);
        newPasswordField.setDisabled(true);
        changePassword.setDisabled(true);
    }

    public void handleMainMenuButtons(ProBending game) {

    }

    //getters and setters

    public static ForgetPasswordController getForgetPasswordController(){
        return forgetPasswordController;
    }

    public Actor getTable() {
        return table;
    }
}
