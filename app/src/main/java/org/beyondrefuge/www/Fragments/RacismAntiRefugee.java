package org.beyondrefuge.www.Fragments;

import android.content.Intent;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Recoded Cedan on 21.02.2018.
 */

public class RacismAntiRefugee extends Fragment {
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
        final ArrayList<Video> caseStudyArray=new ArrayList<>();
        final ArrayList<Video> howToMediateArray=new ArrayList<>();

        final RecyclerView recyclerViewCaseStudy;
        final RecyclerView recyclerViewHowToMediate ;
        VideoAdapter videoAdapterCaseStudy;
        VideoAdapter videoAdapterHowToMediate;

        final View view=inflater.inflate(R.layout.racism_antirefugee,null);
        ButterKnife.bind(this,view);
        recyclerViewCaseStudy=(RecyclerView)view.findViewById(R.id.case_study_voices);

        recyclerViewHowToMediate=(RecyclerView)view.findViewById(R.id.recycler_howtomediate);


        RecyclerView.LayoutManager mlayoutManager=new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCaseStudy.setLayoutManager(mlayoutManager);
        recyclerViewHowToMediate.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCaseStudy.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCaseStudy.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.HORIZONTAL));
        
        recyclerViewHowToMediate.setItemAnimator(new DefaultItemAnimator());
        recyclerViewHowToMediate.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.HORIZONTAL));


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

                                JSONObject voicesVideo=racism.getJSONObject("voicesVideo");

                                String image= String.valueOf(voicesVideo.getString("videoImage"));

                                Picasso.with(getContext()).load(image).into(voicesImage);

                                String videoname= String.valueOf(voicesVideo.getString("videoName"));

                                voicesvideoName.setText(videoname);

                                final String url=String.valueOf(voicesVideo.getString("videoUrl"));

                                voicesImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
                                        intent.putExtra("video", url);
                                        startActivity(intent);
                                    }
                                });


                                JSONArray jsonArrayCase=racism.optJSONArray("caseStudyVideo");
                                for(int i=0; i<jsonArrayCase.length(); i++){
                                    String imageCase=jsonArrayCase.getJSONObject(i).getString("videoImage");
                                    String videoNameCase=jsonArrayCase.getJSONObject(i).getString("videoName");
                                    String videoUrlCase=jsonArrayCase.getJSONObject(i).getString("videoUrl");
                                    caseStudyArray.add(new Video(imageCase,videoNameCase,videoUrlCase));
                                    recyclerViewCaseStudy.setAdapter(new VideoAdapter(caseStudyArray,getContext()));

                                }
                                JSONArray jsonArrayHow=racism.optJSONArray("howToMediateVideo");
                                for(int j=0; j<jsonArrayHow.length(); j++){
                                    String imageHow=jsonArrayHow.getJSONObject(j).getString("videoImage");
                                    String videoNameHow=jsonArrayHow.getJSONObject(j).getString("videoName");
                                    String videoUrlHow=jsonArrayHow.getJSONObject(j).getString("videoUrl");
                                    howToMediateArray.add(new Video(imageHow,videoNameHow,videoUrlHow));
                                    recyclerViewHowToMediate.setAdapter(new VideoAdapter(howToMediateArray,getContext()));

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

