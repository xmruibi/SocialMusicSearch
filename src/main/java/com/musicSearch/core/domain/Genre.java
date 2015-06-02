package com.musicSearch.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="genre")
public class Genre {

	@Id
	private String id;

	private String content;
}
