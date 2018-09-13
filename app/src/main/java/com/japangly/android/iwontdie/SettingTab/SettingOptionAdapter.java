package com.japangly.android.iwontdie.SettingTab;

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

import java.util.ArrayList;

public class SettingOptionAdapter extends ArrayAdapter<SettingOption> {

    public SettingOptionAdapter(@NonNull Activity context, ArrayList<SettingOption> settingOptions) {

        super(context, 0, settingOptions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.setting_option_list, parent, false);
        }

        SettingOption currentWord = getItem(position);

        TextView khmer = listItemView.findViewById(R.id.title);
        khmer.setText(currentWord.getTitle());

        TextView english = listItemView.findViewById(R.id.sub_title);
        english.setText(currentWord.getSubTitle());

        ImageView image = listItemView.findViewById(R.id.image);
        image.setImageResource(currentWord.getImage());

        return listItemView;
    }
}
