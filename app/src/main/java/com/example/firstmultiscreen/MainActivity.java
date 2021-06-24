package com.example.firstmultiscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText hellotext10, hellotext20;
    Button hellobutton10;
    TextView hellotextView10;
    ProgressBar progressBar1;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        hellotext10 = findViewById(R.id.text10);
        hellotext20 = findViewById(R.id.text20);
        hellobutton10 = findViewById(R.id.button10);
        hellotextView10 = findViewById(R.id.textView10);
        progressBar1 = findViewById(R.id.progressBar1);

        hellobutton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name10 = hellotext10.getText().toString().trim();
                String password10 = hellotext20.getText().toString().trim();

                if (TextUtils.isEmpty(name10)) {
                    hellotext10.setError("Email Field is Empty.");
                    return;
                }

                if (TextUtils.isEmpty(password10)) {
                    hellotext20.setError("Password Field Is Empty");
                    return;
                }
                if(password10.length() < 6) {
                    hellotext20.setError("Password Must Be At Least 6 Characters Long");
                }

                progressBar1.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(name10, password10).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity3.class));

                        }else {
                            Toast.makeText(MainActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar1.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
    public void openActivity2(View v) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);



    }
}