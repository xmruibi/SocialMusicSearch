package com.musicSearch.core.index.service;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.Client;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicSearch.core.index.domain.IndexedMusic;

@Service
public class MusicIndexingService {

	private final static Logger logger = Logger
			.getLogger(MusicIndexingService.class);

	@Autowired	
	private Client client ;
	
	private ObjectMapper mapper = new ObjectMapper();

	// experiment_1
	public void bulkIndex(List<IndexedMusic> musicCollection) {
		client.delete(new DeleteRequest("musics"));
		logger.info("Indexing bulk request of " + musicCollection.size()
				+ " documents");
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for (IndexedMusic music : musicCollection) {
			String json = null;
			try {
				json = mapper.writeValueAsString(music);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
			bulkRequest.add(client.prepareIndex("musics", "music",
					UUID.randomUUID().toString()).setSource(json));
		}
		BulkResponse response = bulkRequest.execute().actionGet();
		if (response.hasFailures()) {
			throw new RuntimeException(
					"there was an error indexing the bulk request of "
							+ musicCollection.size() + " documents: " +response.buildFailureMessage());
		}
	}
	

}
