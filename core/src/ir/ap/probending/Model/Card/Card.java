package ir.ap.probending.Model.Card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ir.ap.probending.Model.Card.Abilities.Ability;

public class Card {
    private Ability ability;
    String name;
    String description;
    int Power;
    boolean isHero;
    Texture texture;
    Sprite sprite;
    int playingRow;

    public Card(Ability ability, String name, String description, int power, boolean isHero, Texture texture, Sprite sprite, int playingRow) {
        this.ability = ability;
        this.name = name;
        this.description = description;
        Power = power;
        this.isHero = isHero;
        this.texture = texture;
        this.sprite = sprite;
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
        return Power;
    }

    public void setPower(int power) {
        Power = power;
    }

    public boolean isHero() {
        return isHero;
    }

    public void setHero(boolean hero) {
        isHero = hero;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getPlayingRow() {
        return playingRow;
    }

    public void setPlayingRow(int playingRow) {
        this.playingRow = playingRow;
    }
}
