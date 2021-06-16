package com.qa.animelist.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.animelist.domain.Anime;

@Repository
public interface AnimeRepo extends JpaRepository<Anime, Integer> {

	public List<Anime> findByGenreIgnoreCase(String genre);
}
