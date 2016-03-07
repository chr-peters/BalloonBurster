package com.christian_peters.balloonbuster.scenes;

import java.text.DecimalFormat;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
    private AssetManager assetmanager;
    private Group playGroup;
    private Label scoreLabel;
    private DecimalFormat formatter;

    public PlayHUD(SpriteBatch batch, AssetManager assetmanager, PlayScreen screen){
        this.screen = screen;
        this.assetmanager = assetmanager;
        this.score = 0;
        this.formatter = new DecimalFormat("#.##");
        this.paused = false;
        this.viewport = new FitViewport(BalloonBusterGame.V_WIDTH, BalloonBusterGame.V_HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, batch);
        
        this.playGroup = new Group();
        //add playbackground
        Image playBackground = new Image(assetmanager.get("hudBottomBackground.png", Texture.class));
        playBackground.setSize(BalloonBusterGame.V_WIDTH, 60);
        playGroup.addActor(playBackground);
        
        //add score label
        this.scoreLabel = new Label("Time: "+formatter.format(score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreLabel.setHeight(50);
        scoreLabel.setFontScale(3);
        scoreLabel.setPosition(6, 3);
        playGroup.addActor(scoreLabel);
        
        stage.addActor(playGroup);        
    }

    public void update(float dt){
    	score += dt;
    	scoreLabel.setText("Time: "+formatter.format(score));
    }

    public void render(){
    	stage.draw();
    }

    public void pause(){

    }

    public void resume(){

    }
}
