package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.Data.Regex;
import ir.ap.probending.Model.Data.SaveUser;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.User;
import ir.ap.probending.ProBending;
import ir.ap.probending.Model.ScreenMasterSetting;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.List;

public class ProfileController {
    private static ProfileController profileController = new ProfileController();
    private Table table = new Table();
    private Label usernameLabel = new Label("Username : ", GameAssetManager.getGameAssetManager().getSkin());
    private Label nicknameLabel = new Label("Nickname : ", GameAssetManager.getGameAssetManager().getSkin());
    private Label rankLabel = new Label("Rank : ", GameAssetManager.getGameAssetManager().getSkin());
    private Label playedGamesLabel = new Label("Played Games : ", GameAssetManager.getGameAssetManager().getSkin());
    private Label wonGamesLabel = new Label("Won Games : ", GameAssetManager.getGameAssetManager().getSkin());
    private Label lostGamesLabel = new Label("Lost Games : ", GameAssetManager.getGameAssetManager().getSkin());
    private Label drawGamesLabel = new Label("Draw Games : ", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton backToMainMenuButton = new TextButton("Back", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton changePasswordButton = new TextButton("Change Password", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton changeNicknameButton = new TextButton("Change Nickname", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton changeUsernameButton = new TextButton("Change Username", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton changeEmailButton = new TextButton("Change Email", GameAssetManager.getGameAssetManager().getSkin());
    private Window changePasswordWindow = new Window("", GameAssetManager.getGameAssetManager().getSkin());
    private Window changeNicknameWindow = new Window("", GameAssetManager.getGameAssetManager().getSkin());
    private Window changeUsernameWindow = new Window("", GameAssetManager.getGameAssetManager().getSkin());
    private Window changeEmailWindow = new Window("", GameAssetManager.getGameAssetManager().getSkin());
    private Label changeEmailLabel = new Label("Enter new email", GameAssetManager.getGameAssetManager().getSkin());
    private Label changeUsernameLabel = new Label("Enter new username", GameAssetManager.getGameAssetManager().getSkin());
    private Label changeNicknameLabel = new Label("Enter new nickname", GameAssetManager.getGameAssetManager().getSkin());
    private Label changePasswordLabel = new Label("Enter new password", GameAssetManager.getGameAssetManager().getSkin());
    private Label changePasswordLabel2 = new Label("Enter old password", GameAssetManager.getGameAssetManager().getSkin());
    private TextField changeEmailField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private TextField changeUsernameField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private TextField changeNicknameField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private TextField changePasswordField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private TextField changePasswordField2 = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton submitEmailButton = new TextButton("Submit", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton submitUsernameButton = new TextButton("Submit", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton submitNicknameButton = new TextButton("Submit", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton submitPasswordButton = new TextButton("Submit", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton backToProfileButtonChangeUsername = new TextButton("Back", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton backToProfileButtonChangeNickname = new TextButton("Back", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton backToProfileButtonChangeEmail = new TextButton("Back", GameAssetManager.getGameAssetManager().getSkin());
    private TextButton backToProfileButtonChangePassword = new TextButton("Back", GameAssetManager.getGameAssetManager().getSkin());
    private Label errorLabelPassword = new Label("", GameAssetManager.getGameAssetManager().getSkin());
    private Label errorLabelNickname = new Label("", GameAssetManager.getGameAssetManager().getSkin());
    private Label errorLabelUsername = new Label("", GameAssetManager.getGameAssetManager().getSkin());
    private Label errorLabelEmail = new Label("", GameAssetManager.getGameAssetManager().getSkin());


    private ProfileController() {
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();

        if (GameMaster.getGameMaster().getGuestUser1() != null){
            table.add(usernameLabel).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(nicknameLabel).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(rankLabel).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(playedGamesLabel).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(wonGamesLabel).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(lostGamesLabel).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(drawGamesLabel).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(changePasswordButton).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(changeNicknameButton).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(changeUsernameButton).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(changeEmailButton).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(backToMainMenuButton).fillX();

            usernameLabel.setText("Username : " + GameMaster.getGameMaster().getLoggedInUser1().getUsername());
            nicknameLabel.setText("Nickname : " + GameMaster.getGameMaster().getLoggedInUser1().getNickname());
            rankLabel.setText("Rank : " + GameMaster.getGameMaster().getLoggedInUser1().getRank());
            playedGamesLabel.setText("Played Games : " + GameMaster.getGameMaster().getLoggedInUser1().getGamePlayedCount());
            wonGamesLabel.setText("Won Games : " + GameMaster.getGameMaster().getLoggedInUser1().getGameWonCount());
            lostGamesLabel.setText("Lost Games : " + GameMaster.getGameMaster().getLoggedInUser1().getGameLostCount());
            drawGamesLabel.setText("Draw Games : " + (GameMaster.getGameMaster().getLoggedInUser1().getGamePlayedCount() - GameMaster.getGameMaster().getLoggedInUser1().getGameWonCount() - GameMaster.getGameMaster().getLoggedInUser1().getGameLostCount()));

        }
        else {
            table.add(new Label("You are not logged in", GameAssetManager.getGameAssetManager().getSkin())).fillX();
            table.row().pad(10, 0, 10, 0);
            table.add(backToMainMenuButton).fillX();
        }

        changePasswordWindow.add(changePasswordLabel2).fillX();
        changePasswordWindow.row().pad(10, 0, 10, 0);
        changePasswordWindow.add(changePasswordField2).fillX();
        changePasswordWindow.row().pad(10, 0, 10, 0);
        changePasswordWindow.add(changePasswordLabel).fillX();
        changePasswordWindow.row().pad(10, 0, 10, 0);
        changePasswordWindow.add(changePasswordField).fillX();
        changePasswordWindow.row().pad(10, 0, 10, 0);
        changePasswordWindow.add(errorLabelPassword).fillX();
        changePasswordWindow.row().pad(10, 0, 10, 0);
        changePasswordWindow.add(submitPasswordButton).fillX();
        changePasswordWindow.row().pad(10, 0, 10, 0);
        changePasswordWindow.add(backToProfileButtonChangePassword).fillX();

        changeNicknameWindow.add(changeNicknameLabel).fillX();
        changeNicknameWindow.row().pad(10, 0, 10, 0);
        changeNicknameWindow.add(changeNicknameField).fillX();
        changeNicknameWindow.row().pad(10, 0, 10, 0);
        changeNicknameWindow.add(errorLabelNickname).fillX();
        changeNicknameWindow.row().pad(10, 0, 10, 0);
        changeNicknameWindow.add(submitNicknameButton).fillX();
        changeNicknameWindow.row().pad(10, 0, 10, 0);
        changeNicknameWindow.add(backToProfileButtonChangeNickname).fillX();

        changeUsernameWindow.add(changeUsernameLabel).fillX();
        changeUsernameWindow.row().pad(10, 0, 10, 0);
        changeUsernameWindow.add(changeUsernameField).fillX();
        changeUsernameWindow.row().pad(10, 0, 10, 0);
        changeUsernameWindow.add(errorLabelUsername).fillX();
        changeUsernameWindow.row().pad(10, 0, 10, 0);
        changeUsernameWindow.add(submitUsernameButton).fillX();
        changeUsernameWindow.row().pad(10, 0, 10, 0);
        changeUsernameWindow.add(backToProfileButtonChangeUsername).fillX();

        changeEmailWindow.add(changeEmailLabel).fillX();
        changeEmailWindow.row().pad(10, 0, 10, 0);
        changeEmailWindow.add(changeEmailField).fillX();
        changeEmailWindow.row().pad(10, 0, 10, 0);
        changeEmailWindow.add(errorLabelEmail).fillX();
        changeEmailWindow.row().pad(10, 0, 10, 0);
        changeEmailWindow.add(submitEmailButton).fillX();
        changeEmailWindow.row().pad(10, 0, 10, 0);
        changeEmailWindow.add(backToProfileButtonChangeEmail).fillX();

        table.addActor(changePasswordWindow);
        changePasswordWindow.setVisible(false);
        table.addActor(changeNicknameWindow);
        changeNicknameWindow.setVisible(false);
        table.addActor(changeUsernameWindow);
        changeUsernameWindow.setVisible(false);
        table.addActor(changeEmailWindow);
        changeEmailWindow.setVisible(false);

        changeEmailWindow.setSize(700, 700);
        changeUsernameWindow.setSize(700, 700);
        changeNicknameWindow.setSize(700, 700);
        changePasswordWindow.setSize(700, 700);

        errorLabelEmail.setColor(1, 0, 0, 1);
        errorLabelUsername.setColor(1, 0, 0, 1);
        errorLabelNickname.setColor(1, 0, 0, 1);
        errorLabelPassword.setColor(1, 0, 0, 1);
    }

    private void setChangePasswordButton(ProBending game){
        changePasswordButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changePasswordWindow.setPosition(Gdx.graphics.getWidth()/2 - changePasswordWindow.getWidth()/2, Gdx.graphics.getHeight()/2 - changePasswordWindow.getHeight()/2);
                changePasswordWindow.setVisible(true);
            }
        });
    }

    private void setChangeNicknameButton(ProBending game){
        changeNicknameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeNicknameWindow.setPosition(Gdx.graphics.getWidth()/2 - changeNicknameWindow.getWidth()/2, Gdx.graphics.getHeight()/2 - changeNicknameWindow.getHeight()/2);
                changeNicknameWindow.setVisible(true);
            }
        });
    }

    private void setChangeUsernameButton(ProBending game){
        changeUsernameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeUsernameWindow.setPosition(Gdx.graphics.getWidth()/2 - changeUsernameWindow.getWidth()/2, Gdx.graphics.getHeight()/2 - changeUsernameWindow.getHeight()/2);
                changeUsernameWindow.setVisible(true);
            }
        });
    }

    private void setChangeEmailButton(ProBending game){
        changeEmailButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeEmailWindow.setPosition(Gdx.graphics.getWidth()/2 - changeEmailWindow.getWidth()/2, Gdx.graphics.getHeight()/2 - changeEmailWindow.getHeight()/2);
                changeEmailWindow.setVisible(true);
            }
        });
    }

    private void setSubmitEmailButton(ProBending game){
        submitEmailButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String email = changeEmailField.getText();
                if (email.equals(GameMaster.getGameMaster().getLoggedInUser1().getEmail())){
                    errorLabelEmail.setText("New email can not be the same as old email");
                }
                else if (Regex.EMAIL.matches(email)){
                    GameMaster.getGameMaster().getLoggedInUser1().setEmail(email);
                    SaveUser.updateUser(GameMaster.getGameMaster().getLoggedInUser1());
                    changeEmailWindow.setVisible(false);
                }
                else {
                    errorLabelEmail.setText("Invalid Email");
                }
            }
        });
    }

    private void setSubmitUsernameButton(ProBending game){
        submitUsernameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (changeUsernameField.getText().equals(GameMaster.getGameMaster().getLoggedInUser1().getUsername())){
                    errorLabelUsername.setText("New username can not be the same as old username");
                }
                else {
                    GameMaster.getGameMaster().getLoggedInUser1().setUsername(changeUsernameField.getText());
                    SaveUser.updateUser2(GameMaster.getGameMaster().getLoggedInUser1());
                    changeUsernameWindow.setVisible(false);
                }
            }
        });
    }

    private void setSubmitNicknameButton(ProBending game){
        submitNicknameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (changeNicknameField.getText().equals(GameMaster.getGameMaster().getLoggedInUser1().getNickname())){
                    errorLabelNickname.setText("New nickname can not be the same as old nickname");
                }
                else {
                    GameMaster.getGameMaster().getLoggedInUser1().setNickname(changeNicknameField.getText());
                    SaveUser.updateUser(GameMaster.getGameMaster().getLoggedInUser1());
                    changeNicknameWindow.setVisible(false);
                }
            }
        });
    }

    private void setSubmitPasswordButton(ProBending game){
        submitPasswordButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (changePasswordField.getText().equals(changePasswordField2.getText())){
                    errorLabelPassword.setText("New password can not be the same as old password");
                }
                else if (!GameMaster.getGameMaster().getLoggedInUser1().getPassword().equals(changePasswordField2.getText())){
                    errorLabelPassword.setText("Invalid old Password");
                }
                else if (!Regex.PASSWORD.matches(changePasswordField.getText())){
                    errorLabelPassword.setText("Password must obey this regex : " + Regex.PASSWORD.getPatternString());
                }
                else {
                    GameMaster.getGameMaster().getLoggedInUser1().setPassword(changePasswordField.getText());
                    SaveUser.updateUser(GameMaster.getGameMaster().getLoggedInUser1());
                    changePasswordWindow.setVisible(false);
                }
            }
        });
    }

    private void setBackToProfileButtonChangeUsername(ProBending game){
        backToProfileButtonChangeUsername.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeUsernameWindow.setVisible(false);
            }
        });
    }

    private void setBackToProfileButtonChangeNickname(ProBending game){
        backToProfileButtonChangeNickname.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeNicknameWindow.setVisible(false);
            }
        });
    }

    private void setBackToProfileButtonChangeEmail(ProBending game){
        backToProfileButtonChangeEmail.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeEmailWindow.setVisible(false);
            }
        });
    }

    private void setBackToProfileButtonChangePassword(ProBending game){
        backToProfileButtonChangePassword.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changePasswordWindow.setVisible(false);
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

    public void updateLabels(){
        usernameLabel.setText("Username : " + GameMaster.getGameMaster().getLoggedInUser1().getUsername());
        nicknameLabel.setText("Nickname : " + GameMaster.getGameMaster().getLoggedInUser1().getNickname());
        rankLabel.setText("Rank : " + GameMaster.getGameMaster().getLoggedInUser1().getRank());
        playedGamesLabel.setText("Played Games : " + GameMaster.getGameMaster().getLoggedInUser1().getGamePlayedCount());
        wonGamesLabel.setText("Won Games : " + GameMaster.getGameMaster().getLoggedInUser1().getGameWonCount());
        lostGamesLabel.setText("Lost Games : " + GameMaster.getGameMaster().getLoggedInUser1().getGameLostCount());
        drawGamesLabel.setText("Draw Games : " + (GameMaster.getGameMaster().getLoggedInUser1().getGamePlayedCount() - GameMaster.getGameMaster().getLoggedInUser1().getGameWonCount() - GameMaster.getGameMaster().getLoggedInUser1().getGameLostCount()));

    }

    public void handleProfileButtons(ProBending game){
        setChangePasswordButton(game);
        setChangeNicknameButton(game);
        setChangeUsernameButton(game);
        setChangeEmailButton(game);
        setSubmitEmailButton(game);
        setSubmitUsernameButton(game);
        setSubmitNicknameButton(game);
        setSubmitPasswordButton(game);
        setBackToMainMenuButton(game);
        setBackToProfileButtonChangeUsername(game);
        setBackToProfileButtonChangeNickname(game);
        setBackToProfileButtonChangeEmail(game);
        setBackToProfileButtonChangePassword(game);
    }

    //getters and setters

    public static ProfileController getProfileController(){
        return profileController;
    }

    public Actor getTable() {
        return table;
    }
}
