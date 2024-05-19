package ir.ap.probending;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.View.MainMenuScreen;

public class ProBending extends Game {

	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ScreenMasterSetting.getInstance().setGame(this);
		ScreenMasterSetting.getInstance().setMainMenuScreen(new MainMenuScreen(ScreenMasterSetting.getInstance().getGame()));
		this.setScreen(ScreenMasterSetting.getInstance().getMainMenuScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
