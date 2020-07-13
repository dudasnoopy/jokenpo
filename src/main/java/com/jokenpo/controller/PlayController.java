package com.jokenpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jokenpo.contract.PlayContract;
import com.jokenpo.dto.PlayDto;

@RestController
@RequestMapping("/play")
public class PlayController {
	
	@Autowired
	private PlayContract playContract;
	
	
	@GetMapping
	public PlayDto play() {
		return this.playContract.play();
	}
	
}
