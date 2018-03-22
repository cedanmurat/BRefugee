package org.beyondrefuge.www.Fragments;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.beyondrefuge.www.Adapter.VideoAdapter;
import org.beyondrefuge.www.Model.Video;
import org.beyondrefuge.www.R;
import org.beyondrefuge.www.VideoPlayerActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Recoded Cedan on 21.02.2018.
 */

public class Home extends Fragment {

    @BindView(R.id.voices_image_r)
    ImageView voicesImage;
    @BindView(R.id.nameVideo)
    TextView voicesvideoName;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ArrayList<Video> caseStudyArrayRAR=new ArrayList<>();
        final ArrayList<Video> howToMediaArrayRAR=new ArrayList<>();
        final RecyclerView recyclerViewCaseStudyRAR;
        VideoAdapter videoAdapterCaseStudyRAR;
        VideoAdapter videoAdapterHowToMediaRAR;

        final ArrayList<Video> caseStudyArrayN=new ArrayList<>();
        final RecyclerView recyclerViewCaseStudyN;
        VideoAdapter videoAdapterCaseStudyN;

        final ArrayList<Video> caseStudyArrayS=new ArrayList<>();
        final RecyclerView recyclerViewCaseStudyS;
        VideoAdapter videoAdapterCaseStudyS;

        View view= inflater.inflate(R.layout.home,null);

        recyclerViewCaseStudyRAR=(RecyclerView)view.findViewById(R.id.m_recycler_view_rar);
        recyclerViewCaseStudyN=(RecyclerView)view.findViewById(R.id.m_recycler_view_n);
        recyclerViewCaseStudyS=(RecyclerView)view.findViewById(R.id.m_recycler_view_s);


        Ion.with(this)
                .load("https://api.myjson.com/bins/1d5hap")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e!=null){
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }else{
                            try {

                                JSONObject baseJsonResponse = new JSONObject(result.toString());

                                JSONObject racism=baseJsonResponse.getJSONObject("racism");

                                JSONArray jsonArrayCaseRacism=racism.optJSONArray("caseStudyVideo");
                                for(int i=0; i<jsonArrayCaseRacism.length(); i++){
                                    String imageCase=jsonArrayCaseRacism.getJSONObject(i).getString("videoImage");
                                    String videoNameCase=jsonArrayCaseRacism.getJSONObject(i).getString("videoName");
                                    String videoUrlCase=jsonArrayCaseRacism.getJSONObject(i).getString("videoUrl");
                                    caseStudyArrayRAR.add(new Video(imageCase,videoNameCase,videoUrlCase));
                                    recyclerViewCaseStudyRAR.setAdapter(new VideoAdapter(caseStudyArrayRAR,getContext()));

                                }


                                JSONObject nationalism=baseJsonResponse.getJSONObject("nationalism");

                                JSONArray jsonArrayCaseNationalism=nationalism.optJSONArray("caseStudyVideo");
                                for(int i=0; i<jsonArrayCaseNationalism.length(); i++){
                                    String imageCase=jsonArrayCaseNationalism.getJSONObject(i).getString("videoImage");
                                    String videoNameCase=jsonArrayCaseNationalism.getJSONObject(i).getString("videoName");
                                    String videoUrlCase=jsonArrayCaseNationalism.getJSONObject(i).getString("videoUrl");
                                    caseStudyArrayN.add(new Video(imageCase,videoNameCase,videoUrlCase));
                                    recyclerViewCaseStudyN.setAdapter(new VideoAdapter(caseStudyArrayN,getContext()));

                                }

                                JSONObject sexism=baseJsonResponse.getJSONObject("sexism");


                                JSONArray jsonArrayCase=sexism.optJSONArray("caseStudyVideo");
                                for(int i=0; i<jsonArrayCase.length(); i++){
                                    String imageCase=jsonArrayCase.getJSONObject(i).getString("videoImage");
                                    String videoNameCase=jsonArrayCase.getJSONObject(i).getString("videoName");
                                    String videoUrlCase=jsonArrayCase.getJSONObject(i).getString("videoUrl");
                                    caseStudyArrayS.add(new Video(imageCase,videoNameCase,videoUrlCase));
                                    recyclerViewCaseStudyS.setAdapter(new VideoAdapter(caseStudyArrayS,getContext()));

                                }



                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }


                        }
                    }
                });


        RecyclerView.LayoutManager mlayoutManager=new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCaseStudyRAR.setLayoutManager(mlayoutManager);
        recyclerViewCaseStudyRAR.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.HORIZONTAL));

        recyclerViewCaseStudyN.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCaseStudyN.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.HORIZONTAL));

        recyclerViewCaseStudyS.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCaseStudyS.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.HORIZONTAL));


        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

