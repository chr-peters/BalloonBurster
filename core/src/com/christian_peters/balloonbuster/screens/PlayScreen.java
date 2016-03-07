package com.christian_peters.balloonbuster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonbuster.BalloonBusterGame;
import com.christian_peters.balloonbuster.PlayBackground;
import com.christian_peters.balloonbuster.PlayBalloons;
import com.christian_peters.balloonbuster.scenes.PlayHUD;

/**
 * Created by Chris on 06.03.2016.
 */
public class PlayScreen implements Screen {

    private BalloonBusterGame game;
    private AssetManager assetmanager;
    private SpriteBatch batch;
    private PlayBackground background;
    private PlayBalloons balloons;
    private PlayHUD hud;
    private OrthographicCamera camera;
    private Viewport viewport;

    public PlayScreen(BalloonBusterGame game) {
        this.game = game;
        this.assetmanager = game.getAssetManager();
        this.batch = game.getSpriteBatch();

        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(BalloonBusterGame.V_WIDTH, BalloonBusterGame.V_HEIGHT, camera);
        camera.translate(BalloonBusterGame.V_WIDTH/2,  BalloonBusterGame.V_HEIGHT/2);
        camera.update();

        loadAssets();

        this.background = new PlayBackground(assetmanager);

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        background.render(batch);
        //TODO Render
        batch.end();
    }

    public void update(float dt) {
        background.update(dt);
    }

    public void gamePause() {

    }

    public void gameResume() {

    }

    public void loadAssets() {
        assetmanager.load("sky.jpg", Texture.class);
        assetmanager.load("cloud.png", Texture.class);
        assetmanager.load("sun.png", Texture.class);
        assetmanager.finishLoading();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
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
