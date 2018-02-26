package org.beyondrefuge.www.Model;

import java.util.Date;

/**
 * Created by SEYMEN on 23.02.2018.
 */

public class Video {
    private long id=new Date().getTime();
    private int imageMain;
    private  String textHeadLine;

    public Video(int imageMain, String textHeadLine) {
        this.id = id;
        this.imageMain = imageMain;
        this.textHeadLine = textHeadLine;
    }

    public Video() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImageMain() {
        return imageMain;
    }

    public void setImageMain(int imageMain) {
        this.imageMain = imageMain;
    }

    public String getTextHeadLine() {
        return textHeadLine;
    }

    public void setTextHeadLine(String textHeadLine) {
        this.textHeadLine = textHeadLine;
    }
}
