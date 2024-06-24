package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.Factions.FactionObjects;
import ir.ap.probending.Model.Game.PreGame;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.ProBending;
import java.util.ArrayList;


public class GameUIController {
    private final Table table = new Table();
    private static GameUIController gameUIController = new GameUIController(ScreenMasterSetting.getInstance().getPreGameScreen().getStage());
    private final Image boardImage = new Image(new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getGameBoardImage())));
    private final TextButton startGameButton = new TextButton("Start Gameawdawdadadawdawdawdawdawdawdawd", GameAssetManager.getGameAssetManager().getSkin());
    private final Table playerHandTable = new Table();
    private final ScrollPane playerHandScrollPane = new ScrollPane(playerHandTable);
    private ScrollPane.ScrollPaneStyle scrollPaneStyle;

    private GameUIController(Stage stage) {
        scrollPaneStyle = GameAssetManager.getGameAssetManager().getSkin().get("default", ScrollPane.ScrollPaneStyle.class);
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(GameAssetManager.getGameAssetManager().getNations())));

        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();
        table.addActor(boardImage);
        boardImage.setFillParent(true);
        boardImage.setPosition(0, 0);
        boardImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        playerHandTable.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        playerHandTable.top().left();
        playerHandScrollPane.setScrollingDisabled(true, false);
        playerHandScrollPane.setScrollbarsVisible(true);
        playerHandScrollPane.setFadeScrollBars(false);
        playerHandScrollPane.setSmoothScrolling(true);
        playerHandScrollPane.setScrollBarPositions(false, true);
        playerHandScrollPane.setStyle(scrollPaneStyle);
        playerHandTable.setBackground(drawable);
        playerHandScrollPane.setSize(950, 130);
        playerHandScrollPane.setPosition(580, 110);

        table.addActor(playerHandScrollPane);

    }

    public void handlePreGameController(ProBending game) {

    }

    //getters and setters
    public static GameUIController getGameUIController() {
        return gameUIController;
    }

    public Table getTable() {
        return table;
    }

    public Table getPlayerHandTable() {
        return playerHandTable;
    }
}
