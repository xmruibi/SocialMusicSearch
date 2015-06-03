# CollaborativeMusicSearch
This is innovative thinking on music search engine with collaborative dynamic bullet comments techniques. The basic idea is using bullet comments to generate music profile. According to these profiles, we can build better ranking during search with some retrieval algorithms. 

### Potential Techniques
Based on Spring-Boot App with Spring-Data-MongoDB&ElasticSearch&RESTful Service. 

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
