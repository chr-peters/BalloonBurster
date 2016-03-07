package com.christian_peters.balloonbuster.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Chris on 06.03.2016.
 */
public class Balloon extends Sprite {
    private float velocity;

    public Balloon(Texture texture) {
    	super(texture);
    	this.velocity = 100f;
    }

	public void update(float dt){
		this.translateY(velocity * dt);
    }

    public void setVelocity(float velocity){
        this.velocity = velocity;
    }
}
