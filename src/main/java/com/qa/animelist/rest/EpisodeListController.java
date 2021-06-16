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

import com.qa.animelist.domain.Episode;
import com.qa.animelist.dto.EpisodeDTO;
import com.qa.animelist.service.EpisodeListService;

@RestController
@RequestMapping("/episodelist")
public class EpisodeListController {

	private EpisodeListService service;
	
	@Autowired
	public EpisodeListController(EpisodeListService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public EpisodeDTO createEpisode(@RequestBody Episode episode) {
		return this.service.createEpisode(episode);	
	}
	
	@GetMapping("/{animeTitle}")
	public List<EpisodeDTO> getEpisodeList(@PathVariable String animeTitle) {
		return this.service.getEpisodeList(animeTitle);
	}
	
	@PutMapping("/update/{id}") 
	public EpisodeDTO updateEpisode(@RequestBody Episode episode, @PathVariable int id) {
		return this.service.updateEpisode(id, episode);
	}
	
	@DeleteMapping("/remove/{id}")
	public boolean delete(@PathVariable int id) {
		return this.service.delete(id);
	}
}	
