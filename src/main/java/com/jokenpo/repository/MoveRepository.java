package com.jokenpo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jokenpo.dto.MoveDto;

@Repository
public class MoveRepository extends BaseRepository<MoveDto>{
	
	private List<MoveDto> repository;

	public MoveRepository() {
		this.repository = new ArrayList<>();
	}
	
	@Override
	protected List<MoveDto> getRepository() {
		return this.repository;
	}

}
