package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Card.CardsInfo;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.ProBending;


public class PreGameController {
    private static PreGameController preGameController = new PreGameController(ScreenMasterSetting.getInstance().getPreGameScreen().getStage());
    private Table table = new Table();
    Card card = new Card(CardsInfo.Amon.getAbility() , CardsInfo.Amon.getName() , CardsInfo.Amon.getDescription() , CardsInfo.Amon.getPower() , CardsInfo.Amon.isHero() , new Texture(Gdx.files.internal(CardsInfo.Amon.getPictureLocation())) , new Sprite(new Texture(Gdx.files.internal(CardsInfo.Amon.getPictureLocation()))) , CardsInfo.Amon.getPlayingRow());
    private Table storeTable = new Table();
    private Table deckTable = new Table();
    ScrollPane storeScrollPane = new ScrollPane(storeTable);
    ScrollPane deckScrollPane = new ScrollPane(deckTable);

    private PreGameController(Stage stage) {
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();
        storeTable.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        storeTable.setFillParent(true);
        storeTable.center();
        deckTable.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        deckTable.setFillParent(true);
        deckTable.center();

        stage.addActor(table);
        stage.addActor(storeScrollPane);
        stage.addActor(deckScrollPane);
        storeTable.add(card);


        // Set the size and position of the ScrollPane objects
        storeScrollPane.setSize(stage.getWidth() / 3, stage.getHeight() / 2);
        storeScrollPane.setPosition(0, stage.getHeight() / 2);
        deckScrollPane.setSize(stage.getWidth() / 3, stage.getHeight() / 2);
        deckScrollPane.setPosition(stage.getWidth() / 2, 0);

        // Make the scroll bars always visible
        storeScrollPane.setFadeScrollBars(false);
        deckScrollPane.setFadeScrollBars(false);

    }


    public void handlePreGameController(ProBending game) {
    }

    public void drawCards(SpriteBatch batch){

    }

    //getters and setters

    public static PreGameController getPreGameController(){
        return preGameController;
    }

    public Actor getTable() {
        return table;
    }

    public Table getStoreTable() {
        return storeTable;
    }

    public Table getDeckTable() {
        return deckTable;
    }
}
