package com.jokenpo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokenpo.contract.PlayContract;
import com.jokenpo.dto.MoveDto;
import com.jokenpo.dto.PlayDto;
import com.jokenpo.exception.GameException;
import com.jokenpo.repository.MoveRepository;

@Service
public class PlayService implements PlayContract{

	@Autowired
	private MoveRepository moveRepository;

	@Override
	public PlayDto play() {
		List<PlayDto> plays = new ArrayList<>();
		for(MoveDto move : this.moveRepository.list()) {
			PlayDto play = new PlayDto(move, move.getPlayer().getName() + " won!", 0);
			for(MoveDto m : this.moveRepository.list())
				if(move.getOption().getWinsFrom().contains(m.getOption().name()))
					play.addWin();
			plays.add(play);
		}
		Collections.sort(plays);
		if(plays.size() > 1 && plays.get(0).getWins() != plays.get(1).getWins())
			return plays.get(0);
		throw new GameException("Game without winners");
	}
	

}
