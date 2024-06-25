package ir.ap.probending.Model.Card;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import ir.ap.probending.Control.GameUIController;
import ir.ap.probending.Control.PreGameController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import com.badlogic.gdx.Gdx;


public class Card extends Actor {
    private Ability ability;
    String name;
    String description;
    int power;
    int originalPower;
    boolean isHero;
    Texture cardTexture;
    Sprite cardSprite;
    Texture cardPowerTexture;
    Sprite cardPowerSprite;
    int playingRow;
    float originalX = 100;
    float originalY = 100;
    private boolean isClicked = false;

    public Card(Ability ability, String name, String description, int power, boolean isHero, Texture cardTexture, int playingRow) {
        this.ability = ability;
        this.name = name;
        this.description = description;
        this.power = power;
        this.originalPower = power;
        this.isHero = isHero;
        this.cardTexture = cardTexture;
        this.cardSprite = new Sprite(cardTexture);
        this.playingRow = playingRow;
        this.setX(originalX);
        this.setY(originalY);
        setSize(cardSprite.getWidth(), cardSprite.getHeight());

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (PreGameController.getPreGameController().getDeckTable().getChildren().contains(Card.this, true)) {
                    PreGameController.getPreGameController().addCardToStorage(Card.this);
                    PreGameController.getPreGameController().removeCardFromDeck(Card.this);
                }
                else if (PreGameController.getPreGameController().getStorageTable().getChildren().contains(Card.this, true)) {
                    PreGameController.getPreGameController().addCardToDeck(Card.this);
                    PreGameController.getPreGameController().removeCardFromStorage(Card.this);
                }
                return true;
            }
        });
    }

    private Card(Card card) {
        this.ability = null; //TODO: implement clone method
        this.name = card.name;
        this.description = card.description;
        this.power = card.power;
        this.originalPower = card.originalPower;
        this.isHero = card.isHero;
        this.cardTexture = card.cardTexture;
        this.cardSprite = new Sprite(cardTexture);
        this.playingRow = card.playingRow;
        this.setX(originalX);
        this.setY(originalY);
        setSize(cardSprite.getWidth(), cardSprite.getHeight());

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (PreGameController.getPreGameController().getDeckTable().getChildren().contains(Card.this, true)) {
                    PreGameController.getPreGameController().addCardToStorage(Card.this);
                    PreGameController.getPreGameController().removeCardFromDeck(Card.this);
                }
                else if (PreGameController.getPreGameController().getStorageTable().getChildren().contains(Card.this, true)) {
                    PreGameController.getPreGameController().addCardToDeck(Card.this);
                    PreGameController.getPreGameController().removeCardFromStorage(Card.this);
                }
                return true;
            }
        });
    }

    private Card(Card card , int x) {
        this.ability = null; //TODO: implement clone method
        this.name = card.name;
        this.description = card.description;
        this.power = card.power;
        this.originalPower = card.originalPower;
        this.isHero = card.isHero;
        this.cardTexture = card.cardTexture;
        this.cardSprite = new Sprite(cardTexture);
        this.playingRow = card.playingRow;
        this.setX(originalX);
        this.setY(originalY);
        setSize(cardSprite.getWidth(), cardSprite.getHeight());

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameUIController.getGameUIController().showBigCardFromHandAtTheSideOfTheScreenForBetterViewOnTheCardAfterPlayerClickedOnTheCardFromHand(Card.this);
                isClicked = true;
                if (GameUIController.getGameUIController().getClickedCard() != null && GameUIController.getGameUIController().getClickedCard() != Card.this)
                    GameUIController.getGameUIController().getClickedCard().setClicked(false);
                GameUIController.getGameUIController().setClickedCard(Card.this);

                if (GameUIController.getGameUIController().getPlayerHandTable().getChildren().contains(Card.this, true) && isClicked) {
                    switch (Card.this.getPlayingRow()) {
                        case 0:
                            GameUIController.getGameUIController().setCanPlaceCardOnRow0(true);
                            break;
                        case 1:
                            GameUIController.getGameUIController().setCanPlaceCardOnRow1(true);
                            break;
                        case 2:
                            GameUIController.getGameUIController().setCanPlaceCardOnRow2(true);
                            break;
                        case 3:
                            GameUIController.getGameUIController().setCanPlaceCardOnRow3(true);
                            break;
                        case 4:
                            GameUIController.getGameUIController().setCanPlaceCardOnRow4(true);
                            break;
                        case 5:
                            GameUIController.getGameUIController().setCanPlaceCardOnRow5(true);
                            break;
                        case 6:
                            GameUIController.getGameUIController().setCanPlaceCardOnSpellRow(true);
                            break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        cardSprite.setPosition(getX(), getY());
        cardSprite.draw(batch);

        int count = PreGameController.getPreGameController().getCardNumber(this);
        if (count > 1) {
            BitmapFont font = new BitmapFont();
            font.setColor(Color.BLACK);
            font.draw(batch, String.valueOf(count), getX() + getWidth() - 20, getY() + 20);
        }
    }


    //getters and setters

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isHero() {
        return isHero;
    }

    public void setHero(boolean hero) {
        isHero = hero;
    }

    public Texture getTexture() {
        return cardTexture;
    }

    public void setTexture(Texture cardTexture) {
        this.cardTexture = cardTexture;
    }

    public Sprite getSprite() {
        return cardSprite;
    }

    public void setSprite(Sprite cardSprite) {
        this.cardSprite = cardSprite;
    }

    public int getPlayingRow() {
        return playingRow;
    }

    public void setPlayingRow(int playingRow) {
        this.playingRow = playingRow;
    }

    public Card clone() {
        return new Card(this);
    }

    public Card clone2() {
        return new Card(this, 1);
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
