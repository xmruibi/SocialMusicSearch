package com.musicSearch.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicSearch.core.repository.MusicRepository;

@Service
public class MusicRetrievalService {
	private final MusicRepository musicRepository;

	@Autowired
	public MusicRetrievalService(MusicRepository musicRepository) {
		this.musicRepository = musicRepository;
	}
}
