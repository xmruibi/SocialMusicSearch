package com.musicSearch.core.util.crawler;

import java.io.*;
import java.util.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import com.musicSearch.core.domain.Music;

public class MusicCrawler {
	String mainUrl;
	static String domain;
	static Map<String, String> genreLinks;

	List<String> links;
	List<Music> musicList;
	int pageRange;

	public MusicCrawler(String u) throws IOException {
		mainUrl = u;
		genreLinks = new HashMap<>();
		links = new ArrayList<>();
		musicList = new ArrayList<>();
	}

	public List<Music> getMusicList() {
		return musicList;
	}

	public static void setDomain(String d) {
		domain = d;
	}

	public void getGenresAndLinks() throws IOException {

		crawling(mainUrl, new DOMOperation() {

			@Override
			public void iterate(Document doc) {
				Elements tags = doc.getElementsByClass("tagList").first()
						.getElementsByTag("li");
				for (Element tag : tags) {
					Elements tmp = tag.getElementsByTag("a");
					if (tmp.size() > 0) {
						Element linkNode = tmp.first();
						genreLinks.put(linkNode.text(), linkNode.attr("href"));
					}
				}
			}

		});

	}

	public int getPageRange() throws IOException {

		crawling(mainUrl, new DOMOperation() {

			@Override
			public void iterate(Document doc) {
				Element lastPageElement = doc.select(".pagination .lastpage")
						.first();
				if (lastPageElement != null) {
					pageRange = Integer.parseInt(lastPageElement.text());
				}
			}

		});
		return pageRange;
	}

	public void crawling(String url, DOMOperation operation) throws IOException {
		Document doc = Jsoup.connect(url).get();
		System.out.println("Connection");
		operation.iterate(doc);
	}

	public void backtracking(int limit) throws IOException {

		getPageRange();
		int target = Math.min(limit, pageRange);
		String url = mainUrl + "?page=";
		for (int i = 1; i <= target; i++) {

			crawling(url + i, new DOMOperation() {

				@Override
				public void iterate(Document doc) {

					Elements titleEle = doc
							.select("#freeTracks li .trackTitle");
					for (Element e : titleEle) {
						links.add(e.attr("href"));
					}
				}

			});
		}
	}

	public void crawlMusic() throws IOException {

		for (String link : links) {
			System.out.println(link);
			crawling(domain + link, new DOMOperation() {

				@Override
				public void iterate(Document doc) {
					
					FieldGrabber grabber = new MusicFieldGrabber(doc);
					
					Music music = new Music(grabber.grabTitle(), grabber.grabSourceURL());
					music.setArtist(grabber.grabArtist());
					music.setDescription(grabber.grabDescription());
					music.setPlayCount(grabber.grabPlayCount());
					music.setOriginTags(grabber.grabOriginTags());
					musicList.add(music);
				}

			});

		}

	}

	public void backupToFile(String fileName) {
		BufferedWriter writer;
		try {

			FileOutputStream out = new FileOutputStream(fileName);
			writer = new BufferedWriter(new OutputStreamWriter(out));

			for (String link : links) {
				writer.write(link + "\n");
			}
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

interface DOMOperation {

	public void iterate(Document doc);

}
