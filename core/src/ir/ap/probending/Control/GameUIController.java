package ir.ap.probending.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import ir.ap.probending.Model.Card.Card;
import ir.ap.probending.Model.Data.GameAssetManager;
import ir.ap.probending.Model.Game.Game;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.ProBending;


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
        addPassButtonsView();
        addPassLabels();
        addEndGameDialog();
        addCurrentTurnUserNameToView();
        addPowerSumLabels();
        addSetWonLabels();
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

    private void rowClickAction(boolean canPlaceCard , Table table){
        if (canPlaceCard && clickedCard != null){
            table.add(clickedCard).pad(10);
            setAllCanPlaceCardToFalse();
            Game.getGame().playCard(clickedCard);
            cardImage.setVisible(false);
        }
    }

    private void eventListenersForTables(){
        row0ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow0, row0Table);
            }
        });

        row1ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow1, row1Table);
            }
        });

        row2ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow2, row2Table);
            }
        });

        row3ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow3, row3Table);
            }
        });

        row4ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow4, row4Table);
            }
        });

        row5ScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnRow5, row5Table);
            }
        });

        spellRowScrollPane.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rowClickAction(canPlaceCardOnSpellRow, spellRowTable);
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
        row0ScrollPane.setScrollingDisabled(true, false);
        row0ScrollPane.setScrollbarsVisible(true);
        row0ScrollPane.setFadeScrollBars(false);
        row0ScrollPane.setSmoothScrolling(true);
        row0ScrollPane.setScrollBarPositions(false, true);
        row0ScrollPane.setStyle(scrollPaneStyle);
        row0Table.setBackground(drawable);
        row0ScrollPane.setSize(830, 130);
        row0ScrollPane.setPosition(700, 250);

        table.addActor(row0ScrollPane);
    }

    private void addRow1TableView(){
        row1Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        row1Table.top().left();
        row1ScrollPane.setScrollingDisabled(true, false);
        row1ScrollPane.setScrollbarsVisible(true);
        row1ScrollPane.setFadeScrollBars(false);
        row1ScrollPane.setSmoothScrolling(true);
        row1ScrollPane.setScrollBarPositions(false, true);
        row1ScrollPane.setStyle(scrollPaneStyle);
        row1Table.setBackground(drawable);
        row1ScrollPane.setSize(830, 130);
        row1ScrollPane.setPosition(700, 390);

        table.addActor(row1ScrollPane);
    }

    private void addRow2TableView(){
        row2Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        row2Table.top().left();
        row2ScrollPane.setScrollingDisabled(true, false);
        row2ScrollPane.setScrollbarsVisible(true);
        row2ScrollPane.setFadeScrollBars(false);
        row2ScrollPane.setSmoothScrolling(true);
        row2ScrollPane.setScrollBarPositions(false, true);
        row2ScrollPane.setStyle(scrollPaneStyle);
        row2Table.setBackground(drawable);
        row2ScrollPane.setSize(830, 130);
        row2ScrollPane.setPosition(700, 530);

        table.addActor(row2ScrollPane);
    }

    private void addRow3TableView(){
        row3Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        row3Table.top().left();
        row3ScrollPane.setScrollingDisabled(true, false);
        row3ScrollPane.setScrollbarsVisible(true);
        row3ScrollPane.setFadeScrollBars(false);
        row3ScrollPane.setSmoothScrolling(true);
        row3ScrollPane.setScrollBarPositions(false, true);
        row3ScrollPane.setStyle(scrollPaneStyle);
        row3Table.setBackground(drawable);
        row3ScrollPane.setSize(830, 130);
        row3ScrollPane.setPosition(700, 670);

        table.addActor(row3ScrollPane);
    }

    private void addRow4TableView(){
        row4Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        row4Table.top().left();
        row4ScrollPane.setScrollingDisabled(true, false);
        row4ScrollPane.setScrollbarsVisible(true);
        row4ScrollPane.setFadeScrollBars(false);
        row4ScrollPane.setSmoothScrolling(true);
        row4ScrollPane.setScrollBarPositions(false, true);
        row4ScrollPane.setStyle(scrollPaneStyle);
        row4Table.setBackground(drawable);
        row4ScrollPane.setSize(830, 130);
        row4ScrollPane.setPosition(700, 810);

        table.addActor(row4ScrollPane);
    }

    private void addRow5TableView(){
        row5Table.setSkin(GameAssetManager.getGameAssetManager().getSkin());
        row5Table.top().left();
        row5ScrollPane.setScrollingDisabled(true, false);
        row5ScrollPane.setScrollbarsVisible(true);
        row5ScrollPane.setFadeScrollBars(false);
        row5ScrollPane.setSmoothScrolling(true);
        row5ScrollPane.setScrollBarPositions(false, true);
        row5ScrollPane.setStyle(scrollPaneStyle);
        row5Table.setBackground(drawable);
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
        spellRowTable.setBackground(drawable);
        spellRowScrollPane.setSize(300, 130);
        spellRowScrollPane.setPosition(140, 500);

        table.addActor(spellRowScrollPane);
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
}
