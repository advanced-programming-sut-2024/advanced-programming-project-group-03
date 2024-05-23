package ir.ap.probending;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ir.ap.probending.ProBending;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		config.setTitle("Pro Bending");
		new Lwjgl3Application(new ProBending(), config);
	}

}