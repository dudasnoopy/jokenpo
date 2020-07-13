package com.jokenpo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokenpo.contract.PlayerContract;
import com.jokenpo.dto.PlayerDto;
import com.jokenpo.repository.PlayerRepository;

@Service
public class PlayerService implements PlayerContract{
	
	@Autowired
	private PlayerRepository repository;

	@Override
	public PlayerDto save(PlayerDto player) {
		this.repository.save(player);
		return player;
	}

	@Override
	public void delete(PlayerDto player) {
		this.repository.delete(player);
	}

	@Override
	public PlayerDto get(String id) {
		return this.repository.get(id);
	}

	@Override
	public List<PlayerDto> list() {
		return this.repository.list();
	}

	@Override
	public PlayerDto update(PlayerDto player) {
		if(this.repository.get(player.getId()) != null)
			this.repository.save(player);
		return player;
	}

}
