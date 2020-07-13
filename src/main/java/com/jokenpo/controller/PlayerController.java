package com.jokenpo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jokenpo.contract.PlayerContract;
import com.jokenpo.dto.PlayerDto;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private PlayerContract playerContract;
	
	@PostMapping
	public PlayerDto save(@RequestBody PlayerDto player) {
		return this.playerContract.save(player);
	}
	
	@PutMapping
	public PlayerDto update(@RequestBody PlayerDto player) {
		return this.playerContract.update(player);
	}
	
	@GetMapping("/{id}")
	public PlayerDto get(@PathVariable("id") String id) {
		return this.playerContract.get(id);
	}
	
	@GetMapping
	public List<PlayerDto> list() {
		return this.playerContract.list();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		this.playerContract.delete(this.playerContract.get(id));
	}

}
