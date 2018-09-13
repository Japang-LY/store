package com.japangly.android.iwontdie.PostTab;

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

public class PostAdapter extends ArrayAdapter<PostStatus> {

    public PostAdapter(@NonNull Activity context, ArrayList<PostStatus> postStatuses) {

        super(context, 0, postStatuses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.post_status_list_view, parent, false);
        }

        PostStatus currentWord = getItem(position);

        ImageView postImage = listItemView.findViewById(R.id.post_image);
        Picasso.get()
                .load(currentWord.getImageUrl())
                .into(postImage);


        TextView caption = listItemView.findViewById(R.id.post_caption);
        caption.setText(currentWord.getPostStatus());

        TextView user = listItemView.findViewById(R.id.post_username);
        user.setText(currentWord.getPostUser());

        TextView time = listItemView.findViewById(R.id.post_time);
        time.setText(currentWord.getPostTime());

        return listItemView;
    }
}