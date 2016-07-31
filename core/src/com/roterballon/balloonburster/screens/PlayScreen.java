package com.roterballon.balloonburster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.roterballon.balloonburster.Background;

/**
 * Created by Chris on 06.03.2016.
 */
public class PlayScreen implements Screen {

    private com.roterballon.balloonburster.BalloonBursterGame game;
    private AssetManager assetmanager;
    private SpriteBatch batch;
    private Background background;
    private com.roterballon.balloonburster.PlayBalloons balloons;
    private com.roterballon.balloonburster.scenes.PlayHUD hud;
    private OrthographicCamera camera;
    private Viewport viewport;
    private boolean gameOver;
    public static Music music;

    public PlayScreen(com.roterballon.balloonburster.BalloonBursterGame game) {
        this.game = game;
        this.assetmanager = game.getAssetManager();
        this.batch = game.getSpriteBatch();
        this.gameOver = false;

        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(com.roterballon.balloonburster.BalloonBursterGame.V_WIDTH,
                com.roterballon.balloonburster.BalloonBursterGame.V_HEIGHT, camera);
        camera.translate(com.roterballon.balloonburster.BalloonBursterGame.V_WIDTH / 2,
                com.roterballon.balloonburster.BalloonBursterGame.V_HEIGHT / 2);
        camera.update();

        this.background = new Background(assetmanager);
        this.balloons = new com.roterballon.balloonburster.PlayBalloons(assetmanager);
        this.hud = new com.roterballon.balloonburster.scenes.PlayHUD(game);

        if (music == null) {
            music = assetmanager.get("sound/playmusic.mp3");
            music.setLooping(true);
        }
        music.play();
    }

    public void restart() {
        this.balloons = new com.roterballon.balloonburster.PlayBalloons(assetmanager);
        this.hud = new com.roterballon.balloonburster.scenes.PlayHUD(game);
        this.gameOver = false;
        this.background.resume();
        this.music.play();
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        background.render(batch);
        balloons.render(batch);
        batch.end();
        hud.render();
    }

    public void update(float dt) {
        background.update(dt);
        handleTouch();
        balloons.update(dt);
        hud.update(dt);
        if (!gameOver && balloons.isGameOver()) {
            background.pause();
            balloons.pause();
            hud.gameOver();
            music.pause();
            gameOver = true;
        }
    }

    private void handleTouch() {
        if (Gdx.input.justTouched() && !gameOver) {
            Vector3 worldKoors = camera.unproject(new Vector3(Gdx.input.getX(),
                    Gdx.input.getY(), 0), viewport.getScreenX(), viewport
                    .getScreenY(), viewport.getScreenWidth(), viewport
                    .getScreenHeight());
            balloons.onTouch(worldKoors.x, worldKoors.y);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
        hud.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        //used to avoid music bug
        music = assetmanager.get("sound/playmusic.mp3");
        music.setLooping(true);
        music.pause();
        game.transition(new MenuScreen(game));
    }

    @Override
    public void hide() {
        music.pause();
    }

    @Override
    public void dispose() {
        hud.dispose();
        music.dispose();
    }
}
