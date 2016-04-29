package com.christian_peters.balloonbuster.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
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

	public AboutHUD(BalloonBusterGame game) {
		this.game = game;
		this.assetmanager = game.getAssetManager();
		this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"),
				assetmanager.get("skin/uiskin.atlas", TextureAtlas.class));
		this.viewport = new FitViewport(BalloonBusterGame.V_WIDTH,
				BalloonBusterGame.V_HEIGHT, new OrthographicCamera());
		this.stage = new Stage(this.viewport, game.getSpriteBatch());

		// create wrapper table
		Table wrapper = new Table();
		wrapper.top();
		wrapper.setWidth(BalloonBusterGame.V_WIDTH * 0.8f);
		wrapper.setHeight(BalloonBusterGame.V_HEIGHT * 0.8f);


		Label aboutLabel = new Label("About", skin, "logo");
		wrapper.add(aboutLabel).expandX().align(Align.left);
		wrapper.row();

		// create scroll wrapper table
		Table scrollWrapper = new Table();
		//scrollWrapper.debug();
		scrollWrapper.top();

		// create content for scrollWrapper
		Label graphicsLabel = new Label("Graphics", skin, "bold");
		scrollWrapper.add(graphicsLabel).expandX().width(wrapper.getWidth()*0.9f);
		scrollWrapper.row();

		String longText = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, "
				+ "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, "
				+ "sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. "
				+ "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. "
				+ "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor "
				+ "invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. "
				+ "At vero eos et accusam et justo duo dolores et ea rebum. "
				+ "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
		
		Label longTextLabel = new Label(longText, skin);
		longTextLabel.setWrap(true);
		scrollWrapper.add(longTextLabel).align(Align.left).width(wrapper.getWidth()*0.9f);
		scrollWrapper.pack();

		// create scroll pane
		ScrollPane scrollContainer = new ScrollPane(scrollWrapper);
		scrollContainer.debugAll();
		scrollContainer.setOverscroll(false, true);
		System.out.println(scrollContainer.getHeight());

		wrapper.add(scrollContainer).expandX().align(Align.left);

		// set Background
		wrapper.setBackground(new NinePatchDrawable(new NinePatch(assetmanager
				.get("img/bg_ninepatch.png", Texture.class), 10, 10, 10, 10)));
		wrapper.pack();

		wrapper.setWidth(BalloonBusterGame.V_WIDTH * 0.8f);
		wrapper.setHeight(BalloonBusterGame.V_HEIGHT * 0.8f);

		wrapper.setPosition(BalloonBusterGame.V_WIDTH / 2,
				BalloonBusterGame.V_HEIGHT - wrapper.getHeight() / 2 - 220,
				Align.center);
		// padTop of 220

		ImageButton menu = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(assetmanager.get("img/buttons/btn_menu.png",
						Texture.class))), new TextureRegionDrawable(
				new TextureRegion(assetmanager.get(
						"img/buttons/btn_menu_pressed.png", Texture.class))));

		menu.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				AboutHUD.this.game
						.transition(new MenuScreen(AboutHUD.this.game));
			}

		});

		menu.setSize(400, 70);
		menu.setPosition(BalloonBusterGame.V_WIDTH / 2, wrapper.getY() - 50,
				Align.top | Align.center);

		stage.addActor(wrapper);
		stage.addActor(menu);

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
		this.stage.dispose();
	}
}
