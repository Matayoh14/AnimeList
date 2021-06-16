package com.qa.animelist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Episode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String animeTitle;
	
	private Integer number;
	
	private String title;
	
	private String desc;
	
	@ManyToOne
	private Anime anime;
	
	public Episode() {}

	public Episode(Integer number, String animeTitle) {
		super();
		this.number = number;
		this.animeTitle = animeTitle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Anime getAnime() {
		return anime;
	}

	public void setAnime(Anime anime) {
		this.anime = anime;
	}

	public String getAnimeTitle() {
		return animeTitle;
	}

	public void setAnimeId(String animeTitle) {
		this.animeTitle = animeTitle;
	}

	@Override
	public String toString() {
		return "Episode [number=" + number + ", animeTitle=" + animeTitle + ", title=" + title + ", desc=" + desc + "]";
	}
	
	
	
	
}
