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

import java.util.ArrayList;


public class PreGameController {
    private static PreGameController preGameController = new PreGameController(ScreenMasterSetting.getInstance().getPreGameScreen().getStage());
    private final Table table = new Table();
    Card card = new Card(CardsInfo.Amon.getAbility() , CardsInfo.Amon.getName() , CardsInfo.Amon.getDescription() , CardsInfo.Amon.getPower() , CardsInfo.Amon.isHero() , new Texture(Gdx.files.internal(CardsInfo.Amon.getPictureLocation()))  , CardsInfo.Amon.getPlayingRow());
    Card card2 = new Card(CardsInfo.Amon.getAbility() , CardsInfo.Amon.getName() , CardsInfo.Amon.getDescription() , CardsInfo.Amon.getPower() , CardsInfo.Amon.isHero() , new Texture(Gdx.files.internal(CardsInfo.Amon.getPictureLocation()))  , CardsInfo.Amon.getPlayingRow());
    Card card3 = new Card(CardsInfo.Amon.getAbility() , CardsInfo.Amon.getName() , CardsInfo.Amon.getDescription() , CardsInfo.Amon.getPower() , CardsInfo.Amon.isHero() , new Texture(Gdx.files.internal(CardsInfo.Amon.getPictureLocation()))  , CardsInfo.Amon.getPlayingRow());
    private final Table storageTable = new Table();
    private final Table deckTable = new Table();
    private ScrollPane storageScrollPane = new ScrollPane(storageTable);
    private ScrollPane deckScrollPane = new ScrollPane(deckTable);
    private final Stage stage ;
    private ArrayList<Card> deckCards = new ArrayList<>();
    private ArrayList<Card> storageCards = new ArrayList<>();
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
        storageTable.top().left();
        storageScrollPane.setScrollingDisabled(true, false);

        deckTable.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        deckTable.setFillParent(true);
        deckTable.center();
        deckTable.top().left();
        deckScrollPane.setScrollingDisabled(true, false);

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

        addCardToStorage(card);
        addCardToStorage(card2);
        addCardToDeck(card3);
    }


    public void handlePreGameController(ProBending game) {

    }

    public void drawCards(SpriteBatch batch) {

    }

    public void addCardToDeck(Card card) {
        deckCards.add(card);
        deckTable.add(card).pad(10 , 10 , 10 , 10);
    }

    public void addCardToStorage(Card card) {
        storageCards.add(card);
        storageTable.add(card).pad(10 , 10 , 10 , 10);
    }

    public void removeCardFromDeck(Card card) {
        deckCards.remove(card);
        deckTable.removeActor(card);
    }

    public void removeCardFromStorage(Card card) {
        storageCards.remove(card);
        storageTable.removeActor(card);
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
