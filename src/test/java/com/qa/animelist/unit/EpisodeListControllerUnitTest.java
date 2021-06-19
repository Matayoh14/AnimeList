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

import com.qa.animelist.domain.Episode;
import com.qa.animelist.dto.EpisodeDTO;
import com.qa.animelist.rest.EpisodeListController;
import com.qa.animelist.service.EpisodeListService;
import com.qa.animelist.utils.EpisodeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:anime-schema.sql",
		"classpath:anime-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class EpisodeListControllerUnitTest {

	@MockBean
	private EpisodeListService service;

	@Autowired
	EpisodeListController controller = new EpisodeListController(service);

	@Autowired
	private EpisodeMapper mapper;

	@Test
	public void CreateEpisodeTest() {
		// Given
		Episode newEpisode = new Episode(1, "Erased");
		EpisodeDTO expected = mapper.mapToDTO(newEpisode);

		// When
		Mockito.when(this.service.createEpisode(newEpisode)).thenReturn(expected);

		// Then
		assertThat(this.controller.createEpisode(newEpisode)).isEqualTo(expected);

		Mockito.verify(this.service, Mockito.times(1)).createEpisode(newEpisode);
	}

	@Test
	public void DeleteTest() {
		// Given
		Integer testId = 1;

		// When
		Mockito.when(this.service.delete(testId)).thenReturn(true);

		// Then
		assertThat(this.controller.delete(testId)).isEqualTo(true);

		Mockito.verify(this.service, Mockito.times(1)).delete(testId);
	}

	@Test
	public void GetEpisodeListTest() {
		// Given
		String animeTitle = "Erased";
		List<EpisodeDTO> expected = new ArrayList<>();
		expected.add(mapper.mapToDTO(new Episode(1, "Erased", null, null, null)));

		// When
		Mockito.when(this.service.getEpisodeList(animeTitle)).thenReturn(expected);

		// Then
		assertThat(this.controller.getEpisodeList(animeTitle)).isEqualTo(expected);

		Mockito.verify(this.service, Mockito.times(1)).getEpisodeList(animeTitle);
	}

	@Test
	public void UpdateEpisodeTest() {
		// Given
		Episode newData = new Episode(1, "Erased", 1, "The Beginning", "The First Episode");
		Integer testId = 1;
		EpisodeDTO expected = mapper.mapToDTO(newData);

		// When
		Mockito.when(this.service.updateEpisode(testId, newData)).thenReturn(expected);

		// Then
		assertThat(this.controller.updateEpisode(newData, testId)).isEqualTo(expected);

		Mockito.verify(this.service, Mockito.times(1)).updateEpisode(testId, newData);
	}

}
