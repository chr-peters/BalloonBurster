package com.christian_peters.balloonbuster.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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
import com.christian_peters.balloonbuster.screens.MenuScreen;
import com.christian_peters.balloonbuster.screens.ScoreScreen;

public class AboutHUD {
	private BalloonBusterGame game;
	private Stage stage;
	private Skin skin;
	private Viewport viewport;
	private AssetManager assetmanager;
	
	public AboutHUD(BalloonBusterGame game){
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"), assetmanager.get("skin/uiskin.atlas", TextureAtlas.class));	
		this.viewport = new FitViewport(BalloonBusterGame.V_WIDTH, BalloonBusterGame.V_HEIGHT, new OrthographicCamera());
		this.stage = new Stage(this.viewport, game.getSpriteBatch());
		
		Table table = new Table();
		table.setWidth(BalloonBusterGame.V_WIDTH*0.8f);
		table.top();
		
		Label aboutLabel = new Label("About", skin, "logo");
		table.add(aboutLabel).colspan(2).expandX().align(Align.left);
		table.row();
		
		table.add(new Label("Graphics:", skin, "bold")).expandX().align(Align.left);
		String graphics = "Marius Nolden";
		table.add(new Label(graphics, skin)).expandX().align(Align.left);
		table.row();
		
		table.add(new Label("Music:", skin, "bold")).expandX().align(Align.left);
		String music = "Final Striker";
		table.add(new Label(music, skin)).expandX().align(Align.left);
		table.row().padTop(50);
		
		String developer = "A game developed by Christian Peters";
		Label devLabel = new Label(developer, skin);
		devLabel.setWrap(true);
		devLabel.setAlignment(Align.center);
		table.add(devLabel).expandX().align(Align.left).colspan(2).width(table.getWidth());
		table.pack();
		
		table.setPosition(BalloonBusterGame.V_WIDTH/2, BalloonBusterGame.V_HEIGHT/2 + 150, Align.center);
		
		//set Background
		Pixmap pm = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pm.setColor(1, 1, 1, 0.3f);
		pm.fill();
		table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pm))));
		table.pack();
		
		ImageButton menu = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(assetmanager.get("img/buttons/btn_menu.png",
						Texture.class))), new TextureRegionDrawable(
				new TextureRegion(assetmanager.get(
						"img/buttons/btn_menu_pressed.png", Texture.class))));
		
		menu.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				AboutHUD.this.game.transition(new MenuScreen(AboutHUD.this.game));
			}
			
		});
		
		menu.setSize(400, 70);
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
		this.stage.dispose();
	}
}
