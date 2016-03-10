package com.christian_peters.balloonbuster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonbuster.BalloonBusterGame;
import com.christian_peters.balloonbuster.MenuBackground;
import com.christian_peters.balloonbuster.scenes.MenuHUD;

public class MenuScreen implements Screen{
	private BalloonBusterGame game;
	private MenuHUD hud;
	private MenuBackground background;
	private AssetManager assetmanager;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	public MenuScreen(BalloonBusterGame game){
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.batch = game.getSpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(BalloonBusterGame.V_WIDTH, BalloonBusterGame.V_HEIGHT, camera);
		camera.translate(BalloonBusterGame.V_WIDTH/2, BalloonBusterGame.V_HEIGHT/2);
		camera.update();
		//TODO create hud
		this.background = new MenuBackground(assetmanager);
	}
	
	public void update (float dt){
		background.update(dt);
		//TODO update hud
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
		batch.end();
		//TODO render hud
	}

	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height);
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
		// TODO Auto-generated method stub
		
	}
}
