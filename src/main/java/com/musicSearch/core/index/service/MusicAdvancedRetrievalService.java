package com.musicSearch.core.index.service;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicSearch.core.index.domain.IndexedMusic;
import com.musicSearch.core.index.repository.MusicIndexRepository;

@Service
public class MusicAdvancedRetrievalService {

	@Autowired
	private MusicIndexRepository musicIndexRepository;
	
	public List<IndexedMusic> findMusic(String keyword) {
		QueryBuilder matchquery = QueryBuilders.matchQuery("commentContent", keyword);
		
		return (List<IndexedMusic>) musicIndexRepository.search(matchquery);
	}
}
