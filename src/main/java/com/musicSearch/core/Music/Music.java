package com.musicSearch.core.Music;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="music")
public class Music {
	@Id
	private String id;
	
	private String name;
	
	private Date playTime;
	
	private String sourceURL;
	
	private String artist;

}
