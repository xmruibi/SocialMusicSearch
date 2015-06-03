package com.musicSearch.core.index.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.musicSearch.core.index.domain.IndexedMusic;

public interface MusicIndexRepository extends ElasticsearchRepository<IndexedMusic, String>{
}
