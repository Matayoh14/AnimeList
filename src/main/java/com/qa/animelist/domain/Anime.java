package com.qa.animelist.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Anime {
	
	static Integer idGen;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/*
	static{
		idGen = -1;
	}
	
	{
		id = idGen++;
	}
	*/
	
	@Column(nullable = false)
	private String title;
	
	@Column
	private String genre;
	
	@Column
	private Long episodes;
	
	@Column
	private Long season;
	
	@OneToMany(mappedBy = "anime")
	@JsonIgnore
	private List<Episode> episodeList = new ArrayList<>();
	
	public Anime() {}

	public Anime(String title, String genre, Long episodes, Long season) {
		super();
		this.title = title;
		this.genre = genre;
		this.episodes = episodes;
		this.season = season;
	}
	

	public static Integer getIdGen() {
		return idGen;
	}

	public static void setIdGen(Integer idGen) {
		Anime.idGen = idGen;
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
	
	

	public List<Episode> getEpisodeList() {
		return episodeList;
	}

	public void setEpisodeList(List<Episode> episodeList) {
		this.episodeList = episodeList;
	}

	@Override
	public String toString() {
		return "Anime [id=" + id + ", title=" + title + ", genre=" + genre + ", episodes=" + episodes + ", season="
				+ season + "]";
	}
	
	
}
