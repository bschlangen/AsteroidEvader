package com.bschlangen.asteroidevader;

import com.badlogic.gdx.Game;
import com.bschlangen.helpers.AssetLoader;
import com.bschlangen.screens.GameScreen;

public class AEGame extends Game {

	@Override
	public void create () {
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose () {
		super.dispose();
		AssetLoader.dispose();
	}
}
