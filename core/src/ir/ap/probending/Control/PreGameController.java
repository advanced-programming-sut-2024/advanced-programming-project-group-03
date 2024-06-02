package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Card.CardObjects;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.Factions.FactionObjects;
import ir.ap.probending.Model.PreGame;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.ProBending;
import java.util.ArrayList;


public class PreGameController {
    private static PreGameController preGameController = new PreGameController(ScreenMasterSetting.getInstance().getPreGameScreen().getStage());
    private final PreGame preGame = new PreGame();
    private final Table table = new Table();
    private final Table storageTable = new Table();
    private final Table deckTable = new Table();
    private final Window changeFactionWindow = new Window("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton changeFactionButton = new TextButton("Change Faction", GameAssetManager.getGameAssetManager().getSkin());
    private final ImageButton waterTribeButton = new ImageButton(GameAssetManager.getGameAssetManager().getSkin(), "toggle");
    private final ImageButton earthKingdomButton = new ImageButton(GameAssetManager.getGameAssetManager().getSkin(), "toggle");
    private final ImageButton fireNationButton = new ImageButton(GameAssetManager.getGameAssetManager().getSkin(), "toggle");
    private final ImageButton airNomadsButton = new ImageButton(GameAssetManager.getGameAssetManager().getSkin(), "toggle");
    private final ScrollPane storageScrollPane = new ScrollPane(storageTable);
    private final ScrollPane deckScrollPane = new ScrollPane(deckTable);
    private ScrollPane.ScrollPaneStyle scrollPaneStyle;
    private ScrollPane.ScrollPaneStyle storageScrollPaneStyle;
    private ScrollPane.ScrollPaneStyle deckScrollPaneStyle;
    private final Stage stage;
    private final Label powerSumLabel = new Label("Total Power: 0", GameAssetManager.getGameAssetManager().getSkin());
    private final Label cardCountLabel = new Label("Card Count: 0", GameAssetManager.getGameAssetManager().getSkin());
    private final Label specialCardCount = new Label("Special Cards : 0", GameAssetManager.getGameAssetManager().getSkin());
    private final Label normalCardCount = new Label("Unit Cards : 0", GameAssetManager.getGameAssetManager().getSkin());
    private final Label heroCardCount = new Label("Hero Cards : 0", GameAssetManager.getGameAssetManager().getSkin());
    private final Label deckCards = new Label("Deck", GameAssetManager.getGameAssetManager().getSkin());
    private final Label storageCards = new Label("Storage", GameAssetManager.getGameAssetManager().getSkin());

    private PreGameController(Stage stage) {
        this.stage = stage;
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();
        scrollPaneStyle = GameAssetManager.getGameAssetManager().getSkin().get("default", ScrollPane.ScrollPaneStyle.class);

        factionViewSetUp();
        labelsViewSetUp();
        //TODO have to implement load saved deck and faction

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

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(GameAssetManager.getGameAssetManager().getNations())));
        TextureRegionDrawable drawable2 = new TextureRegionDrawable(new TextureRegion(new Texture(GameAssetManager.getGameAssetManager().getNations())));
        storageTable.setBackground(drawable);
        deckTable.setBackground(drawable2);

    }

    public void handlePreGameController(ProBending game) {
        setChangeFactionButton();
    }

    public int getCardNumber(Card card) {
        ArrayList<Card> allCards;
        if (preGame.getDeckCards().contains(card)) {
            allCards = preGame.getDeckCards();
        } else if (preGame.getStorageCards().contains(card)) {
            allCards = preGame.getStorageCards();
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
        preGame.getDeckCards().add(card);
        sortCards();
        refreshDeckTable();
    }

    public void addCardToStorage(Card card) {
        preGame.getStorageCards().add(card);
        sortCards();
        refreshStorageTable();
    }

    public void removeCardFromDeck(Card card) {
        preGame.getDeckCards().remove(card);
        sortCards();
        refreshDeckTable();
    }

    public void removeCardFromStorage(Card card) {
        preGame.getStorageCards().remove(card);
        sortCards();
        refreshStorageTable();
    }

    public void refreshDeckTable() {
        refreshTables(deckTable, preGame.getDeckCards());
    }

    public void refreshStorageTable() {
        refreshTables(storageTable, preGame.getStorageCards());
    }

    private void refreshTables(Table storageTable, ArrayList<Card> storageCards) {
        try {
            ArrayList<String> cardNames = new ArrayList<>();
            storageTable.clear();
            storageTable.row();

            for (Card storageCard : storageCards) {
                if (cardNames.contains(storageCard.getName())) {
                    continue;
                }
                cardNames.add(storageCard.getName());
                if (cardNames.size() % 4 == 1) {
                    storageTable.row();
                }
                storageTable.add(storageCard).pad(10);
            }
            refreshLabels();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sortCards(){
        preGame.getStorageCards().sort((card1, card2) -> card2.getPower() - card1.getPower());
        preGame.getDeckCards().sort((card1, card2) -> card2.getPower() - card1.getPower());
    }
    public void refreshLabels() {
        int powerSum = 0;
        for (Card card : preGame.getDeckCards()) {
            powerSum += card.getPower();
        }
        powerSumLabel.setText("Power Sum: " + powerSum);
        cardCountLabel.setText("Card Count: " + preGame.getDeckCards().size());

        int specialCount = 0;
        int normalCount = 0;
        int heroCount = 0;

        for (Card card : preGame.getDeckCards()) {
            if (card.isHero()){
                heroCount++;
                normalCount++;
            }
            else if (card.getPlayingRow() == 6){
                specialCount++;
            }
            else {
                normalCount++;
            }
        }

        specialCardCount.setText("Special Cards: " + specialCount);
        normalCardCount.setText("Unit Cards: " + normalCount);
        heroCardCount.setText("Hero Cards: " + heroCount);
    }

    private void setChangeFactionButton() {
        changeFactionButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeFactionWindow.setVisible(true);
                changeFactionWindow.toFront();
                changeFactionWindow.setPosition((float) Gdx.graphics.getWidth() / 2 - changeFactionWindow.getWidth() / 2, 500);
            }
        });

        waterTribeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                preGame.changeFaction(FactionObjects.WATER.getFaction().clone());
                changeFactionWindow.setVisible(false);
            }
        });

        earthKingdomButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                preGame.changeFaction(FactionObjects.EARTH.getFaction().clone());
                changeFactionWindow.setVisible(false);
            }
        });

        fireNationButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                preGame.changeFaction(FactionObjects.FIRE.getFaction().clone());
                changeFactionWindow.setVisible(false);
            }
        });

        airNomadsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                preGame.changeFaction(FactionObjects.AIR.getFaction().clone());
                changeFactionWindow.setVisible(false);
            }
        });
    }

    private void factionViewSetUp(){
        stage.addActor(changeFactionButton);
        changeFactionButton.setPosition((float) Gdx.graphics.getWidth() / 2 - changeFactionButton.getWidth() / 2, 950);
        stage.addActor(changeFactionWindow);
        changeFactionWindow.setSize(1100, 500);
        changeFactionWindow.setVisible(false);
        changeFactionWindow.setMovable(false);
        changeFactionWindow.add(waterTribeButton).pad(10);
        changeFactionWindow.add(earthKingdomButton).pad(10);
        changeFactionWindow.add(fireNationButton).pad(10);
        changeFactionWindow.add(airNomadsButton).pad(10);

        Texture waterTribeTexture = new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getWaterTribeCard()));
        Texture earthKingdomTexture = new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getEarthKingdomCard()));
        Texture airNomadsTexture = new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getAirNomadsCard()));
        Texture fireNationTexture = new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getFireNationCard()));
        Drawable waterTribeDrawable = new TextureRegionDrawable(new TextureRegion(waterTribeTexture));
        Drawable earthKingdomDrawable = new TextureRegionDrawable(new TextureRegion(earthKingdomTexture));
        Drawable airNomadsDrawable = new TextureRegionDrawable(new TextureRegion(airNomadsTexture));
        Drawable fireNationDrawable = new TextureRegionDrawable(new TextureRegion(fireNationTexture));

        ImageButton.ImageButtonStyle waterTribeButtonStyle = new ImageButton.ImageButtonStyle(waterTribeButton.getStyle());
        waterTribeButtonStyle.imageUp = waterTribeDrawable;
        waterTribeButton.setStyle(waterTribeButtonStyle);

        ImageButton.ImageButtonStyle airNomadsButtonStyle = new ImageButton.ImageButtonStyle(airNomadsButton.getStyle());
        airNomadsButtonStyle.imageUp = airNomadsDrawable;
        airNomadsButton.setStyle(airNomadsButtonStyle);

        ImageButton.ImageButtonStyle fireNationButtonStyle = new ImageButton.ImageButtonStyle(fireNationButton.getStyle());
        fireNationButtonStyle.imageUp = fireNationDrawable;
        fireNationButton.setStyle(fireNationButtonStyle);

        ImageButton.ImageButtonStyle earthKingdomButtonStyle = new ImageButton.ImageButtonStyle(earthKingdomButton.getStyle());
        earthKingdomButtonStyle.imageUp = earthKingdomDrawable;
        earthKingdomButton.setStyle(earthKingdomButtonStyle);


    }

    private void labelsViewSetUp(){
        stage.addActor(powerSumLabel);
        stage.addActor(cardCountLabel);
        powerSumLabel.setPosition(60, 100);
        cardCountLabel.setPosition(360, 100);
        stage.addActor(specialCardCount);
        stage.addActor(normalCardCount);
        stage.addActor(heroCardCount);
        specialCardCount.setPosition(660, 100);
        normalCardCount.setPosition(960, 100);
        heroCardCount.setPosition(1260, 100);
        stage.addActor(deckCards);
        stage.addActor(storageCards);
        deckCards.setPosition((float) Gdx.graphics.getWidth() - 960, 900);
        storageCards.setPosition(60, 900);
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

    public PreGame getPreGame() {
        return preGame;
    }
}
