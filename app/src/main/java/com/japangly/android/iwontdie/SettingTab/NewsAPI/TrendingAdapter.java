package com.japangly.android.iwontdie.SettingTab.NewsAPI;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.japangly.android.iwontdie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrendingAdapter extends ArrayAdapter<TrendingCustomClass> {

    public TrendingAdapter(@NonNull Activity context, ArrayList<TrendingCustomClass> trendingArticles) {

        super(context, 0, trendingArticles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.trending_articles_list, parent, false);
        }

        TrendingCustomClass currentWord = getItem(position);

        ImageView imageView = listItemView.findViewById(R.id.articles_image);
        Picasso.get()
                .load(currentWord.getUrlToImage())
                .into(imageView);

        TextView title = listItemView.findViewById(R.id.title_articles);
        title.setText(currentWord.getTitle());

        TextView description = listItemView.findViewById(R.id.description_articles);
        description.setText(currentWord.getDescription());

        return listItemView;
    }
}
