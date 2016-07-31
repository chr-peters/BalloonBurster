package com.roterballon.balloonburster;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.roterballon.balloonburster.screens.LoadingScreen;
import com.roterballon.balloonburster.screens.MenuScreen;
import com.roterballon.balloonburster.screens.PlayScreen;
import com.roterballon.balloonburster.screens.TransitionScreen;

public class BalloonBursterGame extends Game {
	public static float V_WIDTH;//Not final due to later changes
	// regarding to different Screen resolutions
	public static float V_HEIGHT;

	private SpriteBatch batch;
	private AssetManager assetmanager;
	private ScoreManager scores;


	@Override
	public void create () {
		this.batch = new SpriteBatch();
		//used to avoid sound bug
		PlayScreen.music = null;
		this.assetmanager = new AssetManager();
		this.scores = new ScoreManager(Gdx.files.local("scores.json"), 10);

		float aspectRatio = (float)(Gdx.graphics.getWidth())/Gdx.graphics.getHeight();
		V_HEIGHT = 1280f;
		V_WIDTH = V_HEIGHT*aspectRatio;
		
		loadAssets();

		Gdx.input.setCatchBackKey(true);//Used for back-button-navigation

		setScreen(new LoadingScreen(this));
	}
	
	public void transition(Screen target){
		setScreen(new TransitionScreen(this, this.getScreen(), target));
	}

	@Override
	public void render () {
		super.render();
		//Back Button Navigation
		if(!this.getScreen().getClass().equals(MenuScreen.class) && Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
			this.transition(new MenuScreen(this));
		}
	}
	
	public void loadAssets() {
		assetmanager.load("img/sky.png", Texture.class);
		assetmanager.load("img/clouds/clouds.atlas", TextureAtlas.class);
		assetmanager.load("img/buttons/buttons.atlas", TextureAtlas.class);
		assetmanager.load("img/balloons/balloons.atlas", TextureAtlas.class);
		assetmanager.load("img/burst.png", Texture.class);
		assetmanager.load("img/sun.png", Texture.class);
		assetmanager.load("img/bg_ninepatch.png", Texture.class);
		assetmanager.load("skin/uiskin.atlas", TextureAtlas.class);
		assetmanager.load("sound/playmusic.mp3", Music.class);
		assetmanager.load("sound/burst.mp3", Sound.class);
	}

	public SpriteBatch getSpriteBatch(){
		return this.batch;
	}

	public AssetManager getAssetManager(){
		return this.assetmanager;
	}
	
	public ScoreManager getScoreManager(){
		return this.scores;
	}
	
	@Override
	public void dispose(){
		this.assetmanager.dispose();
		this.batch.dispose();
	}
}
