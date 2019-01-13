package com.dius.tennis.coding.excercise;


import static com.dius.tennis.coding.excercise.service.ScoreCalculator.scoreTieBreak;
import static com.dius.tennis.coding.excercise.service.ScoreCalculator.getGameScore;

import com.dius.tennis.coding.excercise.domain.Player;

public class Match {
	
	private final Player player1;
	private final Player player2;
	
	
	public Match(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		
	}
	
	public void pointWonBy(Player player) {
		player.winPoint();
	}


	public String score() {
		if (isMatchStarted()) {
			return "0-0";
		} else if (isTieBreak()) {
			return scoreTieBreak(player1, player2);
	    } else if (isMatchFinished()) {
			return String.format("%s-%s, %s wins", player1.getWinGame(), player2.getWinGame(), getSetWinningPlayer().getName());
		} else {
			return getGameScore(player1, player2);
		}
		
	}
	
	private boolean isTieBreak() {
		return player1.getWinGame() == 6 && player2.getWinGame() == 6;
	}
	
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

	private boolean isMatchFinished() {
		
		return player1.getWinGame() >= 6 && player1.getWinGame() > player2.getWinGame() && (player1.getWinGame() - player2.getWinGame()) > 1
				
			 ||  player2.getWinGame() >= 6 && player2.getWinGame() > player1.getWinGame() && (player2.getWinGame() - player1.getWinGame()) > 1;
	}

	private boolean isMatchStarted() {
		return player1.getWinGame() == 0 
				&& player2.getWinGame() == 0
				&& player1.getScore() == 0
				&& player2.getScore() == 0;
	}

}
