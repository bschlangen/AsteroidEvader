package com.bschlangen.gameobjects;

import com.bschlangen.gameworld.GameWorld;

/**
 * Created by brenten on 1/11/16.
 */


public class ScrollHandler {

    private Asteroid rock1, rock2, rock3, rock4;
    static final float SPEED = -80;
    private int gameWidth;
    private int gameHight;
    private int count = 0;
    private GameWorld gameWorld;
    private float startingPoint;
    private float x1;
    private float x2;
    private float x3;
    private float x4;

    public ScrollHandler(GameWorld gameWorld, int gameWidth, int gameHeight){

        this.gameWorld = gameWorld;
        this.gameWidth = gameWidth;
        this.gameHight = gameHeight;
        startingPoint = gameWidth/2;
        x1 = gameWidth - 10;
        x2 = (gameWidth * 3 / 4);
        x3 = (gameWidth / 2);
        x4 = (gameWidth / 4);

        rock1 = new Asteroid(this, startingPoint + x1, gameHeight/2, gameWidth/15, gameHeight/11, SPEED, gameWidth, gameHeight, this.gameWorld.getShip().getX());
        rock2 = new Asteroid(this, startingPoint + x2, gameHeight/7, gameWidth/12, gameHeight/10, SPEED, gameWidth, gameHeight, this.gameWorld.getShip().getX());
        rock3 = new Asteroid(this, startingPoint + x3, gameHeight*3/4, gameWidth/12, gameHeight/10, SPEED, gameWidth, gameHeight, this.gameWorld.getShip().getX());
        rock4 = new Asteroid(this, startingPoint + x4, gameHeight*4/5, gameWidth/16, gameHeight/11, SPEED, gameWidth, gameHeight, this.gameWorld.getShip().getX());
    }

    public void update(float delta){

        rock1.update(delta);
        rock2.update(delta);
        rock3.update(delta);
        rock4.update(delta);
    }

    public void onRestart(){
        rock1.velocity.x = SPEED;
        rock2.velocity.x = SPEED;
        rock3.velocity.x = SPEED;
        rock4.velocity.x = SPEED;
    }

    public boolean collides(Ship ship){
        return rock1.collides(ship) || rock2.collides(ship)
                || rock3.collides(ship) || rock4.collides(ship);
    }

    public Asteroid getRock1(){
        return rock1;
    }

    public Asteroid getRock2(){
        return rock2;
    }

    public Asteroid getRock3(){
        return rock3;
    }

    public Asteroid getRock4(){
        return rock4;
    }

    public void addToScore() {
        this.gameWorld.addToScore();
    }

    public void stop(){
        rock1.stop();
        rock2.stop();
        rock3.stop();
        rock4.stop();

        rock1.reset(startingPoint + x1);
        rock2.reset(startingPoint + x2);
        rock3.reset(startingPoint + x3);
        rock4.reset(startingPoint + x4);
    }

}
