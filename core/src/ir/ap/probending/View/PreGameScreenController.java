package ir.ap.probending.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import ir.ap.probending.Model.Data.SaveUser;
import ir.ap.probending.Model.Factions.Faction;
import ir.ap.probending.Model.Factions.FactionObjects;
import ir.ap.probending.Control.GameController;
import ir.ap.probending.Control.PreGameController;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.Model.User;
import ir.ap.probending.ProBending;
import java.util.ArrayList;
import java.util.List;


public class PreGameScreenController {
    private static PreGameScreenController preGameScreenController = new PreGameScreenController(ScreenMasterSetting.getInstance().getPreGameScreen().getStage());
    private final Table table = new Table();
    private final Table storageTable = new Table();
    private final Table deckTable = new Table();
    private final Window changeFactionWindow = new Window("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton changeFactionButton = new TextButton("Change Faction", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton chooseLeaderButton = new TextButton("Choose Leader", GameAssetManager.getGameAssetManager().getSkin());
    private final Label saveDeckLabel = new Label("Save Deck", GameAssetManager.getGameAssetManager().getSkin() , "title");
    private final Label loadDeckLabel = new Label("Load Deck", GameAssetManager.getGameAssetManager().getSkin() , "title");
    private final TextButton saveDeckButton = new TextButton("Save Deck", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton loadDeckButton = new TextButton("Load Deck", GameAssetManager.getGameAssetManager().getSkin());
    private final Window saveDeckWindow = new Window("", GameAssetManager.getGameAssetManager().getSkin());
    private final Window loadDeckWindow = new Window("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton saveDeckToFileButton = new TextButton("Save To Json File", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton saveDeckToDBButton = new TextButton("Save To Database", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton closeSaveDeck = new TextButton("Close", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton closeLoadDeck = new TextButton("Close", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton loadDeckFromFileButton = new TextButton("Load From Json File", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton loadDeckFromDBButton = new TextButton("Load From Database", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField saveDeckTextField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField loadDeckTextField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton applyLeaderButton = new TextButton("Apply", GameAssetManager.getGameAssetManager().getSkin());
    private final Window chooseLeaderWindow = new Window("", GameAssetManager.getGameAssetManager().getSkin());
    private final Label leaderLabel = new Label("Choose your Leader Ability", GameAssetManager.getGameAssetManager().getSkin() , "title");
    private final SelectBox<String> leaderSelectBox = new SelectBox<>(GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton backToMainMenuButton = new TextButton("Back To Main Menu", GameAssetManager.getGameAssetManager().getSkin());
    private final ImageButton waterTribeButton = new ImageButton(GameAssetManager.getGameAssetManager().getSkin(), "toggle");
    private final ImageButton earthKingdomButton = new ImageButton(GameAssetManager.getGameAssetManager().getSkin(), "toggle");
    private final ImageButton fireNationButton = new ImageButton(GameAssetManager.getGameAssetManager().getSkin(), "toggle");
    private final ImageButton airNomadsButton = new ImageButton(GameAssetManager.getGameAssetManager().getSkin(), "toggle");
    private final TextButton playGameButton = new TextButton("Play Game", GameAssetManager.getGameAssetManager().getSkin());
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
    private final Label leaderAbility = new Label("Leader Ability", GameAssetManager.getGameAssetManager().getSkin());
    private final Label waterFactionAbilitiyLabel = new Label("Water : Draw a card from deck whenever you win a set", GameAssetManager.getGameAssetManager().getSkin());
    private final Label earthFactionAbilitiyLabel = new Label("Earth : Decides who takes the first turn", GameAssetManager.getGameAssetManager().getSkin());
    private final Label fireFactionAbilitiyLabel = new Label("Fire : Keeps a random unit card after each round", GameAssetManager.getGameAssetManager().getSkin());
    private final Label airFactionAbilitiyLabel = new Label("Air : Wins any round that ends in a draw", GameAssetManager.getGameAssetManager().getSkin());
    private final Label airFactionAbilitiyLabel2 = new Label("Air : 2 random cards from burnt cards are placed in the battle field at the start of 3rd set", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton chooseOpponentButton = new TextButton("Choose Opponent", GameAssetManager.getGameAssetManager().getSkin());
    private final Window chooseOpponentWindow = new Window("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton submitOpponentButton = new TextButton("Submit", GameAssetManager.getGameAssetManager().getSkin());
    private final Label opponentLabel = new Label("Enter Opponent Name", GameAssetManager.getGameAssetManager().getSkin());
    private final TextField opponentTextField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
    private final Label errorChooseOppLabel = new Label("", GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton closeChooseOpponent = new TextButton("Close", GameAssetManager.getGameAssetManager().getSkin());
    private boolean usedBackButton = false;

    private PreGameScreenController(Stage stage) {
        this.stage = stage;
        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();
        scrollPaneStyle = GameAssetManager.getGameAssetManager().getSkin().get("default", ScrollPane.ScrollPaneStyle.class);

        factionViewSetUp();
        labelsViewSetUp();
        leaderViewSetup();
        saveDeckViewSetUp();
        loadDeckViewSetUp();
        setChooseOpponentButton();

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

        stage.addActor(playGameButton);
        playGameButton.setPosition(1500 , 50);

    }

    public void update(int specialCount){

        if (PreGameController.getPreGame().getDeckCards().size() < 12){
            playGameButton.setDisabled(true);
            playGameButton.setVisible(false);
            cardCountLabel.setColor(Color.RED);
        }
        else {
            playGameButton.setDisabled(false);
            playGameButton.setVisible(true);
            cardCountLabel.setColor(heroCardCount.getColor());
            if (specialCount > 3){
                specialCardCount.setColor(Color.RED);
                playGameButton.setDisabled(true);
                playGameButton.setVisible(false);
            }
            else {
                specialCardCount.setColor(heroCardCount.getColor());
                playGameButton.setDisabled(false);
                playGameButton.setVisible(true);
            }
        }
        if (specialCount > 3){
            specialCardCount.setColor(Color.RED);
        }
        else {
            specialCardCount.setColor(heroCardCount.getColor());
        }
    }

    public void setPlayGameButton(ProBending game){
        playGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameController.getGame().startGame();
                game.setScreen(ScreenMasterSetting.getInstance().getGameScreen());
                GameUIController.getGameUIController().resetGameUI();
                //veto cards
                GameUIController.getGameUIController().activateCardListWindow();
                ArrayList<Card> vetoCards = new ArrayList<>();
                for (Card card : GameController.getGame().getGameBoard().getPlayer1().getHand()) {
                    vetoCards.add(card.clone5());
                }
                GameUIController.getGameUIController().addCardsToCardListWindow(vetoCards);

                //set leaders and factions
                GameController.getGame().getGameBoard().getPlayer1Board().setLeader(PreGameController.getPreGame().getSelectedLeader().clone6());
                GameController.getGame().getGameBoard().getPlayer1Board().setFaction(PreGameController.getPreGame().getPlayerFaction());

                GameUIController.getGameUIController().addLeadersToLeaderTable1( GameController.getGame().getGameBoard().getPlayer1Board().getLeader());
                GameUIController.getGameUIController().addLeadersToLeaderTable2( GameController.getGame().getGameBoard().getPlayer2Board().getLeader());
                GameUIController.getGameUIController().setCurrentTurnPlayerUsername(GameController.getGame().getCurrentPlayer().getUser().getUsername() + " 's turn");
                selectRandomCardsAndFactionForPlayer2();

                //add hand cards of current player to view
                GameController.getGame().setUpHandView(GameController.getGame().getCurrentPlayer());

                //setup views that are dependent to gameboard
                GameUIController.getGameUIController().addUsernameLabels();
                GameUIController.getGameUIController().updateRows();
            }
        });
    }

    private void selectRandomCardsAndFactionForPlayer2() {
        GameController.getGame().getGameBoard().getPlayer2Board().setFaction(Faction.getRandomFaction());
        GameController.getGame().getGameBoard().getPlayer2Board().setLeader(PreGameController.getPreGame().getRandomLeader(GameController.getGame().getGameBoard().getPlayer2Board().getFaction()).clone6());
        GameController.getGame().getGameBoard().getPlayer2().addCardsToDeck(PreGameController.getPreGame().getRandomDeckCards(GameController.getGame().getGameBoard().getPlayer2Board().getFaction()));
    }

    public void handlePreGameController(ProBending game) {
        setPlayGameButton(game);
        setChangeFactionButton();
        setBackToMainMenuButton(game);
    }

    public int getCardNumber(Card card) {
        ArrayList<Card> allCards;
        if (PreGameController.getPreGame().getDeckCards().contains(card)) {
            allCards = PreGameController.getPreGame().getDeckCards();
        } else if (PreGameController.getPreGame().getStorageCards().contains(card)) {
            allCards = PreGameController.getPreGame().getStorageCards();
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
        PreGameController.getPreGame().getDeckCards().add(card);
        sortCards();
        refreshDeckTable();
    }

    public void addCardToStorage(Card card) {
        PreGameController.getPreGame().getStorageCards().add(card);
        sortCards();
        refreshStorageTable();
    }

    public void removeCardFromDeck(Card card) {
        PreGameController.getPreGame().getDeckCards().remove(card);
        sortCards();
        refreshDeckTable();
    }

    public void removeCardFromStorage(Card card) {
        PreGameController.getPreGame().getStorageCards().remove(card);
        sortCards();
        refreshStorageTable();
    }

    public void refreshDeckTable() {
        refreshTables(deckTable, PreGameController.getPreGame().getDeckCards());
    }

    public void refreshStorageTable() {
        refreshTables(storageTable, PreGameController.getPreGame().getStorageCards());
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
        PreGameController.getPreGame().getStorageCards().sort((card1, card2) -> card2.getPower() - card1.getPower());
        PreGameController.getPreGame().getDeckCards().sort((card1, card2) -> card2.getPower() - card1.getPower());
    }
    public void refreshLabels() {
        int powerSum = 0;
        for (Card card : PreGameController.getPreGame().getDeckCards()) {
            powerSum += card.getPower();
        }
        powerSumLabel.setText("Power Sum: " + powerSum);
        cardCountLabel.setText("Card Count: " + PreGameController.getPreGame().getDeckCards().size());

        int specialCount = 0;
        int normalCount = 0;
        int heroCount = 0;

        for (Card card : PreGameController.getPreGame().getDeckCards()) {
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

        leaderAbility.setText("Leader Ability : " + PreGameController.getPreGame().getSelectedLeader().getDescription());
        update(specialCount);
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
                PreGameController.getPreGame().changeFaction(FactionObjects.WATER.getFaction().clone() , true);
                changeFactionWindow.setVisible(false);
                PreGameController.getPreGame().setSelectedLeader(PreGameController.getPreGame().getPlayerFaction().getLeaderArray().get(0));
            }
        });

        earthKingdomButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PreGameController.getPreGame().changeFaction(FactionObjects.EARTH.getFaction().clone() , true);
                changeFactionWindow.setVisible(false);
                PreGameController.getPreGame().setSelectedLeader(PreGameController.getPreGame().getPlayerFaction().getLeaderArray().get(0));
            }
        });

        fireNationButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PreGameController.getPreGame().changeFaction(FactionObjects.FIRE.getFaction().clone() , true);
                changeFactionWindow.setVisible(false);
                PreGameController.getPreGame().setSelectedLeader(PreGameController.getPreGame().getPlayerFaction().getLeaderArray().get(0));
            }
        });

        airNomadsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PreGameController.getPreGame().changeFaction(FactionObjects.AIR.getFaction().clone() , true);
                changeFactionWindow.setVisible(false);
                PreGameController.getPreGame().setSelectedLeader(PreGameController.getPreGame().getPlayerFaction().getLeaderArray().get(0));
            }
        });
    }

    private void factionViewSetUp(){
        stage.addActor(changeFactionButton);
        changeFactionButton.setPosition((float) Gdx.graphics.getWidth() / 2 - changeFactionButton.getWidth() / 2, 950);
        stage.addActor(changeFactionWindow);
        changeFactionWindow.setSize(1200, 800);
        changeFactionWindow.setPosition((float) Gdx.graphics.getWidth() / 2 - changeFactionWindow.getWidth() / 2, 500);
        changeFactionWindow.setVisible(false);
        changeFactionWindow.setMovable(false);
        changeFactionWindow.row();
        changeFactionWindow.addActor(waterTribeButton);
        changeFactionWindow.addActor(earthKingdomButton);
        changeFactionWindow.addActor(fireNationButton);
        changeFactionWindow.addActor(airNomadsButton);
        waterTribeButton.setPosition(180, 400);
        earthKingdomButton.setPosition(360, 400);
        fireNationButton.setPosition(540, 400);
        airNomadsButton.setPosition(720, 400);
        waterTribeButton.setSize(180 ,356);
        earthKingdomButton.setSize(180 ,356);
        fireNationButton.setSize(180 ,356);
        airNomadsButton.setSize(180 ,356);

        changeFactionWindow.addActor(waterFactionAbilitiyLabel);
        waterFactionAbilitiyLabel.setColor(Color.BLUE);

        changeFactionWindow.addActor(earthFactionAbilitiyLabel);
        earthFactionAbilitiyLabel.setColor(Color.GREEN);

        changeFactionWindow.addActor(fireFactionAbilitiyLabel);
        fireFactionAbilitiyLabel.setColor(Color.RED);

        changeFactionWindow.addActor(airFactionAbilitiyLabel);
        airFactionAbilitiyLabel.setColor(Color.YELLOW);

        changeFactionWindow.addActor(airFactionAbilitiyLabel2);
        airFactionAbilitiyLabel2.setColor(Color.YELLOW);

        waterFactionAbilitiyLabel.setPosition(10, 210);
        earthFactionAbilitiyLabel.setPosition(10, 160);
        fireFactionAbilitiyLabel.setPosition(10, 110);
        airFactionAbilitiyLabel.setPosition(10, 60);
        airFactionAbilitiyLabel2.setPosition(10, 10);

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

    private void leaderViewSetup(){
        stage.addActor(chooseLeaderButton);
        chooseLeaderButton.setPosition((float) Gdx.graphics.getWidth() / 2 - chooseLeaderButton.getWidth() / 2 + chooseLeaderButton.getWidth() + 50, 950);
        stage.addActor(chooseLeaderWindow);
        chooseLeaderWindow.setSize(1800, 800);
        chooseLeaderWindow.setVisible(false);
        chooseLeaderWindow.setMovable(false);
        chooseLeaderWindow.add(leaderLabel).pad(10);
        chooseLeaderWindow.row();
        chooseLeaderWindow.add(leaderSelectBox).pad(10);
        chooseLeaderWindow.row();
        chooseLeaderWindow.add(applyLeaderButton).pad(10);

        chooseLeaderButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chooseLeaderWindow.setVisible(true);
                chooseLeaderWindow.toFront();
                chooseLeaderWindow.setPosition((float) Gdx.graphics.getWidth() / 2 - chooseLeaderWindow.getWidth() / 2, 500);
                ArrayList<String> leaderAbilities = new ArrayList<>();
                for (Card leader : PreGameController.getPreGame().getPlayerFaction().getLeaderArray()) {
                    leaderAbilities.add(leader.getDescription());
                }

                leaderSelectBox.setItems(leaderAbilities.toArray(new String[0]));
            }
        });

        applyLeaderButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                for (Card leader : PreGameController.getPreGame().getPlayerFaction().getLeaderArray()) {
                    if (leader.getDescription().equals(leaderSelectBox.getSelected())) {
                        PreGameController.getPreGame().setSelectedLeader(leader);
                        break;
                    }
                }

                chooseLeaderWindow.setVisible(false);
                leaderAbility.setText(PreGameController.getPreGame().getSelectedLeader().getDescription());
            }
        });
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

        stage.addActor(leaderAbility);
        leaderAbility.setPosition(50, 50);
    }

    private void saveDeckViewSetUp(){
        stage.addActor(saveDeckButton);
        saveDeckButton.setPosition(50, 950);
        stage.addActor(saveDeckWindow);
        saveDeckWindow.setSize(500, 500);
        saveDeckWindow.setPosition(0 , 500);
        saveDeckWindow.setVisible(false);
        saveDeckWindow.setMovable(false);
        saveDeckWindow.addActor(saveDeckLabel);
        saveDeckLabel.setPosition(90, 380);
        saveDeckLabel.setFontScale(0.7f);
        saveDeckWindow.addActor(saveDeckTextField);
        saveDeckTextField.setWidth(500);
        saveDeckTextField.setPosition(0, 300);
        saveDeckWindow.addActor(saveDeckToFileButton);
        saveDeckToFileButton.setPosition(0, 200);
        saveDeckWindow.addActor(saveDeckToDBButton);
        saveDeckToDBButton.setPosition(0, 100);
        saveDeckWindow.addActor(closeSaveDeck);
        closeSaveDeck.setPosition(0, 0);

        saveDeckButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveDeckWindow.setVisible(true);
                saveDeckWindow.toFront();
            }
        });

        saveDeckToFileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PreGameController.getPreGame().saveDeckToFile(saveDeckTextField.getText());
                saveDeckWindow.setVisible(false);
            }
        });

        saveDeckToDBButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PreGameController.getPreGame().saveDeckToDB();
                saveDeckWindow.setVisible(false);
            }
        });

        closeSaveDeck.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveDeckWindow.setVisible(false);
            }
        });
    }

    private void loadDeckViewSetUp(){
        stage.addActor(loadDeckButton);
        loadDeckButton.setPosition(350, 950);
        stage.addActor(loadDeckWindow);
        loadDeckWindow.setSize(600, 500);
        loadDeckWindow.setPosition(0 , 500);
        loadDeckWindow.setVisible(false);
        loadDeckWindow.setMovable(false);
        loadDeckWindow.addActor(loadDeckLabel);
        loadDeckLabel.setPosition(90, 380);
        loadDeckLabel.setFontScale(0.7f);
        loadDeckWindow.addActor(loadDeckTextField);
        loadDeckTextField.setWidth(500);
        loadDeckTextField.setPosition(0, 300);
        loadDeckWindow.addActor(loadDeckFromFileButton);
        loadDeckFromFileButton.setPosition(0, 200);
        loadDeckWindow.addActor(loadDeckFromDBButton);
        loadDeckFromDBButton.setPosition(0, 100);
        loadDeckWindow.addActor(closeLoadDeck);
        closeLoadDeck.setPosition(0, 0);

        loadDeckButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loadDeckWindow.setVisible(true);
                loadDeckWindow.toFront();
            }
        });

        loadDeckFromFileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PreGameController.getPreGame().loadDeckFromFile(loadDeckTextField.getText());
                loadDeckWindow.setVisible(false);
            }
        });

        loadDeckFromDBButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PreGameController.getPreGame().loadDeckFromDB();
                loadDeckWindow.setVisible(false);
            }
        });

        closeLoadDeck.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loadDeckWindow.setVisible(false);
            }
        });
    }

    private void setBackToMainMenuButton(ProBending game){
        stage.addActor(backToMainMenuButton);
        backToMainMenuButton.setPosition(1500, 0);
        backToMainMenuButton.getLabel().setScale(0.5f);
        backToMainMenuButton.getLabel().setFontScale(0.5f);
        backToMainMenuButton.setSize(300, 50);

        backToMainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                usedBackButton = true;
                game.setScreen(ScreenMasterSetting.getInstance().getMainMenuScreen());
            }
        });
    }

    private void setChooseOpponentButton(){
        stage.addActor(chooseOpponentButton);
        chooseOpponentButton.setPosition(1200, 0);
        chooseOpponentButton.getLabel().setScale(0.5f);
        chooseOpponentButton.getLabel().setFontScale(0.5f);
        chooseOpponentButton.setSize(300, 50);
        stage.addActor(chooseOpponentWindow);
        chooseOpponentWindow.setSize(500, 500);
        chooseOpponentWindow.setPosition(Gdx.graphics.getWidth() / 2 - chooseOpponentWindow.getWidth() / 2, 500);
        chooseOpponentWindow.setVisible(false);
        chooseOpponentWindow.setMovable(false);
        chooseOpponentWindow.addActor(opponentLabel);
        opponentLabel.setPosition(90, 380);
        opponentLabel.setFontScale(0.7f);
        chooseOpponentWindow.addActor(opponentTextField);
        opponentTextField.setWidth(500);
        opponentTextField.setPosition(20, 300);
        chooseOpponentWindow.addActor(errorChooseOppLabel);
        errorChooseOppLabel.setPosition(20, 250);
        chooseOpponentWindow.addActor(submitOpponentButton);
        submitOpponentButton.setPosition(20, 100);
        chooseOpponentWindow.addActor(closeChooseOpponent);
        closeChooseOpponent.setPosition(20, 0);

        chooseOpponentButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chooseOpponentWindow.setVisible(true);
                chooseOpponentWindow.toFront();
            }
        });

        submitOpponentButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (opponentTextField.getText().equals("")){
                    errorChooseOppLabel.setText("Please enter a name");
                    errorChooseOppLabel.setColor(Color.RED);
                }
                else {
                    boolean found = false;
                    List<User> users = SaveUser.loadUsers();
                    for (User user : users) {
                        if (user.getUsername().equals(opponentTextField.getText())) {
                            PreGameController.getPreGame().setOpponentPlayer(user);
                            found = true;
                            break;
                        }
                    }
                    if (!found){
                        errorChooseOppLabel.setText("User not found");
                        errorChooseOppLabel.setColor(Color.RED);
                    }
                    else {
                        chooseOpponentWindow.setVisible(false);
                        errorChooseOppLabel.setText("");
                    }
                }
            }
        });

        closeChooseOpponent.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chooseOpponentWindow.setVisible(false);
            }
        });
    }

    //getters and setters

    public static PreGameScreenController getPreGameController() {
        return preGameScreenController;
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

    public static void setPreGameController() {
        preGameScreenController = new PreGameScreenController(ScreenMasterSetting.getInstance().getPreGameScreen().getStage());
    }

    public void setUsedBackButton(boolean usedBackButton) {
        this.usedBackButton = usedBackButton;
    }

    public boolean isUsedBackButton() {
        return usedBackButton;
    }
}
