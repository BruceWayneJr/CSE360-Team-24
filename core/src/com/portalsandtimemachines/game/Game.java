package com.portalsandtimemachines.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture boardBackground;
	
	private Viewport viewport;
	OrthographicCamera camera;
	
	private Sprite boardSprite;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		boardBackground = new Texture("10x10_checkered_board.png");
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
		viewport = new FitViewport(1024, 768, camera);
		
		boardSprite = new Sprite(boardBackground);
		boardSprite.setOriginCenter();
		boardSprite.setSize(512, 512);
		boardSprite.setPosition(1024/2 - 512/2, 768 - 512);
	}

	public void resize(int width, int height) {
	    viewport.update(width, height);
	}
	
	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		boardSprite.draw(batch);
		batch.end();
	}
}
