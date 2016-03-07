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
    private float curQuantity;//Number of Balloons visible at the same time
    private int maxQuantity;
    private float quantityChangeFactor;//multiplier of dt in each frame
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
        this.curQuantity = 4;
        this.maxQuantity = 10;
        this.quantityChangeFactor = 0.1f;
        this.curDelay = 1.5f;
        this.minDelay = 0.4f;
        this.delayChangeFactor = 0.1f;
        this.timeSinceLastBalloon = 0f;
        this.curVelocity = 400f;
        this.maxVelocity = 1000f;
        this.accelerationFactor = 10f;
        this.balloons = new ArrayList<Balloon>();
        this.touched = new ArrayList<Balloon>();
    }

    public void update(float dt){
    	for(Balloon b: touched){
    		balloons.remove(b);
    	}
    	
    	if(balloons.size()<(int)curQuantity && timeSinceLastBalloon>=curDelay){
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

    	//Update Quantity
    	if(curQuantity + quantityChangeFactor*dt<=maxQuantity){
    		curQuantity += quantityChangeFactor*dt;
    	} else {
    		curQuantity = maxQuantity;
    	}
    	
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
    	float height = 100f;
    	float balloonAspect = (float)(balloonTexture.getWidth())/balloonTexture.getHeight();
    	tmp.setSize(height*balloonAspect, height);
    	tmp.setPosition(r.nextFloat()*(BalloonBusterGame.V_WIDTH-tmp.getWidth()), -1*tmp.getHeight());
    	tmp.setVelocity(curVelocity);
    	balloons.add(tmp);
    }
}
