package ir.ap.probending.Model.Card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
                    PreGameController.getPreGameController().removeCardFromDeck(Card.this);
                    PreGameController.getPreGameController().addCardToStorage(Card.this);
                }
                else if (PreGameController.getPreGameController().getStorageTable().getChildren().contains(Card.this, true)) {
                    PreGameController.getPreGameController().removeCardFromStorage(Card.this);
                    PreGameController.getPreGameController().addCardToDeck(Card.this);
                }
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        cardSprite.setPosition(getX(), getY());
        cardSprite.draw(batch);
    }

    private boolean isOutOfBounds() {
        return getX() < 0 || getY() < 0 || getX() + getWidth() > Gdx.graphics.getWidth() || getY() + getHeight() > Gdx.graphics.getHeight();
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

}
