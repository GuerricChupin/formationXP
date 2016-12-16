package fr.excilys.formation.bowliwood;

import java.util.ArrayList;

import fr.excilys.formation.bowliwood.Throw.ThrowResult;

/**
 * Player class.
 */
public class Player {

	private String name;

	private ArrayList<Throw> throwList;

	public Player(String name_) {
		this.name = name_;
		this.throwList = new ArrayList<Throw>();
	}

	public String getName() {
		return this.name;
	}

	public void add_throw(Throw thr) {
		this.throwList.add(thr);
	}

	public void setLastThrow(Throw thr) {
		this.throwList.set(this.throwList.size() - 1, thr);

	}

	/**
	 * calculates the score. For now, it doesn't take into account the previous
	 * throw.
	 * 
	 * @return
	 */
	public int score() {
		int score = 0;
		ThrowResult previous_thr = ThrowResult.OTHER;

		if (throwList.isEmpty()) {
			return 0;
		} else {
			for (Throw thr : this.throwList) {
				switch (thr.getThrowType()) {
				case STRIKE:
					if (previous_thr == ThrowResult.OTHER) {
						score += Throw.MAX_SCORE;
					} else {
						score += 2 * Throw.MAX_SCORE;
					}

					break;
				case SPARE:
					if (previous_thr == ThrowResult.OTHER) {
						score += Throw.MAX_SCORE;
					} else if (previous_thr == ThrowResult.SPARE) {
						score += 2 * thr.getFirstHit() + thr.getSecondHit();
					} else {
						score += 2 * Throw.MAX_SCORE;
					}
					break;
				case OTHER:
					if (previous_thr == ThrowResult.OTHER) {
						score += thr.getFirstHit() + thr.getSecondHit();
					} else if (previous_thr == ThrowResult.SPARE) {
						score += 2 * thr.getFirstHit() + thr.getSecondHit();
					} else {
						score += 2 * (thr.getFirstHit() + thr.getSecondHit());
					}
					break;
				}

				previous_thr = thr.getThrowType();
			}

			return score;
		}
	}

}
