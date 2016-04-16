package com.portalsandtimemachines.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Dice {
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 1;
	Animation rollAnimation;
    Texture diceSheet;
    TextureRegion[] rollFrames;
    TextureRegion currentFrame;
    float stateTime;
    boolean animate;
    private Random rand;
    
    Dice(){
    	rand = new Random();
    	diceSheet = new Texture("dice.png");
    	TextureRegion[][] temp = TextureRegion.split(diceSheet, 
    			diceSheet.getWidth() / FRAME_COLS, 
    			diceSheet.getHeight() / FRAME_ROWS);
    	rollFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
    	int index = 0;
    	for(int i = 0; i < FRAME_ROWS; i++){
    		for(int j = 0; j < FRAME_COLS; j++){
    			rollFrames[index++] = temp[i][j];
    		}
    	}
    	rollAnimation = new Animation(0.05f, rollFrames);
    	stateTime = 0f;
    	animate = true;
    }
    
    void draw(SpriteBatch batch){
    	if(animate){
//    		stateTime += Gdx.graphics.getDeltaTime();
    		stateTime = rand.nextInt(5) * 0.05f;
    	}
    	currentFrame = rollAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, 
        		Gdx.graphics.getWidth()/2 - (diceSheet.getWidth() / FRAME_COLS),
        		Gdx.graphics.getHeight()/2 - (diceSheet.getHeight() / FRAME_COLS));
    }
    
    void showNumber(float index){
    	animate = false;
    	currentFrame = rollAnimation.getKeyFrame(index - 1, false);
    }
    
    void changeAnimate(){
    	animate = !animate;
    }
    
    int getStateTimeAsIndex(){
    	return (int)stateTime % FRAME_COLS;
    }
}
