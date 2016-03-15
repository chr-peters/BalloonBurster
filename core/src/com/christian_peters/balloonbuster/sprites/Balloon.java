package com.christian_peters.balloonbuster.sprites;

import com.badlogic.gdx.graphics.Texture;
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
    
    public enum States {FLY, BURST, DEAD};//uml
    private Balloon.States curState;//uml
    private Texture burstTexture;
    private float burstTime;
    private float timeSinceBurst;
    
    public Balloon(Array<TextureAtlas.AtlasRegion> frames, Texture burstTexture, float height){
    	this.timePassed = 0;
    	anim = new Animation(0.12f, frames);
    	anim.setPlayMode(PlayMode.LOOP);
    	super.setRegion(anim.getKeyFrame(timePassed));
    	float aspect = (float)(super.getRegionWidth())/super.getRegionHeight();
    	this.setSize(aspect * height, height);
    	this.curState = States.FLY;
    	this.burstTime = 0.03f;
    	this.timeSinceBurst = 0f;
    	this.burstTexture = burstTexture;
    }

	public void update(float dt){
		this.translateY(velocity * dt);
		this.timePassed += dt;
		if(curState == Balloon.States.FLY){
			super.setRegion(anim.getKeyFrame(timePassed));
		} else if (curState == Balloon.States.BURST){
			if(timeSinceBurst>=burstTime){
				curState = Balloon.States.DEAD;
				return;
			}
			timeSinceBurst += dt;
		}
    }
	
	public void burst(){
		curState = Balloon.States.BURST;
		float textureAspect = (float)(burstTexture.getWidth())/burstTexture.getHeight();
		this.setSize(this.getWidth(), this.getWidth()/textureAspect);
		this.translateY(100f);
		this.setRegion(burstTexture);
	}

    public void setVelocity(float velocity){
        this.velocity = velocity;
    }
    
    public Balloon.States getCurState(){//uml
    	return this.curState;
    }
}
