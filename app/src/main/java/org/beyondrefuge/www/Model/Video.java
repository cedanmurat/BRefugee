package org.beyondrefuge.www.Model;

import java.util.Date;

/**
 * Created by SEYMEN on 23.02.2018.
 */

public class Video {
    private long id=new Date().getTime();
    private String imageMain;
    private  String textHeadLine;
    private String videoUrl;

    public Video(String imageMain, String textHeadLine, String videoUrl) {
        this.imageMain = imageMain;
        this.textHeadLine = textHeadLine;
        this.videoUrl = videoUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageMain() {
        return imageMain;
    }

    public void setImageMain(String imageMain) {
        this.imageMain = imageMain;
    }

    public String getTextHeadLine() {
        return textHeadLine;
    }

    public void setTextHeadLine(String textHeadLine) {
        this.textHeadLine = textHeadLine;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
