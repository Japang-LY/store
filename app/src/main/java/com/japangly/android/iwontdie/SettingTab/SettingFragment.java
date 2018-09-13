package com.japangly.android.iwontdie.SettingTab;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.japangly.android.iwontdie.AuthActivity;
import com.japangly.android.iwontdie.R;
import com.japangly.android.iwontdie.SettingTab.NewsAPI.TrendingActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private TextView usernameFirebase, emailFirebase;
    private ImageView profileFirebase;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        usernameFirebase = getView().findViewById(R.id.username_firebase);
        if (user.getDisplayName() == null) {

            String name = new String(user.getEmail());
            String[] array = name.split("@");
            usernameFirebase.setText(array[0]);
        } else {

            usernameFirebase.setText(user.getDisplayName());
        }

        emailFirebase = getView().findViewById(R.id.sign_in_email);
        emailFirebase.setText(user.getEmail());

        if (user.getPhotoUrl() != null) {
            profileFirebase = getView().findViewById(R.id.profile_firebase);
            Glide.with(this).load(user.getPhotoUrl()).into(profileFirebase);
        }

        ArrayList<SettingOption> settingOptions = new ArrayList<>();
        settingOptions.add(new SettingOption("News", "Developing trends", R.drawable.news_icon));
        settingOptions.add(new SettingOption("Feedback", "Information about reactions", R.drawable.feedback_icon));
        settingOptions.add(new SettingOption("About", "Full information about", R.drawable.about_icon));
        settingOptions.add(new SettingOption("Feature 1", "A distinctive attribute", R.drawable.new_feature_icon));
        settingOptions.add(new SettingOption("Feature 2", "A distinctive attribute", R.drawable.new_feature_icon));
        settingOptions.add(new SettingOption("Feature 3", "A distinctive attribute", R.drawable.new_feature_icon));
        settingOptions.add(new SettingOption("Logout", "Go through the procedures", R.drawable.logout_button));

        SettingOptionAdapter settingOptionAdapter = new SettingOptionAdapter(getActivity(), settingOptions);
        ListView listView = getView().findViewById(R.id.dynamic_list_view);
        listView.setAdapter(settingOptionAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), TrendingActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), FeedbackActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), AboutActivity.class));
                        break;

                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:
                        signOut();
                        break;
                }
            }
        });
    }

    private void signOut() {

        mAuth.signOut();
        getActivity().finish();
        startActivity(new Intent(getActivity().getApplicationContext(), AuthActivity.class));
    }
}
