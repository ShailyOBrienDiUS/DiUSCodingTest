package com.dius.tennis.coding.excercise.domain;

public final class Player {
	
	private final String name;
	private int score;
	private int winGame;

	public Player(String name) {
		this.name = name;
		this.score = 0;
		this.winGame = 0;
	}
	
	public void winPoint() {
		this.score = this.score + 1;
	}
	
	public Integer getPoint() {
		return Point.getDisplayPoint(score);
	}
	
	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		
		return "player " + name;
	}
	
	public void winGame() {
		this.winGame = this.winGame + 1;
	}
	
	public int getWinGame() {
		return winGame;
	}

	public String getName() {
		return this.name;
	}
	
	public void resetScore() {
		this.score = 0;
	}

}
