package com.musicSearch.core.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories
public class ApplicationConfig {
	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		String host = "";
		String port = "";
		String username = "";
		String password = "";
		Mongo mongo = new Mongo() {
		}; 
		
		return null;
	}
}
