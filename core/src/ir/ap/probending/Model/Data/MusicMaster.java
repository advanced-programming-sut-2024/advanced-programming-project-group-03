package ir.ap.probending.Model.Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicMaster {
    private static final MusicMaster instance = new MusicMaster();
    private Music bgMusic ;

    public void playBgMusicMenu() {
        bgMusic.setLooping(true);
        bgMusic.play();
    }
    private MusicMaster() {
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal(GameAssetManager.getGameAssetManager().getMenuMusic()));
        bgMusic.setVolume(0.5f);
    }

    public static MusicMaster getInstance() {
        return instance;
    }
}
