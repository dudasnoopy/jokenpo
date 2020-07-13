package com.jokenpo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jokenpo.contract.MoveContract;
import com.jokenpo.dto.MoveDto;
import com.jokenpo.enuns.Option;

@RestController
@RequestMapping("/move")
public class MoveController {
	
	@Autowired
	private MoveContract gameContract;
	
	@PostMapping("/{move}/player/{idPlayer}")
	public MoveDto addMove(@PathVariable("idPlayer") String idPlayer, @PathVariable("move") Option move) {
		return this.gameContract.add(idPlayer, move);
	}
	
	@PutMapping("/{move}/player/{idPlayer}")
	public MoveDto updateMove(@PathVariable("idPlayer") String idPlayer, @PathVariable("move") Option move) {
		return this.gameContract.update(idPlayer, move);
	}
	
	@GetMapping("/player/{idPlayer}")
	public MoveDto getMove(@PathVariable("idPlayer") String idPlayer) {
		return this.gameContract.get(idPlayer);
	}
	
	@DeleteMapping("/player/{idPlayer}")
	public void deleteMove(@PathVariable("idPlayer") String idPlayer) {
		this.gameContract.remove(idPlayer);
	}
	
	@GetMapping
	public List<MoveDto> list() {
		return this.gameContract.list();
	}
}
