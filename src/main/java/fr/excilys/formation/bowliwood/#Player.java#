package fr.excilys.formation.bowliwood;

import fr.excilys.formation.bowliwood.Throw.ThrowResult;

/**
 * Player class.
 */
public class Player {

    private String name;

    private Throw[] throwList;

    public Player(String name_) {
        this.name = name_;
        this.throwList = new Throw[] {};
    }

    
    /**
     * calculates the score.
     * For now, it doesn't take into account the previous throw.
     * @return
     */
    public int score() {
        int score = 0;
        ThrowResult previous_thr = ThrowResult.OTHER;
 
        for (Throw thr : this.throwList) {
            switch (thr.getThrowType()) {
            case STRIKE:
                score = 10;
                break;
            case SPARE:
                score = thr.getFirstHit() + thr.getSecondHit();
                break;
            case OTHER:
                score = thr.getFirstHit() + thr.getSecondHit();
                break;
            }
            
            

            previous_thr = thr.getThrowType();
        }

        return score;
    }

}
