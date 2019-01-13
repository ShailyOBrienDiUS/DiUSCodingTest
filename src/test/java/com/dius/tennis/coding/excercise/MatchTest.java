package com.dius.tennis.coding.excercise;

import static org.junit.Assert.assertThat;

import java.util.stream.IntStream;

import org.junit.Before;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

import com.dius.tennis.coding.excercise.domain.Player;

public class MatchTest {
	
	private Match match;
	private Player player1;
	private Player player2;

	@Before
	public void setUp() {
		player1 = new Player("player 1");
		player2 = new Player("player 2");
		match = new Match(player1, player2);
	}

	@Test
	public void testNewMatchReturnsLoveAll() {
		
		assertThat(match.score(), equalTo("0-0"));
	}
	
	@Test
	public void testGameWonByPlayer1WithoutDeuce() {
		
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 15-0"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 15-15"));
		
		match.pointWonBy(player1);
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 40-15"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 40-30"));
		
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("1-0"));
		
	}
	
	@Test
	public void testPlayer2WonAllPointsInAGame() {
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 0-15"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 0-30"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 0-40"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-1"));
		
	}
	
	@Test
	public void testFirstGameWonByPlayer1StartSecondGame() {
		
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 15-0"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 15-15"));
		
		match.pointWonBy(player1);
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 40-15"));
		
		match.pointWonBy(player1);
		
		assertThat("1-0", equalTo(match.score()));
		
		///Point reset after game win
		assertThat(player1.getPoint(), equalTo(0));
		assertThat(player2.getPoint(), equalTo(0));
		
		//Start second Game
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("1-0, 0-15"));
		
	}
	
	@Test
	public void shouldReturnDeuceWhenEachPlayerhas_atleastScored_3Points() {
		
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 15-0"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 15-15"));
		
		match.pointWonBy(player1);
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 40-15"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 40-30"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, Deuce"));
		
	}
	
	
	@Test
	public void shouldReturnAdvantagePlayerWhenEachPlayerhas_atleastScored_3Points_APlayerHas_1MorePoint() {
		
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 15-0"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 15-15"));
		
		match.pointWonBy(player1);
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 40-15"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 40-30"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, Deuce"));
		
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, Advantage player 1"));
		
	}
	
	
	@Test
	public void testGameWonByPlayer1_Deuce_Advantage_Win() {
		
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 15-0"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 15-15"));
		
		match.pointWonBy(player1);
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 40-15"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 40-30"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, Deuce"));
		
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, Advantage player 1"));
		
		match.pointWonBy(player1);
		
		assertThat("1-0", equalTo(match.score()));
		
	}
	
	
	@Test
	public void testGameWonByPlayer2Deuce_Advantage_Deuce_Advantage_Win() {
		
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 15-0"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 15-15"));
		
		match.pointWonBy(player1);
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, 40-15"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, 40-30"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, Deuce"));
		
		match.pointWonBy(player1);
		
		assertThat(match.score(), equalTo("0-0, Advantage player 1"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, Deuce"));
		
        match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-0, Advantage player 2"));
		
		match.pointWonBy(player2);
		
		assertThat(match.score(), equalTo("0-1"));
		
	}
	
	@Test
	public void testSetWonByPlayer1() {
		
		//Consider Player1 has won 6 games in a set
		IntStream.range(0, 6).forEach(idx -> player1.winGame());
		
		//Consider Player2 has won 4 games in a set
		IntStream.range(0, 4).forEach(idx -> player2.winGame());
		
		assertThat(match.score(), equalTo("6-4, player 1 wins"));
		
		
	}
	
	@Test
	public void testSetWonByPlayer1_7_5GameScore() {
		
		//Consider Player1 has won 6 games in a set
		IntStream.range(0, 7).forEach(idx -> player1.winGame());
		
		//Consider Player2 has won 4 games in a set
		IntStream.range(0, 5).forEach(idx -> player2.winGame());
		
		assertThat(match.score(), equalTo("7-5, player 1 wins"));
	}
	
	@Test
	public void testTieBreak() {
		
		//Consider Player1 has won 6 games in a set
		IntStream.range(0, 6).forEach(idx -> player1.winGame());
		
		//Consider Player2 has won 6 games in a set
		IntStream.range(0, 6).forEach(idx -> player2.winGame());
		
		assertThat(match.score(), equalTo("6-6, 0-0"));
		//start tie break
		
		player1.winPoint();
		player2.winPoint();
		
		assertThat(match.score(), equalTo("6-6, 1-1"));
		
		player1.winPoint();
		player1.winPoint();
		player2.winPoint();
		
		assertThat(match.score(), equalTo("6-6, 3-2"));
		
		player1.winPoint();
		player1.winPoint();
		player2.winPoint();
		
		assertThat(match.score(), equalTo("6-6, 5-3"));
		
		player1.winPoint();
		
		assertThat(match.score(), equalTo("6-6, 6-3"));
		
		player1.winPoint();
		player2.winPoint();

		assertThat(match.score(), equalTo("7-6, player 1 wins"));
		
	}
	

}
