package com.christian_peters.balloonbuster;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

		startGame();
	}

	public void startGame(){
		//To be executed from the main-menu
		setScreen(new PlayScreen(this));
	}

	public void gameOver(){

	}

	@Override
	public void render () {
		super.render();
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
