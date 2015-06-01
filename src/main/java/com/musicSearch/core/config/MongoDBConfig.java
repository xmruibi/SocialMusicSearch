package com.musicSearch.core.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.musicSearch.core.repository.RepositoryPackage;
import com.musicSearch.core.service.ServicePackage;

@Configuration
@ComponentScan(basePackageClasses = ServicePackage.class)
@EnableMongoRepositories(basePackageClasses=RepositoryPackage.class)
public class MongoDBConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "musicSearch";
	}

	@Override
	public Mongo mongo() throws Exception {
		// TODO Auto-generated method stub
		ServerAddress serverAdress = new ServerAddress("localhost", 27017);
		Mongo mongo = new MongoClient(serverAdress);
		mongo.setWriteConcern(WriteConcern.SAFE);
		return mongo;

	}

	@Override
	protected String getMappingBasePackage() {
		// TODO Auto-generated method stub
		return "com.musicSearch.core.domain";
	}
	
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
}
