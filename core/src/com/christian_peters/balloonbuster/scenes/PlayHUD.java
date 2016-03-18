package com.christian_peters.balloonbuster.scenes;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonbuster.BalloonBusterGame;
import com.christian_peters.balloonbuster.screens.MenuScreen;
import com.christian_peters.balloonbuster.screens.PlayScreen;
import com.christian_peters.balloonbuster.screens.ScoreScreen;

/**
 * Created by Chris on 06.03.2016.
 */
public class PlayHUD {
	private BalloonBusterGame game;
	private Stage stage;
	private Viewport viewport;
	private float score;
	private AssetManager assetmanager;
	private Group playGroup;
	private Group gameOverGroup;
	private Label scoreLabel;
	private Label gameOverScoreLabel;// To store the score for the gameover
										// screen
	private TextField nameField;
	private DecimalFormat formatter;
	private boolean gameOver;
	private Skin skin;
	private Preferences prefs;

	public PlayHUD(BalloonBusterGame game) {
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.score = 0;
		this.formatter = new DecimalFormat("0.00");
		this.viewport = new FitViewport(BalloonBusterGame.V_WIDTH,
				BalloonBusterGame.V_HEIGHT, new OrthographicCamera());
		this.stage = new Stage(viewport, game.getSpriteBatch());
		this.gameOver = false;

		//Create Skin
		this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"), assetmanager.get("skin/uiskin.atlas", TextureAtlas.class));

		this.playGroup = new Group();

		// add score label
		this.scoreLabel = new Label(formatter.format(score) + "s",
				skin, "bold-outline");
		scoreLabel.setHeight(50);
		scoreLabel.setPosition(BalloonBusterGame.V_WIDTH/2 - scoreLabel.getPrefWidth()/2, 3);
		playGroup.addActor(scoreLabel);
		
		playGroup.setSize(stage.getWidth(), stage.getHeight());

		stage.addActor(playGroup);

		this.gameOverGroup = new Group();

		// add Layout and Widgets
		Table table = new Table();
		table.top();

		Label gameOverLabel = new Label("Game Over!", skin, "logo");
		table.add(gameOverLabel).colspan(2).expandX();
		table.row().padTop(50);
		
		Label yourTimeLabel = new Label("Your Time:", skin);
		table.add(yourTimeLabel).expandX().align(Align.left);
		gameOverScoreLabel = new Label(formatter.format(score)
				+ "s", skin);
		table.add(gameOverScoreLabel).expandX().align(Align.left);
		
		table.row().expandX().padTop(50);
		Label nameLabel = new Label("Your Name:", skin);
		table.add(nameLabel).expandX().align(Align.left);
		
		//add name field with default name from preferences
		this.prefs = Gdx.app.getPreferences("settings");
		String name = prefs.getString("name", "nameless");
		this.nameField = new TextField(name, skin);
		this.nameField.setMaxLength(10);//10 characters at maximum
		table.add(nameField).expandX().width(350).align(Align.left);
		
		table.row().expandX().padTop(50);
		
		ImageButton restartButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(assetmanager.get("img/buttons/btn_restart.png",
						Texture.class))), new TextureRegionDrawable(
				new TextureRegion(assetmanager.get(
						"img/buttons/btn_restart_pressed.png", Texture.class))));
		restartButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				PlayHUD.this.prefs.putString("name", PlayHUD.this.nameField.getText());
				prefs.flush();
				PlayHUD.this.game.getScoreManager().put(PlayHUD.this.nameField.getText(), PlayHUD.this.score);
				Gdx.input.setOnscreenKeyboardVisible(false);
				PlayScreen screen = (PlayScreen) PlayHUD.this.game.getScreen();
				screen.restart();
			}
		});
		table.add(restartButton).expandX().size(400, 70).colspan(2);
		
		table.row().expandX().padTop(50);		
		
		ImageButton mainMenuButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(assetmanager.get("img/buttons/btn_menu.png",
						Texture.class))), new TextureRegionDrawable(
				new TextureRegion(assetmanager.get(
						"img/buttons/btn_menu_pressed.png", Texture.class))));
		mainMenuButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				PlayHUD.this.prefs.putString("name", PlayHUD.this.nameField.getText());
				prefs.flush();
				PlayHUD.this.game.getScoreManager().put(PlayHUD.this.nameField.getText(), PlayHUD.this.score);
				Gdx.input.setOnscreenKeyboardVisible(false);
				PlayHUD.this.game.transition(new MenuScreen(PlayHUD.this.game));;
			}
		});
		table.add(mainMenuButton).expandX().size(400, 70).colspan(2);
		
		//set Background
		Pixmap pm = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pm.setColor(1, 1, 1, 0.3f);
		pm.fill();
		table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pm))));
		table.pack();
		
		table.setWidth(BalloonBusterGame.V_WIDTH);
		
		table.setPosition(BalloonBusterGame.V_WIDTH/2, BalloonBusterGame.V_HEIGHT-table.getHeight()/2-220, Align.center);

		gameOverGroup.addActor(table);

		gameOverGroup.setVisible(false);
		stage.addActor(gameOverGroup);
		
		Gdx.input.setInputProcessor(stage);
	}

	public void update(float dt) {
		if (!gameOver) {
			score += dt;
			scoreLabel.setText(formatter.format(score) + "s");
			scoreLabel.setPosition(BalloonBusterGame.V_WIDTH/2 - scoreLabel.getPrefWidth()/2, 3);
		}
		stage.act(dt);
	}

	public void render() {
		stage.draw();
	}

	public void gameOver() {
		this.gameOver = true;
		this.playGroup.setVisible(false);
		gameOverScoreLabel.setText(formatter.format(score)
				+ "s");
		this.gameOverGroup.setVisible(true);
	}
	
	public void resize(int width, int height){
		this.viewport.update(width, height);
	}
	
	public void dispose(){
		stage.dispose();
	}
}
