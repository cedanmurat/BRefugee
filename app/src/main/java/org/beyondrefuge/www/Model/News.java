package org.beyondrefuge.www.Model;

/**
 * Created by Recoded Cedan on 22.02.2018.
 */

public class News {
    private String author;

    private String headline;

    private String date;

    private String news;

    private String url;

    private int image;

    public News(String author, String headline, String date, String news, String url, int image) {
        this.author = author;
        this.headline = headline;
        this.date = date;
        this.news = news;
        this.url = url;
        this.image = image;
    }

    public News() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
