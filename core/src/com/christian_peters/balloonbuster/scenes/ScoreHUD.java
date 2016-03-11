package com.christian_peters.balloonbuster.scenes;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonbuster.BalloonBusterGame;
import com.christian_peters.balloonbuster.ScoreManager;

public class ScoreHUD {
	private BalloonBusterGame game;
	private Stage stage;
	private Skin skin;
	private Viewport viewport;
	private AssetManager assetmanager;
	private ScoreManager scores;
	private DecimalFormat formatter;// add this to uml
	
	public ScoreHUD(BalloonBusterGame game){
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.viewport = new FitViewport(BalloonBusterGame.V_WIDTH, BalloonBusterGame.V_HEIGHT, new OrthographicCamera());
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
			Label position = new Label((i+1)+".", skin);
			table.add(position);
			if(i<scores.getSize()){
				Label name = new Label(scores.get(i).name, skin);
				table.add(name).expandX().align(Align.left);
				Label score = new Label(formatter.format(scores.get(i).score)+"s", skin);
				table.add(score);
			} else {
				Label name = new Label("", skin);
				table.add(name).expandX().align(Align.left);
				Label score = new Label("", skin);
				table.add(score);
			}
		}
				
		//set Background
		Pixmap pm = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pm.setColor(1, 1, 1, 0.3f);
		pm.fill();
		table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pm))));
		table.pack();
		
		table.setWidth(BalloonBusterGame.V_WIDTH*0.8f);
		table.setPosition(BalloonBusterGame.V_WIDTH/2, BalloonBusterGame.V_HEIGHT/2, Align.center);
		TextButton menu = new TextButton("Menu", skin);
		menu.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScoreHUD.this.game.startMenu();
			}
			
		});
		menu.setSize(300, 70);
		menu.setPosition(BalloonBusterGame.V_WIDTH/2, table.getY()-50, Align.top | Align.center);
		
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
