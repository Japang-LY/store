package com.japangly.android.iwontdie.HomeTab;

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

public class PhoneAdapter extends ArrayAdapter<Phones> {

    public PhoneAdapter(@NonNull Activity context, ArrayList<Phones> phonesArrayList) {

        super(context, 0, phonesArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.items_phones_grid, parent, false);
        }

        Phones currentWord = getItem(position);

        ImageView imageUrl = listItemView.findViewById(R.id.image_phone);
        Picasso.get()
                .load(currentWord.getImageUrl())
                .into(imageUrl);

        TextView name = listItemView.findViewById(R.id.name_phone);
        name.setText(currentWord.getName());

        TextView price = listItemView.findViewById(R.id.price_phone);
        price.setText(currentWord.getPrice());

        return listItemView;
    }
}