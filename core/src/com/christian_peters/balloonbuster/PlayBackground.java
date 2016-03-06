package com.christian_peters.balloonbuster;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
        Texture skyTexture = assetmanager.get("sky.jpg", Texture.class);
        float textureAspect = (float)(skyTexture.getWidth())/skyTexture.getHeight();
        sky = new Sky(skyTexture);
        sky.setSize(BalloonBusterGame.V_HEIGHT*textureAspect, BalloonBusterGame.V_HEIGHT);
        sky.setCenter(BalloonBusterGame.V_WIDTH / 2, BalloonBusterGame.V_HEIGHT / 2);
    }

    public void update(float dt){

    }

    public void render(SpriteBatch batch){
        sky.draw(batch);
    }

    public void pause(){

    }

    public void resume(){

    }
}
