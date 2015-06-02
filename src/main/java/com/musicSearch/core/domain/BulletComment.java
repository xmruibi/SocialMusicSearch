package com.musicSearch.core.domain;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bulletComment")
public class BulletComment {

	@Id
	private String id;

	private Date timeStamp;

	@DBRef
	private User user;

	private String content;

}
