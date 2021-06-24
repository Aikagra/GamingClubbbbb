package com.example.firstmultiscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    private ListView listView;
    private Button add;
    private EditText edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        listView = findViewById(R.id.listView);
        edit = findViewById(R.id.edit);
        add = findViewById(R.id.add);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.memberActivity);




        add.setOnClickListener((v) -> {
             String txt_name = edit.getText().toString();
             if (txt_name.isEmpty()){
                 Toast.makeText(this, "Name Field is Empty!", Toast.LENGTH_SHORT).show();
             } else {
                 FirebaseDatabase.getInstance().getReference().child("Languages").child("Name").setValue(txt_name);
             }


         });


        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main7 , list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Languages");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    list.add(snapshot.getValue().toString());
                    findViewById(R.id.progressBar79).setVisibility(View.INVISIBLE);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.homeActivity:
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.memberActivity:


                    case R.id.scheduleActivity:
                        startActivity(new Intent(getApplicationContext(),MainActivity8.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.winsActivity:
                        startActivity(new Intent(getApplicationContext(),MainActivity6.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;



                }

            }
        });


    }
}