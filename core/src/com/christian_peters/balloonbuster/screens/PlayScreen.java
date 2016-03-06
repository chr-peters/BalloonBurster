package com.christian_peters.balloonbuster.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonbuster.BalloonBusterGame;
import com.christian_peters.balloonbuster.PlayBackground;

/**
 * Created by Chris on 06.03.2016.
 */
public class PlayScreen implements Screen{

    private BalloonBusterGame game;
    private AssetManager assetmanager;
    private PlayBackground background;
    //TODO Add HUD and Balloons
    private OrthographicCamera camera;
    private Viewport viewport;

    public PlayScreen(BalloonBusterGame game){
        this.game = game;
        //TODO initialize private variables
    }

    @Override
    public void render(float delta) {
        update(delta);
    }

    public void update(float dt){

    }

    public void gamePause(){

    }

    public void gameResume(){

    }

    public void loadAssets(){

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
