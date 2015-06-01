package com.musicSearch.core.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicSearch.core.service.MusicService;

@RestController
@RequestMapping("/music")
public class MusicController {
	private static Logger LOGGER = Logger.getLogger(MusicController.class);

	private final MusicService musicService;
	
	@Autowired
	public MusicController(MusicService musicService) {
		this.musicService = musicService;
	}
}
