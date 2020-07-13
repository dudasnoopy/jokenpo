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
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jokenpo.dto.PlayerDto;
import com.jokenpo.repository.PlayerRepository;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControlerTest {
	
	@MockBean
	private PlayerRepository playerRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void addPlayerTest() throws JsonProcessingException, Exception {
		PlayerDto player = new PlayerDto("Jogador 1");
		
		mockMvc.perform(post("/player")
				.content(player.toJson())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		;
		
		Mockito.verify(this.playerRepository, times(1)).save(Mockito.any());
		
	}
	
	@Test
	void updatePlayerTest() throws JsonProcessingException, Exception {
		PlayerDto player = new PlayerDto("Jogador 1");
		when(this.playerRepository.get(player.getId())).thenReturn(player);
		
		mockMvc.perform(put("/player")
				.content(player.toJson())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		;
		
		Mockito.verify(this.playerRepository, times(1)).save(Mockito.any());
	}
	
	@Test
	void getPlayerTest() throws JsonProcessingException, Exception {
		PlayerDto player = new PlayerDto("Jogador 1");
		when(this.playerRepository.get(player.getId())).thenReturn(player);
		
		mockMvc.perform(get("/player/{id}", player.getId())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.id", is(player.getId())))
		.andExpect(jsonPath("$.name", is("Jogador 1")))
		;
		
	}
	
	@Test
	void deletePlayer() throws JsonProcessingException, Exception {
		String id = UUID.randomUUID().toString();
		mockMvc.perform(delete("/player/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		;
		
		Mockito.verify(this.playerRepository, times(1)).get(id);
		Mockito.verify(this.playerRepository, times(1)).delete(Mockito.any());
	}
	
	@Test
	void listPlayers() throws JsonProcessingException, Exception {
		Mockito.when(this.playerRepository.list()).thenReturn(Arrays.asList(new PlayerDto("Player 1"), new PlayerDto("Player 2")));
		
		mockMvc.perform(get("/player")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$[0].id", is(notNullValue())))
		.andExpect(jsonPath("$[0].name", is("Player 1")))
		.andExpect(jsonPath("$[1].id", is(notNullValue())))
		.andExpect(jsonPath("$[1].name", is("Player 2")))
		;
		
	}

}
