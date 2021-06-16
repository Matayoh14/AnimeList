package com.qa.animelist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.qa.animelist.domain.Episode;
import com.qa.animelist.dto.EpisodeDTO;
import com.qa.animelist.repo.EpisodeRepo;
import com.qa.animelist.utils.EpisodeMapper;

@Service
public class EpisodeListService {
	
	private EpisodeRepo repo;
	
	private EpisodeMapper mapper;

	public EpisodeListService(EpisodeRepo repo, EpisodeMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	public EpisodeDTO createEpisode(Episode episode) {
		Episode saved = this.repo.save(episode);
		return this.mapper.mapToDTO(saved);
	}
	
	public EpisodeDTO findEpisode(Integer id) {
		Optional<Episode> optionalEpisode = this.repo.findById(id);
		Episode found = optionalEpisode.orElseThrow(() -> new EntityNotFoundException());
		return this.mapper.mapToDTO(found);
	}
	
	public EpisodeDTO updateEpisode(Integer id, Episode newData) {
		Episode existing = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		existing.setTitle(newData.getTitle());
		existing.setDesc(newData.getDesc());
		
		Episode updated = this.repo.save(existing);
		return this.mapper.mapToDTO(updated);
	}
	
	public boolean delete(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	public List<EpisodeDTO> getEpisodeList(String animeTitle) {
		List<Episode> episodeList = this.repo.findAllByAnimeTitleIgnoreCase(animeTitle);
		List<EpisodeDTO> dtos = new ArrayList<>();
		
		EpisodeDTO dto = null;
		for(Episode episode : episodeList) {
			dto = this.mapper.mapToDTO(episode);
			dtos.add(dto);
		}
		
		return dtos;
	}
}
