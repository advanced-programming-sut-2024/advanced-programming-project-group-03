package ir.ap.probending.Control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ir.ap.probending.Model.Data.Regex;
import ir.ap.probending.Model.Data.SaveUser;
import ir.ap.probending.Model.Data.SecurityQuestions;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.User;
import ir.ap.probending.ProBending;
import ir.ap.probending.Model.ScreenMasterSetting;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.Gdx;

public class PickQuestionMenuController {
    private static PickQuestionMenuController pickQuestionMenuController = new PickQuestionMenuController();
    private Table table = new Table();
    private final SelectBox<String> questionSelector = new SelectBox<>(GameAssetManager.getGameAssetManager().getSkin());
    private final TextField answerField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton submitAnswer = new TextButton("Submit Answer", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton backToSignUpMenu = new TextButton("Back", GameAssetManager.getGameAssetManager().getSkin());
    Label errorLabel = new Label("", GameAssetManager.getGameAssetManager().getSkin());

    private String selectedUsername;
    private String selectedPassword;
    private String selectedEmail;
    private String selectedNickname;

    private PickQuestionMenuController() {
        table.addActor(GameAssetManager.getGameAssetManager().getBackground());
        table.setFillParent(true);
        table.center();
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());

        table.row().pad(10, 0, 10, 0);
        table.add(questionSelector).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(answerField).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(errorLabel).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(submitAnswer).fillX();
        table.row().pad(10, 0, 10, 0);
        table.add(backToSignUpMenu).fillX();

        questionSelector.setItems(SecurityQuestions.getQuestions().toArray(new String[0]));

        errorLabel.setColor(Color.RED);
    }

    private void setSubmitAnswerButton(ProBending game){
        submitAnswer.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Regex.VALIDANSWER.matches(answerField.getText()) && questionSelector.getSelectedIndex() != 0) {
                    User user = new User(selectedUsername, selectedPassword, selectedEmail, selectedNickname);
                    user.addQuestionAnswer(questionSelector.getSelectedIndex() , answerField.getText());

                    SaveUser.saveUser(user);

                    ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().clear();
                    ScreenMasterSetting.getInstance().getMainMenuScreen().setStage(new Stage(new ScreenViewport()));
                    Gdx.input.setInputProcessor(ScreenMasterSetting.getInstance().getMainMenuScreen().getStage());
                    ScreenMasterSetting.getInstance().getMainMenuScreen().getStage().addActor(MainMenuController.getMainMenuController().getTable());

                    selectedEmail = "";
                    selectedNickname = "";
                    selectedPassword = "";
                    selectedUsername = "";
                }
                else {
                    errorLabel.setText("Invalid Answer or question not selected!");
                }
            }
        });
    }

    public void handlePickQuestionMenuButtons(ProBending game) {
        setSubmitAnswerButton(game);
    }

    //getters and setters

    public static PickQuestionMenuController getPickQuestionMenuController(){
        return pickQuestionMenuController;
    }

    public Actor getTable() {
        return table;
    }

    public void setSelectedUsername(String selectedUsername) {
        this.selectedUsername = selectedUsername;
    }

    public void setSelectedPassword(String selectedPassword) {
        this.selectedPassword = selectedPassword;
    }

    public void setSelectedEmail(String selectedEmail) {
        this.selectedEmail = selectedEmail;
    }

    public void setSelectedNickname(String selectedNickname) {
        this.selectedNickname = selectedNickname;
    }
}
