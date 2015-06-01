package com.musicSearch.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicSearch.core.repository.MusicRepository;

@Service
public class MusicService {

	private final MusicRepository musicRepository;
	
	@Autowired
	public MusicService(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}
}
