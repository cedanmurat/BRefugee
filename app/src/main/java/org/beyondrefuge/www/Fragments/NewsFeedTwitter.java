package org.beyondrefuge.www.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Recoded Cedan on 22.02.2018.
 */

public class NewsFeedTwitter extends Fragment {
    private ArrayList<NewsFeedNewsModel> arrayList = new ArrayList<>();
    private RecyclerView rc;
    private TextView noTweets;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_feed_twitter, null);
        noTweets=(TextView)view.findViewById(R.id.no_tweet);
        noTweets.setVisibility(View.GONE);
        rc = (RecyclerView) view.findViewById(R.id.news_feed_twitter_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        rc.setLayoutManager(linearLayoutManager);

        getCredentials();

        return view;
    }

    Future<JsonObject> loading;
    private String accessToken;

    private void getCredentials() {
        Ion.with(this)
                .load("https://api.twitter.com/oauth2/token")
                // embedding twitter api key and secret is a bad idea, but this isn't a real twitter app :)
                .basicAuthentication("keGxkx0KZISHROjJNHDyUySNM", "alIx5DK4e1cE5sqr2WPkiaUf1Ory9XDu8QRYesWmTZnOMrCi7r")
                .setBodyParameter("grant_type", "client_credentials")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {

                            return;
                        }
                        accessToken = result.get("access_token").getAsString();
                        load();
                    }
                });
    }

    private void load() {
        if (loading != null && !loading.isDone() && !loading.isCancelled())
            return;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String myString = preferences.getString("taggedWord", "refugee");

        String q = myString;


        int count = 100;

        loading = Ion.with(this)
                .load("https://api.twitter.com/1.1/search/tweets.json?q=" + q + "&result_type=mixed&count=100")
                .setHeader("Authorization", "Bearer " + accessToken)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e != null) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {

                            try {
                                JSONObject object = new JSONObject(result.getAsJsonObject().toString());
                                JSONArray array = object.getJSONArray("statuses");

                                for (int i = 0; i < array.length(); i++) {

                                    String tweet = array.getJSONObject(i).getString("text");
                                    String userName = array.getJSONObject(i).getJSONObject("user").getString("name");
                                    String url = array.getJSONObject(i).getJSONObject("entities").getJSONArray("urls").getJSONObject(0).optString("url");
                                    String imageUrl = array.getJSONObject(i).getJSONObject("user").getString("profile_image_url");
                                    String title=array.getJSONObject(i).getJSONObject("user").getString("screen_name");
                                    String time=array.getJSONObject(i).getString("created_at");
                                    String location= array.getJSONObject(i).getJSONObject("user").getString("location");
                                    int retweet=array.getJSONObject(i).getInt("retweet_count");
                                    int favorite=array.getJSONObject(i).getInt("favorite_count");
                                    int likeNumb=(retweet+favorite);
                                    String likeNumber=""+likeNumb;

                                    arrayList.add(new NewsFeedNewsModel(title, tweet, url, time, "", userName, imageUrl, location,likeNumber));
                                    rc.setAdapter(new NewsFeedListAdapter(arrayList, getContext()));
                                    if(arrayList.isEmpty()){
                                        noTweets.setVisibility(View.VISIBLE);
                                    }

                                }

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }

                        }
                    }
                });
    }
}
