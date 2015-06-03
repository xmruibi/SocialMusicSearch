package com.musicSearch.core;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.musicSearch.core.converter.MusicIndexingConverter;
import com.musicSearch.core.domain.Music;
import com.musicSearch.core.index.service.MusicIndexingService;
import com.musicSearch.core.service.MusicService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MusicSearchApplication.class)
@WebAppConfiguration
public class ElasticSearchTest {
	
	@Autowired
	private MusicIndexingService musicIndexingService;
	
	@Autowired 
	private MusicService musicService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void terarDownAfterClass() throws Exception {

	}
	@Test
	public void indexTest() {
		List<Music> musics = musicService.findAllMusic();
		musicIndexingService.bulkIndex(MusicIndexingConverter.collectionConvert(musics));
	}

}
