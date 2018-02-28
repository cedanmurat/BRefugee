package org.beyondrefuge.www.Model;

import android.text.TextUtils;

import java.util.Date;

/**
 * Created by Recoded Cedan on 23.02.2018.
 */

public class NewsFeedNewsModel {

    private long id=new Date().getTime();
    private String title;
    private String news;
    private String url;
    private String time;
    private String newsImage;
    private String author;
    private String authorImage;
    private String location;
    private String likeNumber;

    public NewsFeedNewsModel( String title, String news, String url, String time, String newsImage, String author, String authorImage, String location, String likeNumber) {
        this.id = id;
        this.title = title;
        this.news = news;
        this.url = url;
        this.time = time;
        this.newsImage = newsImage;
        this.author = author;
        this.authorImage = authorImage;
        this.location = location;
        this.likeNumber = likeNumber;
    }

    public NewsFeedNewsModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        if (TextUtils.isEmpty(authorImage)) {
            return "https://cdn.instructables.com/FNZ/G0YE/H1426KZP/FNZG0YEH1426KZP.LARGE.jpg";
        } else {
            return authorImage;
        }

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

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }
}
