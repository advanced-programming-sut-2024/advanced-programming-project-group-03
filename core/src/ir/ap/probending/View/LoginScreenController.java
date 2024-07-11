package ir.ap.probending.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Data.SaveUser;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.User;
import ir.ap.probending.ProBending;
import ir.ap.probending.Model.ScreenMasterSetting;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.List;

public class LoginScreenController {
    private static LoginScreenController loginScreenController = new LoginScreenController();
    private Table table = new Table();

    private final TextField usernameField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField passwordField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton loginButton = new TextButton("Login", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton forgetPasswordButton = new TextButton("Forgot Password", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton signUpButton = new TextButton("Don't have an account", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton backToMainMenuButton = new TextButton("Back", GameAssetManager.getGameAssetManager().getSkin());
    private final Label errorLabel = new Label("", GameAssetManager.getGameAssetManager().getSkin());
    private final Label usernameLabel = new Label("Username", GameAssetManager.getGameAssetManager().getSkin());
    private final Label passwordLabel = new Label("Password", GameAssetManager.getGameAssetManager().getSkin());
    private final CheckBox rememberMeCheckBox = new CheckBox("Remember Me", GameAssetManager.getGameAssetManager().getSkin());

    private LoginScreenController() {
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();

        table.row().pad(10, 0, 10, 0);
        table.add(usernameLabel).fillX();
        table.row();
        table.add(usernameField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(passwordLabel).fillX();
        table.row();
        table.add(passwordField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(rememberMeCheckBox).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(loginButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(forgetPasswordButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(errorLabel).fillX();
        table.row();
        table.add(signUpButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(backToMainMenuButton).fillX();

    }

    private void setLoginButton(ProBending game){
        loginButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                List<User> users = SaveUser.loadUsers();
                boolean isUserExist = false;
                boolean isPasswordCorrect = false;
                boolean isRememberMe = rememberMeCheckBox.isChecked();
                User loggedInUser = null;

                for (User user : users) {
                    if (user.getUsername().equals(usernameField.getText())) {
                        isUserExist = true;
                        if (user.getPassword().equals(passwordField.getText())) {
                            isPasswordCorrect = true;
                            loggedInUser = user;
                        }
                    }
                }

                if (isRememberMe){
                    for (User user : users){
                        if (user.isRememberMe()){
                            user.setRememberMe(false);
                            SaveUser.updateUser(user);
                        }
                    }
                }

                if (!isUserExist) {
                    errorLabel.setText("User does not exist");
                }
                else if (!isPasswordCorrect) {
                    errorLabel.setText("Password is incorrect");
                }
                else {
                    ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                    ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                    Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                    ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(MainMenuScreenController.getMainMenuController().getTable());
                    errorLabel.setText("");
                    loggedInUser.setRememberMe(isRememberMe);
                    SaveUser.updateUser(loggedInUser);
                    GameMaster.getGameMaster().setLoggedInUser1(loggedInUser);
                }
            }
        });
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

    private void setBackToMainMenuButton(ProBending game){
        backToMainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(MainMenuScreenController.getMainMenuController().getTable());
            }
        });
    }

    private void setSignUpButton(ProBending game){
        signUpButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(SignInScreenController.getSignInController().getTable());
            }
        });
    }

    public void handleLoginButtons(ProBending game) {
        setForgetPasswordButton(game);
        setBackToMainMenuButton(game);
        setSignUpButton(game);
        setLoginButton(game);
    }

    //getters and setters

    public static LoginScreenController getLoginController(){
        return loginScreenController;
    }

    public Actor getTable() {
        return table;
    }
}