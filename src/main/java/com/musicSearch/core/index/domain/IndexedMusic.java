package com.musicSearch.core.index.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = IndexedMusic.INDEX_NAME, type = IndexedMusic.TYPE_NAME)
public class IndexedMusic implements Serializable {

	private static final long serialVersionUID = -353918577072086923L;
	public static final String INDEX_NAME = "musics";
	public static final String TYPE_NAME = "music";

	@Id
	private String id;

	private String commentContent;

	public IndexedMusic(String id, String commentContent) {
		this.id = id;
		this.commentContent = commentContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
}
