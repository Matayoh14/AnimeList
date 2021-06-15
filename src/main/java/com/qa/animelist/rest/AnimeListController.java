package com.qa.animelist.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.animelist.domain.Anime;
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
	public String createAnime(@RequestBody Anime anime) {
		return this.service.createAnime(anime);
	}
	
	@DeleteMapping("/remove/{id}")
	public String delete(@PathVariable int id) {
		return this.service.delete(id);
	}
	
	@GetMapping("/")
	public List<Anime> getAnimeList(){
		return this.service.getAnimeList();
	}
}
