package com.christian_peters.balloonbuster.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonbuster.BalloonBusterGame;
import com.christian_peters.balloonbuster.screens.PlayScreen;

/**
 * Created by Chris on 06.03.2016.
 */
public class PlayHUD {
    private Stage stage;
    private Viewport viewport;
    private float score;
    private PlayScreen screen;
    private boolean paused;

    public PlayHUD(SpriteBatch batch, PlayScreen screen){
        this.screen = screen;
        this.score = 0;
        this.paused = false;
        this.viewport = new FitViewport(BalloonBusterGame.V_WIDTH, BalloonBusterGame.V_HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, batch);
    }

    public void update(float dt){

    }

    public void render(){

    }

    public void pause(){

    }

    public void resume(){

    }
}
