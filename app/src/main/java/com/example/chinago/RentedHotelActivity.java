package com.example.chinago;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RentedHotelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rented_hotel);
        TextView EmptyRoomText = findViewById(R.id.EmptyRoomText);

        RecyclerView recyclerView = findViewById(R.id.HotelRecView);
        HotelRecViewAdapter adapter = new HotelRecViewAdapter(this, "RentHotel");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter.setHotels(Utils.getRentedHotels());

        if (Utils.getRentedHotels().isEmpty()) {
            EmptyRoomText.setVisibility(View.VISIBLE);
        }
    }
}