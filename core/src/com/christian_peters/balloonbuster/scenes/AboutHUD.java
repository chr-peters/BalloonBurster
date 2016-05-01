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
		wrapper.setHeight(BalloonBusterGame.V_HEIGHT * 0.65f);


		Label aboutLabel = new Label("About", skin, "logo");
		wrapper.add(aboutLabel).expandX().align(Align.left);
		wrapper.row();

		// create scroll wrapper table
		Table scrollWrapper = new Table();
		//scrollWrapper.debug();
		scrollWrapper.top();

		// create content for scrollWrapper
		Label graphicsLabel = new Label("Graphics", skin, "bold");
		scrollWrapper.add(graphicsLabel).width(wrapper.getWidth()*0.9f);
		scrollWrapper.row();
		
		//cloud 1 attribution
		Label cloud1LinkLabel = new Label("Clouds", skin, "link");
		cloud1LinkLabel.setAlignment(Align.center);
		cloud1LinkLabel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.net.openURI("https://www.flickr.com/photos/craightonmiller/5944377614/");
			}
		});
		scrollWrapper.add(cloud1LinkLabel).width(wrapper.getWidth() * 0.9f);
		scrollWrapper.row();

		String cloud1Text = "by Craighton Miller is licensed under CC BY 2.0";
		Label cloud1TextLabel = new Label(cloud1Text, skin);
		cloud1TextLabel.setWrap(true);
		scrollWrapper.add(cloud1TextLabel).width(wrapper.getWidth() * 0.9f);
		scrollWrapper.row();

		//cloud 2 attribution
		Label cloud2LinkLabel = new Label("Single white cloud on a clear blue sky", skin, "link");
		cloud2LinkLabel.setWrap(true);
		cloud2LinkLabel.setAlignment(Align.center);
		cloud2LinkLabel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.net.openURI("https://www.flickr.com/photos/horiavarlan/4777129318");
			}
		});
		scrollWrapper.add(cloud2LinkLabel).width(wrapper.getWidth() * 0.9f).padTop(50);
		scrollWrapper.row();

		String cloud2Text = "by Horia Varlan is licensed under CC BY 2.0";
		Label cloud2TextLabel = new Label(cloud2Text, skin);
		cloud2TextLabel.setWrap(true);
		scrollWrapper.add(cloud2TextLabel).width(wrapper.getWidth() * 0.9f);
		scrollWrapper.row();

		//balloons attribution
		Label balloonsTextLabel = new Label("Balloons created by Marius Nolden", skin);
		balloonsTextLabel.setWrap(true);
		balloonsTextLabel.setAlignment(Align.center);
		scrollWrapper.add(balloonsTextLabel).width(wrapper.getWidth() * 0.9f).padTop(50);
		scrollWrapper.row();

		//sound attribution
		Label soundLabel = new Label("Sound", skin, "bold");
		scrollWrapper.add(soundLabel).width(wrapper.getWidth() * 0.9f).padTop(50);
		scrollWrapper.row();

		//balloon pop attribution
		Label balloonPopLinkLabel = new Label("Balloon Pop", skin, "link");
		balloonPopLinkLabel.setAlignment(Align.center);
		balloonPopLinkLabel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.net.openURI("http://www.freesound.org/people/qubodup/sounds/222373/");
			}
		});
		scrollWrapper.add(balloonPopLinkLabel).width(wrapper.getWidth() * 0.9f);
		scrollWrapper.row();

		String balloonPopText = "by qubodup is licensed under CC BY 3.0";
		Label balloonPopTextLabel = new Label(balloonPopText, skin);
		balloonPopTextLabel.setWrap(true);
		scrollWrapper.add(balloonPopTextLabel).width(wrapper.getWidth() * 0.9f);
		scrollWrapper.row();
		
		String dev = "A game developed by Christian Peters";
		Label devLabel = new Label(dev, skin, "bold-outline");
		devLabel.setWrap(true);
		devLabel.setAlignment(Align.center);
		scrollWrapper.add(devLabel).width(wrapper.getWidth()*0.9f).padTop(50);

		scrollWrapper.pack();

		// create scroll pane
		ScrollPane scrollContainer = new ScrollPane(scrollWrapper);
		scrollContainer.setOverscroll(false, true);

		wrapper.add(scrollContainer).expandX().align(Align.left);

		// set Background
		wrapper.setBackground(new NinePatchDrawable(new NinePatch(assetmanager
				.get("img/bg_ninepatch.png", Texture.class), 10, 10, 10, 10)));
		wrapper.pack();

		wrapper.setWidth(BalloonBusterGame.V_WIDTH * 0.8f);
		wrapper.setHeight(BalloonBusterGame.V_HEIGHT * 0.65f);

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
