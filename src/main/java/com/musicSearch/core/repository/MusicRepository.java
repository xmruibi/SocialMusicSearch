package com.musicSearch.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.musicSearch.core.domain.BulletTag;
import com.musicSearch.core.domain.Music;

import java.lang.String;
import java.util.List;
import java.sql.Date;

public interface MusicRepository extends MongoRepository<Music, String> {


	@Query("{'title' : {$regex : ?0}}")
	List<Music> findByTitleRegex(String title);
	
	
	@Query("{'artist' : {$regex : ?0}}")
	List<Music> findByArtist(String artist);
	
	@Query("{''}")
	List<Music> findByBulletTags(String bullettagName);
	
	@Query("{'releaseDate' : {$lt : ?0}}")
	List<Music> findByReleaseDateLessThan(Date releasedate);
	
	@Query("{'releaseDate' : {$gt : ?0}}")
	List<Music> findByReleaseDate(Date releasedate);
}
