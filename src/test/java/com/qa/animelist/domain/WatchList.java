package com.qa.animelist.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class WatchList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String owner;
	
	@OneToMany(mappedBy = "watchlist")
	private List<Anime> animelist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<Anime> getAnimeList() {
		return animelist;
	}

	public void setAnimeList(List<Anime> anime) {
		this.animelist = anime;
	}
	
	
}
