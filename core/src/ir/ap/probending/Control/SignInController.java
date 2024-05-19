package ir.ap.probending.Control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ir.ap.probending.Model.GameAssetManager;
import ir.ap.probending.ProBending;

public class SignInController {
    private static SignInController signInController = new SignInController();
    private Table table = new Table();
    private final TextButton playButton = new TextButton("Play", GameAssetManager.getGameAssetManager().getSkin());

    private SignInController() {
        table.addActor(GameAssetManager.getGameAssetManager().getBackground());
        table.setFillParent(true);
        table.center();
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.add(playButton).fillX();
        table.row().pad(10, 0, 10, 0);

    }

    private void playButton(ProBending game){
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("You ");
            }
        });
    }

    public void handleSignInMenuButtons(ProBending game) {
        playButton(game);
    }

    //getters and setters

    public static SignInController getSignInController(){
        return signInController;
    }

    public Actor getTable() {
        return table;
    }
}
