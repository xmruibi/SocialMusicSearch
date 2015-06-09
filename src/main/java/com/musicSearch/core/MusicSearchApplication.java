package com.musicSearch.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.musicSearch.core.config.ApplicationConfig;
import com.musicSearch.core.config.ElasticSearchConfig;
import com.musicSearch.core.config.MongoDBConfig;
import com.musicSearch.core.config.WebMVCConfig;

/**
 * Main entrance for Spring Boot App
 * 
 * @author ruibi
 *
 */
@Configuration
@EnableAutoConfiguration
@Import(RepositoryRestMvcConfiguration.class)
public class MusicSearchApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(
				MusicSearchApplication.class, args);
		//SpringApplication.run(MusicSearchApplication.class, args);
	}

	@Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(MongoDBConfig.class,
					ElasticSearchConfig.class, ApplicationConfig.class,
					WebMVCConfig.class);
	    }

	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	String status() {
		return "{\"state\": \"running\"}";
	}
}
