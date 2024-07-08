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

    private final String bgAppa1 = "bglive/ezgif-frame-001.jpg";
    private final String bgAppa2 = "bglive/ezgif-frame-002.jpg";
    private final String bgAppa3 = "bglive/ezgif-frame-003.jpg";
    private final String bgAppa4 = "bglive/ezgif-frame-004.jpg";
    private final String bgAppa5 = "bglive/ezgif-frame-005.jpg";
    private final String bgAppa6 = "bglive/ezgif-frame-006.jpg";
    private final String bgAppa7 = "bglive/ezgif-frame-007.jpg";
    private final String bgAppa8 = "bglive/ezgif-frame-008.jpg";
    private final String bgAppa9 = "bglive/ezgif-frame-009.jpg";
    private final String bgAppa10 = "bglive/ezgif-frame-010.jpg";
    private final String bgAppa11 = "bglive/ezgif-frame-011.jpg";
    private final String bgAppa12 = "bglive/ezgif-frame-012.jpg";
    private final String bgAppa13 = "bglive/ezgif-frame-013.jpg";
    private final String bgAppa14 = "bglive/ezgif-frame-014.jpg";
    private final String bgAppa15 = "bglive/ezgif-frame-015.jpg";
    private final String bgAppa16 = "bglive/ezgif-frame-016.jpg";
    private final String bgAppa17 = "bglive/ezgif-frame-017.jpg";
    private final String bgAppa18 = "bglive/ezgif-frame-018.jpg";
    private final String bgAppa19 = "bglive/ezgif-frame-019.jpg";
    private final String bgAppa20 = "bglive/ezgif-frame-020.jpg";
    private final String bgAppa21 = "bglive/ezgif-frame-021.jpg";
    private final String bgAppa22 = "bglive/ezgif-frame-022.jpg";
    private final String bgAppa23 = "bglive/ezgif-frame-023.jpg";
    private final String bgAppa24 = "bglive/ezgif-frame-024.jpg";
    private final String bgAppa25 = "bglive/ezgif-frame-025.jpg";
    private final String bgAppa26 = "bglive/ezgif-frame-026.jpg";
    private final String bgAppa27 = "bglive/ezgif-frame-027.jpg";
    private final String bgAppa28 = "bglive/ezgif-frame-028.jpg";
    private final String bgAppa29 = "bglive/ezgif-frame-029.jpg";
    private final String bgAppa30 = "bglive/ezgif-frame-030.jpg";
    private final String bgAppa31 = "bglive/ezgif-frame-031.jpg";
    private final String bgAppa32 = "bglive/ezgif-frame-032.jpg";
    private final String bgAppa33 = "bglive/ezgif-frame-033.jpg";
    private final String bgAppa34 = "bglive/ezgif-frame-034.jpg";
    private final String bgAppa35 = "bglive/ezgif-frame-035.jpg";
    private final String bgAppa36 = "bglive/ezgif-frame-036.jpg";
    private final String bgAppa37 = "bglive/ezgif-frame-037.jpg";
    private final String bgAppa38 = "bglive/ezgif-frame-038.jpg";
    private final String bgAppa39 = "bglive/ezgif-frame-039.jpg";
    private final String bgAppa40 = "bglive/ezgif-frame-040.jpg";
    private final String bgAppa41 = "bglive/ezgif-frame-041.jpg";
    private final String bgAppa42 = "bglive/ezgif-frame-042.jpg";
    private final String bgAppa43 = "bglive/ezgif-frame-043.jpg";
    private final String bgAppa44 = "bglive/ezgif-frame-044.jpg";
    private final String bgAppa45 = "bglive/ezgif-frame-045.jpg";
    private final String bgAppa46 = "bglive/ezgif-frame-046.jpg";
    private final String bgAppa47 = "bglive/ezgif-frame-047.jpg";
    private final String bgAppa48 = "bglive/ezgif-frame-048.jpg";
    private final String bgAppa49 = "bglive/ezgif-frame-049.jpg";
    private final String bgAppa50 = "bglive/ezgif-frame-050.jpg";
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
