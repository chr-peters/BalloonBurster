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
        //Create Sky
        Texture skyTexture = assetmanager.get("sky.jpg", Texture.class);
        float skyAspect = (float)(skyTexture.getWidth())/skyTexture.getHeight();
        sky = new Sky(skyTexture);
        sky.setSize(BalloonBusterGame.V_HEIGHT*skyAspect, BalloonBusterGame.V_HEIGHT);
        sky.setCenter(BalloonBusterGame.V_WIDTH / 2, BalloonBusterGame.V_HEIGHT / 2);
        
        //Create Clouds
        clouds = new CloudManager(assetmanager);
        
        //Create Sun
        Texture sunTexture = assetmanager.get("sun.png", Texture.class);
        float sunAspect = (float)(sunTexture.getWidth())/sunTexture.getHeight();
        sun = new Sun(sunTexture);
        float sunHeight = 150f;
        sun.setSize(sunHeight*sunAspect, sunHeight);
        sun.setCenter(BalloonBusterGame.V_WIDTH*0.8f, 1100f);
        sun.setOrigin(sun.getWidth()/2, sun.getHeight()/2);
    }

    public void update(float dt){
    	if(!paused){
	    	sky.update(dt);
	    	sun.update(dt);
	    	clouds.update(dt);
    	}
    }

    public void render(SpriteBatch batch){
        sky.draw(batch);
        sun.draw(batch);
        clouds.render(batch);
    }

    public void pause(){
    	this.paused = true;
    }
}
