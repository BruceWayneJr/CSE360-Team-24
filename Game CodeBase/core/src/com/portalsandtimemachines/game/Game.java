package com.portalsandtimemachines.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Game extends ApplicationAdapter{
	SpriteBatch batch;
	Texture boardBackground;
	Texture gamePieceTexture;
	Texture portalTexture;
	
	private Viewport viewport;
	OrthographicCamera camera;
	
	private Sprite boardSprite;
	
	
	
	private Sprite portalSprite1;
	private Sprite portalSprite2;
	private Sprite portalSprite3;
	private Sprite portalSprite4;
	private Sprite portalSprite5;
	private Sprite portalSprite6;
	private Sprite portalSprite7;
	private Sprite portalSprite8;
	
	private float boardSize;
	private float numberOfSpacesPerRow;
	private float windowWidth;
	private Array<Vector2> boardTransforms;
	private Array<GamePiece> gamePieces;
	
	private int index = 0;
	
//	ShapeRenderer shapeRenderer;
	
	GamePiece gamePiece;
	Stage gamestage; 	
	Skin gameskin;
	TextButton rollDice;
	GameBoard obj = new GameBoard();
	
	@Override
	public void create () {
		// Sprite batch to store all sprites before sending to GPU
		batch = new SpriteBatch();
		gamestage = new Stage();
		gameskin = new Skin();
		// Checkered background texture
		boardBackground = new Texture("10x10_checkered_board.png");
		gamePieceTexture = new Texture("GamePiece.png");
		portalTexture = new Texture("brunswick-spiral-black-white.png");
		
		BitmapFont bfont=new BitmapFont();
//		bfont.scale(1);
		gameskin.add("default",bfont);
		
		Pixmap pixmap = new Pixmap(100, 50, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		gameskin.add("white", new Texture(pixmap));

		Gdx.input.setInputProcessor(gamestage);
        
        TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = gameskin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = gameskin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = gameskin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = gameskin.newDrawable("white", Color.LIGHT_GRAY);
		
		textButtonStyle.font = gameskin.getFont("default");

		gameskin.add("default", textButtonStyle);
		
		rollDice = new TextButton("Roll Dice", gameskin);
		gamestage.addActor(rollDice);
		
	    // Camera to manage viewport and  view matricies
		camera = new OrthographicCamera();
		// Initialize camera to window size
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// Initialize viewport size to camera size
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		// helper variable for board size
		boardSize = camera.viewportHeight;
		numberOfSpacesPerRow = 10;
		windowWidth = camera.viewportWidth;

		// Sprite to help with board texture transformation
		boardSprite = new Sprite(boardBackground);
		boardSprite.setOriginCenter();
		boardSprite.setSize(boardSize, boardSize);
		boardSprite.setPosition(Gdx.graphics.getWidth()/2 - boardSize/2, 0); // 0 may need to be set as screenHeight/2 - boardsize/2

		
		portalSprite1 = new Sprite(portalTexture);
		portalSprite1.setOriginCenter();
		portalSprite1.setSize(32, 32);
		
		portalSprite2 = new Sprite(portalTexture);
		portalSprite2.setOriginCenter();
		portalSprite2.setSize(32, 32);
		
		portalSprite3 = new Sprite(portalTexture);
		portalSprite3.setOriginCenter();
		portalSprite3.setSize(32, 32);
		
		portalSprite4 = new Sprite(portalTexture);
		portalSprite4.setOriginCenter();
		portalSprite4.setSize(32, 32);
		
		portalSprite5 = new Sprite(portalTexture);
		portalSprite5.setOriginCenter();
		portalSprite5.setSize(32, 32);
		
		portalSprite6 = new Sprite(portalTexture);
		portalSprite6.setOriginCenter();
		portalSprite6.setSize(32, 32);
		
		portalSprite7 = new Sprite(portalTexture);
		portalSprite7.setOriginCenter();
		portalSprite7.setSize(32, 32);
		
		portalSprite8 = new Sprite(portalTexture);
		portalSprite8.setOriginCenter();
		portalSprite8.setSize(32, 32);		
		// Used to draw shapes on screen 
//		shapeRenderer = new ShapeRenderer();
		
		// ArrayList to hold centers of the spaces
		boardTransforms = new Array<Vector2>(100);
		boardTransforms = calculateTransforms(boardSize, numberOfSpacesPerRow, windowWidth);
		
		// Draw portalSprite at the 44th space
		// Simply for testing purposes.
		
		obj.init();
		int[] temp = obj.portal_positions(); 
		int i = 0;
		
		portalSprite1.setPosition(boardTransforms.get(temp[i]).x - 32, boardTransforms.get(temp[i]).y);
		i++;
		
		portalSprite2.setPosition(boardTransforms.get(temp[i]).x - 32, boardTransforms.get(temp[i]).y);
		i++;
		
		portalSprite3.setPosition(boardTransforms.get(temp[i]).x - 32, boardTransforms.get(temp[i]).y);
		i++;
		
		portalSprite4.setPosition(boardTransforms.get(temp[i]).x - 32, boardTransforms.get(temp[i]).y);
		i++;
		
		portalSprite5.setPosition(boardTransforms.get(temp[i]).x - 32, boardTransforms.get(temp[i]).y);
		i++;
		
		portalSprite6.setPosition(boardTransforms.get(temp[i]).x - 32, boardTransforms.get(temp[i]).y);
		i++;
		
		portalSprite7.setPosition(boardTransforms.get(temp[i]).x - 32, boardTransforms.get(temp[i]).y);
		i++;
		
		portalSprite8.setPosition(boardTransforms.get(temp[i]).x - 32, boardTransforms.get(temp[i]).y);
		i++;
		
		gamePiece = new GamePiece(0, gamePieceTexture, boardTransforms.get(0));
		
		rollDice.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				Random rand = new Random();
				int temp = rand.nextInt((6 - 1) + 1) + 1;
				moving_piece(temp);
				JOptionPane.showMessageDialog(null,"Clicked " + temp);
//				rollDice.setText("Starting new game");
			}
		});
	}

	public Array<Vector2> calculateTransforms(float boardSize, float numberOfSpacesPerRow, float windowWidth){
		Array<Vector2> temporaryArray = new Array<Vector2>(100);
		for(int i = 0; i < 10; i++){ // Horizontal movement
			if(i % 2 == 0){
				for(int j = 0; j < 10; j++){ // Vertical movement
					//Vector2 transform = new Vector2(768/10 * i, 768/10 * j);
					temporaryArray.add(new Vector2(boardSize/numberOfSpacesPerRow * j + ((boardSize/numberOfSpacesPerRow)/2) + ((windowWidth - boardSize)/2), 
							boardSize/numberOfSpacesPerRow * i + ((boardSize/numberOfSpacesPerRow)/2)));
				}
			}
			else{
				for(int j = 9; j >= 0; j--){ // Vertical movement
					//Vector2 transform = new Vector2(768/10 * i, 768/10 * j);
					temporaryArray.add(new Vector2(boardSize/numberOfSpacesPerRow * j + ((boardSize/numberOfSpacesPerRow)/2) + ((windowWidth - boardSize)/2), 
							boardSize/numberOfSpacesPerRow * i + ((boardSize/numberOfSpacesPerRow)/2)));
				}
			}
		}
		return temporaryArray;
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
		
		gamestage.draw();
		
		batch.begin();
		boardSprite.draw(batch);
		
		portalSprite1.draw(batch);
		portalSprite2.draw(batch);
		portalSprite3.draw(batch);
		portalSprite4.draw(batch);
		portalSprite5.draw(batch);
		portalSprite6.draw(batch);
		portalSprite7.draw(batch);
		portalSprite8.draw(batch);
		
		gamePiece.draw(batch);
		batch.end();
		
//		shapeRenderer.setProjectionMatrix(camera.combined);
//		shapeRenderer.begin(ShapeType.Filled);
//		shapeRenderer.setColor(1, 0, 0, 1);
//		for(Vector2 transform: boardTransforms){
//			shapeRenderer.circle(transform.x, transform.y, 5);
//		}
//		shapeRenderer.circle(boardTransforms.get(index).x, boardTransforms.get(index).y, 5);
//		shapeRenderer.end();
		
//		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
//			if(index < 99){
//				index++;
//			}
//			else{
//				index = 0;
//			}
//			
//			gamePiece.moveToPosition(boardTransforms.get(index));
//		}
	}
	
	
	public void moving_piece(int value)
	{
		index = index + value;
		gamePiece.moveToPosition(boardTransforms.get(index));
		
		if(obj.check_portal(index) != 0)
		{
			index = index + obj.check_portal(index);
			gamePiece.moveToPosition(boardTransforms.get(index));
		}
	}
	
	@Override
	public void dispose(){
		boardBackground.dispose();
//		shapeRenderer.dispose();
		batch.dispose();
	}
}