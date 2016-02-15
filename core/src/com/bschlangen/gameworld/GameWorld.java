package com.bschlangen.gameworld;

import com.badlogic.gdx.Gdx;
import com.bschlangen.gameobjects.ScrollHandler;
import com.bschlangen.gameobjects.Ship;
import com.bschlangen.helpers.AssetLoader;

/**
 * Created by brenten on 1/9/16.
 * Recommendations by Kathryn
 * Makes new instances of a Ship, Asteroids, a ScrollHandler.
 *
 * Handles GameStates

 TODO: Add advertisements?

 */
public class GameWorld {

    private Ship ship;
    private ScrollHandler scrollHandler;
    private GameState currentState;
    private int score;
    private int midPointY;

    public GameWorld(int gameWidth, int gameHeight){
        midPointY = gameHeight / 2;
        ship = new Ship(gameHeight*2/5, midPointY - 5, gameWidth/9, gameHeight/9, gameHeight);
        scrollHandler = new ScrollHandler(this, gameWidth, gameHeight);
        score = 0;
        currentState = GameState.READY;
    }

    public enum GameState{
        READY, RUNNING, GAMEOVER;
    }

    public void update(float delta){

        switch(currentState){
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            case GAMEOVER:
                restart();
                break;
        }
    }

    private void updateReady(float delta){
        // Start game again once screen is touched.
        if(Gdx.input.isTouched()) {
            currentState = GameState.RUNNING;
        }
    }

    public void updateRunning(float delta){
        ship.update(delta);
        scrollHandler.update(delta);

        if(scrollHandler.collides(ship) || ship.outOfBounds()){
            scrollHandler.stop();
            AssetLoader.stopAccelerateSound();
            AssetLoader.playExplosion();
            currentState = GameState.GAMEOVER;
        }
    }

    public boolean isReady(){
        return currentState == GameState.READY;
    }

    public boolean isGameOver(){
        return currentState == GameState.GAMEOVER;
    }

    public boolean isRunning(){
        return currentState == GameState.RUNNING;
    }

    public void addToScore(){
        score += 1;
    }

    public void restart(){

        try {Thread.sleep(500);}
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        currentState = GameState.READY;
        score = 0;
        scrollHandler.onRestart();
        ship.onRestart();
        currentState = GameState.READY;
    }

    public GameState getCurrentState(){
        return currentState;
    }

    public int getScore(){
        return score;
    }

    public Ship getShip(){
        return ship;
    }

    public ScrollHandler getScrollHandler(){
        return scrollHandler;
    }
}