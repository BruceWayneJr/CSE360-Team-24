package com.portalsandtimemachines.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;

public class GameTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNotNull(obj);
	}
	
	@Test
	public void testgamepiececreation() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gamePiece);
	}
	
	@Test
	public void testBoardstagen() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gamestage);
	}
	
	@Test
	public void rolltextButtonCreation() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.rollDice);
	}
	
	@Test
	public void gameskinCreation() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gameskin);
	}
	
	@Test
	public void testBoardBackgoung() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.boardBackground);
	}
//	
	
//	Texture gamePieceTexture;
//	Texture portalTexture;
//	Texture timemachineTexture;
	
	@Test
	public void testgamePieceTexture() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gamePieceTexture);
	}
	
	@Test
	public void testportalTexture() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.portalTexture);
	}
	
	@Test
	public void testtimemachineTexture() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.timemachineTexture);
	}
	
}
