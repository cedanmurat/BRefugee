package org.beyondrefuge.www.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.beyondrefuge.www.Adapter.NewsFeedListAdapter;
import org.beyondrefuge.www.Model.NewsFeedNewsModel;
import org.beyondrefuge.www.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Recoded Cedan on 22.02.2018.
 */

public class NewsFeedPopular extends Fragment {
    private ArrayList<NewsFeedNewsModel> arrayList = new ArrayList<>();
    private RecyclerView rc;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String q = "refugee";
        View view=inflater.inflate(R.layout.news_feed_popular,null);
        rc = (RecyclerView) view.findViewById(R.id.news_feed_popular_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        rc.setLayoutManager(linearLayoutManager);
        Ion.with(this)
                .load("https://newsapi.org/v2/everything?q="+q+"&sortBy=popularity&apiKey=828bd4aaa4dc4829b3f1aa77855e29c3")

                .asJsonObject()

                .setCallback(new FutureCallback<JsonObject>() {

                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {


                            try {
                                JSONObject object = new JSONObject(result.getAsJsonObject().toString());
                                JSONArray array = object.getJSONArray("articles");

                                for (int i = 0; i < array.length(); i++) {

                                    String title = array.getJSONObject(i).getString("title");

                                    String author=array.getJSONObject(i).getString("author");

                                    String time = array.getJSONObject(i).getString("publishedAt");

                                    String description = array.getJSONObject(i).getString("description");

                                    String webUrl = array.getJSONObject(i).getString("url");

                                    String image=array.getJSONObject(i).getString("urlToImage");

                                    arrayList.add(new NewsFeedNewsModel(title, description, webUrl, time, "", author, image, "",""));

                                    rc.setAdapter(new NewsFeedListAdapter(arrayList, getContext()));

                                }


                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }

                        }
                    }

                });
        return view;
    }

}
