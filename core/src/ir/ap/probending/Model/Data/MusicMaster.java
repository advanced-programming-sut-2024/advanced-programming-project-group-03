package ir.ap.probending.Model.Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.Timer;
import java.util.TimerTask;

public class MusicMaster {
    private static final MusicMaster instance = new MusicMaster();
    private Music bgMusic ;
    Timer timer = new Timer();

    public void playBgMusicMenu() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                bgMusic.setLooping(true);
                bgMusic.play();
            }
        };

        timer.schedule(task, 6000);

    }
    private MusicMaster() {
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal(GameAssetManager.getGameAssetManager().getMenuMusic()));
        bgMusic.setVolume(0.5f);
    }

    public static MusicMaster getInstance() {
        return instance;
    }
}
