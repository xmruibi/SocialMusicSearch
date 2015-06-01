package com.musicSearch.core.domain;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "music")
public class Music {
	@Id
	@Indexed(unique = true) 
	private String id;

	@Indexed 
	private String title;

	private Long playTime;

	private String sourceURL;

	private String artist;

	private Date releaseDate;

	private String description;

	private List<String> genres;

	@DBRef
	private List<BulletTag> bulletTags;

	private Long playCount;

	
	public Music(String id, String title, String sourceURL){
		this.id = id;
		this.title = title;
		this.sourceURL = sourceURL;
	}
	
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public Long getPlayTime() {
		return playTime;
	}

	public String getSourceURL() {
		return sourceURL;
	}

	public List<String> getGenres() {
		return genres;
	}

	public Long getPlayCount() {
		return playCount;
	}

	public String getArtist() {
		return artist;
	}

	public List<BulletTag> getBulletTags() {
		return bulletTags;
	}

	public String getDescription() {
		return description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPlayTime(Long playTime) {
		this.playTime = playTime;
	}

	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public void setBulletTags(List<BulletTag> bulletTags) {
		this.bulletTags = bulletTags;
	}

	public void setPlayCount(Long playCount) {
		this.playCount = playCount;
	}
}
