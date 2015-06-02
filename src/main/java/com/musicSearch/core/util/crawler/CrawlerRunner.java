package com.musicSearch.core.util.crawler;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrawlerRunner {
    
    public static void main(String[] args) {
    
        try {
            MP3Crawler crawler = new MP3Crawler("http://cn.last.fm", "http://cn.last.fm/music/+free-music-downloads");
        } catch (IOException ex) {
//            Logger.getLogger(CrawlerRunner.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    
    }
    
}
