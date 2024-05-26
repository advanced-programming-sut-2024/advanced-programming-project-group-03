package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Card.CardsInfo;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.ProBending;


public class PreGameController {
    private static PreGameController preGameController = new PreGameController(ScreenMasterSetting.getInstance().getPreGameScreen().getStage());
    private Table table = new Table();
    Card card = new Card(CardsInfo.Amon.getAbility() , CardsInfo.Amon.getName() , CardsInfo.Amon.getDescription() , CardsInfo.Amon.getPower() , CardsInfo.Amon.isHero() , new Texture(Gdx.files.internal(CardsInfo.Amon.getPictureLocation()))  , CardsInfo.Amon.getPlayingRow());
    private Table storageTable = new Table();
    private Table deckTable = new Table();
    ScrollPane storageScrollPane = new ScrollPane(storageTable);
    ScrollPane deckScrollPane = new ScrollPane(deckTable);
    Stage stage ;
    Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    Pixmap pixmap2 = new Pixmap(1, 1, Pixmap.Format.RGBA8888);

    private PreGameController(Stage stage) {
        this.stage = stage;
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();

        storageTable.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        storageTable.setFillParent(true);
        storageTable.center();

        deckTable.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        deckTable.setFillParent(true);
        deckTable.center();

        stage.addActor(table);
        stage.addActor(storageScrollPane);
        stage.addActor(deckScrollPane);

        storageScrollPane.setPosition(0,0);
        deckScrollPane.setPosition((float) Gdx.graphics.getWidth() / 2,0);
        storageScrollPane.setSize(900, 700);
        deckScrollPane.setSize(900, 700);

        pixmap.setColor(Color.RED); // Replace with your color
        pixmap.fill();
        pixmap2.setColor(Color.BLUE); // Replace with your color
        pixmap2.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        TextureRegionDrawable drawable2 = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap2)));
        storageTable.setBackground(drawable);
        deckTable.setBackground(drawable2);

        deckTable.add(card);
        storageScrollPane.invalidate();
    }


    public void handlePreGameController(ProBending game) {

    }

    public void drawCards(SpriteBatch batch) {

    }

    //getters and setters

    public static PreGameController getPreGameController(){
        return preGameController;
    }

    public Table getTable() {
        return table;
    }

    public Table getStorageTable() {
        return storageTable;
    }

    public Table getDeckTable() {
        return deckTable;
    }

    public Stage getStage() {
        return stage;
    }
}
