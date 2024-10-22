package ir.ap.probending.Model.Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicMaster {
    private static final MusicMaster instance = new MusicMaster();
    private Music bgMusic ;

    public void playBgMusicMenu() {
        bgMusic.setLooping(true);
        bgMusic.play();
        bgMusic.setVolume(10f);
    }
    private MusicMaster() {
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal(GameAssetManager.getGameAssetManager().getMenuMusic()));
    }

    public static MusicMaster getInstance() {
        return instance;
    }
}
