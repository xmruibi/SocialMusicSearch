
# CollaborativeMusicSearch

### Brief Intro
Although the text information retrieval is very mature in current world, the media files are still hard to be annotated and retrieved. The problem is lacking of the sementic analysis on this media file. Here, we come up with an innovative thinking on music search engine with collaborative dynamic bullet comments techniques. The bullet comment is a kind of comment added on each frame of the media file. Those bullet comments releases user's sentimental infomation on a certain song or song's segment. According to these comment, we not only can set up profile on each song, but also on those song's specific segment. 

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

	mongod

3. Check MongoDB status and access data 

	mongo
	use musicSearch
	db.music.stats()


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

4. if cluster name is not "elasticsearch", please check issue #1
	[https://github.com/xmruibi/CollaborativeMusicSearch/issues/4]

### Iteration #1 -- May 27th - Jun 4th, 2015

#### Data Crawler - source come from last.fm

Music Metadata - Store in MongoDB which is on AWS EC2;

Music .mp3 file - Store in AWS S3;

** optional senario: MapReduce Crawler; Periodically Update; 


#### Framework Set Up:

Spring Boot - Web Service;

Spring-Data-MongoDB repository, domain;

Spring-Data-REST service, controller;

Spring-Data-ElasticSearch indexedrepository, index.domain

### Dataflow:

In: 

    Crawler -> Music Info -> MongoDB 
            -> Music Sourcce ->  AWS S3 
            -> Music Comment -> ElasticSearch Indexing 

Out:

    Basic CRUD -> MongoDB repository
    Basic Retrieval -> MongoDB repository
    Advanced Retrieval -> ElasticSearch Index -> TFIDF ranking/customized ranking algorithms -> Music ID -> Search in MongoDB 
    



#### Test Framework

- JUnit






#####The basic idea comes from @vincentlau0493, who also is the chief contributor. 
