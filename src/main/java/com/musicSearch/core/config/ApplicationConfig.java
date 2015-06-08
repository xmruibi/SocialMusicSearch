package com.musicSearch.core.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

/**
 *  This configuration is to set up Spring boot and indicate the application property file
 * @author ruibi
 */
@Configuration
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
	public @Bean LoggingEventListener mongoEventListener() {
		return new LoggingEventListener();
	}
	
}
