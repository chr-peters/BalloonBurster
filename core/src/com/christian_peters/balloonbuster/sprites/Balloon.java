package com.christian_peters.balloonbuster.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Chris on 06.03.2016.
 */
public class Balloon extends Sprite {
    private float velocity;
    private Animation anim;
    private float timePassed;
    
    public Balloon(Array<TextureAtlas.AtlasRegion> frames, float height){
    	this.timePassed = 0;
    	anim = new Animation(0.12f, frames);
    	anim.setPlayMode(PlayMode.LOOP);
    	super.setRegion(anim.getKeyFrame(timePassed));
    	float aspect = (float)(super.getRegionWidth())/super.getRegionHeight();
    	this.setSize(aspect * height, height);
    }

	public void update(float dt){
		this.translateY(velocity * dt);
		this.timePassed += dt;
    	super.setRegion(anim.getKeyFrame(timePassed));
    }

    public void setVelocity(float velocity){
        this.velocity = velocity;
    }
}
