package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ir.ap.probending.Model.Data.SecurityQuestions;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.ProBending;
import ir.ap.probending.Model.Data.SaveUser;
import ir.ap.probending.Model.User;

import java.util.List;

public class ForgetPasswordController {
    private static ForgetPasswordController forgetPasswordController = new ForgetPasswordController();
    private Table table = new Table();
    private final TextField usernameField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton submitUsername = new TextButton("Submit Username", GameAssetManager.getGameAssetManager().getSkin());
    private final Label questionSelector = new Label("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField answerField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField newPasswordField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton changePassword = new TextButton("Change Password", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton backToLoginMenu = new TextButton("Back", GameAssetManager.getGameAssetManager().getSkin());
    private final Label errorLabel = new Label("", GameAssetManager.getGameAssetManager().getSkin());
    private final Label usernameLabel = new Label("Username", GameAssetManager.getGameAssetManager().getSkin());
    private final Label questionLabel = new Label("Security Question", GameAssetManager.getGameAssetManager().getSkin());
    private final Label answerLabel = new Label("Answer", GameAssetManager.getGameAssetManager().getSkin());
    private final Label newPasswordLabel = new Label("New Password", GameAssetManager.getGameAssetManager().getSkin());
    private User selectedUser;

    private ForgetPasswordController() {
        table.addActor(GameAssetManager.getGameAssetManager().getBackground());
        table.setFillParent(true);
        table.center();
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());

        table.row().pad(10, 0, 10, 0);
        table.add(usernameLabel).fillX();
        table.row();
        table.add(usernameField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(submitUsername).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(questionLabel).fillX();
        table.row();
        table.add(questionSelector).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(answerLabel).fillX();
        table.row();
        table.add(answerField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(newPasswordLabel).fillX();
        table.row();
        table.add(newPasswordField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(errorLabel).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(changePassword).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(backToLoginMenu).fillX();

        answerField.setDisabled(true);
        newPasswordField.setDisabled(true);
        changePassword.setDisabled(true);
        errorLabel.setColor(1, 0, 0, 1);
    }

    private void setSubmitUsernameButton(ProBending game){
        submitUsername.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                List<User> users = SaveUser.loadUsers();
                for (User user : users) {
                    if (user.getUsername().equals(usernameField.getText())) {
                        questionSelector.setText(SecurityQuestions.getQuestions().get(user.getQuestionIndex()));
                        answerField.setDisabled(false);
                        newPasswordField.setDisabled(false);
                        changePassword.setDisabled(false);
                        selectedUser = user;
                        break;
                    }
                }
            }
        });
    }

    private void setChangePasswordButton(ProBending game){
        changePassword.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (answerField.getText().equals(selectedUser.getQuestionAnswer())) {
                    selectedUser.setPassword(newPasswordField.getText());
                    SaveUser.saveUser(selectedUser);
                    errorLabel.setText("Password changed successfully!");
                    errorLabel.setColor(0, 1, 0, 1);
                }
                else {
                    errorLabel.setText("Invalid Answer or password is empty!");
                }
            }
        });
    }

    private void setBackToLoginMenuButton(ProBending game){
        backToLoginMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(LoginController.getLoginController().getTable());
            }
        });
    }

    public void handleMainMenuButtons(ProBending game) {
        setBackToLoginMenuButton(game);
        setSubmitUsernameButton(game);
        setChangePasswordButton(game);
    }

    //getters and setters

    public static ForgetPasswordController getForgetPasswordController(){
        return forgetPasswordController;
    }

    public Actor getTable() {
        return table;
    }
}
