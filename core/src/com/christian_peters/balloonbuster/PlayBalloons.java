package com.christian_peters.balloonbuster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.christian_peters.balloonbuster.sprites.Balloon;

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
	private List<Balloon> balloons;
	private List<Balloon> touched;// List of the touched balloons which will be
									// removed in the next frame
	private float curVelocity;
	private float maxVelocity;
	private float accelerationFactor;// multiplier of dt in each frame
	private AssetManager assetmanager;
	

	public PlayBalloons(AssetManager assetmanager) {
		this.assetmanager = assetmanager;
		this.curDelay = 1.5f;
		this.minDelay = 0.3f;
		this.delayChangeFactor = 0.08f;
		this.timeSinceLastBalloon = 0f;
		this.curVelocity = 700f;
		this.maxVelocity = 1300f;
		this.accelerationFactor = 20f;
		this.balloonHeight = 250f;
		this.balloons = new ArrayList<Balloon>();
		this.touched = new ArrayList<Balloon>();
	}

	public void update(float dt) {
		if (paused) {
			return;
		}
		for (Balloon b : touched) {
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

		for (Balloon b : balloons) {
			b.update(dt);
		}
		timeSinceLastBalloon += dt;
	}

	public void render(SpriteBatch batch) {
		for (Balloon b : balloons) {
			b.draw(batch);
		}
	}

	public boolean isGameOver() {
		for (Balloon b : balloons) {
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
		for (Balloon b : balloons) {
			if (b.getBoundingRectangle().contains(x, y)) {
				touched.add(b);
			}
		}
	}

	private void addBalloon() {
		Random r = new Random();
		Balloon tmp = new Balloon(assetmanager.get("img/balloons/balloon_red.atlas",
				TextureAtlas.class).getRegions(), this.balloonHeight);
		tmp.setPosition(
				r.nextFloat() * (BalloonBusterGame.V_WIDTH - tmp.getWidth()),
				-1 * tmp.getHeight());
		tmp.setVelocity(curVelocity);
		balloons.add(tmp);
	}
}
