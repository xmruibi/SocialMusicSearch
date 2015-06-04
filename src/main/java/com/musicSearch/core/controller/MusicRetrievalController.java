package com.musicSearch.core.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.musicSearch.core.domain.Music;
import com.musicSearch.core.index.service.MusicAdvancedRetrievalService;

@RestController
@RequestMapping("/musics/search")
public class MusicRetrievalController {
	private static Logger LOGGER = Logger.getLogger(MusicRetrievalController.class);

	private final MusicAdvancedRetrievalService musicAdvancedRetrievalService;
	
	@Autowired
	public MusicRetrievalController(MusicAdvancedRetrievalService musicAdvancedRetrievalService) {
		this.musicAdvancedRetrievalService = musicAdvancedRetrievalService;
	}
	
	@RequestMapping(value = "{keyword}", method = RequestMethod.GET)
	public List<Music> searchByKeyword(@PathVariable("keyword") String keyword) {
		return musicAdvancedRetrievalService.findMultipleFields(keyword);
	}	
	@RequestMapping(value = "/prefix/{keyword}", method = RequestMethod.GET)
	public List<Music> searchByPrefix(@PathVariable("keyword") String keyword) {
		return musicAdvancedRetrievalService.findPrefix(keyword);
	}
}
