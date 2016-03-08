package com.christian_peters.balloonbuster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.christian_peters.balloonbuster.sprites.Balloon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chris on 06.03.2016.
 */
public class PlayBalloons {
    private boolean paused;
    private float curDelay;
    private float minDelay;
    private float delayChangeFactor;//multiplier of dt in each frame
    private float timeSinceLastBalloon;
    private List<Balloon> balloons;
    private List<Balloon> touched;//List of the touched balloons which will be removed in the next frame
    private float curVelocity;
    private float maxVelocity;
    private float accelerationFactor;//multiplier of dt in each frame
    private AssetManager assetmanager;

    public PlayBalloons(AssetManager assetmanager){
        this.assetmanager = assetmanager;
        this.curDelay = 2f;
        this.minDelay = 0.25f;
        this.delayChangeFactor = 0.08f;
        this.timeSinceLastBalloon = 0f;
        this.curVelocity = 500f;
        this.maxVelocity = 1300f;
        this.accelerationFactor = 18f;
        this.balloons = new ArrayList<Balloon>();
        this.touched = new ArrayList<Balloon>();
    }

    public void update(float dt){
    	for(Balloon b: touched){
    		balloons.remove(b);
    	}
    	
    	if(timeSinceLastBalloon>=curDelay){
    		addBalloon();
    		timeSinceLastBalloon = 0f;
    	}
    	
    	//Update Velocity
    	if(curVelocity+accelerationFactor*dt<=maxVelocity){
    		curVelocity += dt*accelerationFactor;
    	} else {
    		curVelocity = maxVelocity;
    	}
    	
    	//Update Delay
    	if(curDelay-delayChangeFactor*dt>=minDelay){
    		curDelay -= delayChangeFactor*dt;
    	} else {
    		curDelay = minDelay;
    	}
    	
    	System.out.println(curVelocity);
    	
    	for(Balloon b: balloons){
    		b.update(dt);
    	}
    	timeSinceLastBalloon += dt;
    }

    public void render(SpriteBatch batch){
    	for(Balloon b: balloons){
    		b.draw(batch);
    	}
    }

    public boolean isGameOver(){
        return false;
    }

    public void pause(){

    }

    public void resume(){

    }

    public void onTouch(float x, float y){
    	for(Balloon b: balloons){
    		if(b.getBoundingRectangle().contains(x, y)){
    			touched.add(b);
    		}
    	}
    }

    private void addBalloon(){
    	Random r = new Random();
    	Texture balloonTexture = assetmanager.get("balloon.png", Texture.class);
    	Balloon tmp = new Balloon(balloonTexture);
    	float height = 150f;
    	float balloonAspect = (float)(balloonTexture.getWidth())/balloonTexture.getHeight();
    	tmp.setSize(height*balloonAspect, height);
    	tmp.setPosition(r.nextFloat()*(BalloonBusterGame.V_WIDTH-tmp.getWidth()), -1*tmp.getHeight());
    	tmp.setVelocity(curVelocity);
    	balloons.add(tmp);
    }
}
