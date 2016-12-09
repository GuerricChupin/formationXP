package fr.excilys.formation.bowliwood;

/**
 * Game class.
 */
public class Game {

    private Player[] playerList;

    private int currentPlayer;

    private int turnCount;


    public Game(Player[] playerList_) {
        this.turnCount = 0;
        this.playerList = playerList_;
        this.currentPlayer = 0;
    }


    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * update currentPlayer
     * (the current player can play again if he just did a strike or a spare).
     * @param myThrow
     */
    public void nextPlayer(Throw myThrow) {
        if (myThrow.isOther()) {
            this.currentPlayer = (this.currentPlayer + 1) % this.playerList.length;
            if (this.currentPlayer == 0) {
                this.turnCount +=1;
            }
        }

    }

    
}
