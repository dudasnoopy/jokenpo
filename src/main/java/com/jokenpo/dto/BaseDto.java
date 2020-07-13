package com.jokenpo.dto;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	public BaseDto() {
		this.setId(UUID.randomUUID().toString());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String toJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj))
			return true;
		if(obj != null && obj instanceof BaseDto && this.id != null && ((BaseDto) obj).id != null)
			return this.id.equals(((BaseDto) obj).id);
		return false;
	}

}
