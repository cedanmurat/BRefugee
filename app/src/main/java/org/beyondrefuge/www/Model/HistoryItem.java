package org.beyondrefuge.www.Model;

import android.content.Context;

import java.util.List;

import io.realm.RealmObject;
import io.realm.Realm;
/**
 * Created by dell on 3/7/2018.
 */

public class HistoryItem extends RealmObject {
    public String title;
    public String url;

    public HistoryItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public HistoryItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
