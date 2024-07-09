package ir.ap.probending.Model.Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager ;
    private Skin skin ;
    private final String backgroundImage = "bg.jpg";
    private final String gameBoardImage = "board.jpg";
    private final String saveDataUserLocation = "S:\\Github Clones\\advanced-programming-project-group-03\\users.json";
    private final String liveAppaBackgroundLocation = "bgliveappa.gif";
    private final String waterTribeCard = "FactionCards/water.png";
    private final String earthKingdomCard = "FactionCards/earth.png";
    private final String fireNationCard = "FactionCards/fire.png";
    private final String airNomadsCard = "FactionCards/air.png";
    private final String paisho = "paisho.jpg";
    private final String nations = "nations.jpg";

    //sfx
    private final String bgMenuMusic = "SFX/Musics/bgmenu.mp3";
    private final TextureRegion[] bgAppaFrames = new TextureRegion[50];
    private GameAssetManager() {


    }
    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null){
            gameAssetManager = new GameAssetManager();
            gameAssetManager.setSkin(new Skin(Gdx.files.internal("skin/pixthulhu-ui.json")));
        }
        return gameAssetManager;
    }

    private void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Skin getSkin() {
        return skin;
    }
    public String getBackground() {
        return backgroundImage;
    }
    public String getSaveDataUserLocation() {
        return saveDataUserLocation;
    }
    public String getLiveAppaBackgroundLocation() {
        return liveAppaBackgroundLocation;
    }
    public TextureRegion[] getBgAppaFrames() {
        return bgAppaFrames;
    }
    public String getWaterTribeCard() {
        return waterTribeCard;
    }
    public String getEarthKingdomCard() {
        return earthKingdomCard;
    }
    public String getFireNationCard() {
        return fireNationCard;
    }
    public String getAirNomadsCard() {
        return airNomadsCard;
    }
    public String getMenuMusic() {
        return bgMenuMusic;
    }
    public String getPaisho() {
        return paisho;
    }

    public String getNations() {
        return nations;
    }

    public String getGameBoardImage() {
        return gameBoardImage;
    }

}
