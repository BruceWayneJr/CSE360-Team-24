package com.portalsandtimemachines.game;
import static org.junit.Assert.*;
import org.junit.Test;

public class GameBoardTest {
	
	@Test
	public void testInit() {
		GameBoard testobj = new GameBoard();
		assertNotNull(testobj);
		assertEquals("Status:", "Initializing the portal values", "Initializing the portal values");
	}

	@Test
	public void testRoll_die() {
		GameBoard testobj = new GameBoard();
		boolean outOfBounds = true;
		
		int rolledNumber = testobj.roll_die();
		//System.out.println(rolledNumber);
		
		if( rolledNumber >= 1 || rolledNumber <= 6)
			outOfBounds = false;
		
		assertFalse(outOfBounds); 
	}

	@Test
	public void testPrint() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		testobj.init();
		int[] temp = testobj.portal_positions();
		assertEquals(temp.length,8);
		
		
	}

	@Test
	public void testPortal_positions() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		testobj.init();
		int[] temp = testobj.portal_positions();
		assertNotNull(temp);
	}


	@Test
	public void testCheck_portal() {
		GameBoard testobj = new GameBoard();
		testobj.init();
		assertEquals(0,testobj.check_portal(150));
		
	}

	@Test
	public void testInit_time_machine() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		assertNotNull(testobj);
		assertEquals("Status:", "Initializing the Time Machine values", "Initializing the Time Machine values");
	}

	@Test
	public void testTM_positions() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		testobj.init_time_machine();
		int[] temp = testobj.TM_positions();
		assertNotNull(temp);
		
	}


	@Test
	public void testCheck_TM() {
//		fail("Not yet implemented");
		GameBoard testobj = new GameBoard();
		testobj.init_time_machine();
		assertEquals(0,testobj.check_TM(150));
	}

}
