package com.christian_peters.balloonbuster;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.christian_peters.balloonbuster.sprites.Sky;
import com.christian_peters.balloonbuster.sprites.Sun;

/**
 * Created by Chris on 06.03.2016.
 */
public class PlayBackground {
    private boolean paused;
    private CloudManager clouds;
    private Sun sun;
    private Sky sky;
    private AssetManager assetmanager;

    public PlayBackground(AssetManager assetmanager){
        this.assetmanager = assetmanager;
    }

    public void update(float dt){

    }

    public void render(SpriteBatch batch){

    }

    public void pause(){

    }

    public void resume(){

    }
}
