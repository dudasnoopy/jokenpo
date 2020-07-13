package com.jokenpo.enuns;

import java.util.Arrays;
import java.util.List;

public enum Option {
	
	PAPER(PAPER()),
	SCISSORS(SCISSORS()),
	SPOCK(SPOCK()),
	LIZARD(LIZARD()),
	STONE(STONE()),
	;
	
	private List<String> winsFrom;
	
	private Option(List<String> winsFrom) {
		this.winsFrom = winsFrom;
	}
	
	public List<String> getWinsFrom() {
		return winsFrom;
	}
	
	private static final List<String> PAPER() {
		return Arrays.asList("STONE", "SPOCK");
	}
	
	private static final List<String> SCISSORS() {
		return Arrays.asList("PAPER", "LIZARD");
	}
	
	private static final List<String> SPOCK() {
		return Arrays.asList("SCISSORS", "STONE");
	}
	
	private static final List<String> LIZARD() {
		return Arrays.asList("SPOCK", "PAPER");
	}
	
	private static final List<String> STONE() {
		return Arrays.asList("LIZARD", "SCISSORS");
	}
	
}
