package com.jokenpo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jokenpo.dto.PlayerDto;

@Repository
public class PlayerRepository extends BaseRepository<PlayerDto>{
	
	private List<PlayerDto> repository;

	public PlayerRepository() {
		this.repository = new ArrayList<>();
	}
	
	@Override
	protected List<PlayerDto> getRepository() {
		return this.repository;
	}

}
