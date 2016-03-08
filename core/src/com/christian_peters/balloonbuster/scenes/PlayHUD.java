package com.christian_peters.balloonbuster.scenes;

import java.text.DecimalFormat;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonbuster.BalloonBusterGame;
import com.christian_peters.balloonbuster.screens.PlayScreen;

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
	private Label gameOverScoreLabel;//To store the score for the gameover screen
	private DecimalFormat formatter;
	private boolean gameOver;

	public PlayHUD(SpriteBatch batch, AssetManager assetmanager,
			BalloonBusterGame game) {
		this.game = game;
		this.assetmanager = assetmanager;
		this.score = 0;
		this.formatter = new DecimalFormat("#.##");
		this.viewport = new FitViewport(BalloonBusterGame.V_WIDTH,
				BalloonBusterGame.V_HEIGHT, new OrthographicCamera());
		this.stage = new Stage(viewport, batch);
		this.gameOver = false;

		this.playGroup = new Group();
		// add playbackground
		Image playBackground = new Image(assetmanager.get(
				"img/hudBottomBackground.png", Texture.class));
		playBackground.setSize(BalloonBusterGame.V_WIDTH, 60);
		playGroup.addActor(playBackground);

		// add score label
		this.scoreLabel = new Label("Time: " + formatter.format(score)+"s",
				new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		scoreLabel.setHeight(50);
		scoreLabel.setFontScale(3);
		scoreLabel.setPosition(6, 3);
		playGroup.addActor(scoreLabel);

		stage.addActor(playGroup);

		this.gameOverGroup = new Group();
		// add gameOverBackground
		Image gameOverBackground = new Image(assetmanager.get(
				"img/gameOverBackground.png", Texture.class));
		gameOverBackground.setSize(BalloonBusterGame.V_WIDTH,
				BalloonBusterGame.V_HEIGHT);
		gameOverGroup.addActor(gameOverBackground);

		// add Layout and Widgets
		Table table = new Table();
		table.debug();
		table.top();
		table.setSize(BalloonBusterGame.V_WIDTH, BalloonBusterGame.V_HEIGHT);
		
		Label gameOverLabel = new Label("Game Over!", new Label.LabelStyle(
				new BitmapFont(), Color.BLACK));
		gameOverLabel.setFontScale(6);
		table.add(gameOverLabel).expandX().padTop(250);
		table.row();
		gameOverScoreLabel = new Label("Your Time: "+formatter.format(score)+"s", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		gameOverScoreLabel.setFontScale(4);
		table.add(gameOverScoreLabel).expandX().padTop(50);
		table.row();
		
		
		TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.font = new BitmapFont();
		TextButton restartButton = new TextButton("Restart", buttonStyle);
		table.add(restartButton).expandX();
		playGroup.addActor(table);//For debugging reasons

		gameOverGroup.setVisible(false);
		stage.addActor(gameOverGroup);
	}

	public void update(float dt) {
		if (!gameOver) {
			score += dt;
			scoreLabel.setText("Time: " + formatter.format(score)+"s");
		}
	}

	public void render() {
		stage.draw();
	}

	public void gameOver() {
		this.gameOver = true;
		this.playGroup.setVisible(false);
		gameOverScoreLabel.setText("Your Time: " + formatter.format(score)+"s");
		this.gameOverGroup.setVisible(true);
	}
}
