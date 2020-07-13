package com.jokenpo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import com.jokenpo.repository.PlayerRepository;

@SpringBootTest
@AutoConfigureMockMvc
class MoveControlerTest {
	
	@MockBean
	private PlayerRepository playerRepository;
	
	@MockBean
	private MoveRepository moveRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void addMove() throws JsonProcessingException, Exception {
		
		PlayerDto player = new PlayerDto("Jogador 1");
		when(this.playerRepository.get(player.getId())).thenReturn(player);
		
		mockMvc.perform(post("/move/{move}/player/{idPlayer}", Option.SCISSORS, player.getId())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.id", is(notNullValue())))
		.andExpect(jsonPath("$.player.id", is(player.getId())))
		.andExpect(jsonPath("$.player.name", is("Jogador 1")))
		.andExpect(jsonPath("$.option", is(Option.SCISSORS.name())))
		;
		
		Mockito.verify(this.moveRepository, times(1)).get(player.getId());
		Mockito.verify(this.moveRepository, times(1)).save(Mockito.any());
		
	}
	
	@Test
	void addOrUpdateMove() throws JsonProcessingException, Exception {
		
		PlayerDto player = new PlayerDto("Jogador 1");
		when(this.playerRepository.get(player.getId())).thenReturn(player);
		when(this.moveRepository.get(player.getId())).thenReturn(new MoveDto(player, null));
		
		mockMvc.perform(post("/move/{move}/player/{idPlayer}", Option.SCISSORS, player.getId())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.id", is(notNullValue())))
		.andExpect(jsonPath("$.player.id", is(player.getId())))
		.andExpect(jsonPath("$.player.name", is("Jogador 1")))
		.andExpect(jsonPath("$.option", is(Option.SCISSORS.name())))
		;
		
		Mockito.verify(this.moveRepository, times(1)).get(player.getId());
		Mockito.verify(this.moveRepository, times(1)).save(Mockito.any());
		
	}
	
	@Test
	void updateMove() throws JsonProcessingException, Exception {
		
		PlayerDto player = new PlayerDto("Jogador 1");
		when(this.playerRepository.get(player.getId())).thenReturn(player);
		when(this.moveRepository.get(player.getId())).thenReturn(new MoveDto(player, null));
		
		mockMvc.perform(put("/move/{move}/player/{idPlayer}", Option.SCISSORS, player.getId())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.id", is(notNullValue())))
		.andExpect(jsonPath("$.player.id", is(player.getId())))
		.andExpect(jsonPath("$.player.name", is("Jogador 1")))
		.andExpect(jsonPath("$.option", is(Option.SCISSORS.name())))
		;
		
		Mockito.verify(this.moveRepository, times(1)).get(player.getId());
		Mockito.verify(this.moveRepository, times(1)).save(Mockito.any());
	}
	
	@Test
	void getMove() throws JsonProcessingException, Exception {
		
		PlayerDto player = new PlayerDto("Jogador 1");
		Mockito.when(this.moveRepository.get(Mockito.anyString())).thenReturn(new MoveDto(player, Option.SCISSORS));
		
		
		mockMvc.perform(get("/move/player/{idPlayer}", player.getId())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.id", is(notNullValue())))
		.andExpect(jsonPath("$.player.id", is(player.getId())))
		.andExpect(jsonPath("$.player.name", is("Jogador 1")))
		.andExpect(jsonPath("$.option", is(Option.SCISSORS.name())))
		;
		
	}
	
	@Test
	void deleteMove() throws JsonProcessingException, Exception {
		
		PlayerDto player = new PlayerDto("Jogador 1");
		when(this.moveRepository.get(player.getId())).thenReturn(new MoveDto(player, null));
		
		mockMvc.perform(delete("/move/player/{idPlayer}", player.getId())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		;
		
		Mockito.verify(this.moveRepository, times(1)).get(player.getId());
		Mockito.verify(this.moveRepository, times(1)).delete(Mockito.any());
	}
	
	@Test
	void listMoves() throws JsonProcessingException, Exception {
		
		when(this.moveRepository.list()).thenReturn(Arrays.asList(
				new MoveDto(new PlayerDto("Player 1"), Option.SCISSORS),
				new MoveDto(new PlayerDto("Player 2"), Option.STONE),
				new MoveDto(new PlayerDto("Player 3"), Option.PAPER)
				));
		
		mockMvc.perform(get("/move")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$[0].id", is(notNullValue())))
		.andExpect(jsonPath("$[0].player.id", is(notNullValue())))
		.andExpect(jsonPath("$[0].player.name", is("Player 1")))
		.andExpect(jsonPath("$[0].option", is(Option.SCISSORS.name())))
		.andExpect(jsonPath("$[1].id", is(notNullValue())))
		.andExpect(jsonPath("$[1].player.id", is(notNullValue())))
		.andExpect(jsonPath("$[1].player.name", is("Player 2")))
		.andExpect(jsonPath("$[1].option", is(Option.STONE.name())))
		.andExpect(jsonPath("$[2].id", is(notNullValue())))
		.andExpect(jsonPath("$[2].player.id", is(notNullValue())))
		.andExpect(jsonPath("$[2].player.name", is("Player 3")))
		.andExpect(jsonPath("$[2].option", is(Option.PAPER.name())))
		;
		
	}
	
}
