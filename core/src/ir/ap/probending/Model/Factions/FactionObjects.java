package ir.ap.probending.Model.Factions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import ir.ap.probending.Model.Card.CardObjects;
import ir.ap.probending.Model.Data.GameAssetManager;

import java.util.ArrayList;

public enum FactionObjects {
    FIRE(new Faction("Fire", new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getFireNationCard())), CardObjects.getFireLeaderCards(), CardObjects.getFireCards())),
    WATER(new Faction("Water", new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getWaterTribeCard())) , CardObjects.getWaterLeaderCards(), CardObjects.getWaterCards())),
    EARTH(new Faction("Earth", new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getEarthKingdomCard())), CardObjects.getEarthLeaderCards(),CardObjects.getEarthCards())),
    AIR(new Faction("Air", new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getAirNomadsCard())), CardObjects.getAirLeaderCards(), CardObjects.getAirCards()));
    private final Faction faction;
    FactionObjects(Faction faction) {
        this.faction = faction;
    }

    public Faction getFaction() {
        return faction;
    }
}
