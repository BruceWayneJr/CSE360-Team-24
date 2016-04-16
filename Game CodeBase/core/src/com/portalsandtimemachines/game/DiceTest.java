package com.portalsandtimemachines.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {
	@Test
	public void testDice() {
		Dice diceobj = new Dice();
		assertNotNull(diceobj);
	}

	@Test
	public void testDraw() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeAnimate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStateTimeAsIndex() {
		Dice diceobj = new Dice();
		assertEquals(9,diceobj.getStateTimeAsIndex());
	}

}
