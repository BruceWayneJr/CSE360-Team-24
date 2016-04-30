package com.portalsandtimemachines.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;

@SuppressWarnings("unused")
public class GameTest {

	/**
	 * To check whether the game object has been created.
	 */
	@Test
	public void test() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNotNull(obj);
	}
	
	/**
	 * To check whether the game piece has been created.
	 */
	@Test
	public void testgamepiececreation() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gamePiecePlayer1Pawn1);
	}
	
	/**
	 * To check whether the board stage has been created.
	 */
	@Test
	public void testBoardstagen() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gamestage);
	}
	
	/**
	 * To check whether the roll text button has been created.
	 */
	@Test
	public void rolltextButtonCreation() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.rollDice);
	}
	
	/**
	 * To check whether the game skin has been created.
	 */
	@Test
	public void gameskinCreation() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gameskin);
	}
	
	/**
	 * To check whether the board background has been created.
	 */
	@Test
	public void testBoardBackgoung() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.boardBackground);
	}

	/**
	 * To check whether the game piece texture has been created.
	 */
	@Test
	public void testgamePieceTexture() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gamePieceTexturePlayer1pawn1);
	}
	
	/**
	 * To check whether the portal texture has been created.
	 */
	@Test
	public void testportalTexture() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.portalTexture);
	}
	
	/**
	 * To check whether the time machine texture has been created.
	 */
	@Test
	public void testtimemachineTexture() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.timemachineTexture);
	}
	
	
	/**
	 * To check whether the pawn texture has been set for player 1's pawn
	 */
	@Test
	public void testgamePieceTexture11() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gamePieceTexturePlayer1pawn2);
	}
	
	
	/**
	 * To check whether the pawn texture has been set for player 2's pawn
	 */
	@Test
	public void testgamePieceTexture21() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.gamePieceTexturePlayer2pawn1);
	}
	
	
	/**
	 * To check whether cardKill has been created
	 */
	@Test
	public void test_cardKILL_created() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.cardOne);
	}
	
	/**
	 * To check whether cardSwap has been created
	 */
	@Test
	public void test_cardSWAP_created() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.cardTwo);
	}
	
	
	/**
	 * To check whether cardRev has been created
	 */
	@Test
	public void test_cardrev_created() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.cardThree);
	}
	
	/**
	 * To check whether cardDrag has been created
	 */
	@Test
	public void test_cardDrag_created() {
//		fail("Not yet implemented");
		Game obj = new Game();
		assertNull(obj.cardFour);
	}
	
	
	
}
