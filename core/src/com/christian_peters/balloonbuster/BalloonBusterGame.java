package com.christian_peters.balloonbuster;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BalloonBusterGame extends Game {
	public static int V_WIDTH;//Not final due to later changes
	// regarding to different Screen resolutions
	public static int V_HEIGHT;

	private SpriteBatch batch;
	private AssetManager assetmanager;


	@Override
	public void create () {

	}

	public void startGame(){
		//To be executed from the main-menu
	}

	public void gameOver(){
		
	}

	@Override
	public void render () {
		super.render();
	}
}
