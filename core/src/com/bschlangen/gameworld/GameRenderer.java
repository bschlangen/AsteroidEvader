package com.bschlangen.gameworld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bschlangen.gameobjects.Asteroid;
import com.bschlangen.gameobjects.ScrollHandler;
import com.bschlangen.gameobjects.Ship;
import com.bschlangen.helpers.AssetLoader;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;


/**
 * Created by brenten on 1/9/16.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private Ship ship;
    private ScrollHandler scroller;
    private Asteroid rock1;
    private Asteroid rock2;
    private Asteroid rock3;
    private Asteroid rock4;

    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private boolean soundActive;

    private SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;
    private int gameWidth;
    private int score;
    private int oldScore;

    private BitmapFont font;
    private boolean alreadyStarted;
    private GlyphLayout textLayout;
    private float textWidth;

    private String GAMEOVER = "GAME OVER";
    private String touchToStart = "Touch to Start";
    private String scoreStr;
    private String oldScoreStr;
    private String HIGHSCORE = "HIGH SCORE:  ";

    public GameRenderer(GameWorld world, int gameHeight, int gameWidth, int midPointY){

        myWorld = world;

        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        this.midPointY = midPointY;
        // Create Orthographic Camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, gameWidth, gameHeight);

        batcher = new SpriteBatch();
        // attaching batcher to camera.
        batcher.setProjectionMatrix(cam.combined);

        soundActive = false;

        font = new BitmapFont();

        initGameObjects();
        alreadyStarted = false;
        textLayout = new GlyphLayout();
    }

    private void initGameObjects(){
        ship = myWorld.getShip();
        scroller = myWorld.getScrollHandler();
        rock1 = scroller.getRock1();
        rock2 = scroller.getRock2();
        rock3 = scroller.getRock3();
        rock4 = scroller.getRock4();
    }

    private void renderAsteroids(){
        // Draw the First Asteroid
        batcher.draw(AssetLoader.rock1, rock1.getX(), rock1.getY(),  rock1.getWidth(), rock1.getHeight());
        // Draw the Second Asteroid
        batcher.draw(AssetLoader.rock2, rock2.getX(), rock2.getY(),  rock2.getWidth(), rock2.getHeight());
        // Draw the Third Asteroid
        batcher.draw(AssetLoader.rock3, rock3.getX(), rock3.getY(), rock3.getWidth(), rock3.getHeight());
        // Draw the Fourth Asteroid
        batcher.draw(AssetLoader.rock4, rock4.getX(), rock4.getY(), rock4.getWidth(), rock4.getHeight());
    }

    public void render(float runTime){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Ship ship = myWorld.getShip();

        // determine if screen is being held.
        if(Gdx.input.isTouched())
        {
            if(myWorld.isRunning()){
                // makes Ship go up (call onClick() method), and set
                ship.onClick();
                alreadyStarted = true;
                if(!soundActive) {
                    AssetLoader.playAccelerateSound();
                }
            }
        }
        else{
            AssetLoader.stopAccelerateSound();
            soundActive = false;
        }


        batcher.begin();

        // Draw the background
        batcher.draw(AssetLoader.bg, 0, 0, gameWidth, gameHeight);

        // Draw the Ship
        batcher.draw(AssetLoader.ship, ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());

        // Draw the Asteroids
        renderAsteroids();

        // Draw the Score
        score = myWorld.getScore();
        scoreStr = Integer.toString(score);

        // Hold score for gameover
        if(myWorld.isRunning()) {

            oldScore = score;
            oldScoreStr = Integer.toString(oldScore);
            AssetLoader.font.draw(batcher, Integer.toString(myWorld.getScore()), gameWidth/2 - scoreStr.length()*20/2, gameHeight*9/10);
        }

        // Draw Game Over and Final Score
        if(myWorld.isReady()){

            // Determine if new High Score
            if(oldScore > AssetLoader.getHighScore()){
                AssetLoader.setHighScore(oldScore);
            }

            AssetLoader.font.draw(batcher, touchToStart, (gameWidth / 2 - (15 * touchToStart.length() / 2)), gameHeight / 6);

            // If the first round has already happened
            if(alreadyStarted) {
                AssetLoader.font.draw(batcher, oldScoreStr, gameWidth/2 - oldScoreStr.length()*20/2, gameHeight*9/10);
                AssetLoader.boldFont.draw(batcher, GAMEOVER, (gameWidth / 2 - (25 * GAMEOVER.length() / 2)), gameHeight * 2 / 3);
                // Draw the high score
                AssetLoader.font.draw(batcher, HIGHSCORE + AssetLoader.getHighScore(), (gameWidth / 2 - (16 * touchToStart.length() / 2)), gameHeight / 2);
            }
        }

        if(myWorld.isGameOver()){
            batcher.draw(AssetLoader.explosion, ship.getX()-12, ship.getY()-17, 2*ship.getWidth(), 2*ship.getHeight());
        }

        batcher.end();

    }
}
