package com.example.chinago;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RecView;
    private Button btnShowHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecView = findViewById(R.id.RecView);
        btnShowHistory = findViewById(R.id.btnShowHistory);

        HotelRecViewAdapter adapter = new HotelRecViewAdapter(this);
        adapter.setHotels(Utils.getInstance().getAllHotels());

        RecView.setAdapter(adapter);
        RecView.setLayoutManager(new GridLayoutManager(this, 2));

        // History button in main window handling
        btnShowHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RentedHotelActivity.class);
                startActivity(intent);
            }
        });

        Utils.getInstance();
    }
}