package org.beyondrefuge.www.Model;

/**
 * Created by Recoded Cedan on 23.02.2018.
 */

public class NewsFeedNewsModel {

    private String title;
    private String news;
    private String url;
    private String time;
    private String newsImage;
    private String author;
    private String authorImage;
    private String location;

    public NewsFeedNewsModel(String title, String news, String url, String time, String newsImage, String author, String authorImage, String location) {
        this.title = title;
        this.news = news;
        this.url = url;
        this.time = time;
        this.newsImage = newsImage;
        this.author = author;
        this.authorImage = authorImage;
        this.location = location;
    }

    public NewsFeedNewsModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
