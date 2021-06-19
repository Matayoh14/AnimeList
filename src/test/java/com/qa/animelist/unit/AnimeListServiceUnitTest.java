package com.qa.animelist.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.qa.animelist.repo.AnimeRepo;
import com.qa.animelist.service.AnimeListService;
import com.qa.animelist.utils.AnimeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:anime-schema.sql", "classpath:anime-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class AnimeListServiceUnitTest {

	@Autowired
	private AnimeListService service;
	
	@MockBean
	private AnimeRepo repo;
	
	@Autowired
	private AnimeMapper mapper;
	
	@Test
	public void CreateAnimeTest() {
		//Given
		Anime newAnime = new Anime("Erased", "Thriller", 12L, 1L);
		Anime expectedAnime = new Anime(2,"Erased", "Thriller", 12L, 1L);
		
		//When
		Mockito.when(this.repo.save(newAnime)).thenReturn(expectedAnime);
		
		//Then
		assertThat(mapper.mapFromDTO(this.service.createAnime(newAnime))).isEqualTo(expectedAnime);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(newAnime);
	}
	
	@Test
	public void FindAnimeTest() {
		//Given
		Integer id = 1;
		Optional<Anime> found = Optional.of(new Anime(1,"Erased", "Thriller", 12L, 1L));
		Anime expected = new Anime(1,"Erased", "Thriller", 12L, 1L);
		
		//When
		Mockito.when(this.repo.findById(id)).thenReturn(found);
		
		//Then
		assertThat(mapper.mapFromDTO(this.service.findAnime(id))).isEqualTo(expected);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	public void getAllAnimeTest() {
		//Given
		List<AnimeDTO> expectedList = new ArrayList<>();
		List<Anime> animeList = new ArrayList<>();
		Anime anime = new Anime(1,"Erased", "Thriller", 12L, 1L);
		animeList.add(anime);
		expectedList.add(mapper.mapToDTO(anime));
		
		//When
		Mockito.when(this.repo.findAll()).thenReturn(animeList);
		
		//Then
		assertThat(this.service.getAnimeList()).isEqualTo(expectedList);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void  UpdateAnimeTest() {
		//Given
		Integer testId = 1;
		Anime testData = new Anime("Erased", "Thriller", 12L, 1L);
		Anime existing = new Anime(1, null, null, null, null);
		Anime updated = new Anime(testId, "Erased", "Thriller", 12L, 1L);
		
		//When
		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		
		//Then
		assertThat(mapper.mapFromDTO(this.service.updateAnime(testId, testData))).isEqualTo(updated);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(testId);
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
	}
	
	@Test
	public void DeleteAnimeTest() {
		//Given
		Integer testId = 1;
		
		//When
		Mockito.when(this.repo.existsById(testId)).thenReturn(false);
		
		//Then
		assertThat(this.service.delete(testId)).isTrue();
		
		Mockito.verify(this.repo, Mockito.times(1)).existsById(testId);
	}
}
