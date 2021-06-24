package com.example.firstmultiscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {

    EditText fullName, password, age;
    ImageView backToLogin;
    ProgressBar progressBar;
    Button signup;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mAuth = FirebaseAuth.getInstance();

        signup = (Button) findViewById(R.id.signup);
        fullName = (EditText) findViewById(R.id.fullName);
        password = (EditText) findViewById(R.id.password);
        age = (EditText) findViewById(R.id.age);
        backToLogin = (ImageView) findViewById(R.id.backToLogin);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity2.class));
            finish();

        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fullName.getText().toString().trim();
                String password1 = password.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    fullName.setError("Email Field is Empty.");
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    password.setError("Password Field Is Empty");
                    return;
                }
                if(password1.length() < 6) {
                    password.setError("Password Must Be At Least 6 Characters Long");
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(name,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(MainActivity3.this, "User Succesfully Created!", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(), MainActivity2.class));

                       }else {
                           Toast.makeText(MainActivity3.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           progressBar.setVisibility(View.GONE);
                       }
                    }
                });

            }
        });
    }}






