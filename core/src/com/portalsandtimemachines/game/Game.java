package com.portalsandtimemachines.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.Array;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture boardBackground;
	
	private Viewport viewport;
	OrthographicCamera camera;
	
	private Sprite boardSprite;
	private float boardsize;
	private float numberOfSpacesPerRow;
	private float windowWidth;
	private Array<Vector2> boardTransforms;
	
	ShapeRenderer shapeRenderer;
	
	@Override
	public void create () {
		// Sprite batch to store all sprites before sending to GPU
		batch = new SpriteBatch();
		// Checkered background texture
		boardBackground = new Texture("10x10_checkered_board.png");
		
		// Camera to manage viewport and  view matricies
		camera = new OrthographicCamera();
		// Initialize camera to window size
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// Initialize viewport size to camera size
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		// helper variable for board size
		boardsize = camera.viewportHeight;
		numberOfSpacesPerRow = 10;
		windowWidth = camera.viewportWidth;

		// Sprite to help with board texture transformation
		boardSprite = new Sprite(boardBackground);
		boardSprite.setOriginCenter();
		boardSprite.setSize(boardsize, boardsize);
		boardSprite.setPosition(Gdx.graphics.getWidth()/2 - boardsize/2, 0); // 0 may need to be set as screenHeight/2 - boardsize/2
		
		shapeRenderer = new ShapeRenderer();
		
		boardTransforms = new Array<Vector2>(100);
		
		for(int i = 0; i < 10; i++){ // Horizontal movement
			for(int j = 0; j < 10; j++){ // Vertical movement
//				Vector2 transform = new Vector2(768/10 * i, 768/10 * j);
				boardTransforms.add(new Vector2(768/10 * i + ((boardsize/numberOfSpacesPerRow)/2) + ((windowWidth - boardsize)/2), 
						boardsize/numberOfSpacesPerRow * j + ((boardsize/numberOfSpacesPerRow)/2)));
			}
		}
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
		
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(1, 0, 0, 1);
		for(Vector2 transform: boardTransforms){
			shapeRenderer.circle(transform.x, transform.y, 5);
		}
		shapeRenderer.end();
	}
}
