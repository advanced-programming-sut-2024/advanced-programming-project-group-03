package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.Data.RandomPasswordGenerator;
import ir.ap.probending.Model.Data.Regex;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.ProBending;

public class SignInController {
    private static SignInController signInController = new SignInController();
    private Table table = new Table();
    private final TextField usernameField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField nicknameField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField passwordField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField confirmPasswordField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton randomizePasswordButton = new TextButton("Generate Password", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField emailField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton signUpButton = new TextButton("Sign Up", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton backToLoginMenuButton = new TextButton("Back", GameAssetManager.getGameAssetManager().getSkin());
    private final Label usernameLabel = new Label("Username", GameAssetManager.getGameAssetManager().getSkin());
    private final Label nicknameLabel = new Label("Nickname", GameAssetManager.getGameAssetManager().getSkin());
    private final Label passwordLabel = new Label("Password", GameAssetManager.getGameAssetManager().getSkin());
    private final Label confirmPasswordLabel = new Label("Confirm Password", GameAssetManager.getGameAssetManager().getSkin());
    private final Label emailLabel = new Label("Email", GameAssetManager.getGameAssetManager().getSkin());
    private final Label errorLabel = new Label("", GameAssetManager.getGameAssetManager().getSkin());
    private final Button showPasswordButton = new Button(GameAssetManager.getGameAssetManager().getSkin());
    private final Button showConfirmPasswordButton = new Button(GameAssetManager.getGameAssetManager().getSkin());

    private boolean showPassword = false;

    private SignInController() {
        table.setFillParent(true);
        table.center();
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());

        table.row();
        table.add(usernameLabel).fillX();
        table.row();
        table.add(usernameField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(nicknameLabel).fillX();
        table.row();
        table.add(nicknameField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(passwordLabel).fillX();
        table.row();
        table.add(passwordField).fillX();
        table.add(showPasswordButton).fillX();
        table.row();
        table.add(confirmPasswordLabel).fillX();
        table.row();
        table.add(confirmPasswordField).fillX();
        table.add(showConfirmPasswordButton).fillX();
        table.row();
        table.add(randomizePasswordButton).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(emailLabel).fillX();
        table.row();
        table.add(emailField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(errorLabel).fillX();
        table.row();
        table.add(signUpButton).fillX();
        table.row();
        table.add(backToLoginMenuButton).fillX();

        errorLabel.setColor(Color.RED);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        confirmPasswordField.setPasswordCharacter('*');
        confirmPasswordField.setPasswordMode(true);
    }

    private void showPassword() {
        if (showPassword) {
            passwordField.setPasswordMode(true);
            confirmPasswordField.setPasswordMode(true);
            showPassword = false;
        } else {
            passwordField.setPasswordMode(false);
            confirmPasswordField.setPasswordMode(false);
            showPassword = true;
        }
    }

    private boolean checkForCorrectUsername() {
        String selectedUsername = usernameField.getText();

        if (selectedUsername.length() < 4) {
            errorLabel.setText("Username must be at least 4 characters long");
            return false;
        }
        if (selectedUsername.length() > 20) {
            errorLabel.setText("Username must be at most 20 characters long");
            return false;
        }
        if (!Regex.USERNAME.matches(selectedUsername)) {
            errorLabel.setText("Username must contain only letters, numbers and hyphens");
            return false;
        }
        return true;
    }

    private boolean checkForCorrectPassword() {
        String selectedPassword = passwordField.getText();

        if (selectedPassword.length() < 8) {
            errorLabel.setText("Password must be at least 8 characters long");
            return false;
        }
        if (selectedPassword.length() > 20) {
            errorLabel.setText("Password must be at most 20 characters long");
            return false;
        }
        if (!Regex.PASSWORD.matches(selectedPassword)) {
            errorLabel.setText("Your password must obey this regex" + Regex.PASSWORD.getPatternString());
            return false;
        }
        return true;
    }

    private boolean checkForCorrectConfirmPassword() {
        String selectedPassword = passwordField.getText();
        String selectedConfirmPassword = confirmPasswordField.getText();

        if (!selectedPassword.equals(selectedConfirmPassword)) {
            errorLabel.setText("Passwords do not match");
            return false;
        }
        return true;
    }

    private boolean checkForCorrectEmail() {
        String selectedEmail = emailField.getText();

        if (selectedEmail.length() < 8) {
            errorLabel.setText("Email must be at least 8 characters long");
            return false;
        }
        if (selectedEmail.length() > 50) {
            errorLabel.setText("Email must be at most 50 characters long");
            return false;
        }
        if (!Regex.EMAIL.matches(selectedEmail)) {
            errorLabel.setText("Email must obey this regex" + Regex.EMAIL.getPatternString());
            return false;
        }
        return true;
    }

//    private boolean checkForUsedUsername(){
//        try {
//            List<User> users = SaveUser.loadUsers();
//            for (User user : users){
//                if (user.getUsername().equals(usernameField.getText())){
//                    errorLabel.setText("Username is already used");
//                    return false;
//                }
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return true;
//    }

//    private boolean checkForUsedEmail(){
//        try {
//            List<User> users = SaveUser.loadUsers();
//            for (User user : users){
//                if (user.getEmail().equals(emailField.getText())){
//                    errorLabel.setText("Email is already used");
//                    return false;
//                }
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return true;
//    }

//    private boolean checkForUsedPassword(){
//        try {
//            List<User> users = SaveUser.loadUsers();
//            for (User user : users){
//                if (user.getPassword().equals(passwordField.getText())){
//                    errorLabel.setText("Password is already used by :" + user.getUsername());
//                    return false;
//                }
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return true;
//    }

    private boolean checkForWeakPassword() {
        String selectedPassword = passwordField.getText();
        if (selectedPassword.length() < 8) {
            errorLabel.setText("Password must be at least 8 characters long");
            return false;
        }
        if (selectedPassword.length() > 20) {
            errorLabel.setText("Password must be at most 20 characters long");
            return false;
        }
        if (!Regex.NUMBER.find(selectedPassword)) {
            errorLabel.setText("Password must contain at least one number");
            return false;
        }
        if (!Regex.ALPHABET.find(selectedPassword)) {
            errorLabel.setText("Password must contain at least one letter");
            return false;
        }
        if (!Regex.SPECIAL_CHARACTERS.find(selectedPassword)) {
            errorLabel.setText("Password must contain at least one special character");
            return false;
        }
        return true;

    }

    private void setSignUpButton(ProBending game) {
        signUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (checkForCorrectUsername() && checkForCorrectPassword() && checkForCorrectConfirmPassword() && checkForWeakPassword() && checkForCorrectEmail()) {
                    errorLabel.setText("");
                    String response = ProBending.client.communicate("signup " + usernameField.getText() + " " + passwordField.getText() + " " + emailField.getText() + " " + nicknameField.getText());
                    if (response.equals("Signed up successfully.")) {
                        if (emailField.getText().equals("probendingavatar@gmail.com"))
                            ProBending.client.communicate("sendSignupEmail");
                        ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                        ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                        Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                        ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(PickQuestionMenuController.getPickQuestionMenuController().getTable());

                        PickQuestionMenuController.getPickQuestionMenuController().setSelectedUsername(usernameField.getText());
                        PickQuestionMenuController.getPickQuestionMenuController().setSelectedPassword(passwordField.getText());
                        PickQuestionMenuController.getPickQuestionMenuController().setSelectedEmail(emailField.getText());
                        PickQuestionMenuController.getPickQuestionMenuController().setSelectedNickname(nicknameField.getText());
                    } else
                        errorLabel.setText(response);
                }
            }
        });
    }

    private void setShowPasswordButton() {
        showPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showPassword();
            }
        });
        showConfirmPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showPassword();
            }
        });
    }

    private void setBackToLoginMenuButton(ProBending game) {
        backToLoginMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(LoginController.getLoginController().getTable());
            }
        });
    }

    private void setRandomizePasswordButton(ProBending game) {
        randomizePasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String generatedPassword = RandomPasswordGenerator.generatePassword();

                passwordField.setText(generatedPassword);
                confirmPasswordField.setText(generatedPassword);
            }
        });
    }

    public void handleSignInMenuButtons(ProBending game) {
        setSignUpButton(game);
        setBackToLoginMenuButton(game);
        setRandomizePasswordButton(game);
        setShowPasswordButton();
    }

    //getters and setters

    public static SignInController getSignInController() {
        return signInController;
    }

    public Actor getTable() {
        return table;
    }
}
