package com.christian_peters.balloonburster.scenes;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonburster.ScoreManager;

public class ScoreHUD {
	private com.christian_peters.balloonburster.BalloonBusterGame game;
	private Stage stage;
	private Skin skin;
	private Viewport viewport;
	private AssetManager assetmanager;
	private ScoreManager scores;
	private DecimalFormat formatter;
	
	public ScoreHUD(com.christian_peters.balloonburster.BalloonBusterGame game){
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.viewport = new FitViewport(com.christian_peters.balloonburster.BalloonBusterGame.V_WIDTH, com.christian_peters.balloonburster.BalloonBusterGame.V_HEIGHT, new OrthographicCamera());
		this.scores = game.getScoreManager();
		this.formatter = new DecimalFormat("0.00");
		this.stage = new Stage(viewport, game.getSpriteBatch());
		
		this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"), assetmanager.get("skin/uiskin.atlas", TextureAtlas.class));
		
		Table table = new Table();
		table.top();
		
		Label heading = new Label("Scores", skin, "logo");
		table.add(heading).colspan(3).expandX().align(Align.left);
		
		//add the score entries
		for(int i=0; i<scores.getMaxSize(); i++){
			table.row();
			Label position = new Label((i+1)+".", skin, "bold-outline");
			table.add(position);
			if(i<scores.getSize()){
				Label name = new Label(scores.get(i).name, skin, "bold-outline");
				table.add(name).expandX().align(Align.left);
				Label score = new Label(formatter.format(scores.get(i).score)+"s", skin, "bold-outline");
				table.add(score);
			} else {
				Label name = new Label("", skin, "bold-outline");
				table.add(name).expandX().align(Align.left);
				Label score = new Label("", skin);
				table.add(score);
			}
		}
				
		//set Background
		table.setBackground(new NinePatchDrawable(new NinePatch(assetmanager.get("img/bg_ninepatch.png", Texture.class), 10, 10, 10, 10)));
		table.pack();
		
		table.setWidth(com.christian_peters.balloonburster.BalloonBusterGame.V_WIDTH*0.8f);
		table.setPosition(com.christian_peters.balloonburster.BalloonBusterGame.V_WIDTH/2, com.christian_peters.balloonburster.BalloonBusterGame.V_HEIGHT-table.getHeight()/2-220, Align.center);
		//padTop of 220
		
		ImageButton menu = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(assetmanager.get("img/buttons/btn_menu.png",
						Texture.class))), new TextureRegionDrawable(
				new TextureRegion(assetmanager.get(
						"img/buttons/btn_menu_pressed.png", Texture.class))));
		menu.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScoreHUD.this.game.transition(new com.christian_peters.balloonburster.screens.MenuScreen(ScoreHUD.this.game));
			}
			
		});
		menu.setSize(400, 70);
		menu.setPosition(com.christian_peters.balloonburster.BalloonBusterGame.V_WIDTH/2, table.getY()-50, Align.top | Align.center);
		
		stage.addActor(table);
		stage.addActor(menu);
		
		Gdx.input.setInputProcessor(stage);
	}
	
	public void update(float dt){
		stage.act(dt);
	}
	
	public void render(){
		stage.draw();
	}
	
	public void resize(int width, int height){
		this.viewport.update(width, height);
	}
	
	public void dispose(){
		stage.dispose();
	}
}
