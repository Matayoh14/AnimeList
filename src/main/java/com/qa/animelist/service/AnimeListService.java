package com.qa.animelist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.qa.animelist.domain.Anime;
import com.qa.animelist.dto.AnimeDTO;
import com.qa.animelist.repo.AnimeRepo;
import com.qa.animelist.utils.AnimeMapper;

@Service
public class AnimeListService {
	
	private AnimeRepo repo;
	
	private AnimeMapper mapper;
	
	public AnimeListService(AnimeRepo repo, AnimeMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	public AnimeDTO createAnime(Anime anime) {
		Anime saved = this.repo.save(anime);
		return this.mapper.mapToDTO(saved);
	}
	
	public boolean delete(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	public AnimeDTO findAnime(Integer id) {
		Optional<Anime> optionalAnime = this.repo.findById(id);
		Anime found = optionalAnime.orElseThrow(() -> new EntityNotFoundException());
		return this.mapper.mapToDTO(found);
	}
	
	public List<AnimeDTO> getAnimeList() {
		List<Anime> animeList = this.repo.findAll();
		List<AnimeDTO> dtos = new ArrayList<>();
		
		for(Anime a : animeList) {
			AnimeDTO dto = this.mapper.mapToDTO(a);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public AnimeDTO updateAnime(Integer id, Anime update) {
		Anime toUpdate = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		toUpdate.setTitle(update.getTitle());
		toUpdate.setGenre(update.getGenre());
		toUpdate.setEpisodes(update.getEpisodes());
		toUpdate.setSeason(update.getSeason());
		
		Anime updated = this.repo.save(toUpdate);
		return this.mapper.mapToDTO(updated);
	}
}
