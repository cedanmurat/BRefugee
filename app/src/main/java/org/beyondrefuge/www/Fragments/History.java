package org.beyondrefuge.www.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.AlertDialog;
import org.beyondrefuge.www.MainActivity;
import org.beyondrefuge.www.Model.HistoryItem;
import org.beyondrefuge.www.Model.NewsFeedNewsModel;
import org.beyondrefuge.www.R;
import org.beyondrefuge.www.RelamClass.RealmAdapter;

import java.net.URL;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Recoded Cedan on 21.02.2018.
 */

public class History extends Fragment {

    private  List<NewsFeedNewsModel> newsList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.history,null);
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        ListView listview = (ListView)view.findViewById(R.id.listview);
        final RealmResults<HistoryItem> query = realm.where(HistoryItem.class).findAll();
        if(query.size()>0){
            final RealmAdapter realmAdapter=new RealmAdapter(query,getContext());
            listview.setAdapter(realmAdapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Uri url = Uri.parse(query.get(position).getUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW,url);
                    view.getContext().startActivity(intent);
                }
            });
        }


       // Log.d("asdasd",query.size()+"");

      return view;
    }



}

