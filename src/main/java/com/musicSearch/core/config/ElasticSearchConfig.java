package com.musicSearch.core.config;

import org.elasticsearch.node.NodeBuilder;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.musicSearch.core.index.repository.IndexRepositoryPackage;

@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
@EnableElasticsearchRepositories(basePackageClasses = IndexRepositoryPackage.class)
public class ElasticSearchConfig {
	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchTemplate(NodeBuilder.nodeBuilder().local(true).node()
				.client());
	}
}
