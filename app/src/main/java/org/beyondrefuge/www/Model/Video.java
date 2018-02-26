package org.beyondrefuge.www.Model;

/**
 * Created by SEYMEN on 23.02.2018.
 */

public class Video {
    private int imageMain;
    private  String textHeadLine;

    public Video(int imageMain,  String textHeadLine) {
        this.imageMain = imageMain;
        this.textHeadLine = textHeadLine;
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
