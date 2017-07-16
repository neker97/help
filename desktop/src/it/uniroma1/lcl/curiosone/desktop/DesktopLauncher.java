package it.uniroma1.lcl.curiosone.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import it.uniroma1.lcl.curiosone.Chat;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=Chat.WIDTH;
		config.height=Chat.HEIGHT;
		config.title=Chat.TITLE;
		new LwjglApplication(new Chat(), config);
	}
}
