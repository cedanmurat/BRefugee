package org.beyondrefuge.www.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import org.beyondrefuge.www.Login;
import org.beyondrefuge.www.MainActivity;
import org.beyondrefuge.www.Model.News;
import org.beyondrefuge.www.Model.Video;
import org.beyondrefuge.www.R;
import org.beyondrefuge.www.VideoPlayerActivity;

import java.util.List;

/**
 * Created by SEYMEN on 23.02.2018.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {


    private List<Video> videoList;

    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView recyclerHeadLine;
        public ImageView recyclerImageMain;




        public MyViewHolder(View view) {

            super(view);

            recyclerHeadLine = (TextView) view.findViewById(R.id.text_headline);
            recyclerImageMain = (ImageView) view.findViewById(R.id.main_image_item);


        }

    }


    public VideoAdapter(List<Video> videoList, Context context) {

        this.videoList = videoList;
        this.context=context;
    }


    @Override

    public VideoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.horizontal2_item, parent, false);


        return new VideoAdapter.MyViewHolder(itemView);

    }






    @Override

    public void onBindViewHolder(VideoAdapter.MyViewHolder holder, int position) {

        final Video video = videoList.get(position);

        holder.recyclerHeadLine.setText(video.getTextHeadLine());

        Picasso.with(context).load(video.getImageMain()).into(holder.recyclerImageMain);



        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                Context context=view.getContext();

                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("video", video.getVideoUrl());
                view.getContext().startActivity(intent);

            }

        });

    }


        @Override
        public int getItemCount() {
            return videoList.size();

        }

    }
