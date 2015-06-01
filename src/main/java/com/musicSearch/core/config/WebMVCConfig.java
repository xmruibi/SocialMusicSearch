package com.musicSearch.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import com.musicSearch.core.Music.Music;

@EnableWebMvc
@ComponentScan(basePackageClasses = Music.class)
@Configuration
public class WebMVCConfig {

}
