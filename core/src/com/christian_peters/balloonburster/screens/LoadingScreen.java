package com.christian_peters.balloonburster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonburster.BalloonBursterGame;
import com.christian_peters.balloonburster.sprites.Sky;

public class LoadingScreen implements Screen{
	
	private BalloonBursterGame game;
	private AssetManager assetmanager;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	private BitmapFont logoFont;
	private ShapeRenderer renderer;
	private Sky background;
	private boolean updateFlag; //Used for calling transition method only once

	public LoadingScreen(BalloonBursterGame game){
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.batch = game.getSpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(BalloonBursterGame.V_WIDTH, BalloonBursterGame.V_HEIGHT, camera);
		camera.translate(BalloonBursterGame.V_WIDTH/2, BalloonBursterGame.V_HEIGHT/2);
		camera.update();
		this.logoFont = new BitmapFont(Gdx.files.internal("fonts/blow100.fnt"));
		this.logoFont.getData().scaleX *= 0.9f;
		this.logoFont.getData().scaleY *= 0.9f;
		this.renderer = new ShapeRenderer();

		//Create Sky
		this.background = new Sky(new Texture(Gdx.files.internal("img/sky.png")), BalloonBursterGame.V_HEIGHT);
		this.background.setCenter(BalloonBursterGame.V_WIDTH / 2, BalloonBursterGame.V_HEIGHT / 2);

		this.updateFlag = false;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	public void update(float dt){
		if(!updateFlag && assetmanager.update()){
			game.transition(new MenuScreen(game));
			updateFlag = true;
		}
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(54/255f, 141/255f, 255/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		background.draw(batch);
		logoFont.draw(batch, "Balloon Burster", 0, BalloonBursterGame.V_HEIGHT - 220, BalloonBursterGame.V_WIDTH, Align.center, false);
		batch.end();
		
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(Color.WHITE);
		renderer.rect(BalloonBursterGame.V_WIDTH * 0.1f, 640, BalloonBursterGame.V_WIDTH * 0.8f, 20);
		renderer.setAutoShapeType(true);
		renderer.set(ShapeRenderer.ShapeType.Filled);
		renderer.rect(BalloonBursterGame.V_WIDTH * 0.1f, 640, BalloonBursterGame.V_WIDTH * 0.8f * assetmanager.getProgress(), 20);
		renderer.end();

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
		batch.dispose();
		renderer.dispose();
		logoFont.dispose();
		background.getTexture().dispose();
	}

}
