package com.bschlangen.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.bschlangen.gameworld.GameRenderer;
import com.bschlangen.gameworld.GameWorld;
import com.bschlangen.helpers.InputHandler;

/**
 * Created by brenten on 1/8/16.
 *
 * Creates a GameWorld and a GameRenderer.
 *
 * Sets input processor to a new instance of InputHandler
 *
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;
    private int gameWidth;
    private int gameHeight;

    // Constructor for GameScreen
    public GameScreen(){

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        this.gameWidth = 480;
        this.gameHeight = (int) (screenHeight / (screenWidth / gameWidth));

        int midPointY = (gameHeight / 2);

        world = new GameWorld(gameWidth, gameHeight);
        renderer = new GameRenderer(world, gameHeight, gameWidth,  midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world.getShip()));
    }

    public float getWidth(){
        return this.gameWidth;
    }

    public float getHeight(){
        return this.gameHeight;
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        // Leave blank
    }

}
