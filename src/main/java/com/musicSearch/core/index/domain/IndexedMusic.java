package com.musicSearch.core.index.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.musicSearch.core.domain.BulletComment;

/**
 * This music object for indexing
 * @author ruibi
 *
 */
@Document(indexName = IndexedMusic.INDEX_NAME, type = IndexedMusic.TYPE_NAME)
public class IndexedMusic implements Serializable {

	private static final long serialVersionUID = -353918577072086923L;
	public static final String INDEX_NAME = "musics";
	public static final String TYPE_NAME = "music";

	@Id
	private String id;

	private String title;

	private String artist;

	private String description;

	private List<String> commentContents;

	private List<BulletComment> bulletComments;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(List<String> commentContents) {
		this.commentContents = commentContents;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getCommentContent() {
		return commentContents;
	}

	public void setCommentContent(List<String> commentContent) {
		this.commentContents = commentContent;
	}

	public List<BulletComment> getBulletComments() {
		return bulletComments;
	}

	public void setBulletComments(List<BulletComment> bulletComments) {
		this.bulletComments = bulletComments;
	}

	public IndexedMusic(String id, String title, String artist,
			String description, List<String> commentContents,
			List<BulletComment> bulletComments) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.description = description;
		this.commentContents = commentContents;
		this.bulletComments = bulletComments;
	}

}
