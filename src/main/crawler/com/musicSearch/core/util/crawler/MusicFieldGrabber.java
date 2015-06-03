package com.musicSearch.core.util.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.*;

public class MusicFieldGrabber implements FieldGrabber{
	
	private Document doc;
	
	public MusicFieldGrabber(Document doc) {
		this.doc = doc;
	}
	
	@Override
	public String grabTitle() {
		Element titleEle = doc.select(
				".page-head h1 span:first-child").first();
		String title = titleEle.text();
		return title;
	}

	@Override
	public String grabPlayTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String grabSourceURL() {
		Element sourceEle = doc.select(
				".ecommerce-actions a.ecommerce-btn-text").first();
		String source = sourceEle.attr("href");
		return source;
	}

	@Override
	public String grabArtist() {
		Element artistEle = doc.select(
				".page-head .top-crumb a").first();
		String artist = artistEle.text();
//		print(artist);
		return artist;
	}

	@Override
	public String grabReleaseDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String grabDescription() {
		String description = "";
		
		Element desEle = doc.select(
				".media-body .wiki #wikiAbstract .wiki-text").first();
		if (desEle == null) return description;
		description = desEle.text();
//		print(description);
		return description;
	}

	@Override
	public Long grabPlayCount() {
		Element countEle = doc.getElementById("listenerCount");
		long count = Long.parseLong(countEle.attr("data-count"));
//		print(count);
		return count;
	}

	@Override
	public List<String> grabOriginTags() {
		List<String> tags = new ArrayList<>();
		Element tagEle = doc.select("section.global-tags meta").first();
		String plainTags = tagEle == null ? "" : tagEle.attr("content");
		for (String t : plainTags.split(", ")) {
			tags.add(t);
		}
		print(tags);
		return tags;
	}
	
	public void print(Object o) {
		System.out.println(o);
	}
}
