package com.musicSearch.core.domain;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.codehaus.jackson.*;
import org.codehaus.jackson.annotate.JsonProperty;

@Document(collection = "bulletComment")
public class BulletComment {

	@Id
	private String id;

	private String timeStamp;

	@DBRef
	private User user;

	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {	
		this.timeStamp = timeStamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BulletComment() {
		this.id = UUID.randomUUID().toString();
	}

	public BulletComment(String timeStamp, User user, String content) {
		this.id = UUID.randomUUID().toString();
		this.timeStamp = timeStamp;
		this.user = user;
		this.content = content;
	}

	@Override
	public String toString() {
		return this.id + " " + this.timeStamp + " " + this.user + " "
				+ this.content;
	}
}
