package com.jokenpo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jokenpo.dto.MoveDto;
import com.jokenpo.dto.PlayerDto;
import com.jokenpo.enuns.Option;
import com.jokenpo.repository.MoveRepository;

@SpringBootTest
@AutoConfigureMockMvc
class PlayControlerTest {
	
	@MockBean
	private MoveRepository moveRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void play() throws JsonProcessingException, Exception {
		
		when(this.moveRepository.list()).thenReturn(Arrays.asList(
				new MoveDto(new PlayerDto("Player 1"), Option.SCISSORS),
				new MoveDto(new PlayerDto("Player 2"), Option.STONE)
				));
		
		mockMvc.perform(get("/play")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.message", is("Player 2 won!")))
		.andExpect(jsonPath("$.move.player.id", is(notNullValue())))
		.andExpect(jsonPath("$.move.player.name", is("Player 2")))
		.andExpect(jsonPath("$.move.option", is(Option.STONE.name())))
		.andExpect(jsonPath("$.wins", is(1)))
		;
		
	}
	
	@Test
	void playWithoutWinner() throws JsonProcessingException, Exception {
		
		when(this.moveRepository.list()).thenReturn(Arrays.asList(
				new MoveDto(new PlayerDto("Player 1"), Option.SCISSORS),
				new MoveDto(new PlayerDto("Player 2"), Option.STONE),
				new MoveDto(new PlayerDto("Player 3"), Option.PAPER)
				));
		
		mockMvc.perform(get("/play")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$.message", is("Game without winners")))
		;
		
	}

}
