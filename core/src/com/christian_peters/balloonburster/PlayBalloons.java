package com.christian_peters.balloonburster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Chris on 06.03.2016.
 */
public class PlayBalloons {
	private boolean paused;
	private float curDelay;
	private float minDelay;
	private float delayChangeFactor;// multiplier of dt in each frame
	private float timeSinceLastBalloon;
	private float balloonHeight;
	private List<com.christian_peters.balloonburster.sprites.Balloon> balloons;
	private List<com.christian_peters.balloonburster.sprites.Balloon> touched;// List of the touched balloons which will be
									// removed in the next frame
	private float curVelocity;
	private float maxVelocity;
	private float accelerationFactor;// multiplier of dt in each frame
	private AssetManager assetmanager;

	public PlayBalloons(AssetManager assetmanager) {
		this.assetmanager = assetmanager;
		this.curDelay = 1f;
		this.minDelay = 0.25f;
		this.delayChangeFactor = 0.05f;
		this.timeSinceLastBalloon = 0f;
		this.curVelocity = 700f;
		this.maxVelocity = 1600f;
		this.accelerationFactor = 20f;
		this.balloonHeight = 250f;
		this.balloons = new ArrayList<com.christian_peters.balloonburster.sprites.Balloon>();
		this.touched = new ArrayList<com.christian_peters.balloonburster.sprites.Balloon>();
	}

	public void update(float dt) {
		if (paused) {
			return;
		}
		for (com.christian_peters.balloonburster.sprites.Balloon b : touched) {
			balloons.remove(b);
		}

		if (timeSinceLastBalloon >= curDelay) {
			addBalloon();
			timeSinceLastBalloon = 0f;
		}

		// Update Velocity
		if (curVelocity + accelerationFactor * dt <= maxVelocity) {
			curVelocity += dt * accelerationFactor;
		} else {
			curVelocity = maxVelocity;
		}

		// Update Delay
		if (curDelay - delayChangeFactor * dt >= minDelay) {
			curDelay -= delayChangeFactor * dt;
		} else {
			curDelay = minDelay;
		}

		for (com.christian_peters.balloonburster.sprites.Balloon b : balloons) {
			if (b.getCurState() != com.christian_peters.balloonburster.sprites.Balloon.States.DEAD) {
				b.update(dt);
			} else {
				touched.add(b);
			}
		}
		timeSinceLastBalloon += dt;
	}

	public void render(SpriteBatch batch) {
		for (com.christian_peters.balloonburster.sprites.Balloon b : balloons) {
			b.draw(batch);
		}
	}

	public boolean isGameOver() {
		for (com.christian_peters.balloonburster.sprites.Balloon b : balloons) {
			if (b.getY() >= BalloonBusterGame.V_HEIGHT) {
				return true;
			}
		}
		return false;
	}

	public void pause() {
		this.paused = true;
	}

	public void onTouch(float x, float y) {
		for (com.christian_peters.balloonburster.sprites.Balloon b : balloons) {
			if (b.getBoundingRectangle().contains(x, y)) {
				b.burst();
			}
		}
	}

	private void addBalloon() {
		Random r = new Random();
		com.christian_peters.balloonburster.sprites.Balloon tmp = new com.christian_peters.balloonburster.sprites.Balloon(
				assetmanager.get("img/balloons/balloons.atlas",
						TextureAtlas.class).getRegions(), assetmanager.get(
						"img/burst.png", Texture.class), assetmanager.get(
								"sound/burst.mp3", Sound.class),this.balloonHeight);
		tmp.setPosition(
				r.nextFloat() * (BalloonBusterGame.V_WIDTH - tmp.getWidth()),
				-1 * tmp.getHeight());
		tmp.setVelocity(curVelocity);
		balloons.add(tmp);
	}
}
