package com.qa.animelist.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.animelist.domain.Anime;
import com.qa.animelist.dto.AnimeDTO;
import com.qa.animelist.service.AnimeListService;

@RestController
@RequestMapping("/animelist")
public class AnimeListController {
	
	private AnimeListService service;
	
	@Autowired
	public AnimeListController(AnimeListService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public AnimeDTO createAnime(@RequestBody Anime anime) {
		return this.service.createAnime(anime);
	}
	
	@DeleteMapping("/remove/{id}")
	public boolean delete(@PathVariable int id) {
		return this.service.delete(id);
	}
	
	@GetMapping("/")
	public List<AnimeDTO> getAnimeList(){
		return this.service.getAnimeList();
	}
	
	@PutMapping("/update/{id}")
	public AnimeDTO updateAnime(@PathVariable int id, @RequestBody Anime anime) {
		return this.service.updateAnime(id, anime);
	}
	
}
