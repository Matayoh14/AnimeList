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

import com.qa.animelist.domain.Episode;
import com.qa.animelist.dto.EpisodeDTO;
import com.qa.animelist.repo.EpisodeRepo;
import com.qa.animelist.service.EpisodeListService;
import com.qa.animelist.utils.EpisodeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:anime-schema.sql",
		"classpath:anime-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class EpisodeListServiceUnitTest {

	@Autowired
	private EpisodeListService service;
	
	@MockBean
	private EpisodeRepo repo;
	
	@Autowired
	private EpisodeMapper mapper;
	
	@Test
	public void CreateEpisodeTest() {
		//Given
		Episode newEpisode = new Episode(1,"Erased");
		
		//When
		Mockito.when(this.repo.save(newEpisode)).thenReturn(newEpisode);
		
		//Then
		assertThat(mapper.mapFromDTO(this.service.createEpisode(newEpisode))).isEqualTo(newEpisode);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(newEpisode);
	}
	
	@Test
	public void findEpisodeTest() {
		//Given
		Integer testId = 1;
		Episode found = new Episode(1, null, null, null, null);
		EpisodeDTO expected = mapper.mapToDTO(found);
		
		//When
		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(found));
		
		//Then
		assertThat(this.service.findEpisode(testId)).isEqualTo(expected);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(testId);
	}
	
	@Test
	public void UpdateEpisodeTest() {
		//Given
		Integer testId = 1;
		Episode newData = new Episode(null, null, null, "The Beginning", "The First Episode");
		Optional<Episode> existing = Optional.of(new Episode(1, "Erased", 1, null, null));
		Episode updated = new Episode(1, "Erased", 1, "The Beginning", "The First Episode");
		EpisodeDTO expected = mapper.mapToDTO(updated);
		
		//When
		Mockito.when(this.repo.findById(testId)).thenReturn(existing);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		
		//Then
		assertThat(this.service.updateEpisode(testId, newData)).isEqualTo(expected);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(testId);
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
	}
	
	@Test
	public void DeleteTest() {
		//Given
		Integer testId = 1;
		
		//When
		Mockito.when(this.repo.existsById(testId)).thenReturn(false);
		
		//Then
		assertThat(this.service.delete(testId)).isEqualTo(true);
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(testId);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(testId);
	}
	
	@Test
	public void GetEpisodeListTest() {
		//Given
		String animeTitle = "Erased";
		List<Episode> episodeList = new ArrayList<>();
		episodeList.add(new Episode(1, animeTitle));
		List<EpisodeDTO> expected = new ArrayList<>();
		expected.add(mapper.mapToDTO(new Episode(1, animeTitle)));
		
		//When
		Mockito.when(this.repo.findAllByAnimeTitleIgnoreCase(animeTitle)).thenReturn(episodeList);
		
		//Then
		assertThat(this.service.getEpisodeList(animeTitle)).isEqualTo(expected);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAllByAnimeTitleIgnoreCase(animeTitle);
	}
	
	
}
