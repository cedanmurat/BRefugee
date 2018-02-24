package org.beyondrefuge.www.Adapter;





import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.beyondrefuge.www.Model.NewsFeedNewsModel;
import org.beyondrefuge.www.R;

import java.util.List;

/**
 * Created by Recoded Cedan on 23.02.2018.
 */

public class NewsFeedListAdapter extends RecyclerView.Adapter<NewsFeedListAdapter.MyViewHolder> {

    private List<NewsFeedNewsModel> newsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView newsTitle, newsNews, newsLocation, newsTime, newsAuthorName;

        public ImageView authorImage;


        public MyViewHolder(View view) {

            super(view);

            newsTitle = (TextView) view.findViewById(R.id.news_title);

            newsNews = (TextView) view.findViewById(R.id.news_text_news);

            newsLocation = (TextView) view.findViewById(R.id.news_location);

            newsTime = (TextView) view.findViewById(R.id.news_time);

            newsAuthorName = (TextView) view.findViewById(R.id.author_name);

            authorImage = (ImageView) view.findViewById(R.id.news_author_image);

        }

    }


    public NewsFeedListAdapter(List<NewsFeedNewsModel> newsList) {

        this.newsList = newsList;


    }


    @Override

    public NewsFeedListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.newsfeedfragmentitem, parent, false);


        return new NewsFeedListAdapter.MyViewHolder(itemView);

    }


    @Override

    public void onBindViewHolder(NewsFeedListAdapter.MyViewHolder holder, int position) {

        final NewsFeedNewsModel news = newsList.get(position);

        holder.newsTitle.setText(news.getTitle());

        holder.newsNews.setText(news.getNews());

        holder.newsLocation.setText(news.getLocation());

        holder.newsTime.setText(news.getTime());

        holder.newsAuthorName.setText(news.getAuthor());

        Context context=holder.authorImage.getContext();

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                Uri uri = Uri.parse(news.getUrl());

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                view.getContext().startActivity(intent);


            }

        });


    }


    @Override

    public int getItemCount() {

        return newsList.size();

    }

}
