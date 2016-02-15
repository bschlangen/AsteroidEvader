package com.bschlangen.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by brenten on 1/11/16.
 */
public class Scrollable {

    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    private int gameHeight;
    private int gameWidth;
    protected Random randomHeight;
    private Rectangle bounds;
    private ScrollHandler scrollHandler;
    private boolean scored;
    private float birdX;

    public Scrollable(ScrollHandler scrollHandler, float x, float y, int width, int height, float scrollSpeed, int GameWidth, int GameHeight, float birdPosition){
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        randomHeight = new Random();
        bounds = new Rectangle();
        gameHeight = GameHeight;
        gameWidth = GameWidth;
        this.scrollHandler = scrollHandler;
        scored = false;
        birdX = birdPosition;
    }

    public void update(float delta){
        position.add(velocity.cpy().scl(delta));

        velocity.x -= .06;

        // If scrollable object is off screen
        if(position.x + this.width < 0){

            position.y = getRandomHeight();
            position.x = gameWidth + getRandomSpacing();
            scored = false;
        }

        bounds.set(position.x + 1, position.y + 1, this.width - 2, this.height - 2);

        if(position.x < birdX){
            if(!scored) {
                this.scrollHandler.addToScore();
                scored = true;
            }
        }

    }

    public void reset(float newX){
        position.x = newX;
        position.y = getRandomHeight();
    }

    private int getRandomHeight(){
        return randomHeight.nextInt(gameHeight - this.height);
    }

    private int getRandomSpacing(){
        return randomHeight.nextInt(15);
    }

    public boolean collides(Ship ship){
        if(position.x < ship.getX() + ship.getWidth()){
            return(Intersector.overlaps(ship.getBounds(), bounds) || (Intersector.overlaps(ship.getOtherBounds(), bounds)));
        }
        else{
            return false;
        }
    }

    public void stop(){
        velocity.x = 0;
    }


    // Getters for instance variables

    public float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
