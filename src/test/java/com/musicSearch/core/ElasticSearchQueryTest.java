package com.musicSearch.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.musicSearch.core.index.domain.IndexedMusic;
import com.musicSearch.core.index.service.MusicAdvancedRetrievalService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MusicSearchApplication.class)
@WebAppConfiguration
public class ElasticSearchQueryTest {

	@Autowired
	MusicAdvancedRetrievalService advancedRetrievalService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void terarDownAfterClass() throws Exception {
	}
	
	@Test
	public void queryTest() {
		 advancedRetrievalService.findMusic("got");
		 advancedRetrievalService.findPrefix("hip");	
	}
}
