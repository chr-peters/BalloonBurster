package com.christian_peters.balloonbuster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonbuster.BalloonBusterGame;
import com.christian_peters.balloonbuster.scenes.MenuHUD;

public class LoadingScreen implements Screen{
	
	private BalloonBusterGame game;
	private AssetManager assetmanager;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	private BitmapFont logoFont;
	private ShapeRenderer renderer;

	public LoadingScreen(BalloonBusterGame game){
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.batch = game.getSpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(BalloonBusterGame.V_WIDTH, BalloonBusterGame.V_HEIGHT, camera);
		camera.translate(BalloonBusterGame.V_WIDTH/2, BalloonBusterGame.V_HEIGHT/2);
		camera.update();
		this.logoFont = new BitmapFont(Gdx.files.internal("fonts/altehaas90bold.fnt"));
		this.renderer = new ShapeRenderer();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	public void update(float dt){
		if(assetmanager.update()){
			game.transition(new MenuScreen(game));;
		}
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(54/255f, 141/255f, 255/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);	
		
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(Color.WHITE);
		renderer.rect(BalloonBusterGame.V_WIDTH*0.1f, 640, BalloonBusterGame.V_WIDTH*0.8f, 20);
		renderer.setAutoShapeType(true);
		renderer.set(ShapeRenderer.ShapeType.Filled);
		renderer.rect(BalloonBusterGame.V_WIDTH*0.1f, 640, BalloonBusterGame.V_WIDTH*0.8f*assetmanager.getProgress(), 20);
		renderer.end();
		
		batch.begin();
		logoFont.draw(batch, "Balloon Buster", 0, 1040, BalloonBusterGame.V_WIDTH, Align.center, false);
		batch.end();
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
		renderer.dispose();
	}

}
