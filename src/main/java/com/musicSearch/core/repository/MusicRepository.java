package com.musicSearch.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.musicSearch.core.domain.Music;

import java.lang.String;

/**
 * The interface for music CRUD ops
 * NOTE: extends MongoRepository interface
 * @see MongoRepository 
 * @author ruibi
 *
 */
public interface MusicRepository extends MongoRepository<Music, String> {
		
	// @Query("{'title' : {$regex : ?0}}")
	// List<Music> findByTitleRegex(String title);
	//
	// @Query("{'artist' : {$regex : ?0}}")
	// List<Music> findByArtist(String artist);
	//
	// @Query("{'releaseDate' : {$lt : ?0}}")
	// List<Music> findByReleaseDateLessThan(Date releasedate);
	//
	// @Query("{'releaseDate' : {$gt : ?0}}")
	// List<Music> findByReleaseDate(Date releasedate);
	
}