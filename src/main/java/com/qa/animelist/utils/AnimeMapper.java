package com.qa.animelist.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.animelist.domain.Anime;
import com.qa.animelist.domain.Episode;
import com.qa.animelist.dto.AnimeDTO;
import com.qa.animelist.dto.EpisodeDTO;

@Service
public class AnimeMapper implements Mapper<Anime, AnimeDTO> {
	
	private EpisodeMapper episodeMapper;
	
	public AnimeMapper(EpisodeMapper episodeMapper) {
		super();
		this.episodeMapper = episodeMapper;
	}

	@Override
	public AnimeDTO mapToDTO(Anime anime) {
		AnimeDTO dto = new AnimeDTO();
		dto.setId(anime.getId());
		dto.setTitle(anime.getTitle());
		dto.setGenre(anime.getGenre());
		dto.setEpisodes(anime.getEpisodes());
		dto.setSeason(anime.getSeason());
		List<EpisodeDTO> episodeList = new ArrayList<>();
		for(Episode episode : anime.getEpisodeList()) {
			episodeList.add(this.episodeMapper.mapToDTO(episode));
		}
		dto.setEpisodeList(episodeList);
		return dto;
	}
	
	@Override
	public Anime mapFromDTO(AnimeDTO dto) {
		
		return null;
	}
}
