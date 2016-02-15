package com.bschlangen.gameobjects;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.bschlangen.helpers.AssetLoader;


/**
 * Created by brenten on 1/10/16.
 */
public class Ship {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private int width;
    private int height;
    private float startPosition;
    private int maxHeight;
    private boolean outOfBounds;

    private Rectangle bounds;
    private Rectangle otherBounds;

    public Ship(float x, float y, int width, int height, int maxHeight){
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, -300);
        bounds = new Rectangle();
        otherBounds = new Rectangle();
        startPosition = y;
        this.maxHeight = maxHeight;
        outOfBounds = false;
    }

    public void update(float delta){

        velocity.add(acceleration.cpy().scl(delta));

        // limit ship's falling speed
        if(velocity.y < -200) {
            velocity.y = -200;
        }

        // limit ship's rising speed
        if(velocity.y > 200){
            velocity.y = 200;
        }

        // Prevent ship from leaving top of screen
        if(position.y > this.maxHeight - this.height){
            position.y = this.maxHeight -  this.height - 1;
            acceleration.y = 0;
            velocity.y = 0;
            outOfBounds = true;
        }

        // Prevent ship from falling past bottom of screen
        if(position.y < 0){
            position.y = 0;
            acceleration.y = 0;
            velocity.y = 0;
            outOfBounds = true;
        }

        acceleration.y = -450;

        position.add(velocity.cpy().scl(delta));
        bounds.set(position.x + 10, position.y, this.width-20, this.height);
        otherBounds.set(position.x + 1, position.y + 15, this.width - 2, 6);
    }

    public void onClick() {
        acceleration.y = 500;
    }

    public void onRestart(){
        position.y = startPosition;
        velocity.y = 0;
        outOfBounds = false;
    }

    public boolean outOfBounds(){
        return outOfBounds;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }


    public Rectangle getBounds(){
        return bounds;
    }

    public Rectangle getOtherBounds(){return otherBounds;}

}
