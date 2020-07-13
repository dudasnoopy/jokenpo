package com.jokenpo.contract;

import java.util.List;

import com.jokenpo.dto.PlayerDto;

public interface PlayerContract {
	
	PlayerDto save(PlayerDto player);
	
	void delete(PlayerDto player);
	
	PlayerDto get(String id);
	
	List<PlayerDto> list();

	PlayerDto update(PlayerDto player);

}
