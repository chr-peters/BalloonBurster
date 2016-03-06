package com.christian_peters.balloonbuster;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.christian_peters.balloonbuster.sprites.Balloon;

import java.util.List;

/**
 * Created by Chris on 06.03.2016.
 */
public class PlayBalloons {
    private boolean paused;
    private int quantity;//Number of Balloons visible at the same time
    private float delay;
    private List<Balloon> balloons;
    private float curVelocity;
    private AssetManager assetmanager;

    public PlayBalloons(AssetManager assetmanager){
        this.assetmanager = assetmanager;
        //TODO initialize private variables
    }

    public void update(float dt){

    }

    public void render(SpriteBatch batch){

    }

    public boolean isGameOver(){
        return false;
    }

    public void pause(){

    }

    public void resume(){

    }

    private void handleTouch(){

    }

    private void addBalloon(){

    }
}
