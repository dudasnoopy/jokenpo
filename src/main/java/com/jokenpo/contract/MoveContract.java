package com.jokenpo.contract;

import java.util.List;

import com.jokenpo.dto.MoveDto;
import com.jokenpo.enuns.Option;

public interface MoveContract {

	MoveDto add(String idPlayer, Option move);

	void remove(String idPlayer);

	List<MoveDto> list();

	MoveDto get(String idPlayer);

	MoveDto update(String idPlayer, Option move);

}
