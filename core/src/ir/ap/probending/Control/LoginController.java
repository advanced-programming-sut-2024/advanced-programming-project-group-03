package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.google.gson.Gson;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Data.SaveUser;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.User;
import ir.ap.probending.ProBending;
import ir.ap.probending.Model.ScreenMasterSetting;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.List;

public class LoginController {
    private String verificationCode = "";
    private static LoginController loginController = new LoginController();
    private Table table = new Table();

    private final TextField usernameField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField passwordField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField verificationCodeField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton verificationCodeButton = new TextButton("Verify", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton loginButton = new TextButton("Login", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton forgetPasswordButton = new TextButton("Forgot Password", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton signUpButton = new TextButton("Don't have an account", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton backToMainMenuButton = new TextButton("Back", GameAssetManager.getGameAssetManager().getSkin());
    private final Label errorLabel = new Label("", GameAssetManager.getGameAssetManager().getSkin());
    private final Label usernameLabel = new Label("Username", GameAssetManager.getGameAssetManager().getSkin());
    private final Label passwordLabel = new Label("Password", GameAssetManager.getGameAssetManager().getSkin());
    private final Label verificationCodeLabel = new Label("Verification Code", GameAssetManager.getGameAssetManager().getSkin());

    private LoginController() {
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
        table.add(loginButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(forgetPasswordButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(errorLabel).fillX();
        table.row();
        table.add(signUpButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(verificationCodeLabel).fillX();
        table.row();
        table.add(verificationCodeField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(verificationCodeButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(backToMainMenuButton).fillX();
        
    }

    private void setLoginButton(ProBending game){
        loginButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String response = ProBending.client.communicate("login " + usernameField.getText() + " " + passwordField.getText());

                switch (response) {
                    case "Sending email confirmation link":
                        verificationCode = ProBending.client.communicate("sendLoginEmail");
                        errorLabel.setText("Enter the verification code sent to your email");
                        usernameField.setText("");
                        passwordField.setText("");
                        break;
                    default:
                        errorLabel.setText(response);
                        break;
                }
            }
        });
    }

    private void setVerificationCodeButton(ProBending game){
        verificationCodeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                if (verificationCodeField.getText().equals(verificationCode) && !verificationCodeField.getText().equals("")) {
                if (true) {
                    String loggedInUserString = ProBending.client.communicate("getUser");
                    Gson gson = new Gson();
                    User loggedInUser = gson.fromJson(loggedInUserString, User.class);
                    ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                    ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                    Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                    ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(MainMenuController.getMainMenuController().getTable());
                    errorLabel.setText("");

                    GameMaster.getGameMaster().setLoggedInUser1(loggedInUser);
                }
                else {
                    errorLabel.setText("Verification code is incorrect");
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
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(MainMenuController.getMainMenuController().getTable());
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
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(SignInController.getSignInController().getTable());
            }
        });
    }

    public void handleLoginButtons(ProBending game) {
        setForgetPasswordButton(game);
        setBackToMainMenuButton(game);
        setSignUpButton(game);
        setLoginButton(game);
        setVerificationCodeButton(game);
    }

    //getters and setters

    public static LoginController getLoginController(){
        return loginController;
    }

    public Actor getTable() {
        return table;
    }
}
