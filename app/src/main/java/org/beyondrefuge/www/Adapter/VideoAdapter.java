package org.beyondrefuge.www.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.beyondrefuge.www.Model.News;
import org.beyondrefuge.www.Model.Video;
import org.beyondrefuge.www.R;

import java.util.List;

/**
 * Created by SEYMEN on 23.02.2018.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {


    private List<Video> videoList;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView recyclerHeadLine;
        public ImageView recyclerImageMain;



        public MyViewHolder(View view) {

            super(view);

            recyclerHeadLine = (TextView) view.findViewById(R.id.text_headline);
            recyclerImageMain = (ImageView) view.findViewById(R.id.main_image_item);


        }

    }


    public VideoAdapter(List<Video> videoList) {

        this.videoList = videoList;

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
        holder.recyclerImageMain.setImageResource(video.getImageMain());


       /* holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                Uri uri = Uri.parse(video.getUrl());

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                view.getContext().startActivity(intent);



            }

        });*/

    }


        @Override
        public int getItemCount() {
            return videoList.size();

        }

    }
