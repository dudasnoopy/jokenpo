package com.jokenpo.dto;

import java.io.Serializable;

public class PlayDto implements Serializable, Comparable<PlayDto> {

	private static final long serialVersionUID = 1L;

	private MoveDto move;

	private String message;

	private int wins;

	public PlayDto(MoveDto move, String message, int wins) {
		super();
		this.move = move;
		this.message = message;
		this.wins = wins;
	}

	public MoveDto getMove() {
		return move;
	}

	public String getMessage() {
		return message;
	}

	public int getWins() {
		return wins;
	}

	public void addWin() {
		wins++;
	}

	@Override
	public int compareTo(PlayDto o) {
		if (this.wins > o.wins) { 
			return -1; 
		} if (this.wins < o.wins) { 
			return 1; 
		} 
		return 0; 
	}

}
