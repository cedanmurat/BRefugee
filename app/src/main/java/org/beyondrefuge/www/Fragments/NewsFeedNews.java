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

import org.beyondrefuge.www.Adapter.NewsFeedListAdapter;
import org.beyondrefuge.www.Model.NewsFeedNewsModel;
import org.beyondrefuge.www.R;

import java.util.ArrayList;

/**
 * Created by Recoded Cedan on 22.02.2018.
 */

public class NewsFeedNews extends Fragment {




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<NewsFeedNewsModel> newsFeedNewsArrayList=new ArrayList<>();
        newsFeedNewsArrayList.add(new NewsFeedNewsModel("Helping Refugee","A quick and simplified answer is that Lorem Ipsum refers to text that the DTP (Desktop Publishing) industry use as replacement text when the real text is not available","http://www.loremipsum.de/about_lorem_ipsum.html","01.01.2018", null,"Adam Sand", null,null));
        newsFeedNewsArrayList.add(new NewsFeedNewsModel("Helping Refugee","A quick and simplified answer is that Lorem Ipsum refers to text that the DTP (Desktop Publishing) industry use as replacement text when the real text is not available","http://www.loremipsum.de/about_lorem_ipsum.html","01.01.2018", null,"Adam Sand", null,null));
        newsFeedNewsArrayList.add(new NewsFeedNewsModel("Helping Refugee","A quick and simplified answer is that Lorem Ipsum refers to text that the DTP (Desktop Publishing) industry use as replacement text when the real text is not available","http://www.loremipsum.de/about_lorem_ipsum.html","01.01.2018", null,"Adam Sand", null,null));
        newsFeedNewsArrayList.add(new NewsFeedNewsModel("Helping Refugee","A quick and simplified answer is that Lorem Ipsum refers to text that the DTP (Desktop Publishing) industry use as replacement text when the real text is not available","http://www.loremipsum.de/about_lorem_ipsum.html","01.01.2018", null,"Adam Sand", null,null));
        newsFeedNewsArrayList.add(new NewsFeedNewsModel("Helping Refugee","A quick and simplified answer is that Lorem Ipsum refers to text that the DTP (Desktop Publishing) industry use as replacement text when the real text is not available","http://www.loremipsum.de/about_lorem_ipsum.html","01.01.2018", null,"Adam Sand", null,null));
        View view=inflater.inflate(R.layout.news_feed_news,null);
        RecyclerView rc= (RecyclerView)view.findViewById(R.id.news_feed_news_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        rc.setLayoutManager(linearLayoutManager);
        rc.setAdapter(new NewsFeedListAdapter(newsFeedNewsArrayList));

          return view;
    }


}
