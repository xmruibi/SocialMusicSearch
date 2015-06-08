package com.musicSearch.core.index.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicSearch.core.domain.Music;
import com.musicSearch.core.repository.MusicRepository;

/**
 * Retrieval Services on Elastic Search 
 * @author ruibi
 *
 */
@Service
public class MusicAdvancedRetrievalService {

	@Autowired
	MusicRepository musicRepository;

	@Autowired
	private Client client; // need to initial elastic search client

	/**
	 * This is keyword search method in comment contents field in music object
	 * @param keyword
	 * @return
	 */
	public List<Music> findMusic(String keyword) {
		QueryBuilder matchquery = QueryBuilders.fuzzyLikeThisFieldQuery(
				"commentContents").likeText(keyword);
		SearchRequestBuilder requestBuilder = client.prepareSearch("musics")
				.setQuery(matchquery);
		SearchResponse response = requestBuilder.execute().actionGet();
		SearchHits hits = response.getHits();
		List<String> musicIdsList = new ArrayList<String>();
		Iterator<SearchHit> iterator = hits.iterator();
		while (iterator.hasNext()) {
			musicIdsList.add(iterator.next().getSource().get("id").toString());
		}
		return (List<Music>) musicRepository.findAll(musicIdsList);
	}

	/**
	 * This prefix search in multiple fields in music object
	 * this method is for the user auto suggestion 
	 * @param prefix
	 * @return
	 */
	public List<Music> findPrefix(String prefix) {
		QueryBuilder matchquery = QueryBuilders.multiMatchQuery(prefix,
				"title", "artist", "description", "commentContents",
				"bulletComments.content").type(
				MatchQueryBuilder.Type.PHRASE_PREFIX);
		SearchRequestBuilder requestBuilder = client.prepareSearch("musics")
				.setQuery(matchquery);
		SearchResponse response = requestBuilder.execute().actionGet();
		SearchHits hits = response.getHits();
		List<String> musicIdsList = new ArrayList<String>();
		Iterator<SearchHit> iterator = hits.iterator();
		while (iterator.hasNext()) {
			musicIdsList.add(iterator.next().getSource().get("id").toString());
		}
		return (List<Music>) musicRepository.findAll(musicIdsList);
	}

	/**
	 * This keyword match in multiple fields in music object
	 * @param keyword
	 * @return
	 */
	public List<Music> findMultipleFields(String keyword) {
		QueryBuilder matchquery = QueryBuilders.multiMatchQuery(keyword,
				"title", "artist", "description", "commentContents",
				"bulletComments.content").type(MatchQueryBuilder.Type.PHRASE);
		SearchRequestBuilder requestBuilder = client.prepareSearch("musics")
				.setQuery(matchquery);
		SearchResponse response = requestBuilder.execute().actionGet();
		SearchHits hits = response.getHits();
		List<String> musicIdsList = new ArrayList<String>();
		Iterator<SearchHit> iterator = hits.iterator();
		while (iterator.hasNext()) {
			SearchHit hit = iterator.next();
			musicIdsList.add(hit.getSource().get("id").toString());
		}
		return (List<Music>) musicRepository.findAll(musicIdsList);
	}
}
