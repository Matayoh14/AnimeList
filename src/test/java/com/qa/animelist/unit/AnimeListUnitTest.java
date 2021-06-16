package com.qa.animelist.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.qa.animelist.domain.Anime;
import com.qa.animelist.service.AnimeListService;

@SpringBootTest
@ActiveProfiles("test")
public class AnimeListUnitTest {

	@Autowired
	private AnimeListService service;
	
	@Test
	public void testCreate() {
		//Given
		Anime newAnime = new Anime("Erased", "Thriller", 12L, 1L);
		List<Anime> expectedAnimeList = new ArrayList<>();
		expectedAnimeList.add(new Anime(0 ,"Erased", "Thriller", 12L, 1L));
		
		//When
		
		//Then
		assertThat(this.service.createAnime(newAnime)).isEqualTo(expectedAnimeList.toString());
	}
	
}
