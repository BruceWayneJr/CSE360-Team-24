package com.portalsandtimemachines.game;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GamePiece {
	public Sprite sprite;
	public int owner;
	public Vector2 transform;
	
	public GamePiece(int playerNumber, Texture texture, Vector2 position){
		owner = playerNumber;
		sprite  = new Sprite(texture);
		sprite.setPosition(position.x, position.y);
		transform = position;
	}
	
	public void draw(SpriteBatch batch){
		sprite.draw(batch);
	}
	
	public void animateToPosition(Vector2 newPosition){
		Vector2 delta = transform.sub(newPosition);
		System.out.println(delta);
		while(!delta.isZero()){
			transform.mulAdd(delta, 1 * Gdx.graphics.getDeltaTime());
			sprite.setPosition(transform.x, transform.y);
			delta = transform.sub(newPosition).nor();
		}
	}
}
