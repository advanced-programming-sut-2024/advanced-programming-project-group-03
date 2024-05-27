package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private final Table storageTable = new Table();
    private final Table deckTable = new Table();
    private ScrollPane storageScrollPane = new ScrollPane(storageTable);
    private ScrollPane deckScrollPane = new ScrollPane(deckTable);
    private ScrollPane.ScrollPaneStyle scrollPaneStyle;
    private ScrollPane.ScrollPaneStyle storageScrollPaneStyle;
    private ScrollPane.ScrollPaneStyle deckScrollPaneStyle;
    private final Stage stage;
    private ArrayList<Card> deckCards = new ArrayList<>();
    private ArrayList<Card> storageCards = new ArrayList<>();
    Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    Pixmap pixmap2 = new Pixmap(1, 1, Pixmap.Format.RGBA8888);

    private PreGameController(Stage stage) {
        this.stage = stage;
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();
        scrollPaneStyle = GameAssetManager.getGameAssetManager().getSkin().get("default", ScrollPane.ScrollPaneStyle.class);

        // for testing
        setUpCards();

        storageTable.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        storageTable.top().left();
        storageScrollPane.setScrollingDisabled(true, false);
        storageScrollPane.setScrollbarsVisible(true);
        storageScrollPane.setFadeScrollBars(false);
        storageScrollPane.setSmoothScrolling(true);
        storageScrollPane.setScrollBarPositions(false, true);
        storageScrollPaneStyle = new ScrollPane.ScrollPaneStyle(scrollPaneStyle);
        storageScrollPane.setStyle(storageScrollPaneStyle);

        deckTable.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        deckTable.top().left();
        deckScrollPane.setScrollingDisabled(true, false);
        deckScrollPane.setScrollbarsVisible(true);
        deckScrollPane.setFadeScrollBars(false);
        deckScrollPane.setSmoothScrolling(true);
        deckScrollPane.setScrollBarPositions(false, true);
        deckScrollPaneStyle = new ScrollPane.ScrollPaneStyle(scrollPaneStyle);
        deckScrollPane.setStyle(deckScrollPaneStyle);

        stage.addActor(table);
        stage.addActor(storageScrollPane);
        stage.addActor(deckScrollPane);

        storageScrollPane.setPosition(60, 190);
        deckScrollPane.setPosition((float) Gdx.graphics.getWidth() - 960, 190);
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

    }

    public void handlePreGameController(ProBending game) {

    }

    public void drawCards(SpriteBatch batch) {

    }

    public int getCardNumber(Card card) {
        ArrayList<Card> allCards;
        if (deckCards.contains(card)) {
            allCards = deckCards;
        } else if (storageCards.contains(card)) {
            allCards = storageCards;
        } else {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i).getName().equals(card.getName())) {
                count++;
            }
        }
        return count;
    }

    public void addCardToDeck(Card card) {
        deckCards.add(card);
        refreshDeckTable();
        refreshStorageTable();
    }

    public void addCardToStorage(Card card) {
        storageCards.add(card);
        refreshStorageTable();
        refreshDeckTable();
    }

    public void removeCardFromDeck(Card card) {
        deckCards.remove(card);
        refreshDeckTable();
        refreshStorageTable();
    }

    public void removeCardFromStorage(Card card) {
        storageCards.remove(card);
        refreshStorageTable();
        refreshDeckTable();
    }

    private void refreshDeckTable() {
        try{
            ArrayList<String> cardNames = new ArrayList<>();
            deckTable.clear();
            int count = deckCards.size();
            for (int i = 0; i < count; i++) {
                if (cardNames.contains(deckCards.get(i).getName())) {
                    count--;
                    continue;
                }
                cardNames.add(deckCards.get(i).getName());
                if (cardNames.size() % 4 == 0) {
                    deckTable.row();
                }
                deckTable.add(deckCards.get(i)).pad(10);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void refreshStorageTable() {
    try {
        ArrayList<String> cardNames = new ArrayList<>();
        storageTable.clear();
        int count = storageCards.size();
        for (int i = 0; i < count; i++) {
            if (cardNames.contains(storageCards.get(i).getName())) { // Use storageCards instead of deckCards
                count--;
                continue;
            }
            cardNames.add(storageCards.get(i).getName());
            if (cardNames.size() % 4 == 0) {
                storageTable.row();
            }
            storageTable.add(storageCards.get(i)).pad(10);
        }
    }
    catch (Exception e){
        e.printStackTrace();
    }
}

    //getters and setters

    public static PreGameController getPreGameController() {
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

    // for testing
    private void setUpCards() {
        Card card = new Card(CardsInfo.Amon.getAbility(), CardsInfo.Amon.getName(), CardsInfo.Amon.getDescription(), CardsInfo.Amon.getPower(), CardsInfo.Amon.isHero(), new Texture(Gdx.files.internal(CardsInfo.Amon.getPictureLocation())), CardsInfo.Amon.getPlayingRow());
        for (int i = 0; i < 20; i++) {
            addCardToDeck(card.clone());
        }
        for (int i = 0; i < 10; i++) {
            addCardToStorage(card.clone());
        }
    }
}
