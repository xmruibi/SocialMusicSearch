package com.musicSearch.core.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.musicSearch.core.domain.Music;
import com.musicSearch.core.service.MusicRetrievalService;
import com.musicSearch.core.service.MusicService;

@RestController
@RequestMapping("/musics/search")
public class MusicRetrievalController {
	private static Logger LOGGER = Logger.getLogger(MusicRetrievalController.class);

	private final MusicRetrievalService musicRetrievalService;
	
	@Autowired
	public MusicRetrievalController(MusicRetrievalService musicRetrievalService) {
		this.musicRetrievalService = musicRetrievalService;
	}
	
	@RequestMapping(value = "/title/{title}", method = RequestMethod.GET)
	public List<Music> searchByTitle(@PathVariable("title") String title) {
		return musicRetrievalService.findByTitle(title);
	}
	
	
}
