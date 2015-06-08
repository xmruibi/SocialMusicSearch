package com.musicSearch.core.config;

import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.musicSearch.core.index.repository.IndexRepositoryPackage;

/**
 * Elastic Search Configuration 
 * @author ruibi
 *
 */
@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
@EnableElasticsearchRepositories(basePackageClasses = IndexRepositoryPackage.class)
public class ElasticSearchConfig {
	@Resource
	private Environment environment;

	@Bean
	public Client client() {
		TransportClient client = new TransportClient();
		TransportAddress address = new InetSocketTransportAddress(
				environment.getProperty("elasticsearch.host"),
				Integer.parseInt(environment.getProperty("elasticsearch.port")));
		client.addTransportAddress(address);
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchTemplate(client());
	}
}
