package com.christian_peters.balloonbuster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.christian_peters.balloonbuster.sprites.Balloon;

public class MenuBalloons {
	private AssetManager assetmanager;

	private float balloonMinHeight;
	private float balloonMaxHeight;
	private int balloonQuantity;
	private float balloonMinVelocity;
	private float balloonMaxVelocity;
	private List<Balloon> balloons;

	public MenuBalloons(AssetManager assetmanager) {

		this.assetmanager = assetmanager;
		this.balloonMinHeight = 50;
		this.balloonMaxHeight = 100;
		this.balloonQuantity = 10;
		this.balloonMinVelocity = 7;
		this.balloonMaxVelocity = 20;

		this.balloons = new ArrayList<Balloon>();
		initBalloons();
	}

	private void initBalloons() {
		for (int i = 0; i < balloonQuantity; i++) {
			addBalloon(false);
		}
	}

	private boolean isVisible(Balloon b) {
		if (b.getY() >= BalloonBusterGame.V_HEIGHT) {
			return false;
		}
		return true;
	}

	private void addBalloon(boolean offScreen) {
		Random r = new Random();
		Balloon tmp;
		float tmpHeight = balloonMinHeight + r.nextFloat()
				* (balloonMaxHeight - balloonMinHeight);
		tmp = new Balloon(assetmanager.get("img/balloons/balloons.atlas",
				TextureAtlas.class).getRegions(), assetmanager.get(
				"img/burst.png", Texture.class), assetmanager.get(
				"sound/burst.mp3", Sound.class), tmpHeight);
		if(offScreen) {
			tmp.setPosition(
					r.nextFloat() * (BalloonBusterGame.V_WIDTH - tmp.getWidth()),
					-tmp.getHeight());
		} else {
			tmp.setPosition(
					r.nextFloat()
							* (BalloonBusterGame.V_WIDTH - tmp.getWidth()),
					r.nextFloat()
							* (BalloonBusterGame.V_HEIGHT - tmp.getHeight()));
		}
		tmp.setVelocity(balloonMinVelocity + r.nextFloat()
				* (balloonMaxVelocity - balloonMinVelocity));
		for (Balloon b : balloons) {// Check for overlapping
			if (b.getBoundingRectangle().overlaps(
					tmp.getBoundingRectangle())) {
				addBalloon(offScreen);
				return;
			}
		}
		balloons.add(tmp);
	}

	public void update(float dt) {
		List<Balloon> invisible = new ArrayList<Balloon>();
		for (Balloon b : balloons) {
			if (isVisible(b)) {
				b.update(dt);
			} else {
				invisible.add(b);
			}
		}

		for (Balloon b : invisible) {
			balloons.remove(b);
			addBalloon(true);
		}
	}

	public void render(SpriteBatch batch) {
		for (Balloon b : balloons) {
			b.draw(batch);
		}
	}
}
