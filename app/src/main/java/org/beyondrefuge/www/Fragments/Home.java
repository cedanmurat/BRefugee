package org.beyondrefuge.www.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class Home extends Fragment {



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayList<Video> caseStudyArrayRAR=new ArrayList<>();
        RecyclerView recyclerViewCaseStudyRAR;
        VideoAdapter videoAdapterCaseStudyRAR;

        ArrayList<Video> caseStudyArrayN=new ArrayList<>();
        RecyclerView recyclerViewCaseStudyN;
        VideoAdapter videoAdapterCaseStudyN;

        ArrayList<Video> caseStudyArrayS=new ArrayList<>();
        RecyclerView recyclerViewCaseStudyS;
        VideoAdapter videoAdapterCaseStudyS;

        View view= inflater.inflate(R.layout.home,null);

        recyclerViewCaseStudyRAR=(RecyclerView)view.findViewById(R.id.m_recycler_view_rar);
        recyclerViewCaseStudyN=(RecyclerView)view.findViewById(R.id.m_recycler_view_n);
        recyclerViewCaseStudyS=(RecyclerView)view.findViewById(R.id.m_recycler_view_s);

        caseStudyArrayRAR.add(new Video(R.drawable.image1,"RacismAntiRefugee 1"));
        caseStudyArrayRAR.add(new Video(R.drawable.image2,"RacismAntiRefugee 2"));
        caseStudyArrayRAR.add(new Video(R.drawable.image3,"RacismAntiRefugee 3"));
        caseStudyArrayRAR.add(new Video(R.drawable.image4,"RacismAntiRefugee 4"));
        caseStudyArrayRAR.add(new Video(R.drawable.image5,"RacismAntiRefugee 5"));
        caseStudyArrayRAR.add(new Video(R.drawable.image1,"RacismAntiRefugee 6"));
        caseStudyArrayRAR.add(new Video(R.drawable.image2,"RacismAntiRefugee 7"));

        caseStudyArrayN.add(new Video(R.drawable.image5,"Nationalism 1"));
        caseStudyArrayN.add(new Video(R.drawable.image4,"Nationalism 2"));
        caseStudyArrayN.add(new Video(R.drawable.image3,"Nationalism 3"));
        caseStudyArrayN.add(new Video(R.drawable.image2,"Nationalism 4"));
        caseStudyArrayN.add(new Video(R.drawable.image1,"Nationalism 5"));
        caseStudyArrayN.add(new Video(R.drawable.image5,"Nationalism 6"));
        caseStudyArrayN.add(new Video(R.drawable.image4,"Nationalism 7"));

        caseStudyArrayS.add(new Video(R.drawable.image3,"Sexism 1"));
        caseStudyArrayS.add(new Video(R.drawable.image2,"Sexism 2"));
        caseStudyArrayS.add(new Video(R.drawable.image5,"Sexism 3"));
        caseStudyArrayS.add(new Video(R.drawable.image1,"Sexism 4"));
        caseStudyArrayS.add(new Video(R.drawable.image4,"Sexism 5"));
        caseStudyArrayS.add(new Video(R.drawable.image2,"Sexism 6"));
        caseStudyArrayS.add(new Video(R.drawable.image3,"Sexism 7"));

        videoAdapterCaseStudyRAR=new VideoAdapter(caseStudyArrayRAR);
        videoAdapterCaseStudyN=new VideoAdapter(caseStudyArrayN);
        videoAdapterCaseStudyS=new VideoAdapter(caseStudyArrayS);

        RecyclerView.LayoutManager mlayoutManager=new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCaseStudyRAR.setLayoutManager(mlayoutManager);
        recyclerViewCaseStudyRAR.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.HORIZONTAL));

        recyclerViewCaseStudyN.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCaseStudyN.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.HORIZONTAL));

        recyclerViewCaseStudyS.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCaseStudyS.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.HORIZONTAL));

        recyclerViewCaseStudyRAR.setAdapter(videoAdapterCaseStudyRAR);
        recyclerViewCaseStudyN.setAdapter(videoAdapterCaseStudyN);
        recyclerViewCaseStudyS.setAdapter(videoAdapterCaseStudyS);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

