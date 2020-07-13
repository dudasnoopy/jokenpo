package com.jokenpo.repository;

import java.util.List;

import com.jokenpo.dto.BaseDto;

public abstract class BaseRepository<T extends BaseDto> {

	protected abstract List<T> getRepository();
	
	public void save(T t) {
		if(this.getRepository().contains(t))
			this.getRepository().remove(t);
		this.getRepository().add(t);
	}
	
	public void delete(T t) {
		this.getRepository().remove(t);
	}
	
	public List<T> list() {
		return this.getRepository();
	}
	
	public T get(String id) {
		for(T dto : this.getRepository())
			if(dto.getId().equals(id))
				return dto;
		return null;
	}

}
