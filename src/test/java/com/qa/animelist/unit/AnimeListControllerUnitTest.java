package com.qa.animelist.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.animelist.domain.Anime;
import com.qa.animelist.dto.AnimeDTO;
import com.qa.animelist.rest.AnimeListController;
import com.qa.animelist.service.AnimeListService;
import com.qa.animelist.utils.AnimeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:anime-schema.sql",
		"classpath:anime-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class AnimeListControllerUnitTest {

	@MockBean
	private AnimeListService service;

	@Autowired
	AnimeListController controller = new AnimeListController(service);

	@Autowired
	private AnimeMapper mapper;

	@Test
	public void CreateAnimeTest() {
		// Given
		Anime newAnime = new Anime(1, "Erased", "Thriller", 12L, 1L);
		AnimeDTO expected = mapper.mapToDTO(newAnime);

		// When
		Mockito.when(this.service.createAnime(newAnime)).thenReturn(expected);

		// Then
		assertThat(this.controller.createAnime(newAnime)).isEqualTo(expected);

		Mockito.verify(this.service, Mockito.times(1)).createAnime(newAnime);
	}

	@Test
	public void DeleteTest() {
		// Given
		Integer testId = 1;

		// When
		Mockito.when(this.service.delete(testId)).thenReturn(false);

		// Then
		assertThat(this.controller.delete(testId)).isEqualTo(false);

		Mockito.verify(this.service, Mockito.times(1)).delete(testId);
	}

	@Test
	public void GetAnimeListTest() {
		// Given
		List<Anime> animeList = new ArrayList<>();
		List<AnimeDTO> expected = new ArrayList<>();
		Anime newAnime = new Anime(1, "Erased", "Thriller", 12L, 1L);
		AnimeDTO newDTO = mapper.mapToDTO(newAnime);
		
		animeList.add(newAnime);
		expected.add(newDTO);

		// When
		Mockito.when(this.service.getAnimeList()).thenReturn(expected);

		// Then
		assertThat(this.controller.getAnimeList()).isEqualTo(expected);
		
		Mockito.verify(this.service, Mockito.times(1)).getAnimeList();
	}
	
	@Test
	public void updateAnime() {
		//Given
		Integer testId = 1;
		Anime newData = new Anime("Erased", "Thriller", 12L, 1L);
		AnimeDTO expected = mapper.mapToDTO(new Anime(1, "Erased", "Thriller", 12L, 1L));
		
		//When
		Mockito.when(this.service.updateAnime(testId, newData)).thenReturn(expected);
		
		//Then
		assertThat(this.controller.updateAnime(testId, newData)).isEqualTo(expected);
		
		Mockito.verify(this.service, Mockito.times(1)).updateAnime(testId, newData);
		}

}
