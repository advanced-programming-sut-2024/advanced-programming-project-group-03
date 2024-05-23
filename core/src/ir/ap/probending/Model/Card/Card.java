package ir.ap.probending.Model.Card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ir.ap.probending.Model.Card.Abilities.Ability;

public class Card {
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
