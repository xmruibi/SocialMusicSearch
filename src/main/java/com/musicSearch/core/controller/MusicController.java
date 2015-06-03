package com.musicSearch.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.musicSearch.core.domain.Music;
import com.musicSearch.core.service.MusicService;

@RestController
@RequestMapping("/musics")
public class MusicController {
	private static Logger LOGGER = Logger.getLogger(MusicController.class);

	private final MusicService musicService;

	@Autowired
	public MusicController(MusicService musicService) {
		this.musicService = musicService;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createMusic(@RequestBody @Valid Music music) {
		musicService.save(music);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		musicService.deleteById(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Music findMusicById(@PathVariable("id") String id) {
		return musicService.findById(id);
	}

	 @RequestMapping(method = RequestMethod.GET)
	public List<Music> findAllMusic() {
		return musicService.findAllMusic();
	}
	 

	
	 @ResponseStatus( value = HttpStatus.BAD_REQUEST )
	 public class BadRequestException extends RuntimeException{
	    //
	 }
	 @ResponseStatus( value = HttpStatus.NOT_FOUND )
	 public class ResourceNotFoundException extends RuntimeException{
	    //
	 }
}
