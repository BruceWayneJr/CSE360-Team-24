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
		fail("Not yet implemented");
	}

	@Test
	public void testPortal_positions() {
		fail("Not yet implemented");
	}

	@Test
	public void testSet_portals() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheck_portal() {
		fail("Not yet implemented");
	}

	@Test
	public void testInit_time_machine() {
		fail("Not yet implemented");
	}

	@Test
	public void testTM_positions() {
		fail("Not yet implemented");
	}

	@Test
	public void testSet_time_machine() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheck_TM() {
		fail("Not yet implemented");
	}

}
