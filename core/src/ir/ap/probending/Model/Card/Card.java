package ir.ap.probending.Model.Card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import ir.ap.probending.Control.PreGameController;
import ir.ap.probending.Model.Card.Abilities.Ability;
import com.badlogic.gdx.Gdx;
import ir.ap.probending.Model.ScreenMasterSetting;

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

    public Card(Ability ability, String name, String description, int power, boolean isHero, Texture cardTexture, Sprite cardSprite, int playingRow) {
        this.ability = ability;
        this.name = name;
        this.description = description;
        this.power = power;
        this.originalPower = power;
        this.isHero = isHero;
        this.cardTexture = cardTexture;
        this.cardSprite = cardSprite;
        this.playingRow = playingRow;

        setSize(cardTexture.getWidth(), cardTexture.getHeight());

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                originalX = getX();
                originalY = getY();
                addAction(Actions.scaleTo(1.2f, 1.2f, 0.1f));
                Card.this.setScale(1.2f);
                PreGameController.getPreGameController().getStoreTable().removeActor(Card.this);
                PreGameController.getPreGameController().getDeckTable().add(Card.this);
                return true;
            }

            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                moveBy(x - getWidth() / 2, y - getHeight() / 2);
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                addAction(Actions.scaleTo(1f, 1f, 0.1f)); // Reset scale on touch up
                Card.this.setScale(1.0f);
                if (isOutOfBounds()) {
                    addAction(Actions.moveTo(originalX, originalY, 0.5f)); // Move back if out of bounds
                }

                //TODO implement moving card from deck to storage and reverse
                if (PreGameController.getPreGameController().getStoreTable().getChildren().contains(Card.this, true)) {
                    PreGameController.getPreGameController().getStoreTable().removeActor(Card.this);
                    PreGameController.getPreGameController().getDeckTable().add(Card.this);
                }
                else if (PreGameController.getPreGameController().getDeckTable().getChildren().contains(Card.this, true)) {
                    PreGameController.getPreGameController().getDeckTable().removeActor(Card.this);
                    PreGameController.getPreGameController().getStoreTable().add(Card.this);
                }
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(cardTexture, getX(), getY(), getWidth(), getHeight());
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
