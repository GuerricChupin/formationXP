package fr.excilys.formation.bowliwood;

import org.junit.Test;
import static org.junit.Assert.*;

public class ContinuousIntegrationTest {

    @Test
    /**
     * Failing test. Should be changed for CI.
     */
    public void testFailure() {
        // fail();
    }

    public void testNextPlayer() {
        Player playerOne = new Player("Player1");
        Player playerTwo = new Player("Player2");
        Game myGame = new Game( new Player[] {playerOne, playerTwo} );

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
}
