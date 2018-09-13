package com.japangly.android.iwontdie.HomeTab;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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
public class HomeFragment extends Fragment {


    private ProgressBar progressbar;
    private ArrayList<Phones> phones;
    private GridView gridView;
    private DatabaseReference databaseReference;
    private PhoneAdapter phoneAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressbar = getView().findViewById(R.id.progressBar);

        gridView = getView().findViewById(R.id.gridView);
        phones = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("phones");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Phones postSnapshotValue = postSnapshot.getValue(Phones.class);
                    phones.add(postSnapshotValue);
                }

                phoneAdapter = new PhoneAdapter(getActivity(), phones);
                gridView.setAdapter(phoneAdapter);

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

