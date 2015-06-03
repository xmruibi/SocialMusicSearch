package com.musicSearch.core.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "music")
public class Music {
	@Id
	private String id;

	@Indexed
	private String title;

	private Long playTime;

	private String genURL;

	private String sourceURL;

	private String artist;

	private Date releaseDate;

	private String description;

	@DBRef
	private List<Genre> genres;

	@DBRef
	private List<BulletComment> bulletComments;

	
	private List<String> originTags;
	
	private Long playCount;

	// public Music(String id, String title, String sourceURL){
	// this.id = id;
	// this.title = title;
	// this.sourceURL = sourceURL;
	// }

	public Music(String title, String sourceURL) {
		this.id = sourceURL.hashCode() + "";
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

	public List<Genre> getGenres() {
		return genres;
	}

	public Long getPlayCount() {
		return playCount;
	}

	public String getArtist() {
		return artist;
	}

	public List<BulletComment> getBulletComments() {
		return bulletComments;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getOriginTags() {
		return originTags;
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

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public void setBulletComments(List<BulletComment> bulletComments) {
		this.bulletComments = bulletComments;
	}

	public void setPlayCount(Long playCount) {
		this.playCount = playCount;
	}

	public String toString() {
		return id + " -> " + title + "(" + sourceURL + ")";
	}

	public String getGenURL() {
		return genURL;
	}

	public void setGenURL(String genURL) {
		this.genURL = genURL;
	}
	
	public void setOriginTags(List<String> originTags) {
		this.originTags = originTags;
	}
}
