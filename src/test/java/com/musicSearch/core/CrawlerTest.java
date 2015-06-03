package com.musicSearch.core;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.musicSearch.core.service.MusicService;
import com.musicSearch.core.util.crawler.MusicCrawler;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MusicSearchApplication.class)
@WebAppConfiguration
public class CrawlerTest {

	@Autowired
	private MusicService musicService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void terarDownAfterClass() throws Exception {

	}

	@Test
	public void crawlerRunner() throws IOException {
		MusicCrawler.setDomain("http://www.last.fm");
		MusicCrawler crawler = new MusicCrawler(
				"http://www.last.fm/music/+free-music-downloads");
		crawler.backtracking(1);
		// crawler.backupToFile("links.txt");
		crawler.crawlMusic();
		musicService.saveAll(crawler.getMusicList());

	}
	
}
