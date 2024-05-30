package ir.ap.probending.Model.Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager = new GameAssetManager();
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private final Image backgroundImage = new Image(new Texture(Gdx.files.internal("bg.jpg")));
    private final String saveDataUserLocation = "users.json";
    private final String liveAppaBackgroundLocation = "bgliveappa.gif";
    private final String waterTribeCard = "FactionCards/water.png";
    private final String earthKingdomCard = "FactionCards/earth.png";
    private final String fireNationCard = "FactionCards/fire.png";
    private final String airNomadsCard = "FactionCards/air.png";

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
        bgAppaFrames[0] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa1)));
        bgAppaFrames[1] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa2)));
        bgAppaFrames[2] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa3)));
        bgAppaFrames[3] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa4)));
        bgAppaFrames[4] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa5)));
        bgAppaFrames[5] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa6)));
        bgAppaFrames[6] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa7)));
        bgAppaFrames[7] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa8)));
        bgAppaFrames[8] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa9)));
        bgAppaFrames[9] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa10)));
        bgAppaFrames[10] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa11)));
        bgAppaFrames[11] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa12)));
        bgAppaFrames[12] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa13)));
        bgAppaFrames[13] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa14)));
        bgAppaFrames[14] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa15)));
        bgAppaFrames[15] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa16)));
        bgAppaFrames[16] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa17)));
        bgAppaFrames[17] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa18)));
        bgAppaFrames[18] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa19)));
        bgAppaFrames[19] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa20)));
        bgAppaFrames[20] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa21)));

        bgAppaFrames[21] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa22)));
        bgAppaFrames[22] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa23)));
        bgAppaFrames[23] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa24)));
        bgAppaFrames[24] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa25)));
        bgAppaFrames[25] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa26)));
        bgAppaFrames[26] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa27)));
        bgAppaFrames[27] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa28)));
        bgAppaFrames[28] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa29)));
        bgAppaFrames[29] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa30)));
        bgAppaFrames[30] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa31)));
        bgAppaFrames[31] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa32)));
        bgAppaFrames[32] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa33)));
        bgAppaFrames[33] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa34)));
        bgAppaFrames[34] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa35)));
        bgAppaFrames[35] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa36)));
        bgAppaFrames[36] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa37)));
        bgAppaFrames[37] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa38)));
        bgAppaFrames[38] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa39)));
        bgAppaFrames[39] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa40)));
        bgAppaFrames[40] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa41)));
        bgAppaFrames[41] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa42)));
        bgAppaFrames[42] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa43)));
        bgAppaFrames[43] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa44)));
        bgAppaFrames[44] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa45)));
        bgAppaFrames[45] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa46)));
        bgAppaFrames[46] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa47)));
        bgAppaFrames[47] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa48)));
        bgAppaFrames[48] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa49)));
        bgAppaFrames[49] = new TextureRegion(new Texture(Gdx.files.internal(bgAppa50)));

    }
    public static GameAssetManager getGameAssetManager(){
        return gameAssetManager;
    }
    public Skin getSkin() {
        return skin;
    }
    public Image getBackground() {
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
}
