package com.christian_peters.balloonburster.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Chris on 06.03.2016.
 */
public class Sun extends Sprite {
	private float rotationVelocity;	
	
    public Sun(Texture texture, float height) {
    	super(texture);
    	
    	float aspect = super.getWidth()/super.getHeight();
    	setSize(height*aspect, height);
    	
    	this.rotationVelocity = 5f;
    }

	public void update(float dt){
		this.rotate(rotationVelocity*dt);
    }
	
	public void setRotationVelocity(float velocity){
		this.rotationVelocity = velocity;
	}
}
