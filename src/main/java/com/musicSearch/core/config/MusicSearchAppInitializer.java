package com.musicSearch.core.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MusicSearchAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
		webApplicationContext.register( MongoDBConfig.class,ApplicationConfig.class,
				WebMVCConfig.class);

		Dynamic dynamc = servletContext.addServlet("dispatcherServlet",
				new DispatcherServlet(webApplicationContext));
		dynamc.addMapping("/*");
		dynamc.setLoadOnStartup(1);
	}

}
