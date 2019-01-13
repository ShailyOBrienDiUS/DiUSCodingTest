package com.dius.tennis.coding.excercise.service;

import com.dius.tennis.coding.excercise.domain.Player;

/**
 * The Class ScoreCalculator.
 */
public class ScoreCalculator {
	
	
	
	/**
	 * Determines the player's game -> score, deuce, advantage or winner
	 * It increments the game won by player
	 *
	 * @param player1 the player 1
	 * @param player2 the player 2
	 * @return score which display games won by each players and current game score
	 */
	public static String getGameScore(Player player1, Player player2) {
		if (player1.getScore() >=3 || player2.getScore() >=3) {
			if (isDeuce(player1, player2)) {
				return String.format("%s-%s, %s", player1.getWinGame(), player2.getWinGame(), "Deuce");
			} else if (hasGameWinner(player1, player2)) {
				Player player = gameWinner(player1, player2);
				player.winGame();
				resetPlayersPoints(player1, player2);
				return String.format("%s-%s", player1.getWinGame(), player2.getWinGame());
				
			} else if (hasAdvantage(player1, player2)) {
				return String.format("%s-%s, %s", player1.getWinGame(), player2.getWinGame(), "Advantage " + advantagePlayer(player1, player2).getName());
			} 
		}
		return String.format("%s-%s, %s-%s", player1.getWinGame(), player2.getWinGame(), player1.getPoint(), player2.getPoint());
	}
	
	/**
	 * This method is responsible for calculating the tie break
	 * The rule to calculate a tie-break is scored one point at a time. The tie-break game continues until one player wins seven points 
	 * by a margin of two or more points. The score for a tie breaker 
	 * goes up incrementally from 0 by 1. i.e a player's score will go from 0 to 1 to 2 to 3 …etc
	 * 
	 * @param player1 the player 1
	 * @param player2 the player 2
	 * @return tie-break score or the match winner player name
	 */
	public static String scoreTieBreak(Player player1, Player player2) {
		int player1Score = player1.getScore();
		int player2Score = player2.getScore();
		
		if (player1Score >=7 && player1Score > player2Score && (player1Score - player2Score) >=2) {
			player1.winGame();
			return String.format("%s-%s, %s wins", player1.getWinGame(), player2.getWinGame(), player1.getName());
		} else if (player2Score >=7 && player2Score > player1Score && (player2Score - player1Score) >=2) {
			player2.winGame();
			return String.format("%s-%s, %s wins", player1.getWinGame(), player2.getWinGame(), player2.getName());
		}
		return String.format("%s-%s, %s-%s", player1.getWinGame(), player2.getWinGame(), player1.getScore(), player2.getScore());
	}
	
    /**
     * Determine which player has won the set
     * A player wins a set by winning at least 6 games and at least 2 games more than the opponent
     * @param player1 the player 1
     * @param player2 the player 2
     * @return the sets the winning player
     */
    public static Player getSetWinningPlayer(Player player1, Player player2) {
		
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
	 * Reset players points.
	 * It resets the players after game finish
	 * @param player1 the player 1
	 * @param player2 the player 2
	 */
	private static void resetPlayersPoints(Player player1, Player player2) {
		player1.resetScore();
		player2.resetScore();
		
	}

	/**
	 * Checks if is deuce.
	 *
	 * @param player1 the player 1
	 * @param player2 the player 2
	 * @return true, if is deuce
	 */
	private static boolean isDeuce(Player player1, Player player2) {
		return player1.getScore() >=3 && player2.getScore() >=3 && player1.getScore() == player2.getScore();
	}
	
	/**
	 * Checks for advantage in a game.
	 * At least 3 points have been scored by each side and a player has one more point than his opponent, 
     * the score of the game is "advantage" for the player in the lead.
	 * @param player1 the player 1
	 * @param player2 the player 2
	 * @return true, if successful
	 */
	private static boolean hasAdvantage(Player player1, Player player2) {
		int player1Score = player1.getScore();
		int player2Score = player2.getScore();
		
		if (player1Score >= 3 && player2Score >= 3) {
			if ((player2Score <= player1Score - 1) || player1Score <= player2Score - 1) {
				return true;
		    }
		}
		
		return false;
	}
	

    /**
     * Determine which player has advantage over another player in a game.
     * 
     *  At least 3 points have been scored by each side and a player has one more point than his opponent, 
     *  the score of the game is "advantage" for the player in the lead.
     *
     * @param player1 the player 1
     * @param player2 the player 2
     * @return the advantage player
     */
    private static Player advantagePlayer(Player player1, Player player2) {
		
		Player winner = null;
		int player1Score = player1.getScore();
		int player2Score = player2.getScore();
		
		if (player1Score >= 3 && player2Score >= 3) {
			
			if (player2Score <= player1Score - 1) {
				winner =  player1;
		    } else if (player1Score <= player2Score - 1) {
		    	winner = player2;
		    }
		}
		return winner;
	}
	
    
    /**
     * Game winner.
     * A game is won by the first player to have won at least 4 points in total and at least 2 points more than the opponent
     * @param player1 the player 1
     * @param player2 the player 2
     * @return the player
     */
    private static Player gameWinner(Player player1, Player player2) {
		
		Player winner = null;
		int player1Score = player1.getScore();
		int player2Score = player2.getScore();
		
		//Player to have won at least 4 points in total and at least 2 points more than the opponent
		if (player1Score >= 4 && player2Score <= player1Score - 2) {
			winner =  player1;
		} else if (player2Score >= 4 && player1Score <= player2Score - 2) {
			winner = player2;
		}
		return winner;
				
	}
	
	/**
	 * Checks for game winner.
	 * A game is won by the first player to have won at least 4 points in total and at least 2 points more than the opponent
	 * @param player1 the player 1
	 * @param player2 the player 2
	 * @return true, if successful
	 */
	private static boolean hasGameWinner(Player player1, Player player2) {
		
		int player1Score = player1.getScore();
		int player2Score = player2.getScore();
		
		if (player1Score >= 4 && player2Score <= player1Score - 2) {
			return true;
		} else if (player2Score >= 4 && player1Score <= player2Score - 2) {
			return true;
		}
		return false;
				
	}

}
