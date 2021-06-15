package com.qa.animelist.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Anime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column
	private String genre;
	
	@Column
	private Long episodes;
	
	@Column
	private Long seasons;
	
	@ManyToOne
	private WatchList watchlist;
	
	public Anime() {}

	public Anime(Long id, String title, String genre, Long episodes, Long seasons) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.episodes = episodes;
		this.seasons = seasons;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getSeasons() {
		return seasons;
	}

	public void setSeasons(Long seasons) {
		this.seasons = seasons;
	}

	@Override
	public String toString() {
		return "Anime [id=" + id + ", title=" + title + ", genre=" + genre + ", episodes=" + episodes + ", seasons="
				+ seasons + "]";
	}
	
	
}
