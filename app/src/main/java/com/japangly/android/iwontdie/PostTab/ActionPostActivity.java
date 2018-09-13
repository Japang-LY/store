package com.japangly.android.iwontdie.PostTab;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.japangly.android.iwontdie.MainActivity;
import com.japangly.android.iwontdie.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ActionPostActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private TextView usernameFirebase, currentTime;
    private ImageView profileFirebase;
    private ImageView chooseImage;
    private EditText caption;
    private ImageView uploadImage;
    private Uri imageUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private StorageTask storageTask;
    private String time;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_post);

        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        usernameFirebase = findViewById(R.id.current_username);
        if (user.getDisplayName() == null) {

            String name = new String(user.getEmail());
            String[] array = name.split("@");
            name = array[0];
        } else {

            name = user.getDisplayName();
        }

        usernameFirebase.setText(name);

        if (user.getPhotoUrl() != null) {
            profileFirebase = findViewById(R.id.profile_firebase_post);
            Glide.with(this).load(user.getPhotoUrl()).into(profileFirebase);
        }

        Calendar calender = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        time = simpleDateFormat.format(calender.getTime());

        currentTime = findViewById(R.id.current_time);
        currentTime.setText(time);

        caption = findViewById(R.id.caption_post_status);
        uploadImage = findViewById(R.id.upload_image);

        chooseImage = findViewById(R.id.choose_image_button);
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFileChooser();
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference("posts");
        databaseReference = FirebaseDatabase.getInstance().getReference("posts");
    }

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            imageUri = data.getData();
            Picasso.get()
                    .load(imageUri)
                    .into(uploadImage);
        }
    }

    private String getFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadFile() {

        if (imageUri != null) {

            final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            storageTask = fileReference.putFile(imageUri)

                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    Uri imageUrl = uri;

                                    Toast.makeText(ActionPostActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                                    PostStatus postStatus = new PostStatus(
                                            caption.getText().toString(),
                                            imageUrl.toString(),
                                            name,
                                            time
                                    );

                                    String uploadId = databaseReference.push().getKey();
                                    if (uploadId != null) {
                                        databaseReference.child(uploadId).setValue(postStatus);
                                    }
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(ActionPostActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {

            Toast.makeText(this, "You cannot post a blank page.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.post_action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save_action:
                if (storageTask != null && storageTask.isInProgress()) {
                    Toast.makeText(ActionPostActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
                return true;

            case R.id.cancel_action:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}