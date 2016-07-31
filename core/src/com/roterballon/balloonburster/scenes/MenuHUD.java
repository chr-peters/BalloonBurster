package com.roterballon.balloonburster.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.roterballon.balloonburster.screens.PlayScreen;

public class MenuHUD {
    private com.roterballon.balloonburster.BalloonBursterGame game;
    private Stage stage;
    private Viewport viewport;
    private Skin skin;
    private AssetManager assetmanager;

    public MenuHUD(com.roterballon.balloonburster.BalloonBursterGame game) {
        this.game = game;
        this.assetmanager = game.getAssetManager();
        this.viewport = new FitViewport(com.roterballon.balloonburster.BalloonBursterGame.V_WIDTH,
                com.roterballon.balloonburster.BalloonBursterGame.V_HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, game.getSpriteBatch());

        this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        table.top();

        Label logo = new Label("Balloon Burster", skin, "logo");
        logo.getStyle().font.getData().scaleX *= 0.9f;
        logo.getStyle().font.getData().scaleY *= 0.9f;
        table.add(logo).expandX().padTop(220);

        table.row();

        ImageButton playButton = new ImageButton(new TextureRegionDrawable(
                assetmanager.get("img/buttons/buttons.atlas",
                        TextureAtlas.class).findRegion("btn_play")), new TextureRegionDrawable(
                assetmanager.get("img/buttons/buttons.atlas",
                        TextureAtlas.class).findRegion("btn_play_pressed")));
        playButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                MenuHUD.this.game.transition(new PlayScreen(MenuHUD.this.game));
            }

        });
        table.add(playButton).expandX().padTop(50).size(400, 70);

        table.row();

        ImageButton scoreButton = new ImageButton(new TextureRegionDrawable(
                assetmanager.get("img/buttons/buttons.atlas",
                        TextureAtlas.class).findRegion("btn_scores")), new TextureRegionDrawable(
                assetmanager.get("img/buttons/buttons.atlas",
                        TextureAtlas.class).findRegion("btn_scores_pressed")));
        scoreButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                MenuHUD.this.game
                        .transition(new com.roterballon.balloonburster.screens.ScoreScreen(MenuHUD.this.game));
            }

        });
        table.add(scoreButton).expandX().padTop(50).size(400, 70);

        table.row();

        ImageButton aboutButton = new ImageButton(new TextureRegionDrawable(
                assetmanager.get("img/buttons/buttons.atlas",
                        TextureAtlas.class).findRegion("btn_about")), new TextureRegionDrawable(
                assetmanager.get("img/buttons/buttons.atlas",
                        TextureAtlas.class).findRegion("btn_about_pressed")));
        aboutButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                MenuHUD.this.game
                        .transition(new com.roterballon.balloonburster.screens.AboutScreen(MenuHUD.this.game));
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
