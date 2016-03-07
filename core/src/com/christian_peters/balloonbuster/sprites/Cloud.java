package com.christian_peters.balloonbuster.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Chris on 06.03.2016.
 */
public class Cloud extends Sprite {
    private boolean movingRight;
    private float velocity;
    
    public Cloud(Texture texture){
    	super(texture);
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
        this.movingRight = movingRight;
    }
    
    public boolean getDirection(){
    	return this.movingRight;
    }

    public void setVelocity(float velocity){
        this.velocity = velocity;
    }
}
