package com.roterballon.balloonburster.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Chris on 06.03.2016.
 */
public class Cloud extends Sprite {
    private boolean movingRight;
    private float velocity;
    
    public Cloud(TextureRegion texture, float height){
    	super(texture);
    	
    	float aspect = super.getWidth()/super.getHeight();
    	setSize(height*aspect, height);
    	
    	this.movingRight = false;
    	this.velocity = 7f;//default
    }

    public void update(float dt){
    	if(movingRight){
    		translateX(velocity*dt);
    	} else {
    		translateX(-1*velocity*dt);
    	}
    }

    public void setDirection(boolean movingRight){
    	if(!movingRight){
    		super.flip(true, false);
    	}
        this.movingRight = movingRight;
    }
    
    public boolean getDirection(){
    	return this.movingRight;
    }

    public void setVelocity(float velocity){
        this.velocity = velocity;
    }
}
