package com.christian_peters.balloonbuster.scenes;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonbuster.BalloonBusterGame;

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
	private TextField nameField;//add this to uml
	private DecimalFormat formatter;
	private boolean gameOver;
	private Skin skin;
	private Preferences prefs;//add this to uml

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
		this.scoreLabel = new Label("Time: " + formatter.format(score) + "s",
				skin);
		scoreLabel.setHeight(50);
		scoreLabel.setPosition(6, 3);
		playGroup.addActor(scoreLabel);
		
		playGroup.setSize(stage.getWidth(), stage.getHeight());

		stage.addActor(playGroup);

		this.gameOverGroup = new Group();
		gameOverGroup.setSize(stage.getWidth(), stage.getHeight());
		// add gameOverBackground
		Image gameOverBackground = new Image(assetmanager.get(
				"img/gameOverBackground.png", Texture.class));
		gameOverBackground.setSize(BalloonBusterGame.V_WIDTH,
				BalloonBusterGame.V_HEIGHT);
		gameOverGroup.addActor(gameOverBackground);

		// add Layout and Widgets
		Table table = new Table();
		table.setFillParent(true);
		table.top();
		table.padTop(250);

		Label gameOverLabel = new Label("Game Over!", skin, "large");
		table.add(gameOverLabel).colspan(2).expandX();
		table.row();
		gameOverScoreLabel = new Label("Your Time: " + formatter.format(score)
				+ "s", skin);
		table.add(gameOverScoreLabel).colspan(2).expandX().padTop(50);
		
		table.row().expandX().padTop(50);
		Label nameLabel = new Label("Your Name:", skin);
		table.add(nameLabel).expandX();
		
		//add name field with default name from preferences
		this.prefs = Gdx.app.getPreferences("settings");
		String name = prefs.getString("name", "nameless");
		this.nameField = new TextField(name, skin);
		table.add(nameField).expandX().width(350);
		
		table.row().expandX().padTop(50);
		
		TextButton restartButton = new TextButton("Restart", skin);
		restartButton.pad(5);
		restartButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				PlayHUD.this.prefs.putString("name", PlayHUD.this.nameField.getText());
				prefs.flush();
				PlayHUD.this.game.getScoreManager().put(PlayHUD.this.nameField.getText(), PlayHUD.this.score);
				PlayHUD.this.game.startGame();
			}
		});
		table.add(restartButton);
		
		
		TextButton mainMenuButton = new TextButton("Menu", skin);
		mainMenuButton.pad(5);
		mainMenuButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				PlayHUD.this.prefs.putString("name", PlayHUD.this.nameField.getText());
				prefs.flush();
				PlayHUD.this.game.getScoreManager().put(PlayHUD.this.nameField.getText(), PlayHUD.this.score);
				PlayHUD.this.game.startMenu();
			}
		});
		table.add(mainMenuButton);

		gameOverGroup.addActor(table);

		gameOverGroup.setVisible(false);
		stage.addActor(gameOverGroup);
		
		Gdx.input.setInputProcessor(stage);
	}

	public void update(float dt) {
		if (!gameOver) {
			score += dt;
			scoreLabel.setText("Time: " + formatter.format(score) + "s");
		}
		stage.act(dt);
	}

	public void render() {
		stage.draw();
	}

	public void gameOver() {
		this.gameOver = true;
		this.playGroup.setVisible(false);
		gameOverScoreLabel.setText("Your Time: " + formatter.format(score)
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
