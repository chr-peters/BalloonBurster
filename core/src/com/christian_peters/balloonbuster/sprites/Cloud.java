package com.christian_peters.balloonbuster.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Chris on 06.03.2016.
 */
public class Cloud extends Sprite {
    private boolean movingRight;
    private float velocity;

    public void update(float dt){

    }

    public void setDirection(boolean movingRight){
        this.movingRight = movingRight;
    }

    public void setVelocity(float velocity){
        this.velocity = velocity;
    }
}
