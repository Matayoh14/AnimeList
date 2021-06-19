package com.qa.animelist.utils;

import org.springframework.stereotype.Service;

import com.qa.animelist.domain.Episode;
import com.qa.animelist.dto.EpisodeDTO;

@Service
public class EpisodeMapper implements Mapper<Episode, EpisodeDTO> {
	
	@Override
	public EpisodeDTO mapToDTO(Episode episode) {
		EpisodeDTO dto = new EpisodeDTO();
		
		dto.setId(episode.getId());
		dto.setNumber(episode.getNumber());
		dto.setDesc(episode.getDesc());
		dto.setTitle(episode.getTitle());
		dto.setAnimeTitle(episode.getAnimeTitle());
		
		return dto;
	}
	
	@Override
	public Episode mapFromDTO(EpisodeDTO dto) {
		Episode mapped = new Episode(dto.getId(), dto.getAnimeTitle(), dto.getNumber(), dto.getTitle(), dto.getDesc());
		return mapped;
	}
}
