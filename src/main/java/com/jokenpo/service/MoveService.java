package com.jokenpo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokenpo.contract.MoveContract;
import com.jokenpo.dto.MoveDto;
import com.jokenpo.dto.PlayerDto;
import com.jokenpo.enuns.Option;
import com.jokenpo.repository.MoveRepository;
import com.jokenpo.repository.PlayerRepository;

@Service
public class MoveService implements MoveContract{

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private MoveRepository moveRepository;
	
	@Override
	public MoveDto add(String idPlayer, Option option) {
		MoveDto move = this.moveRepository.get(idPlayer);
		if(move == null) {
			PlayerDto player = this.playerRepository.get(idPlayer);
			move = new MoveDto(player, option);
			this.moveRepository.save(move);
		}else {
			move.setOption(option);
			this.moveRepository.save(move);
		}
		return move;
	}

	@Override
	public void remove(String idPlayer) {
		MoveDto move = this.moveRepository.get(idPlayer);
		this.moveRepository.delete(move);
	}

	@Override
	public List<MoveDto> list() {
		return this.moveRepository.list();
	}

	@Override
	public MoveDto get(String idPlayer) {
		return this.moveRepository.get(idPlayer);
	}

	@Override
	public MoveDto update(String idPlayer, Option option) {
		MoveDto move = this.moveRepository.get(idPlayer);
		if(move != null) {
			move.setOption(option);
			this.moveRepository.save(move);
		}
		return move;
	}

}
