package com.qa.animelist.dto;

public class EpisodeDTO {

	private Integer id;
	
	private String animeTitle;
	
	private String title;
	
	private Integer number;
	
	private String desc;
	
	public EpisodeDTO() {}

	public EpisodeDTO(Integer number) {
		super();
		this.number = number;
	}

	public EpisodeDTO(Integer id, String animeTitle, String title, Integer number, String desc) {
		super();
		this.id = id;
		this.animeTitle = animeTitle;
		this.title = title;
		this.number = number;
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnimeTitle() {
		return animeTitle;
	}

	public void setAnimeTitle(String animeTitle) {
		this.animeTitle = animeTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
