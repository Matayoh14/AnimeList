package com.qa.animelist.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.animelist.domain.Episode;

@Repository
public interface EpisodeRepo extends JpaRepository<Episode, Integer> {

	public List<Episode> findAllByAnimeTitleIgnoreCase(String animeTitle);
}
