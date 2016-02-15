package com.bschlangen.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by brenten on 1/10/16.
 */
public class AssetLoader {

    public static Texture bg;
    public static Texture ship;
    public static Texture rock1;
    public static Texture rock2;
    public static Texture rock3;
    public static Texture rock4;
    public static Texture explosion;
    public static BitmapFont font;
    public static BitmapFont boldFont;
    public static Preferences prefs;
    public static Sound explosionSound;
    public static Sound accelerateSound;
    public static boolean soundActive = false;
    public static long upSound;
    public static float upVolume = .04f;


    public static void load() {

        bg = new Texture(Gdx.files.internal("spaceBackground.png"));
        ship = new Texture(Gdx.files.internal("saucer.png"));

        rock1 = new Texture(Gdx.files.internal("rock1.png"));
        rock2 = new Texture(Gdx.files.internal("rock1.png"));
        rock3 = new Texture(Gdx.files.internal("rock3.png"));
        rock4 = new Texture(Gdx.files.internal("rock2.png"));

        explosion = new Texture(Gdx.files.internal("explosion.png"));

        font = new BitmapFont(Gdx.files.internal("greenFont.fnt"));
        boldFont = new BitmapFont(Gdx.files.internal("boldFont.fnt"));

        explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosionSound.wav"));
        accelerateSound = Gdx.audio.newSound(Gdx.files.internal("shipSound.wav"));


        prefs = Gdx.app.getPreferences("Asteroid Runner");

        if(!prefs.contains("highScore")){
            prefs.putInteger("highScore", 0);
        }
    }

    public static void setHighScore(int val){
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static void playExplosion(){
        explosionSound.play(0.09f);
    }

    public static void playAccelerateSound(){

        if(!soundActive){
            soundActive = true;
            upSound = accelerateSound.play(upVolume);
            accelerateSound.setPitch(upSound, 0.5f);
            accelerateSound.setLooping(upSound, true);
        }
    }

    public static void stopAccelerateSound(){
        soundActive = false;
        accelerateSound.stop();
    }

    public static int getHighScore(){
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        bg.dispose();
        ship.dispose();
        rock1.dispose();
        rock2.dispose();
        rock3.dispose();
        rock4.dispose();
        explosion.dispose();
        font.dispose();
        boldFont.dispose();
    }
}
