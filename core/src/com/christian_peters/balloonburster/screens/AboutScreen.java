package com.christian_peters.balloonburster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonburster.BalloonBursterGame;
import com.christian_peters.balloonburster.scenes.AboutHUD;

public class AboutScreen implements Screen{
	
	private BalloonBursterGame game;
	private AssetManager assetmanager;
	private SpriteBatch batch;
	private com.christian_peters.balloonburster.MenuBalloons balloons;
	private com.christian_peters.balloonburster.Background background;
	private OrthographicCamera camera;
	private Viewport viewport;
	private AboutHUD hud;
	
	public AboutScreen (BalloonBursterGame game){
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.batch = game.getSpriteBatch();
		
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(BalloonBursterGame.V_WIDTH, BalloonBursterGame.V_HEIGHT, this.camera);
		this.camera.translate(BalloonBursterGame.V_WIDTH/2, BalloonBursterGame.V_HEIGHT/2);
		this.camera.update();
		
		this.background = new com.christian_peters.balloonburster.Background(assetmanager);
		this.balloons = new com.christian_peters.balloonburster.MenuBalloons(assetmanager);
		this.hud = new AboutHUD(game);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(float dt){
		background.update(dt);
		balloons.update(dt);
		hud.update(dt);
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
		this.hud.resize(width, height);
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
		this.hud.dispose();
	}

}
