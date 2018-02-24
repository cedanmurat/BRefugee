package org.beyondrefuge.www.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;


import org.beyondrefuge.www.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsFeed extends Fragment implements View.OnClickListener{

    @BindView(R.id.news_feed_news_button) Button newsFeedNewsButton;
    @BindView(R.id.news_feed_twitter_button) Button newsTwitterNewsButton;
    @BindView(R.id.news_feed_popular_button) Button newsPopularNewsButton;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.newsfeed, null);
        ButterKnife.bind(this,view);
        newsFeedNewsButton.setOnClickListener(this);
        newsTwitterNewsButton.setOnClickListener(this);
        newsPopularNewsButton.setOnClickListener(this);
        displayMenu(1);
        return view ;

    }

    private void displayMenu(int position) {
        Fragment fragment = null;
        switch (position) {
            case 1:
                fragment = new NewsFeedNews();
                break;
            case 2:
                fragment = new NewsFeedTwitter();
                break;
            case 3:
                fragment = new NewsFeedPopular();
                break;
                 }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_news, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View v) {
        if(v==newsFeedNewsButton){
            displayMenu(1);
        }
        if(v==newsTwitterNewsButton){
            displayMenu(2);
        }
        if(v==newsPopularNewsButton){
            displayMenu(3);
        }
    }
}

