package com.example.chinago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class HotelActivity extends AppCompatActivity {

    private TextView HotelName;
    private TextView HotelDescription;
    private TextView HotelRoom;
    private TextView RoomPrice;
    private ImageView HotelImage;
    private Button btnConfirmRent;
    private BottomSheetDialog bottomSheet;
    private Context context;

    public static final String HOTEL_ID_KEY = "hotelId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        initView();

        Intent intent = getIntent();
        if (null != intent) {
            int hotelId = intent.getIntExtra(HOTEL_ID_KEY, -1);
            if (hotelId != -1) {
                Hotel incomingHotel = Utils.getInstance().getHotelById(hotelId);
                if (null != incomingHotel) {
                    setData(incomingHotel);

                    handleOutOfRoom(incomingHotel);
                }
            }
        }
    }

    private void setIntent() {

    }

    private void handleOutOfRoom(Hotel hotel) {
        /*ArrayList<Hotel> outOfRoomHotels = Utils.getInstance().getRentedHotels();
        for(Hotel h: outOfRoomHotels) {
            if (hotel.getRoom() == 0) {
                outOfRoom = true;
            }
        }*/

        boolean outOfRoom = false;

        if (hotel.getRoom() == 0) {
            outOfRoom = true;
        }

        if (outOfRoom) {
            btnConfirmRent.setText("Đã hết phòng");
            btnConfirmRent.setEnabled(false);
        } else {
            btnConfirmRent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToRentHistory(hotel)) {
                        // Add hotel to history list and update room
                        Toast.makeText(HotelActivity.this, "Đã đặt phòng", Toast.LENGTH_SHORT).show();

                        // Refresh page
                        finish();
                        Intent intent = getIntent();
                        intent.getIntExtra(HOTEL_ID_KEY, -1);

                        startActivity(intent);

                    } else {
                        Toast.makeText(HotelActivity.this, "Có lỗi xảy ra, xin vui lòng thử lại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Hotel hotel) {
        HotelName.setText(hotel.getName());
        HotelDescription.setText(hotel.getDescription());
        HotelRoom.setText("Chỉ còn " + String.valueOf(hotel.getRoom()) + " phòng");
        RoomPrice.setText(hotel.getRoomPrice() + " mỗi phòng");
        Glide.with(this).asBitmap().load(hotel.getImageURl()).into(HotelImage);
    }

    private void initView() {
        HotelName = findViewById(R.id.HotelNameDetail);
        HotelDescription = findViewById(R.id.HotelDescriptionDetail);
        HotelRoom = findViewById(R.id.hotelRoom);
        RoomPrice = findViewById(R.id.roomPrice);
        HotelImage = findViewById(R.id.HotelImageDetail);
        btnConfirmRent = findViewById(R.id.confirmRent);
    }
}