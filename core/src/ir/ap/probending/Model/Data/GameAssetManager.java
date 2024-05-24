package ir.ap.probending.Model.Data;

public class GameAssetManager {
    private static final GameAssetManager instance = new GameAssetManager();
    private final String saveDataUserLocation = "users.json";

    public String getSaveDataUserLocation() {
        return saveDataUserLocation;
    }
    private GameAssetManager() {
    }

    public static GameAssetManager getInstance() {
        return instance;
    }
}
