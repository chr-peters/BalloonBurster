package com.christian_peters.balloonburster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen{
	private com.christian_peters.balloonburster.BalloonBusterGame game;
	private com.christian_peters.balloonburster.scenes.MenuHUD hud;
	private com.christian_peters.balloonburster.Background background;
	private com.christian_peters.balloonburster.MenuBalloons balloons;
	private AssetManager assetmanager;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	public MenuScreen(com.christian_peters.balloonburster.BalloonBusterGame game){
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.batch = game.getSpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(com.christian_peters.balloonburster.BalloonBusterGame.V_WIDTH, com.christian_peters.balloonburster.BalloonBusterGame.V_HEIGHT, camera);
		camera.translate(com.christian_peters.balloonburster.BalloonBusterGame.V_WIDTH/2, com.christian_peters.balloonburster.BalloonBusterGame.V_HEIGHT/2);
		camera.update();
		this.hud = new com.christian_peters.balloonburster.scenes.MenuHUD(game);
		this.background = new com.christian_peters.balloonburster.Background(assetmanager);
		this.balloons = new com.christian_peters.balloonburster.MenuBalloons(assetmanager);
	}
	
	public void update (float dt){
		background.update(dt);
		balloons.update(dt);
		hud.update(dt);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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

	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height);
		hud.resize(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		hud.dispose();
	}
}
