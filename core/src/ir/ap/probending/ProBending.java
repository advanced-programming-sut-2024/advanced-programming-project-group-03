package ir.ap.probending;

import Server.Client;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ir.ap.probending.Control.GameStartController;
import ir.ap.probending.Model.ScreenMasterSetting;
import ir.ap.probending.View.GameScreen;
import ir.ap.probending.View.MainMenuScreen;
import ir.ap.probending.View.PreGameScreen;

public class ProBending extends Game {

	public SpriteBatch batch;
	public static Client client = new Client();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		if (!client.establishConnection("localhost", 5000))
			System.out.println("Couldn't connect to server");
		ScreenMasterSetting.getInstance().setGame(this);
		ScreenMasterSetting.getInstance().setMainMenuScreen(new MainMenuScreen(ScreenMasterSetting.getInstance().getGame()));
		ScreenMasterSetting.getInstance().setPreGameScreen(new PreGameScreen(ScreenMasterSetting.getInstance().getGame()));
		ScreenMasterSetting.getInstance().setGameScreen(new GameScreen(ScreenMasterSetting.getInstance().getGame()));
		this.setScreen(ScreenMasterSetting.getInstance().getMainMenuScreen());
		GameStartController.setGame(this);
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
