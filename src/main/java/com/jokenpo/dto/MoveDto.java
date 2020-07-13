package com.jokenpo.dto;

import com.jokenpo.enuns.Option;

public class MoveDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private PlayerDto player;

	private Option option;

	public MoveDto(PlayerDto player, Option move) {
		this.setId(player.getId());
		this.setPlayer(player);
		this.setOption(move);
	}

	public PlayerDto getPlayer() {
		return player;
	}

	public Option getOption() {
		return option;
	}

	public void setPlayer(PlayerDto player) {
		this.player = player;
	}

	public void setOption(Option option) {
		this.option = option;
	}

}
