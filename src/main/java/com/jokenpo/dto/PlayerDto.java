package com.jokenpo.dto;

public class PlayerDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String name;
	
	public PlayerDto() {
		super();
	}
	
	public PlayerDto(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
