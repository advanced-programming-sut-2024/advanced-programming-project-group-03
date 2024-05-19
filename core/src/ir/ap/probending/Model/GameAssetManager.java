package ir.ap.probending.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager = new GameAssetManager();
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private final Color color = new Color(252f , 235f , 155f , 0.8f);
    private final Image backgroundImage = new Image(new Texture(Gdx.files.internal("bg.jpg")));
    private GameAssetManager() {
    }
    public static GameAssetManager getGameAssetManager(){
        return gameAssetManager;
    }
    public Skin getSkin() {
        return skin;
    }

    public Color getColor() {
        return color;
    }

    public Image getBackground() {
        return backgroundImage;
    }
}
