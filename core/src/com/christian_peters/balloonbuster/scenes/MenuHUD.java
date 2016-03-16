package com.christian_peters.balloonbuster.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonbuster.BalloonBusterGame;
import com.christian_peters.balloonbuster.screens.AboutScreen;
import com.christian_peters.balloonbuster.screens.PlayScreen;
import com.christian_peters.balloonbuster.screens.ScoreScreen;

public class MenuHUD {
	private BalloonBusterGame game;
	private Stage stage;
	private Viewport viewport;
	private Skin skin;
	private AssetManager assetmanager;
	
	public MenuHUD (BalloonBusterGame game){
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.viewport = new FitViewport(BalloonBusterGame.V_WIDTH, BalloonBusterGame.V_HEIGHT, new OrthographicCamera());
		this.stage = new Stage(viewport, game.getSpriteBatch());
		
		this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"), assetmanager.get("skin/uiskin.atlas", TextureAtlas.class));
		
		Table table = new Table();
		table.setFillParent(true);
		table.top();
		
		Label logo = new Label("Balloon Buster", skin, "logo");
		table.add(logo).expandX().padTop(220);
		
		table.row();
		
		TextButton playButton = new TextButton("Play", skin);
		playButton.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				MenuHUD.this.game.transition(new PlayScreen(MenuHUD.this.game));
			}
			
		});
		table.add(playButton).expandX().padTop(50).size(400, 70);
		
		table.row();
		
		TextButton scoreButton = new TextButton("Score", skin);
		scoreButton.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				MenuHUD.this.game.transition(new ScoreScreen(MenuHUD.this.game));
			}
			
		});
		table.add(scoreButton).expandX().padTop(50).size(400, 70);
		
		table.row();
		
		TextButton aboutButton = new TextButton("About", skin);
		aboutButton.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				MenuHUD.this.game.transition(new AboutScreen(MenuHUD.this.game));
			}
			
		});
		table.add(aboutButton).expandX().padTop(50).size(400, 70);
		
		stage.addActor(table);
		
		Gdx.input.setInputProcessor(stage);
	}
	
	public void update(float dt){
		stage.act(dt);
	}
	
	public void render (){
		stage.draw();
	}
	
	public void resize(int width, int height){
		this.viewport.update(width, height);
	}
	
	public void dispose(){
		
	}
}
