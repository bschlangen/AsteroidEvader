package com.bschlangen.asteroidevader.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bschlangen.asteroidevader.AEGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Asteroid Evader";
		config.width = 480;
		config.height = 272;
		new LwjglApplication(new AEGame(), config);
	}
}
