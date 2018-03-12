package org.beyondrefuge.www.RelamClass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;

import org.beyondrefuge.www.Fragments.History;
import org.beyondrefuge.www.Model.HistoryItem;
import org.beyondrefuge.www.Model.NewsFeedNewsModel;
import org.beyondrefuge.www.R;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by dell on 3/7/2018.
 */

public class RealmAdapter extends ArrayAdapter<HistoryItem> {

    List<HistoryItem> list;
    Context context;

    public RealmAdapter(List<HistoryItem> list, Context context) {
        super(context, 0, list);
        this.list = list;
        this.context = context;
    }


    public int getCount() {
        return list.size();
    }

    public HistoryItem getItem(int position) {
        return list.get(position);
    }


    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(context).inflate(R.layout.hamidlayout,parent,false);
        TextView isim=convertView.findViewById(R.id.kullaniciAdiText);
        TextView sifre=convertView.findViewById(R.id.kullaniciSifre);
        isim.setText(list.get(position).getTitle().toString());
        sifre.setText(list.get(position).getUrl());
        return convertView;
    }
}
