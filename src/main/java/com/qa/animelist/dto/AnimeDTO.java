package com.qa.animelist.dto;

import java.util.List;

public class AnimeDTO {
	
	private Integer id;
	
	private String title;
	
	private String genre;
	
	private Long episodes;
	
	private Long season;
	
	private List<EpisodeDTO> episodeList;
	
	public AnimeDTO() {}

	public AnimeDTO(Integer id, String title, String genre, Long episodes, Long season) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.episodes = episodes;
		this.season = season;
	}

	public AnimeDTO(String title, String genre, Long episodes, Long season) {
		super();
		this.title = title;
		this.genre = genre;
		this.episodes = episodes;
		this.season = season;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Long getEpisodes() {
		return episodes;
	}

	public void setEpisodes(Long episodes) {
		this.episodes = episodes;
	}

	public Long getSeason() {
		return season;
	}

	public void setSeason(Long season) {
		this.season = season;
	}

	public List<EpisodeDTO> getEpisodeList() {
		return episodeList;
	}

	public void setEpisodeList(List<EpisodeDTO> episodeList) {
		this.episodeList = episodeList;
	}
	
	
	
}
