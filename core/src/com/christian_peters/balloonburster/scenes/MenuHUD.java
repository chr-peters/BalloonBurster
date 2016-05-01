package com.christian_peters.balloonburster.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.christian_peters.balloonburster.screens.AboutScreen;
import com.christian_peters.balloonburster.screens.ScoreScreen;

public class MenuHUD {
	private com.christian_peters.balloonburster.BalloonBusterGame game;
	private Stage stage;
	private Viewport viewport;
	private Skin skin;
	private AssetManager assetmanager;

	public MenuHUD(com.christian_peters.balloonburster.BalloonBusterGame game) {
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.viewport = new FitViewport(com.christian_peters.balloonburster.BalloonBusterGame.V_WIDTH,
				com.christian_peters.balloonburster.BalloonBusterGame.V_HEIGHT, new OrthographicCamera());
		this.stage = new Stage(viewport, game.getSpriteBatch());

		this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"),
				assetmanager.get("skin/uiskin.atlas", TextureAtlas.class));

		Table table = new Table();
		table.setFillParent(true);
		table.top();

		Label logo = new Label("Balloon Burster", skin, "logo");
		table.add(logo).expandX().padTop(220);

		table.row();

		ImageButton playButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(assetmanager.get("img/buttons/btn_play.png",
						Texture.class))), new TextureRegionDrawable(
				new TextureRegion(assetmanager.get(
						"img/buttons/btn_play_pressed.png", Texture.class))));
		playButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				MenuHUD.this.game.transition(new com.christian_peters.balloonburster.screens.PlayScreen(MenuHUD.this.game));
			}

		});
		table.add(playButton).expandX().padTop(50).size(400, 70);

		table.row();

		ImageButton scoreButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(assetmanager.get("img/buttons/btn_scores.png",
						Texture.class))), new TextureRegionDrawable(
				new TextureRegion(assetmanager.get(
						"img/buttons/btn_scores_pressed.png", Texture.class))));
		scoreButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				MenuHUD.this.game
						.transition(new ScoreScreen(MenuHUD.this.game));
			}

		});
		table.add(scoreButton).expandX().padTop(50).size(400, 70);

		table.row();

		ImageButton aboutButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(assetmanager.get("img/buttons/btn_about.png",
						Texture.class))), new TextureRegionDrawable(
				new TextureRegion(assetmanager.get(
						"img/buttons/btn_about_pressed.png", Texture.class))));
		aboutButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				MenuHUD.this.game
						.transition(new AboutScreen(MenuHUD.this.game));
			}

		});
		table.add(aboutButton).expandX().padTop(50).size(400, 70);

		stage.addActor(table);

		Gdx.input.setInputProcessor(stage);
	}

	public void update(float dt) {
		stage.act(dt);
	}

	public void render() {
		stage.draw();
	}

	public void resize(int width, int height) {
		this.viewport.update(width, height);
	}

	public void dispose() {

	}
}
