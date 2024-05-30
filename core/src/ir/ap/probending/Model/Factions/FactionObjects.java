package ir.ap.probending.Model.Factions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import ir.ap.probending.Model.Card.CardObjects;
import ir.ap.probending.Model.Data.GameAssetManager;

import java.util.ArrayList;

public enum FactionObjects {
    FIRE(new Faction("Fire", new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getFireNationCard())), new ArrayList<>(), new ArrayList<>())),
    WATER(new Faction("Water", new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getWaterTribeCard())) , new ArrayList<>(), CardObjects.getWaterCards())),
    EARTH(new Faction("Earth", new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getEarthKingdomCard())), new ArrayList<>(), new ArrayList<>())),
    AIR(new Faction("Air", new Texture(Gdx.files.internal(GameAssetManager.getGameAssetManager().getAirNomadsCard())), new ArrayList<>(), new ArrayList<>()));
    private final Faction faction;
    FactionObjects(Faction faction) {
        this.faction = faction;
    }

    public Faction getFaction() {
        return faction;
    }
}
