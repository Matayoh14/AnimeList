package com.qa.animelist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.animelist.domain.Anime;

@Service
public class AnimeListService {
	
	private List<Anime> animelist = new ArrayList<>();
	
	public String createAnime(Anime anime) {
		this.animelist.add(anime);
		System.out.println(this.animelist);
		return this.animelist.toString();
	}
	
	public String delete(int id) {
		this.animelist.remove(id);
		return this.animelist.toString();
	}
	
	public List<Anime> getAnimeList() {
		return this.animelist;
	}
}
