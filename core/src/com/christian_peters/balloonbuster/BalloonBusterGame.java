package com.christian_peters.balloonbuster;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.christian_peters.balloonbuster.screens.MenuScreen;
import com.christian_peters.balloonbuster.screens.PlayScreen;

public class BalloonBusterGame extends Game {
	public static float V_WIDTH;//Not final due to later changes
	// regarding to different Screen resolutions
	public static float V_HEIGHT;

	private SpriteBatch batch;
	private AssetManager assetmanager;


	@Override
	public void create () {
		batch = new SpriteBatch();
		assetmanager = new AssetManager();

		float aspectRatio = (float)(Gdx.graphics.getWidth())/Gdx.graphics.getHeight();
		V_HEIGHT = 1280f;
		V_WIDTH = V_HEIGHT*aspectRatio;
		
		loadAssets();
		startMenu();
	}

	public void startGame(){
		//To be executed from the main-menu
		setScreen(new PlayScreen(this));
	}
	
	public void startMenu(){
		//To be executed from the loading-screen
		setScreen(new MenuScreen(this));
	}

	public void gameOver(){

	}

	@Override
	public void render () {
		super.render();
	}
	
	public void loadAssets() {
		assetmanager.load("img/sky.jpg", Texture.class);
		assetmanager.load("img/clouds/cloud01.png", Texture.class);
		assetmanager.load("img/clouds/cloud02.png", Texture.class);
		assetmanager.load("img/clouds/cloud03.png", Texture.class);
		assetmanager.load("img/sun.png", Texture.class);
		assetmanager.load("img/gameOverBackground.png", Texture.class);
		assetmanager.load("skin/uiskin.atlas", TextureAtlas.class);
		assetmanager.load("img/balloons/balloon_red.atlas", TextureAtlas.class);
		assetmanager.finishLoading();
	}

	public SpriteBatch getSpriteBatch(){
		return this.batch;
	}

	public AssetManager getAssetManager(){
		return this.assetmanager;
	}
	
	@Override
	public void dispose(){
		this.assetmanager.dispose();
		this.batch.dispose();
	}
}
