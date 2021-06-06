package com.example.chinago;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RecView;
    private Button btnShowHistory, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecView = findViewById(R.id.RecView);
        btnShowHistory = findViewById(R.id.btnShowHistory);
        btnLogout = findViewById(R.id.btnLogout);

        HotelRecViewAdapter adapter = new HotelRecViewAdapter(this, "Main");
        adapter.setHotels(Utils.getInstance().getAllHotels());

        RecView.setAdapter(adapter);
        RecView.setLayoutManager(new GridLayoutManager(this, 1));

        // History button in main window handling
        btnShowHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RentedHotelActivity.class);
                startActivity(intent);
            }
        });

        Utils.getInstance();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}