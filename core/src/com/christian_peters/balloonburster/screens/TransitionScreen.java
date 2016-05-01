package com.christian_peters.balloonburster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TransitionScreen implements Screen{
	
	private float fadeTime;
	private float timePassed;
	private Screen start;
	private Screen end;
	private ShapeRenderer renderer;
	private com.christian_peters.balloonburster.BalloonBusterGame game;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	public TransitionScreen(com.christian_peters.balloonburster.BalloonBusterGame game, Screen start, Screen end){
		this.fadeTime = 0.4f;
		this.timePassed = 0f;
		this.start = start;
		this.end = end;
		this.end.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.game =game;
		
		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(com.christian_peters.balloonburster.BalloonBusterGame.V_WIDTH, com.christian_peters.balloonburster.BalloonBusterGame.V_HEIGHT, camera);
		camera.translate(com.christian_peters.balloonburster.BalloonBusterGame.V_WIDTH/2, com.christian_peters.balloonburster.BalloonBusterGame.V_HEIGHT/2);
		camera.update();
		renderer = new ShapeRenderer();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		if(fadeTime/2>timePassed){
			start.render(delta);
			Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
			renderer.setProjectionMatrix(camera.combined);
			renderer.begin(ShapeRenderer.ShapeType.Filled);
			renderer.setColor(0, 0, 0, timePassed/(fadeTime/2));
			renderer.rect(0, 0, com.christian_peters.balloonburster.BalloonBusterGame.V_WIDTH, com.christian_peters.balloonburster.BalloonBusterGame.V_HEIGHT);
			renderer.end();
			Gdx.graphics.getGL20().glDisable(GL20.GL_BLEND);
		} else if(fadeTime>timePassed){
			end.render(delta);
			Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
			renderer.setProjectionMatrix(camera.combined);
			renderer.begin(ShapeRenderer.ShapeType.Filled);
			renderer.setColor(0, 0, 0, 1-Math.min(1, (timePassed-fadeTime/2)/(fadeTime/2)));
			renderer.rect(0, 0, com.christian_peters.balloonburster.BalloonBusterGame.V_WIDTH, com.christian_peters.balloonburster.BalloonBusterGame.V_HEIGHT);
			renderer.end();
			Gdx.graphics.getGL20().glDisable(GL20.GL_BLEND);
		} else {
			game.setScreen(end);
		}
		timePassed += delta;
	}

	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		renderer.dispose();		
	}

}
