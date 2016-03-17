package com.christian_peters.balloonbuster;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.christian_peters.balloonbuster.sprites.Cloud;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chris on 06.03.2016.
 */
public class CloudManager {
    private AssetManager assetmanager;
    private List<Cloud> clouds;
    private int quantity;
    private float maxHeight;
    private float minHeight;
    private float maxVelocity;
    private float minVelocity;
    private boolean direction;//clouds only move in one direction - uml

    public CloudManager(AssetManager assetmanager){
        this.assetmanager = assetmanager;
        this.quantity = 5;
        this.maxHeight = 80f;
        this.minHeight = 40f;
        this.maxVelocity = 35f;
        this.minVelocity = 7f;
        this.clouds = new ArrayList<Cloud>();
        Random r = new Random();
        this.direction = r.nextBoolean();
        initClouds();
    }
    
    private void initClouds(){
    	for(int i=0; i<quantity; i++){
        	addCloud(false);
    	}
    }
    
    private boolean isVisible(Cloud c){
    	if((c.getX()+c.getWidth()<=0 && !c.getDirection()) || (c.getX()>=BalloonBusterGame.V_WIDTH && c.getDirection())){
    		return false;
    	}
    	return true;
    }
    
    private void addCloud(boolean offScreen){
    	Random r = new Random();
    	Cloud tmp = new Cloud(getRandomCloudTexture(), minHeight + r.nextFloat()*(maxHeight-minHeight));
		if(this.direction){//add a new cloud at the left border and make it move to the right
			if(offScreen) {//if the cloud is to be created off the screen
				tmp.setX(-1*tmp.getWidth());
			} else {
				tmp.setX(r.nextInt((int) (BalloonBusterGame.V_WIDTH-tmp.getWidth())));
			}
			tmp.setDirection(true);
		} else {
			if(offScreen) {
				tmp.setX(BalloonBusterGame.V_WIDTH);
			} else {
				tmp.setX(r.nextInt((int) (BalloonBusterGame.V_WIDTH-tmp.getWidth())));
			}
			tmp.setDirection(false);
		}
		tmp.setY(BalloonBusterGame.V_HEIGHT/3 + r.nextInt((int)(BalloonBusterGame.V_HEIGHT*2/3 - tmp.getHeight())));
		float velocity = minVelocity + r.nextFloat()*(maxVelocity-minVelocity);
		tmp.setVelocity(velocity);
		//check for overlap
		for(Cloud c: clouds){
			if(c.getBoundingRectangle().overlaps(tmp.getBoundingRectangle())){
				addCloud(offScreen);
				return;
			}
		}
		clouds.add(tmp);
    }
    
    private Texture getRandomCloudTexture(){
    	Random r = new Random();
    	switch(r.nextInt(3)){
    		case 0: return assetmanager.get("img/clouds/cloud01.png", Texture.class);
    		case 1: return assetmanager.get("img/clouds/cloud02.png", Texture.class);
    		default:return assetmanager.get("img/clouds/cloud03.png", Texture.class);
    	}
    }

    public void update(float dt){
    	List<Cloud> invisible = new ArrayList<Cloud>();
    	for(Cloud c: clouds){
    		if(isVisible(c)){
    			c.update(dt);
    		} else {
    			invisible.add(c);
    		}
    	}
    	//remove invisible clouds and add new ones
    	for(Cloud c: invisible){
    		clouds.remove(c);
    		addCloud(true);
    	}
    }

    public void render(SpriteBatch batch){
    	for(Cloud c: clouds){
    		c.draw(batch);
    	}
    }
}
