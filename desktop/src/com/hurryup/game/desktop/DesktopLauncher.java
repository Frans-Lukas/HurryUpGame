package com.hurryup.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hurryup.game.hurryupGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		boolean b = false;
		if(arg.length > 0)
			b = (arg[0].equals("server"));
		new LwjglApplication(new hurryupGame(b), config);
	}
}
