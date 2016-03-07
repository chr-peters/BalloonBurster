package com.christian_peters.balloonbuster.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Chris on 06.03.2016.
 */
public class Sun extends Sprite {
	private float rotationVelocity;	
	
    public Sun(Texture texture) {
    	super(texture);
    	this.rotationVelocity = 7f;
    }

	public void update(float dt){
		this.rotate(rotationVelocity*dt);
    }
	
	public void setRotationVelocity(float velocity){
		this.rotationVelocity = velocity;
	}
}
