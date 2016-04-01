package com.portalsandtimemachines.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.portalsandtimemachines.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Portals and Time Machines";
		config.width = 1024;
		config.height = 768;
//		config.useGL30 = true;
		new LwjglApplication(new Game(), config);
	}
}
