package org.beyondrefuge.www.Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.beyondrefuge.www.Adapter.NewsFeedListAdapter;
import org.beyondrefuge.www.Model.NewsFeedNewsModel;
import org.beyondrefuge.www.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by Recoded Cedan on 22.02.2018.
 */

public class NewsFeedNews extends Fragment  {

    private ArrayList<NewsFeedNewsModel> newsFeedNewsArrayList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayoutNews;
    private RecyclerView rc;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String myString = preferences.getString("taggedWord", "refugee");

        String q = myString;

        final View view = inflater.inflate(R.layout.news_feed_news, null);

       /* swipeRefreshLayoutNews = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh_news);

        swipeRefreshLayoutNews.setOnRefreshListener(this);*/

        final RecyclerView rc = (RecyclerView) view.findViewById(R.id.news_feed_news_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());

        rc.setLayoutManager(linearLayoutManager);

        Ion.with(this)
                .load("http://content.guardianapis.com/search?order-by=newest&show-elements=image&q="+q+"&api-key=04fd9296-dacf-4831-a5e6-8d349e8ffd4b")

                .asJsonObject()

                .setCallback(new FutureCallback<JsonObject>() {

                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {


                            try {
                                JSONObject baseJsonResponse = new JSONObject(result.toString());

                                JSONArray featureArray = baseJsonResponse.getJSONObject("response").getJSONArray("results");

                                // If there are results in the features array
                                for (int i = 0; i < featureArray.length(); i++) {

                                    String section = featureArray.getJSONObject(i).getString("sectionName");

                                    String time = featureArray.getJSONObject(i).getString("webPublicationDate");

                                    String title = featureArray.getJSONObject(i).getString("webTitle");

                                    String webUrl = featureArray.getJSONObject(i).getString("webUrl");

                                    newsFeedNewsArrayList.add(new NewsFeedNewsModel(section, title, webUrl, time, "", "", "", "",""));

                                    rc.setAdapter(new NewsFeedListAdapter(newsFeedNewsArrayList, getContext()));

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
