package com.japangly.android.iwontdie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailRegister, passwordRegister, passwordConfirm;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register);

        emailRegister = findViewById(R.id.email_register);
        passwordRegister = findViewById(R.id.password_register);
        passwordConfirm = findViewById(R.id.password_confirm);

        mAuth = FirebaseAuth.getInstance();

        Button createAccountButton = findViewById(R.id.create_account_button);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
            }
        });

    }

    private void registerUser() {

        if (emailRegister.getText().toString().equals("") && passwordRegister.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Blank space is not allowed", Toast.LENGTH_LONG).show();
        } else if (!passwordRegister.getText().toString().equals(passwordConfirm.getText().toString())) {

            Toast.makeText(getApplicationContext(), "Password mismatch!", Toast.LENGTH_LONG).show();
        } else {

            String email = emailRegister.getText().toString();
            String password = passwordRegister.getText().toString();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(getApplicationContext(), "createUserWithEmail:success", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {

                                Toast.makeText(getApplicationContext(), "This email is already in used.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    void updateUI(FirebaseUser user) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
