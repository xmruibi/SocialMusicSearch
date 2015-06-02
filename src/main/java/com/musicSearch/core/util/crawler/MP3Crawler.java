package com.musicSearch.core.util.crawler;


import java.io.IOException;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;



public class MP3Crawler {
    String mainUrl;
    String domain;
    Map<String, String> genreLinks;
    public MP3Crawler(String d, String u) throws IOException {
        mainUrl = u;
        domain = d;
        genreLinks = new HashMap<>();
        getGenresAndLinks();
        
        System.out.println(genreLinks);
        
    }
    
    public void getGenresAndLinks() throws IOException {
        
        crawling(mainUrl, new DOMOperation(){
            
            @Override
            public void iterate(Document doc) {
                Elements tags = doc.getElementsByClass("tagList").first().getElementsByTag("li");
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
    
    
    public void crawling(String url, DOMOperation operation) throws IOException {
        Document doc = Jsoup.connect(url).get();
        System.out.println("Connection");
        operation.iterate(doc);
    }
    
}


interface DOMOperation {

    public void iterate(Document doc);


}
