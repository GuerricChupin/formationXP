package fr.excilys.formation.bowliwood;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Game class.
 */
public class Game {
	
	public final static int MAX_NB_TURN = 2;

	private ArrayList<Player> playerList;

	private int currentPlayer;

	private int previousPlayer;

	private int turnCount;

	public Game(ArrayList<Player> playerList_) {
		this.turnCount = 0;
		this.playerList = playerList_;
		this.currentPlayer = 0;
		this.previousPlayer = 0;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getPreviousPlayer() {
		return previousPlayer;
	}

	public int getCurrentTurn() {
		return this.turnCount;
	}

	/**
	 * update currentPlayer (the current player can play again if he just did a
	 * strike or a spare).
	 * 
	 * @param myThrow
	 */
	public void nextPlayer(Throw myThrow) {
		this.previousPlayer = this.currentPlayer;
		if (myThrow.isOther()) {
			this.currentPlayer = (this.currentPlayer + 1) % this.playerList.size();
			if (this.currentPlayer == 0) {
				this.turnCount += 1;
			}
		}

	}


	/**
	 * begins the game : asks for the number of players and their names.
	 * 
	 */
	public static Game beginGame(Scanner scan) {
		System.out.println("Nombre de joueurs ?");

		while (!scan.hasNextInt()) {
			System.out.println("Erreur : entrez un nombre entier.");
			System.out.println("Nombre de joueurs ?");
			scan.next();
		}

		int nb = scan.nextInt();

		ArrayList<Player> playerList = new ArrayList<Player>();

		for (int i = 0; i < nb; i++) {
			System.out.print("Entrez le nom du joueur suivant : ");
			String name = scan.next();
			playerList.add(new Player(name));
		}
		
		return new Game(playerList);
	}


	/**
	 * used by FallenPin. scans the number of fallen pins.
	 * 
	 */
	private int fallenPinAux(Scanner scan, int prev_score) {
		System.out.println("Combien de quilles sont tombées à ce tour ?");
		
		while (!scan.hasNextInt()) {
			System.out.println("Erreur : entrez un nombre entier.");
			System.out.println("Combien de quilles sont tombées à ce tour ?");
			scan.next();
		}
		int score = scan.nextInt();

		while (score < 0 && (score + prev_score) > Throw.MAX_SCORE) {
			System.out.println("Entrée incorrecte : le score doit être plus petit que " + Throw.MAX_SCORE);
			System.out.println("Combien de quilles sont tombées à ce tour ?");
			while (!scan.hasNextInt()) {
				System.out.println("Erreur : entrez un nombre entier.");
				System.out.println("Combien de quilles sont tombées à ce tour ?");
				scan.next();
			}

			return scan.nextInt();
		}

		return score;
	}


	/**
	 * allows a player to enter the number of pins he's knock down.
	 * 
	 */
	public void fallenPin(Scanner scan) {
		int score1 = fallenPinAux(scan, 0);
		Throw thr;
		if (score1 == 10) {
			thr = Throw.strike();
		} else {
			int score2 = fallenPinAux(scan, score1);
			if (score1 + score2 == 10) {
				thr = Throw.spare(score1);
			} else {
				thr = Throw.other(score1, score2);
			}
		}

		this.playerList.get(getCurrentPlayer()).add_throw(thr);
		this.nextPlayer(thr);
	}

	/**
	 * displays the current score of each player.
	 * 
	 */
	public void displayScore() {
		for (Player player : playerList) {
			System.out.println(player.getName() + ": " + player.score());
		}
	}
	
	/**
	 * displays a nice helpful message to the loser.
	 * 
	 */
	public void messageSoutien() {
		//First : find the loser.
		int min_score = playerList.get(0).score();
		Player loser = playerList.get(0);
		for (Player player : playerList) {
			if (player.score()<min_score) {
				min_score = player.score();
				loser = player;	
			}
		}
		System.out.println("Courage " + loser.getName() + ", tu y arriveras ! L'essentiel, c'est de participer.");
	}

	/**
	 * corrects the number of fallen pins the last player entered.
	 * 
	 */
	public void correctScore(Scanner scan) {
		int score1 = fallenPinAux(scan, 0);
		Throw thr;
		if (score1 == 10) {
			thr = Throw.strike();
		} else {
			int score2 = fallenPinAux(scan, score1);
			if (score1 + score2 == 10) {
				thr = Throw.spare(score1);
			} else {
				thr = Throw.other(score1, score2);
			}
		}
		this.playerList.get(getPreviousPlayer()).setLastThrow(thr);
	}

	/**
	 * the current payer quits the game.
	 * 
	 */
	private void playerQuit(int nCurrentPlayer) {
		this.playerList.remove(nCurrentPlayer);
	}
	
	/**
	 * in order to know what function should be run next.
	 * 
	 */
	public void switchFunction(Scanner scan) {

		String answer;

		System.out.println("Action suivante pour " + this.playerList.get(getCurrentPlayer()).getName() + " : ");
		System.out.println("* Tapez 'Next' (ou 'N') pour passer au tour suivant");
		System.out.println("* Tapez 'Correct' (ou 'C') pour corriger le score que vous venez d'entrer");
		System.out.println("* Tapez 'Quit' (ou 'Q') pour quitter la partie");

		answer = scan.next();
		
		while (!answer.equals("Next") && !answer.equals("Quit") && !answer.equals("Correct") && 
				!answer.equals("N") && !answer.equals("C") && !answer.equals("Q")) {
			System.out.println("Erreur : entrez 'Next', 'Correct' ou 'Quit'.");
			System.out.println("Action suivante pour " + this.playerList.get(getCurrentPlayer()).getName() + " : ");
			answer = scan.next();
		}


		switch (answer.charAt(0)) {
		case 'N':
			fallenPin(scan);
			break;
		case 'C':
			correctScore(scan);
			break;
		case 'Q':
			playerQuit(getCurrentPlayer());
			break;
		}
	}


	/**
	 * MAIN
	 * 
	 */
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		Game game = beginGame(scan);
		
		game.fallenPin(scan);
		game.displayScore();
		
		while (game.turnCount <= MAX_NB_TURN) {
			game.switchFunction(scan);
			game.displayScore();
			game.messageSoutien();
		}
		
	}
}

