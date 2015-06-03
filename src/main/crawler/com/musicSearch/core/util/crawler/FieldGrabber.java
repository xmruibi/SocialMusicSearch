package com.musicSearch.core.util.crawler;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.musicSearch.core.domain.BulletComment;
import com.musicSearch.core.domain.Genre;

public interface FieldGrabber {
	
	public String grabTitle();
	
	public String grabPlayTime();
	
	public String grabSourceURL();
	
	public String grabArtist();
	
	public String grabReleaseDate();
	
	public String grabDescription();
	
	public Long grabPlayCount();
	
	public List<String> grabOriginTags();

}
