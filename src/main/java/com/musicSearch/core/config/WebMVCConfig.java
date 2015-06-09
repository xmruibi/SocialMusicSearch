package com.musicSearch.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.musicSearch.core.domain.Music;

/**
 * Set up static resource path
 * @author ruibi
 *
 */
@Configuration
@ComponentScan({"com.musicSearch.core.controller","com.musicSearch.core.service","com.musicSearch.core.domain"})
public class WebMVCConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/static")
				.setViewName("forward:/index.html");
	}
}
