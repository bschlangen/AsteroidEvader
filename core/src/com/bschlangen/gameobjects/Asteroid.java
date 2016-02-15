package com.bschlangen.gameobjects;

/**
 * Created by brenten on 1/11/16.
 */
public class Asteroid extends Scrollable {

    public Asteroid(ScrollHandler scrollHandler, float x, float y, int width, int height, float scrollSpeed, int GameWidth, int GameHeight, float birdX){
        super(scrollHandler, x, y, width, height, scrollSpeed, GameWidth, GameHeight, birdX);
    }

    @Override
    public void reset(float newX){
        super.reset(newX);
    }


}
