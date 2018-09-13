package com.japangly.android.iwontdie.SettingTab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.japangly.android.iwontdie.MainActivity;
import com.japangly.android.iwontdie.R;

import java.util.Objects;

public class FeedbackActivity extends AppCompatActivity {

    private Button buttonSubmitFeedback;
    private EditText messageFeedback;
    private FirebaseDatabase feedbackDatabase;
    private DatabaseReference messageReference;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        feedbackDatabase = FirebaseDatabase.getInstance();
        messageReference = feedbackDatabase.getReference("feedbacks");

        buttonSubmitFeedback = findViewById(R.id.button_sumbit_feedback);
        messageFeedback = findViewById(R.id.caption_post_status);

        buttonSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                writeFeedback();
            }
        });
    }

    private void writeFeedback() {

        String message = messageFeedback.getText().toString();

        if (!TextUtils.isEmpty(message)) {

            String id = messageReference.push().getKey();
            String username = null;

            if (user.getDisplayName() == null) {

                String name = new String(user.getEmail());
                String[] array = name.split("@");
                username = array[0];
            } else {

                username = user.getDisplayName();
            }

            Feedback feedback = new Feedback(id, username, message);

            messageReference.child(id).setValue(feedback);

            messageFeedback.setText("");

            Toast.makeText(getApplicationContext(), "Thank You for the feedback.", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(getApplicationContext(), "You cannot submit blank message.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            startActivity(new Intent(getApplication(), MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
