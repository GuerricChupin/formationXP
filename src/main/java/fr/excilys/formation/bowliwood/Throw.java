package fr.excilys.formation.bowliwood;

/**
 * Throw class.
 */
public class Throw {
    public final static int MAX_SCORE = 10;

    public enum ThrowResult {
        STRIKE, SPARE, OTHER;
    }

    private ThrowResult throwResult;
    private int firstHit;
    private int secondHit;

    private Throw() {
        this.throwResult = ThrowResult.STRIKE;
        this.firstHit = MAX_SCORE;
        this.secondHit = 0;
    }

    private Throw(final int fst) {
        this.throwResult = ThrowResult.SPARE;
        this.firstHit = fst;
        this.secondHit = MAX_SCORE - fst;
    }

    private Throw(final int fst, final int snd) {
        this.throwResult = ThrowResult.OTHER;
        this.firstHit = fst;
        this.secondHit = snd;
    }

    public static Throw strike() {
        return new Throw();
    }

    public static Throw spare(final int fst) {
        return new Throw(fst);
    }

    public static Throw other(final int fst, final int snd) {
        return new Throw(fst, snd);
    }

    public boolean isOther() {
        return this.throwResult==ThrowResult.OTHER ;
    }

    public boolean isStrike() {
        return this.throwResult==ThrowResult.STRIKE ;
    }

    public boolean isSpare() {
        return this.throwResult==ThrowResult.SPARE ;
    }

    public int getFirstHit() {
        return this.firstHit;
    }
    
    public int getSecondHit() {
        return this.secondHit;
    }

    public ThrowResult getThrowType() {
        return this.throwResult;
    }
}
