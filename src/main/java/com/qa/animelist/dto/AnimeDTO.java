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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((episodes == null) ? 0 : episodes.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((season == null) ? 0 : season.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimeDTO other = (AnimeDTO) obj;
		if (episodes == null) {
			if (other.episodes != null)
				return false;
		} else if (!episodes.equals(other.episodes))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (season == null) {
			if (other.season != null)
				return false;
		} else if (!season.equals(other.season))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
}
