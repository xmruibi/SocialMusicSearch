package com.musicSearch.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicSearch.core.domain.Music;
import com.musicSearch.core.repository.MusicRepository;

@Service
public class MusicRetrievalService {
	private final MusicRepository musicRepository;

	@Autowired
	public MusicRetrievalService(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}
	
	public List<Music> findByTitle(String title){
		return musicRepository.findByTitleRegex(title);
	}
	
	public List<Music> findByAritist(String artist) {
		return musicRepository.findByArtist(artist);
	}
}
