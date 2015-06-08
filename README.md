
# SocialMusicSearch

### Brief Intro
Although the text information retrieval is very mature in current world, the media files are still hard to be annotated and retrieved. The problem is media files are lacking of the sementic sentimental elements. Here, we come up with an innovative thinking on music search engine with live comments (bullet comments) techniques. The idea of live comment is derives from Janpanses Video site - Niconico.com. Users can upload, view and share video clips. Unlike other video sharing sites, however, comments are overlaid directly onto the video, synced to a specific playback time. In this project, we use live comment is a kind of comment added on each frame of the music file. Those bullet comments releases user's sentimental infomation on a certain song or song's segment. According to these comments, we not only can set up profile on each song, but also on those song's specific segment. 

### Techniques
#### Front End
Angular.js

#### Web Service:
Spring-Boot App,
Spring-REST API

#### Non-sql Database 
MongoDB,
Spring-data-Mongodb API

#### Index Service
ElasticSearch,
Spring-data-elasticsearch API

#### Static Music Resource Storage
AWS S3


### Prerequisite Environment Configuration
#### MongoDB  
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

#### Elastic Search Install

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


### Framework & Package Design:

##### Spring Boot App - 
Maven plugin: bootstrap without web.xml;

Bootstrap Main function: MusicSearchApplication;

Spring Configuration - config package: ApplicationConfig, WebMVCConfig, ApplicationInitializer;

##### Spring-Data-MongoDB 
config: MongoDBConfig;

repository;

domain;

##### Spring-Data-REST
config: ApplicationConfig;

service;

controller;

##### Spring-Data-ElasticSearch 
config: ElasticSearchConfig;

index.repository;

index.domain;


### Dataflow Design:
#### In Flow: 
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
    



### Test Framework

- JUnit






#####The basic idea comes from @vincentlau0493, who also is the chief contributor. 
