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

import com.squareup.picasso.Picasso;

import org.beyondrefuge.www.Model.HistoryItem;
import org.beyondrefuge.www.Model.NewsFeedNewsModel;
import org.beyondrefuge.www.R;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Recoded Cedan on 23.02.2018.
 */

public class NewsFeedListAdapter extends RecyclerView.Adapter<NewsFeedListAdapter.MyViewHolder> {

    private List<NewsFeedNewsModel> newsList;

    private Context context;





    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView newsTitle, newsNews, newsLocation, newsTime, newsAuthorName,likeCount;

        public ImageView authorImage, shareButton,likeButton;


        public MyViewHolder(View view) {

            super(view);

            newsTitle = (TextView) view.findViewById(R.id.news_title);

            newsNews = (TextView) view.findViewById(R.id.news_text_news);

            newsLocation = (TextView) view.findViewById(R.id.news_location);

            newsTime = (TextView) view.findViewById(R.id.news_time);

            newsAuthorName = (TextView) view.findViewById(R.id.author_name);

            authorImage = (ImageView) view.findViewById(R.id.news_author_image);

            shareButton = (ImageView) view.findViewById(R.id.news_share_button);

            likeCount=(TextView) view.findViewById(R.id.like_count);

            likeButton=(ImageView)view.findViewById(R.id.like_button);


        }


    }


    public NewsFeedListAdapter(List<NewsFeedNewsModel> newsList, Context context) {

        this.newsList = newsList;
        this.context = context;
        Realm.init(context);

    }


    @Override

    public NewsFeedListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.newsfeedfragmentitem, parent, false);


        return new NewsFeedListAdapter.MyViewHolder(itemView);

    }


    @Override

    public void onBindViewHolder(NewsFeedListAdapter.MyViewHolder holder, final int position) {

        final NewsFeedNewsModel news = newsList.get(position);

        holder.newsTitle.setText(news.getTitle());

        holder.newsNews.setText(news.getNews());

        holder.newsLocation.setText(news.getLocation());

        holder.newsTime.setText(news.getTime());

        holder.newsAuthorName.setText(news.getAuthor());

        holder.likeCount.setText(news.getLikeNumber());

        Picasso.with(context).load(news.getAuthorImage()).into(holder.authorImage);

        if (news.getLikeNumber().isEmpty()){
            holder.likeButton.setVisibility(View.GONE);
        }
        holder.newsNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm realm = Realm.getDefaultInstance();

                HistoryItem item = new HistoryItem(newsList.get(position).getNews(), news
                .getUrl());

                realm.beginTransaction();
                realm.copyToRealm(item);
                realm.commitTransaction();

               Uri uri = Uri.parse(news.getUrl());

               Intent intent = new Intent(Intent.ACTION_VIEW, uri);

               v.getContext().startActivity(intent);


            }
        });

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String shareTitle=news.getTitle();
                String shareSubject=news.getNews();
                i.putExtra(Intent.EXTRA_SUBJECT,shareTitle);
                i.putExtra(Intent.EXTRA_TEXT,shareSubject);
                v.getContext().startActivity(Intent.createChooser(i,"Share it"));

            }
        });

    }


    @Override

    public int getItemCount() {

        return newsList.size();

    }

}
