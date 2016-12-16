package fr.excilys.formation.bowliwood;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ContinuousIntegrationTest {

	@Test
	/**
	 * Failing test. Should be changed for CI.
	 */
	public void testFailure() {
		// fail();
	}

	@Test
	public void testNextPlayer() {
        Player playerOne = new Player("Player1");
        Player playerTwo = new Player("Player2");
        ArrayList<Player> playerList = new ArrayList<Player>();
        playerList.add(playerOne);
        playerList.add(playerTwo);
        Game myGame = new Game( playerList );

        assertEquals(myGame.getCurrentPlayer(), 0);
        myGame.nextPlayer(Throw.strike());
        assertEquals(myGame.getCurrentPlayer(), 0);
        myGame.nextPlayer(Throw.spare(3));
        assertEquals(myGame.getCurrentPlayer(), 0);
        myGame.nextPlayer(Throw.other(3,3));
        assertEquals(myGame.getCurrentPlayer(), 1);
        myGame.nextPlayer(Throw.other(3,3));
        assertEquals(myGame.getCurrentPlayer(), 0);
    }

	@Test
	public void testScore() {
		Player player = new Player("Player");
		player.add_throw(Throw.other(3, 6));
		assertEquals(player.score(), 9);
		player.add_throw(Throw.spare(4));
		assertEquals(player.score(), 19);
		player.add_throw(Throw.other(3, 6));
		assertEquals(player.score(), 31);
		player.add_throw(Throw.strike());
		assertEquals(player.score(), 41);
		player.add_throw(Throw.other(5, 2));
		assertEquals(player.score(), 55);
	}

}
