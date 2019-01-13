package com.dius.tennis.coding.excercise;


import static com.dius.tennis.coding.excercise.service.ScoreCalculator.scoreTieBreak;
import static com.dius.tennis.coding.excercise.service.ScoreCalculator.getGameScore;

import com.dius.tennis.coding.excercise.domain.Player;

/**
 * The Class Match has two public method .
 */
public class Match {
	
	/** The player 1. */
	private final Player player1;
	
	/** The player 2. */
	private final Player player2;
	
	
	/**
	 * Instantiates a new match.
	 *
	 * @param player1 the player 1
	 * @param player2 the player 2
	 */
	public Match(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		
	}
	
	/**
	 * Point won by.
	 * calls winpoint method on player class to increament point
	 *
	 * @param player the player
	 */
	public void pointWonBy(Player player) {
		player.winPoint();
	}


	/**
	 * Score.
	 *
	 * @return game score, winner and set winner
	 */
	public String score() {
		String score = "";
		if (isMatchStarted()) {
			return "0-0";
		} else if (isTieBreak()) {
			score = scoreTieBreak(player1, player2);
		} else {
			score = getGameScore(player1, player2);
			 if (isMatchFinished()) {
				score = String.format("%s-%s, %s wins", player1.getWinGame(), player2.getWinGame(), getSetWinningPlayer().getName());
			}
				
		}
		return score;
	}
	
	/**
	 * Checks if is tie break.
	 * Both player wins 6 games
	 * @return true, 
	 */
	private boolean isTieBreak() {
		return player1.getWinGame() == 6 && player2.getWinGame() == 6;
	}
	
	/**
	 * Return the Set winner.
	 * A player wins a set by winning at least 6 games and at least 2 games more than the opponent
	 * @return the sets the winning player
	 */
	private Player getSetWinningPlayer() {
		
		Player matchWinner = null;
		
		int playe1WinningGames = player1.getWinGame();
		int playe2WinningGames = player2.getWinGame();
		
		if (playe1WinningGames >= 6 && playe1WinningGames > playe2WinningGames && (playe1WinningGames - playe2WinningGames) > 1) {
			matchWinner = player1;
		} else if (playe2WinningGames >= 6 && playe2WinningGames > playe1WinningGames && (playe2WinningGames - playe1WinningGames) > 1) {
			matchWinner = player2;
		}
		
		return matchWinner;
	}

	/**
	 * Checks if is match finished.
	 *
	 * @return true, if is match finished
	 */
	private boolean isMatchFinished() {
		
		return player1.getWinGame() >= 6 && player1.getWinGame() > player2.getWinGame() && (player1.getWinGame() - player2.getWinGame()) > 1
				
			 ||  player2.getWinGame() >= 6 && player2.getWinGame() > player1.getWinGame() && (player2.getWinGame() - player1.getWinGame()) > 1;
	}

	/**
	 * Checks if is match started.
	 *
	 * @return true, if is match started
	 */
	private boolean isMatchStarted() {
		return player1.getWinGame() == 0 
				&& player2.getWinGame() == 0
				&& player1.getScore() == 0
				&& player2.getScore() == 0;
	}

}
