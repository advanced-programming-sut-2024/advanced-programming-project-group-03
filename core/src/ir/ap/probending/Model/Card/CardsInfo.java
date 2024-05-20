package ir.ap.probending.Model.Card;

import ir.ap.probending.Model.Card.Abilities.Ability;
import ir.ap.probending.Model.Card.Abilities.Scorch;

public enum CardsInfo {
    //WaterBenders
    Amon(new Scorch(), "Amon", 8, false, "Cards/Water Tribes/Amon.png", 2 , "The era of bending is over! A new era of equality has begun!");
    private Ability ability;
    String name;
    String description;
    int Power;
    boolean isHero;
    String pictureLocation;
    int playingRow;
    CardsInfo(Ability ability, String name, int power, boolean isHero, String pictureLocation, int playingRow , String description) {
        this.ability = ability;
        this.name = name;
        this.description = description;
        Power = power;
        this.isHero = isHero;
        this.pictureLocation = pictureLocation;
        this.playingRow = playingRow;
    }

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

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation;
    }

    public int getPlayingRow() {
        return playingRow;
    }

    public void setPlayingRow(int playingRow) {
        this.playingRow = playingRow;
    }
}
