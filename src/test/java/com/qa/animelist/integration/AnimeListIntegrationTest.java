package com.qa.animelist.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.animelist.domain.Anime;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:anime-schema.sql", "classpath:anime-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class AnimeListIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void CreateTest() throws Exception {
		Anime testAnime = new Anime("Erased", "Thriller", 12L, 1L);
		String testAnimeAsJSON = this.mapper.writeValueAsString(testAnime);
		
		Anime testSavedAnime = new Anime("Erased", "Thriller", 12L, 1L);
		testSavedAnime.setId(2);
		String testSavedAnimeAsJSON = this.mapper.writeValueAsString(testSavedAnime);
		
		RequestBuilder mockRequest = post("/animelist/create").content(testAnimeAsJSON)
						.contentType(MediaType.APPLICATION_JSON);
		
		ResultMatcher checkStatus = status().isOk();
		
		ResultMatcher checkBody = content().json(testSavedAnimeAsJSON);
		
		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);		
	}
	
	@Test
	public void GetAllTest() throws Exception {
		Anime testAnime = new Anime(1,"Re:Zero", "Isekai", 13L, 1L);
		List<Anime> animeList = List.of(testAnime);
		String testAnimeListAsJSONArray = this.mapper.writeValueAsString(animeList);
		
		this.mvc.perform(get("/animelist/")).andExpect(status().isOk()).andExpect(content().json(testAnimeListAsJSONArray));
	}
	
	@Test
	public void DeleteTest() throws Exception {
		this.mvc.perform(delete("/animelist/remove/1")).andExpect(status().isOk());
	}
	
	@Test
	public void UpdateTest() throws Exception {
		Anime newAnime = new Anime(1,"Erased", "Thriller", 12L, 1L);
		String newAnimeAsJSON = this.mapper.writeValueAsString(newAnime);
		
		RequestBuilder mockRequest = put("/animelist/update/1").content(newAnimeAsJSON)
							.contentType(MediaType.APPLICATION_JSON);
		
		this.mvc.perform(mockRequest).andExpect(status().isOk()).andExpect(content().json(newAnimeAsJSON));
	}
	
}
