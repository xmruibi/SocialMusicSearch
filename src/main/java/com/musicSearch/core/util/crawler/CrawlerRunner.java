package com.musicSearch.core.util.crawler;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrawlerRunner {
    
    public static void main(String[] args) {
    
        try {
        	MusicCrawler.setDomain("http://www.last.fm");
            MusicCrawler crawler = new MusicCrawler("http://www.last.fm/music/+free-music-downloads");
            crawler.backtracking(1);
//            crawler.backupToFile("links.txt");
            crawler.crawlMusic();
            System.out.println(crawler.musicList);
        } catch (IOException ex) {
//            Logger.getLogger(CrawlerRunner.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    
    }
    
}
