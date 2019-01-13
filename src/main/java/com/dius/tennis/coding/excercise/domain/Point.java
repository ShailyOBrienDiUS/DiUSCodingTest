package com.dius.tennis.coding.excercise.domain;

import java.util.HashMap;
import java.util.Map;

public enum Point {
	
	LOVE(0, 0), FIFITEEN(1, 15), THIRTY(2, 30), FOURTY(3, 40);
	
	private final Integer score;
	private final Integer displayPoint;

	private Point(Integer score, Integer displayPoint) {
		this.score = score;
		this.displayPoint = displayPoint;
	}
	
	private static final Map<Integer, Point> scoreMap;
    static {
    	scoreMap = new HashMap<>();
        for (Point value : Point.values()) {
            scoreMap.put(value.score, value);
        }
    }
    
	public Integer getScore() {
		return score;
	}

	public Integer getDisplayPoint() {
		return displayPoint;
	}

	public static Point findByScore(Integer score) {
		return scoreMap.get(score);
	}
	
	public static Integer getDisplayPoint(Integer score) {
		return scoreMap.get(score).getDisplayPoint();
	}
	
}
