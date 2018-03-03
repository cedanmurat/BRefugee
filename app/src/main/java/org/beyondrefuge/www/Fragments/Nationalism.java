package org.beyondrefuge.www.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.beyondrefuge.www.Adapter.VideoAdapter;
import org.beyondrefuge.www.Model.Video;
import org.beyondrefuge.www.R;

import java.util.ArrayList;

/**
 * Created by Recoded Cedan on 21.02.2018.
 */

public class Nationalism extends Fragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayList<Video> caseStudyArray=new ArrayList<>();
        ArrayList<Video> howToMediateArray=new ArrayList<>();

        RecyclerView recyclerViewCaseStudy;
        RecyclerView recyclerViewHowToMediate ;
        VideoAdapter videoAdapterCaseStudy;
        VideoAdapter videoAdapterHowToMediate;

        View view=inflater.inflate(R.layout.nationalism,null);

        recyclerViewCaseStudy=(RecyclerView)view.findViewById(R.id.case_study_voices_n);
        caseStudyArray.add(new Video(R.drawable.refugevideoimage,"Case Study Video 1"));
        caseStudyArray.add(new Video(R.drawable.refugevideoimage,"Case Study Video 2"));
        caseStudyArray.add(new Video(R.drawable.refugevideoimage,"Case Study Video 3"));
        caseStudyArray.add(new Video(R.drawable.refugevideoimage,"Case Study Video 4"));
        caseStudyArray.add(new Video(R.drawable.refugevideoimage,"Case Study Video 5"));
        caseStudyArray.add(new Video(R.drawable.refugevideoimage,"Case Study Video 6"));
        caseStudyArray.add(new Video(R.drawable.refugevideoimage,"Case Study Video 7"));
        recyclerViewHowToMediate=(RecyclerView)view.findViewById(R.id.recycler_howtomediate_n);
        howToMediateArray.add(new Video(R.drawable.refugevideoimage,"How To Mediate Video 1"));
        howToMediateArray.add(new Video(R.drawable.refugevideoimage,"How To Mediate Video 2"));
        howToMediateArray.add(new Video(R.drawable.refugevideoimage,"How To MediateVideo 3"));
        howToMediateArray.add(new Video(R.drawable.refugevideoimage,"How To Mediate Video 4"));
        howToMediateArray.add(new Video(R.drawable.refugevideoimage,"How To Mediate Video 5"));
        videoAdapterCaseStudy=new VideoAdapter(caseStudyArray);
        videoAdapterHowToMediate=new VideoAdapter(howToMediateArray);
        RecyclerView.LayoutManager mlayoutManager=new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCaseStudy.setLayoutManager(mlayoutManager);
        recyclerViewHowToMediate.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCaseStudy.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCaseStudy.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.HORIZONTAL));

        recyclerViewHowToMediate.setItemAnimator(new DefaultItemAnimator());
        recyclerViewHowToMediate.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.HORIZONTAL));

        recyclerViewCaseStudy.setAdapter(videoAdapterCaseStudy);
        recyclerViewHowToMediate.setAdapter(videoAdapterHowToMediate);

        return view;
    }
}

