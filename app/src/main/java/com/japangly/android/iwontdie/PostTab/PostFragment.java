package com.japangly.android.iwontdie.PostTab;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.japangly.android.iwontdie.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {


    private FloatingActionButton floatingActionButton;
    private ListView listView;
    private ArrayList<PostStatus> postStatus;
    private ProgressBar progressbar;
    private DatabaseReference databaseReference;
    private PostAdapter postAdapter;

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        floatingActionButton = getView().findViewById(R.id.floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(), ActionPostActivity.class));
            }
        });

        progressbar = getView().findViewById(R.id.progress_circle);

        listView = getView().findViewById(R.id.post_list_view);
        postStatus = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("posts");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    PostStatus postSnapshotValue = postSnapshot.getValue(PostStatus.class);
                    postStatus.add(postSnapshotValue);
                }

                postAdapter = new PostAdapter(getActivity(), postStatus);
                listView.setAdapter(postAdapter);

                progressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressbar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
