package com.christian_peters.balloonburster.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Chris on 06.03.2016.
 */
public class Sky extends Sprite {
    public Sky(Texture texture, float height) {
        super(texture);
        float aspect = this.getWidth()/this.getHeight();
        setSize(height*aspect, height);
    }

    public void update(float dt){

    }
}
