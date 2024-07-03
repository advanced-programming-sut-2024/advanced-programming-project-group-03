package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import ir.ap.probending.Model.Card.Abilities.CommandersHorn;
import ir.ap.probending.Model.Card.Abilities.Decoy;
import ir.ap.probending.Model.Card.Abilities.Muster;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.Game.Game;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.ProBending;

import java.util.ArrayList;


public class GameUIController {
    private final Table table = new Table();
    private static GameUIController gameUIController = new GameUIController(ScreenMasterSetting.getInstance().getPreGameScreen().getStage());
    private final Image boardImage = new Image(new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getGameBoardImage())));
    private final Image cardImage = new Image();
    private ScrollPane.ScrollPaneStyle scrollPaneStyle;
    TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(GameAssetManager.getGameAssetManager().getNations())));
    private final Label player1Username = new Label("" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label player2Username = new Label("" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label passForPlayer1 = new Label("Passed" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label passForPlayer2 = new Label("Passed" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label currentTurnPlayerUsername = new Label("" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label player1SiegePowerSum = new Label("0" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label player1RangedPowerSum = new Label("0" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label player1CloseCombatPowerSum = new Label("0" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label player2SiegePowerSum = new Label("0" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label player2RangedPowerSum = new Label("0" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label player2CloseCombatPowerSum = new Label("0" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label player1TotalPowerSum = new Label("0" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label player2TotalPowerSum = new Label("0" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label player1SetWon = new Label("Sets Won : 0" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label player2SetWon = new Label("Sets Won : 0" , GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton passButton = new TextButton("Pass", GameAssetManager.getGameAssetManager().getSkin());
    private final Window setEndDialog = new Window("" , GameAssetManager.getGameAssetManager().getSkin());
    private final Label setWinnerLabel = new Label("" , GameAssetManager.getGameAssetManager().getSkin());
    private final TextButton closeEndDialogButton = new TextButton("Close" , GameAssetManager.getGameAssetManager().getSkin());
    private final Window cardListWindow = new Window("Card List" , GameAssetManager.getGameAssetManager().getSkin());
    private final Table cardListTable = new Table();
    private final ScrollPane cardListScrollPane = new ScrollPane(cardListTable);
    private final Table playerHandTable = new Table();
    private final ScrollPane playerHandScrollPane = new ScrollPane(playerHandTable);
    private final Table row0Table = new Table();
    private final ScrollPane row0ScrollPane = new ScrollPane(row0Table);
    private final Table row1Table = new Table();
    private final ScrollPane row1ScrollPane = new ScrollPane(row1Table);
    private final Table row2Table = new Table();
    private final ScrollPane row2ScrollPane = new ScrollPane(row2Table);
    private final Table row3Table = new Table();
    private final ScrollPane row3ScrollPane = new ScrollPane(row3Table);
    private final Table row4Table = new Table();
    private final ScrollPane row4ScrollPane = new ScrollPane(row4Table);
    private final Table row5Table = new Table();
    private final ScrollPane row5ScrollPane = new ScrollPane(row5Table);
    private final Table spellRowTable = new Table();
    private final ScrollPane spellRowScrollPane = new ScrollPane(spellRowTable);
    private final Table commanderHorn7Table = new Table();
    private final ScrollPane commanderHorn7ScrollPane = new ScrollPane(commanderHorn7Table);
    private final Table commanderHorn8Table = new Table();
    private final ScrollPane commanderHorn8ScrollPane = new ScrollPane(commanderHorn8Table);
    private final Table commanderHorn9Table = new Table();
    private final ScrollPane commanderHorn9ScrollPane = new ScrollPane(commanderHorn9Table);
    private final Table commanderHorn10Table = new Table();
    private final ScrollPane commanderHorn10ScrollPane = new ScrollPane(commanderHorn10Table);
    private final Table commanderHorn11Table = new Table();
    private final ScrollPane commanderHorn11ScrollPane = new ScrollPane(commanderHorn11Table);
    private final Table commanderHorn12Table = new Table();
    private final ScrollPane commanderHorn12ScrollPane = new ScrollPane(commanderHorn12Table);
    private final Table leaderPlayer1Table = new Table();
    private final Table leaderPlayer2Table = new Table();
    private final ScrollPane leaderPlayer1ScrollPane = new ScrollPane(leaderPlayer1Table);
    private final ScrollPane leaderPlayer2ScrollPane = new ScrollPane(leaderPlayer2Table);
    private final TextButton playLeaderAbilityButton = new TextButton("Leader Ability" , GameAssetManager.getGameAssetManager().getSkin());

    private boolean canPlaceCardOnRow0 = false;
    private boolean canPlaceCardOnRow1 = false;
    private boolean canPlaceCardOnRow2 = false;
    private boolean canPlaceCardOnRow3 = false;
    private boolean canPlaceCardOnRow4 = false;
    private boolean canPlaceCardOnRow5 = false;
    private boolean canPlaceCardOnSpellRow = false;
    private Card clickedCard;

    private GameUIController(Stage stage) {

        scrollPaneStyle = GameAssetManager.getGameAssetManager().getSkin().get("default", ScrollPane.ScrollPaneStyle.class);

        table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        table.setFillParent(true);
        table.center();

        table.addActor(boardImage);
        boardImage.setFillParent(true);
        boardImage.setPosition(0, 0);
        boardImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        cardImage.setSize(180, 356);
        cardImage.setPosition(1600, 400);
        cardImage.setVisible(false);
        table.addActor(cardImage);

        addHandTableView();
        addRow0TableView();
        addRow1TableView();
        addRow2TableView();
        addRow3TableView();
        addRow4TableView();
        addRow5TableView();
        addSpellRowTableView();
        eventListenersForTables();
        eventListenersForCommanderSlots();
        addPassButtonsView();
        addPassLabels();
        addEndGameDialog();
        addCurrentTurnUserNameToView();
        addPowerSumLabels();
        addSetWonLabels();
        addCommanderHorn7ToView();
        addCommanderHorn8ToView();
        addCommanderHorn9ToView();
        addCommanderHorn10ToView();
        addCommanderHorn11ToView();
        addCommanderHorn12ToView();
        addCardListWindow();
        addLeadersView();
        addLeaderAbilityButtons();
    }

    //functionality methods
    public void handlePreGameController(ProBending game) {

    }

    public void showBigCardFromHandAtTheSideOfTheScreenForBetterViewOnTheCardAfterPlayerClickedOnTheCardFromHand(Card card) {
        cardImage.setDrawable(new TextureRegionDrawable(new TextureRegion(card.getTexture())));
        cardImage.setVisible(true);
    }

    private void setAllCanPlaceCardToFalse(){
        canPlaceCardOnRow0 = false;
        canPlaceCardOnRow1 = false;
        canPlaceCardOnRow2 = false;
        canPlaceCardOnRow3 = false;
        canPlaceCardOnRow4 = false;
        canPlaceCardOnRow5 = false;
        canPlaceCardOnSpellRow = false;
    }

    private void rowClickAction(boolean canPlaceCard , Table table , int row){
        if (canPlaceCard && clickedCard != null){
            if (!(clickedCard.getAbility() instanceof Decoy)){

                if (row == 5) {
                    Game.getGame().playCard(clickedCard , 0 );
                }
                else if (row == 4) {
                    Game.getGame().playCard(clickedCard , 1 );
                }
                else if (row == 3) {
                    Game.getGame().playCard(clickedCard , 2 );
                }
                else {
                    Game.getGame().playCard(clickedCard , row );
                }
            }
        }
        setAllCanPlaceCardToFalse();
        cardImage.setVisible(false);
        updateRows();
    }

    private void eventListenersForTables(){
        row0ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow0, row0Table , 0);
            }
        });

        row1ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow1, row1Table , 1);
            }
        });

        row2ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow2, row2Table , 2);
            }
        });

        row3ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow3, row3Table , 3);
            }
        });

        row4ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow4, row4Table, 4);
            }
        });

        row5ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow5, row5Table , 5);
            }
        });

        spellRowScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnSpellRow, spellRowTable , 6);
            }
        });

    }

    private void eventListenersForCommanderSlots(){
        commanderHorn7ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clickedCard != null && clickedCard.getAbility() instanceof CommandersHorn && Game.getGame().getCurrentTurn() == 1){
                    Game.getGame().playCard(clickedCard , 7);
                    setAllCanPlaceCardToFalse();
                    cardImage.setVisible(false);
                    updateRows();
                }
            }
        });

        commanderHorn8ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clickedCard != null && clickedCard.getAbility() instanceof CommandersHorn && Game.getGame().getCurrentTurn() == 1){
                    Game.getGame().playCard(clickedCard , 8);
                    setAllCanPlaceCardToFalse();
                    cardImage.setVisible(false);
                    updateRows();
                }
            }
        });

        commanderHorn9ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clickedCard != null && clickedCard.getAbility() instanceof CommandersHorn && Game.getGame().getCurrentTurn() == 1){
                    Game.getGame().playCard(clickedCard , 9);
                    setAllCanPlaceCardToFalse();
                    cardImage.setVisible(false);
                    updateRows();
                }
            }
        });

        commanderHorn10ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clickedCard != null && clickedCard.getAbility() instanceof CommandersHorn && Game.getGame().getCurrentTurn() == 2){
                    Game.getGame().playCard(clickedCard , 10);
                    setAllCanPlaceCardToFalse();
                    cardImage.setVisible(false);
                    updateRows();
                }
            }
        });

        commanderHorn11ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clickedCard != null && clickedCard.getAbility() instanceof CommandersHorn && Game.getGame().getCurrentTurn() == 2){
                    Game.getGame().playCard(clickedCard , 11);
                    setAllCanPlaceCardToFalse();
                    cardImage.setVisible(false);
                    updateRows();
                }
            }
        });

        commanderHorn12ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clickedCard != null && clickedCard.getAbility() instanceof CommandersHorn && Game.getGame().getCurrentTurn() == 2){
                    Game.getGame().playCard(clickedCard , 12);
                    setAllCanPlaceCardToFalse();
                    cardImage.setVisible(false);
                    updateRows();
                }
            }
        });
    }

    public void showSetEndDialog(String text){
        setEndDialog.setVisible(true);
        setWinnerLabel.setText(text);
    }

    public void showPassForPlayer1(){
        passForPlayer1.setVisible(true);
    }

    public void showPassForPlayer2(){
        passForPlayer2.setVisible(true);
    }

    public void hidePassForPlayer1(){
        passForPlayer1.setVisible(false);
    }

    public void hidePassForPlayer2(){
        passForPlayer2.setVisible(false);
    }

    public void updateRows(){
        row0Table.clear();
        row1Table.clear();
        row2Table.clear();
        row3Table.clear();
        row4Table.clear();
        row5Table.clear();
        spellRowTable.clear();
        commanderHorn7Table.clear();
        commanderHorn8Table.clear();
        commanderHorn9Table.clear();
        commanderHorn10Table.clear();
        commanderHorn11Table.clear();
        commanderHorn12Table.clear();
        for (Card card : Game.getGame().getGameBoard().getPlayer1Board().getSiege()){

            if (!Game.getGame().getGameBoard().getPlayer1Board().getSiege().isEmpty())
                row0Table.add(card).padTop(-160).padRight(-100);
            else
                row0Table.add(card).padTop(-160);

            card.getSprite().setSize(100 , 200);
        }
        for (Card card : Game.getGame().getGameBoard().getPlayer1Board().getRanged()){
            if (!Game.getGame().getGameBoard().getPlayer1Board().getRanged().isEmpty())
                row1Table.add(card).padTop(-160).padRight(-100);
            else
                row1Table.add(card).padTop(-160);
            card.getSprite().setSize(100 , 200);
        }
        for (Card card : Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat()){
            if (!Game.getGame().getGameBoard().getPlayer1Board().getCloseCombat().isEmpty())
                row2Table.add(card).padTop(-160).padRight(-100);
            else
                row2Table.add(card).padTop(-160);
            card.getSprite().setSize(100 , 200);
        }
        for (Card card : Game.getGame().getGameBoard().getPlayer2Board().getSiege()){
            if (!Game.getGame().getGameBoard().getPlayer2Board().getSiege().isEmpty())
                row5Table.add(card).padTop(-160).padRight(-100);
            else
                row5Table.add(card).padTop(-160);
            card.getSprite().setSize(100 , 200);
        }
        for (Card card : Game.getGame().getGameBoard().getPlayer2Board().getRanged()){
            if (!Game.getGame().getGameBoard().getPlayer2Board().getRanged().isEmpty())
                row4Table.add(card).padTop(-160).padRight(-100);
            else
                row4Table.add(card).padTop(-160);
            card.getSprite().setSize(100 , 200);
        }
        for (Card card : Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat()){
            if (!Game.getGame().getGameBoard().getPlayer2Board().getCloseCombat().isEmpty())
                row3Table.add(card).padTop(-160).padRight(-100);
            else
                row3Table.add(card).padTop(-160);
            card.getSprite().setSize(100 , 200);
        }
        for (Card card : Game.getGame().getGameBoard().getSpellCards()){
            if (!Game.getGame().getGameBoard().getSpellCards().isEmpty()) {
                spellRowTable.add(card).padTop(-160).padRight(-100);
            }
            else {
                spellRowTable.add(card).padTop(-160);
            }
            card.getSprite().setSize(100 , 200);
        }

        if (Game.getGame().getGameBoard().getPlayer1Board().getCommander7() != null){
            Game.getGame().getGameBoard().getPlayer1Board().getCommander7().getSprite().setSize(100 , 200);
            commanderHorn7Table.add(Game.getGame().getGameBoard().getPlayer1Board().getCommander7()).padTop(-160);
        }

        if (Game.getGame().getGameBoard().getPlayer1Board().getCommander8() != null){
            Game.getGame().getGameBoard().getPlayer1Board().getCommander8().getSprite().setSize(100 , 200);
            commanderHorn8Table.add(Game.getGame().getGameBoard().getPlayer1Board().getCommander8()).padTop(-160);
        }

        if (Game.getGame().getGameBoard().getPlayer1Board().getCommander9() != null){
            Game.getGame().getGameBoard().getPlayer1Board().getCommander9().getSprite().setSize(100 , 200);
            commanderHorn9Table.add(Game.getGame().getGameBoard().getPlayer1Board().getCommander9()).padTop(-160);
        }

        if (Game.getGame().getGameBoard().getPlayer2Board().getCommander9() != null){
            Game.getGame().getGameBoard().getPlayer2Board().getCommander9().getSprite().setSize(100 , 200);
            commanderHorn10Table.add(Game.getGame().getGameBoard().getPlayer2Board().getCommander9()).padTop(-160);
        }

        if (Game.getGame().getGameBoard().getPlayer2Board().getCommander8() != null){
            Game.getGame().getGameBoard().getPlayer2Board().getCommander8().getSprite().setSize(100 , 200);
            commanderHorn11Table.add(Game.getGame().getGameBoard().getPlayer2Board().getCommander8()).padTop(-160);
        }

        if (Game.getGame().getGameBoard().getPlayer2Board().getCommander7() != null){
            Game.getGame().getGameBoard().getPlayer2Board().getCommander7().getSprite().setSize(100 , 200);
            commanderHorn12Table.add(Game.getGame().getGameBoard().getPlayer2Board().getCommander7()).padTop(-160);
        }

        Game.getGame().updatePowerLabelsNumbers();

    }

    public void showLeaderAbilityButton(){
        playLeaderAbilityButton.setVisible(true);
    }

    public void hideLeaderAbilityButton(){
        playLeaderAbilityButton.setVisible(false);
    }

    public void activateCardListWindow(){
        cardListWindow.setVisible(true);
    }

    public void deactivateCardListWindow(){
        cardListWindow.setVisible(false);
    }

    public void addCardsToCardListWindow(ArrayList<Card> cards){
        cardListTable.clear();
        int count = 0;
        for (Card card : cards){
            cardListTable.add(card).pad(10);
            count++;
            if (count % 5 == 0)
                cardListTable.row();
        }
    }

    public void clearCardListWindow(){
        cardListTable.clear();
    }

    public void addLeadersToLeaderTable1(Card card){
        leaderPlayer1Table.add(card).pad(10);
        card.getSprite().setSize(100, 200);
    }

    public void addLeadersToLeaderTable2(Card card){
        leaderPlayer2Table.add(card).pad(10);
        card.getSprite().setSize(100, 200);
    }

    //add view methods for cleaner code

    private void addHandTableView(){
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
    private void addRow0TableView(){
        row0Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        row0Table.top().left();
        row0ScrollPane.setScrollingDisabled(true, true);
        row0ScrollPane.setScrollbarsVisible(false);
/*        row0ScrollPane.setFadeScrollBars(true);
        row0ScrollPane.setSmoothScrolling(true);
        row0ScrollPane.setScrollBarPositions(false, true);*/
        row0ScrollPane.setStyle(scrollPaneStyle);
        //row0Table.setBackground(drawable);
        row0ScrollPane.setSize(830, 130);
        row0ScrollPane.setPosition(700, 250);

        table.addActor(row0ScrollPane);
    }

    private void addRow1TableView(){
        row1Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        row1Table.top().left();
        row1ScrollPane.setScrollingDisabled(true, true);
        row1ScrollPane.setScrollbarsVisible(false);
/*        row1ScrollPane.setFadeScrollBars(false);
        row1ScrollPane.setSmoothScrolling(true);
        row1ScrollPane.setScrollBarPositions(false, true);*/
        row1ScrollPane.setStyle(scrollPaneStyle);
        //row1Table.setBackground(drawable);
        row1ScrollPane.setSize(830, 130);
        row1ScrollPane.setPosition(700, 390);

        table.addActor(row1ScrollPane);
    }

    private void addRow2TableView(){
        row2Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        row2Table.top().left();
        row2ScrollPane.setScrollingDisabled(true, true);
        row2ScrollPane.setScrollbarsVisible(false);
/*        row2ScrollPane.setFadeScrollBars(false);
        row2ScrollPane.setSmoothScrolling(true);
        row2ScrollPane.setScrollBarPositions(false, true);*/
        row2ScrollPane.setStyle(scrollPaneStyle);
        //row2Table.setBackground(drawable);
        row2ScrollPane.setSize(830, 130);
        row2ScrollPane.setPosition(700, 530);

        table.addActor(row2ScrollPane);
    }

    private void addRow3TableView(){
        row3Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        row3Table.top().left();
        row3ScrollPane.setScrollingDisabled(true, true);
        row3ScrollPane.setScrollbarsVisible(false);
/*        row3ScrollPane.setFadeScrollBars(false);
        row3ScrollPane.setSmoothScrolling(true);
        row3ScrollPane.setScrollBarPositions(false, true);*/
        row3ScrollPane.setStyle(scrollPaneStyle);
        //row3Table.setBackground(drawable);
        row3ScrollPane.setSize(830, 130);
        row3ScrollPane.setPosition(700, 670);

        table.addActor(row3ScrollPane);
    }

    private void addRow4TableView(){
        row4Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        row4Table.top().left();
        row4ScrollPane.setScrollingDisabled(true, true);
        row4ScrollPane.setScrollbarsVisible(false);
  /*      row4ScrollPane.setFadeScrollBars(false);
        row4ScrollPane.setSmoothScrolling(true);
        row4ScrollPane.setScrollBarPositions(false, true);*/
        row4ScrollPane.setStyle(scrollPaneStyle);
        //row4Table.setBackground(drawable);
        row4ScrollPane.setSize(830, 130);
        row4ScrollPane.setPosition(700, 810);

        table.addActor(row4ScrollPane);
    }

    private void addRow5TableView(){
        row5Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        row5Table.top().left();
        row5ScrollPane.setScrollingDisabled(true, true);
        row5ScrollPane.setScrollbarsVisible(false);
/*        row5ScrollPane.setFadeScrollBars(false);
        row5ScrollPane.setSmoothScrolling(true);
        row5ScrollPane.setScrollBarPositions(false, true);*/
        row5ScrollPane.setStyle(scrollPaneStyle);
        //row5Table.setBackground(drawable);
        row5ScrollPane.setSize(830, 130);
        row5ScrollPane.setPosition(700, 950);

        table.addActor(row5ScrollPane);
    }

    private void addSpellRowTableView(){
        spellRowTable.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        spellRowTable.top().left();
        spellRowScrollPane.setScrollingDisabled(true, false);
        spellRowScrollPane.setScrollbarsVisible(true);
        spellRowScrollPane.setFadeScrollBars(false);
        spellRowScrollPane.setSmoothScrolling(true);
        spellRowScrollPane.setScrollBarPositions(false, true);
        spellRowScrollPane.setStyle(scrollPaneStyle);
        //spellRowTable.setBackground(drawable);
        spellRowScrollPane.setSize(350, 130);
        spellRowScrollPane.setPosition(140, 500);

        table.addActor(spellRowScrollPane);
    }

    private void addCommanderHorn7ToView(){
        commanderHorn7Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        commanderHorn7Table.top().left();
        commanderHorn7ScrollPane.setScrollingDisabled(true, true);
        commanderHorn7ScrollPane.setScrollbarsVisible(false);
        commanderHorn7ScrollPane.setFadeScrollBars(false);
        commanderHorn7ScrollPane.setSmoothScrolling(true);
        commanderHorn7ScrollPane.setScrollBarPositions(false, true);
        commanderHorn7ScrollPane.setStyle(scrollPaneStyle);
        //commanderHorn7Table.setBackground(drawable);
        commanderHorn7ScrollPane.setSize(150, 130);
        commanderHorn7ScrollPane.setPosition(580, 250);

        table.addActor(commanderHorn7ScrollPane);
    }

    private void addCommanderHorn8ToView(){
        commanderHorn8Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        commanderHorn8Table.top().left();
        commanderHorn8ScrollPane.setScrollingDisabled(true, true);
        commanderHorn8ScrollPane.setScrollbarsVisible(false);
        commanderHorn8ScrollPane.setFadeScrollBars(false);
        commanderHorn8ScrollPane.setSmoothScrolling(true);
        commanderHorn8ScrollPane.setScrollBarPositions(false, true);
        commanderHorn8ScrollPane.setStyle(scrollPaneStyle);
        //commanderHorn8Table.setBackground(drawable);
        commanderHorn8ScrollPane.setSize(150, 130);
        commanderHorn8ScrollPane.setPosition(580, 390);

        table.addActor(commanderHorn8ScrollPane);
    }

    private void addCommanderHorn9ToView(){
        commanderHorn9Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        commanderHorn9Table.top().left();
        commanderHorn9ScrollPane.setScrollingDisabled(true, true);
        commanderHorn9ScrollPane.setScrollbarsVisible(false);
        commanderHorn9ScrollPane.setFadeScrollBars(false);
        commanderHorn9ScrollPane.setSmoothScrolling(true);
        commanderHorn9ScrollPane.setScrollBarPositions(false, true);
        commanderHorn9ScrollPane.setStyle(scrollPaneStyle);
        //commanderHorn9Table.setBackground(drawable);
        commanderHorn9ScrollPane.setSize(150, 130);
        commanderHorn9ScrollPane.setPosition(580, 530);

        table.addActor(commanderHorn9ScrollPane);
    }

    private void addCommanderHorn10ToView(){
        commanderHorn10Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        commanderHorn10Table.top().left();
        commanderHorn10ScrollPane.setScrollingDisabled(true, true);
        commanderHorn10ScrollPane.setScrollbarsVisible(false);
        commanderHorn10ScrollPane.setFadeScrollBars(false);
        commanderHorn10ScrollPane.setSmoothScrolling(true);
        commanderHorn10ScrollPane.setScrollBarPositions(false, true);
        commanderHorn10ScrollPane.setStyle(scrollPaneStyle);
        //commanderHorn10Table.setBackground(drawable);
        commanderHorn10ScrollPane.setSize(150, 130);
        commanderHorn10ScrollPane.setPosition(580, 670);

        table.addActor(commanderHorn10ScrollPane);
    }

    private void addCommanderHorn11ToView(){
        commanderHorn11Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        commanderHorn11Table.top().left();
        commanderHorn11ScrollPane.setScrollingDisabled(true, true);
        commanderHorn11ScrollPane.setScrollbarsVisible(false);
        commanderHorn11ScrollPane.setFadeScrollBars(false);
        commanderHorn11ScrollPane.setSmoothScrolling(true);
        commanderHorn11ScrollPane.setScrollBarPositions(false, true);
        commanderHorn11ScrollPane.setStyle(scrollPaneStyle);
        //commanderHorn11Table.setBackground(drawable);
        commanderHorn11ScrollPane.setSize(150, 130);
        commanderHorn11ScrollPane.setPosition(580, 810);

        table.addActor(commanderHorn11ScrollPane);
    }

    private void addCommanderHorn12ToView(){
        commanderHorn12Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        commanderHorn12Table.top().left();
        commanderHorn12ScrollPane.setScrollingDisabled(true, true);
        commanderHorn12ScrollPane.setScrollbarsVisible(false);
        commanderHorn12ScrollPane.setFadeScrollBars(false);
        commanderHorn12ScrollPane.setSmoothScrolling(true);
        commanderHorn12ScrollPane.setScrollBarPositions(false, true);
        commanderHorn12ScrollPane.setStyle(scrollPaneStyle);
        //commanderHorn12Table.setBackground(drawable);
        commanderHorn12ScrollPane.setSize(150, 130);
        commanderHorn12ScrollPane.setPosition(580, 950);

        table.addActor(commanderHorn12ScrollPane);
    }

    private void addPassButtonsView(){
        passButton.setSize(100, 50);
        passButton.setPosition(300, 100);
        passButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Game.getGame().endTurn();
            }
        });
        table.addActor(passButton);
    }

    public void addUsernameLabels(){
        player1Username.setText(Game.getGame().getGameBoard().getPlayer1().getUser().getUsername());
        player1Username.setPosition(100, 50);
        player1Username.setSize(200, 50);
        table.addActor(player1Username);

        player2Username.setText(Game.getGame().getGameBoard().getPlayer2().getUser().getUsername());
        player2Username.setPosition(100, 800);
        player2Username.setSize(200, 50);
        table.addActor(player2Username);
    }

    public void addPassLabels(){
        passForPlayer1.setPosition(300, 150);
        passForPlayer1.setSize(200, 50);
        passForPlayer1.setVisible(false);
        table.addActor(passForPlayer1);

        passForPlayer2.setPosition(300, 900);
        passForPlayer2.setSize(200, 50);
        passForPlayer2.setVisible(false);
        table.addActor(passForPlayer2);
    }

    private void addEndGameDialog(){
        setEndDialog.setSize(800, 400);
        setEndDialog.setPosition(800, 400);
        setEndDialog.setVisible(false);
        closeEndDialogButton.setSize(100, 50);
        closeEndDialogButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setEndDialog.setVisible(false);
                Game.getGame().startNewSet();
            }
        });
        setEndDialog.add(setWinnerLabel).pad(10);
        setEndDialog.row();
        setEndDialog.add(closeEndDialogButton).pad(10);
        table.addActor(setEndDialog);
    }

    public void addCurrentTurnUserNameToView(){
        currentTurnPlayerUsername.setPosition(100, 400);
        currentTurnPlayerUsername.setSize(200, 50);
        table.addActor(currentTurnPlayerUsername);
    }

    private void addPowerSumLabels(){
        player1SiegePowerSum.setPosition(530, 290);
        player1SiegePowerSum.setSize(400, 50);
        table.addActor(player1SiegePowerSum);

        player1RangedPowerSum.setPosition(530, 430);
        player1RangedPowerSum.setSize(200, 50);
        table.addActor(player1RangedPowerSum);

        player1CloseCombatPowerSum.setPosition(530, 560);
        player1CloseCombatPowerSum.setSize(200, 50);
        table.addActor(player1CloseCombatPowerSum);

        player2SiegePowerSum.setPosition(530, 980);
        player2SiegePowerSum.setSize(200, 50);
        table.addActor(player2SiegePowerSum);

        player2RangedPowerSum.setPosition(530, 850);
        player2RangedPowerSum.setSize(200, 50);
        table.addActor(player2RangedPowerSum);

        player2CloseCombatPowerSum.setPosition(530, 710);
        player2CloseCombatPowerSum.setSize(200, 50);
        table.addActor(player2CloseCombatPowerSum);

        player1TotalPowerSum.setPosition(448, 320);
        player1TotalPowerSum.setSize(200, 50);
        table.addActor(player1TotalPowerSum);

        player2TotalPowerSum.setPosition(448, 720);
        player2TotalPowerSum.setSize(200, 50);
        table.addActor(player2TotalPowerSum);
    }

    private void addSetWonLabels(){
        player1SetWon.setPosition(100, 10);
        player1SetWon.setSize(200, 50);
        table.addActor(player1SetWon);

        player2SetWon.setPosition(100, 760);
        player2SetWon.setSize(200, 50);
        table.addActor(player2SetWon);
    }

    private void addCardListWindow(){
        cardListWindow.setSize(1000, 1000);
        cardListWindow.setPosition(500 , 40);
        cardListWindow.setVisible(false);
        cardListScrollPane.setSize(400, 800);
        cardListTable.setSize(400, 800);
        cardListWindow.add(cardListScrollPane);
        table.addActor(cardListWindow);
    }

    private void addLeaderAbilityButtons(){
        playLeaderAbilityButton.setSize(200, 50);
        playLeaderAbilityButton.setPosition(100, 300);
        playLeaderAbilityButton.getLabel().setFontScale(0.5f);
        playLeaderAbilityButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Game.getGame().getCurrentTurn() == 1)
                    Game.getGame().getGameBoard().getPlayer1Board().playLeaderAbility();
                else
                    Game.getGame().getGameBoard().getPlayer2Board().playLeaderAbility();
            }
        });
        table.addActor(playLeaderAbilityButton);
    }

    private void addLeadersView(){
        leaderPlayer1Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        leaderPlayer1Table.top().left();
        leaderPlayer1ScrollPane.setScrollingDisabled(true, true);
        leaderPlayer1ScrollPane.setScrollbarsVisible(false);
        leaderPlayer1ScrollPane.setFadeScrollBars(false);
        leaderPlayer1ScrollPane.setSmoothScrolling(true);
        leaderPlayer1ScrollPane.setScrollBarPositions(false, true);
        leaderPlayer1ScrollPane.setStyle(scrollPaneStyle);
        leaderPlayer1ScrollPane.setSize(150, 300);
        leaderPlayer1ScrollPane.setPosition(130, 120);

        table.addActor(leaderPlayer1ScrollPane);

        leaderPlayer2Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        leaderPlayer2Table.top().left();
        leaderPlayer2ScrollPane.setScrollingDisabled(true, true);
        leaderPlayer2ScrollPane.setScrollbarsVisible(false);
        leaderPlayer2ScrollPane.setFadeScrollBars(false);
        leaderPlayer2ScrollPane.setSmoothScrolling(true);
        leaderPlayer2ScrollPane.setScrollBarPositions(false, true);
        leaderPlayer2ScrollPane.setStyle(scrollPaneStyle);
        leaderPlayer2ScrollPane.setSize(150, 300);
        leaderPlayer2ScrollPane.setPosition(130, 870);

        table.addActor(leaderPlayer2ScrollPane);
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

    public Table getRow0Table() {
        return row0Table;
    }

    public Table getRow1Table() {
        return row1Table;
    }

    public Table getRow2Table() {
        return row2Table;
    }

    public Table getRow3Table() {
        return row3Table;
    }

    public Table getRow4Table() {
        return row4Table;
    }

    public Table getRow5Table() {
        return row5Table;
    }

    public Table getSpellRowTable() {
        return spellRowTable;
    }

    public boolean isCanPlaceCardOnRow0() {
        return canPlaceCardOnRow0;
    }

    public void setCanPlaceCardOnRow0(boolean canPlaceCardOnRow0) {
        this.canPlaceCardOnRow0 = canPlaceCardOnRow0;
    }

    public boolean isCanPlaceCardOnRow1() {
        return canPlaceCardOnRow1;
    }

    public void setCanPlaceCardOnRow1(boolean canPlaceCardOnRow1) {
        this.canPlaceCardOnRow1 = canPlaceCardOnRow1;
    }

    public boolean isCanPlaceCardOnRow2() {
        return canPlaceCardOnRow2;
    }

    public void setCanPlaceCardOnRow2(boolean canPlaceCardOnRow2) {
        this.canPlaceCardOnRow2 = canPlaceCardOnRow2;
    }

    public boolean isCanPlaceCardOnRow3() {
        return canPlaceCardOnRow3;
    }

    public void setCanPlaceCardOnRow3(boolean canPlaceCardOnRow3) {
        this.canPlaceCardOnRow3 = canPlaceCardOnRow3;
    }

    public boolean isCanPlaceCardOnRow4() {
        return canPlaceCardOnRow4;
    }

    public void setCanPlaceCardOnRow4(boolean canPlaceCardOnRow4) {
        this.canPlaceCardOnRow4 = canPlaceCardOnRow4;
    }

    public boolean isCanPlaceCardOnRow5() {
        return canPlaceCardOnRow5;
    }

    public void setCanPlaceCardOnRow5(boolean canPlaceCardOnRow5) {
        this.canPlaceCardOnRow5 = canPlaceCardOnRow5;
    }

    public boolean isCanPlaceCardOnSpellRow() {
        return canPlaceCardOnSpellRow;
    }

    public void setCanPlaceCardOnSpellRow(boolean canPlaceCardOnSpellRow) {
        this.canPlaceCardOnSpellRow = canPlaceCardOnSpellRow;
    }

    public Card getClickedCard() {
        return clickedCard;
    }

    public void setClickedCard(Card clickedCard) {
        this.clickedCard = clickedCard;
    }

    public void setCurrentTurnPlayerUsername(String username){
        currentTurnPlayerUsername.setText(username);
    }

    public void setPlayer1SiegePowerSum(int power){
        player1SiegePowerSum.setText(String.valueOf(power));
    }

    public void setPlayer1RangedPowerSum(int power){
        player1RangedPowerSum.setText(String.valueOf(power));
    }

    public void setPlayer1CloseCombatPowerSum(int power){
        player1CloseCombatPowerSum.setText(String.valueOf(power));
    }

    public void setPlayer2SiegePowerSum(int power){
        player2SiegePowerSum.setText(String.valueOf(power));
    }

    public void setPlayer2RangedPowerSum(int power){
        player2RangedPowerSum.setText(String.valueOf(power));
    }

    public void setPlayer2CloseCombatPowerSum(int power){
        player2CloseCombatPowerSum.setText(String.valueOf(power));
    }

    public void setPlayer1TotalPowerSum(int power){
        player1TotalPowerSum.setText(String.valueOf(power));
    }

    public void setPlayer2TotalPowerSum(int power){
        player2TotalPowerSum.setText(String.valueOf(power));
    }

    public void setPlayer1SetWon(int setWon){
        player1SetWon.setText("Sets Won : " + String.valueOf(setWon));
    }

    public void setPlayer2SetWon(int setWon){
        player2SetWon.setText("Sets Won : " + String.valueOf(setWon));
    }

    public Window getCardListWindow() {
        return cardListWindow;
    }

}
