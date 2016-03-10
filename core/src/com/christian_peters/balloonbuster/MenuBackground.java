package com.christian_peters.balloonbuster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.christian_peters.balloonbuster.sprites.Balloon;
import com.christian_peters.balloonbuster.sprites.Sky;
import com.christian_peters.balloonbuster.sprites.Sun;

public class MenuBackground {
	private CloudManager clouds;
	private Sun sun;
	private Sky sky;
	private List<Balloon> balloons;
	private AssetManager assetmanager;

	private float balloonMinHeight;
	private float balloonMaxHeight;
	private int balloonQuantity;
	private float balloonMinVelocity;
	private float balloonMaxVelocity;

	public MenuBackground(AssetManager assetmanager) {
		this.assetmanager = assetmanager;

		// Create Sky
		Texture skyTexture = assetmanager.get("img/sky.jpg", Texture.class);
		float skyAspect = (float) (skyTexture.getWidth())
				/ skyTexture.getHeight();
		sky = new Sky(skyTexture);
		sky.setSize(BalloonBusterGame.V_HEIGHT * skyAspect,
				BalloonBusterGame.V_HEIGHT);
		sky.setCenter(BalloonBusterGame.V_WIDTH / 2,
				BalloonBusterGame.V_HEIGHT / 2);

		// Create Clouds
		clouds = new CloudManager(assetmanager);

		// Create Sun
		Texture sunTexture = assetmanager.get("img/sun.png", Texture.class);
		float sunAspect = (float) (sunTexture.getWidth())
				/ sunTexture.getHeight();
		sun = new Sun(sunTexture);
		float sunHeight = 150f;
		sun.setSize(sunHeight * sunAspect, sunHeight);
		sun.setCenter(BalloonBusterGame.V_WIDTH * 0.8f, 1100f);
		sun.setOrigin(sun.getWidth() / 2, sun.getHeight() / 2);

		// Create Balloons
		this.balloonMinHeight = 50;
		this.balloonMaxHeight = 100;
		this.balloonQuantity = 10;
		this.balloonMinVelocity = 7;
		this.balloonMaxVelocity = 20;
		
		this.balloons = new ArrayList<Balloon>();
		initBalloons();
	}

	private void initBalloons() {
		Random r = new Random();
		Balloon tmp;
		addLoop:
		for (int i = 0; i < balloonQuantity; i++) {
			float tmpHeight = balloonMinHeight + r.nextFloat()
					* (balloonMaxHeight - balloonMinHeight);
			tmp = new Balloon(assetmanager.get("img/balloons/balloon_red.atlas",
					TextureAtlas.class).getRegions(), tmpHeight);
			tmp.setPosition(
					r.nextFloat()
							* (BalloonBusterGame.V_WIDTH - tmp.getWidth()),
					r.nextFloat()
							* (BalloonBusterGame.V_HEIGHT - tmp.getHeight()));
			tmp.setVelocity(balloonMinVelocity + r.nextFloat()*(balloonMaxVelocity-balloonMinVelocity));
			for(Balloon b: balloons){//Check for overlapping
				if(b.getBoundingRectangle().overlaps(tmp.getBoundingRectangle())){
					i--;
					continue addLoop;
				}
			}
			balloons.add(tmp);
		}
	}
	
	private boolean isVisible(Balloon b){
		if(b.getY()>=BalloonBusterGame.V_HEIGHT){
			return false;
		}
		return true;
	}
	
	private void addBalloon(){
		Random r = new Random();
		Balloon tmp;
		float tmpHeight = balloonMinHeight + r.nextFloat()
				* (balloonMaxHeight - balloonMinHeight);
		tmp = new Balloon(assetmanager.get("img/balloons/balloon_red.atlas",
				TextureAtlas.class).getRegions(), tmpHeight);
		tmp.setPosition(
				r.nextFloat()
						* (BalloonBusterGame.V_WIDTH - tmp.getWidth()),
				- tmp.getHeight());
		tmp.setVelocity(balloonMinVelocity + r.nextFloat()*(balloonMaxVelocity-balloonMinVelocity));
		balloons.add(tmp);
	}

	public void update(float dt) {
		sky.update(dt);
		sun.update(dt);
		clouds.update(dt);
		
		//Update Balloons
		List<Balloon> invisible = new ArrayList<Balloon>();
		for(Balloon b: balloons){
			if(isVisible(b)){
				b.update(dt);
			} else {
				invisible.add(b);
			}
		}
		
		for(Balloon b: invisible){
			balloons.remove(b);
			addBalloon();
		}
	}

	public void render(SpriteBatch batch) {
		sky.draw(batch);
		sun.draw(batch);
		clouds.render(batch);
		
		//Render Balloons
		for(Balloon b: balloons){
			b.draw(batch);
		}
	}
}
