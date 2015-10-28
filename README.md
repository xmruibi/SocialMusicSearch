
# SocialMusicSearch

### Brief Intro
Although the text information retrieval is very mature in current world, the media files are still hard to be annotated and retrieved. The problem is media files are lacking of the sementic sentimental elements. Here, we come up with an innovative thinking on music search engine with live comments (bullet comments) techniques. The idea of live comment is derives from Janpanses Video site - Niconico.com. Users can upload, view and share video clips. Unlike other video sharing sites, however, comments are overlaid directly onto the video, synced to a specific playback time. In this project, we use live comment is a kind of comment added on each frame of the music file. Those bullet comments releases user's sentimental infomation on a certain song or song's segment. According to these comments, we not only can set up profile on each song, but also on those song's specific segment. 


### Techniques
##### Front End
Angular.js 
##### Web Service:
Spring-Boot App,
Spring-REST API
##### Non-sql Database 
MongoDB,
Spring-data-Mongodb API
##### Index Service
ElasticSearch,
Spring-data-elasticsearch API
##### Static Music Resource Storage
AWS S3

## Framework: Why Spring Boot?
Spring framework goes every where in current enterprise application. However, most of people are familiar with Spring MVC. Here I just want to introduce a new Spring Boot project. Hear what its official document said:
> Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run". 

Yes, unlike Spring MVC, the Spring Boot require less configuration and easier to deploy on remote virtual machine or cloud computing platform. Spring Boot has following features:

#### Features
- Create stand-alone Spring applications
- Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
- Provide opinionated 'starter' POMs to simplify your Maven configuration
- Automatically configure Spring whenever possible
- Provide production-ready features such as metrics, health checks and externalized configuration
- Absolutely no code generation and no requirement for XML configuration




## Spring Boot App Configuration
The Spring Boot requires some basic configuration and set up the bootstrap entrance:

- It doesn't need `web.xml` whic is common for Spring MVC;

- Set up the bootstrap by Maven plugin.

- Bootstrap Main function (Entrance of Spring Boot App): MusicSearchApplication;

- Spring Configuration (config package)
	- ApplicationConfig
	```java
	@Configuration
	@PropertySource("classpath:application.properties") // point out the application.properties as configuration source
	public class ApplicationConfig {
		public @Bean LoggingEventListener mongoEventListener() {
			return new LoggingEventListener();
		}
		
	}
	```
	- WebMVCConfig
	```
	@Configuration
	@ComponentScan({"com.musicSearch.core.controller","com.musicSearch.core.service","com.musicSearch.core.domain"}) // here is important to do component scan
	public class WebMVCConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/static")
					.setViewName("forward:/index.html");
			// point out the .css/.js or other static files target and default home page
		}
	}
	```
	- ApplicationInitializer: Core entrance configuration
	```java
	@Configuration
	@EnableAutoConfiguration
	@Import({ MongoDBConfig.class, ElasticSearchConfig.class,
			ApplicationConfig.class, WebMVCConfig.class,
			RepositoryRestMvcConfiguration.class })
	public class MusicSearchApplication extends SpringBootServletInitializer {

		public static void main(String[] args) {
			SpringApplication.run(MusicSearchApplication.class, args);
		}

		@Override
		protected SpringApplicationBuilder configure(
				SpringApplicationBuilder application) {
			return application.sources(MusicSearchApplication.class);
		}
	}
	```


## Design Detail
Here is how I design my Social Music Search project with Spring Boot:

### RESTful Serivce to do the backend and frontend communication
#### Spring-Data-REST
Based on Spring Boot as backend, I set up the RESTful interface by using Spring-Data-REST API:
- Config: ApplicationConfig;
- service;
- controller;

### Non-SQL database, MongoDB, to be the database solution
Spring-Data-MongoDB

- config: MongoDBConfig;
- repository;
- domain: Music, BulletComment, User, Genre...;

### Indexing Service
Spring-Data-ElasticSearch

- config: ElasticSearchConfig; (port:9300)
- index.repository;
- index.domain: Indexed Music;

## Dataflow Design:
### In Flow: 
 Data Crawler - source come from last.fm

 Music Metadata - Store in MongoDB which is on AWS EC2;

 Music file - Store in AWS S3;

** optional senario: MapReduce Crawler; Periodically Update;

    Crawler -> Music Info -> MongoDB 
            -> Music Sourcce ->  AWS S3 
            -> Music Comment -> ElasticSearch Indexing 

#### Out Flow:

    Basic CRUD -> MongoDB repository
    Basic Retrieval -> MongoDB repository
    Advanced Retrieval -> ElasticSearch Index  
    
### RESTful Service URI Design

```
	# basic CRUD: 	
			localhost:8080/music  (PUT, GET, DELETE) // check all music, put one music/ delete one music
			localhost:8080/music/{id} (GET)   //check single music 
			localhost:8080/music/{id}/addComment (POST)   // Add comment
	# Advanced Retrieval: 
			localhost:8080/music/search/{keyword}
			localhost:8080/music/search/prefix/{prefix}
			
```

## Prerequisite Environment Configuration
### MongoDB  
1. Install MongoDB
2. Start MongoDB Service
```
	mongod
```
3. Check MongoDB status and access data 
```
	mongo
	use musicSearch
	db.music.stats()
```

### Elastic Search Install

1. Install Elastic Search

2. Start Elastic Search 
```
	# windows: cd yourPath: service install
							service start
							service stop
	# Mac: cd yourPath: elasticsearch
			or brew install
```
3. Check port by localhost:9200

4. if cluster name is not "elasticsearch", please check issue #1:
	https://github.com/xmruibi/CollaborativeMusicSearch/issues/4


### Test Framework

- JUnit

