package com.example.chinago;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import static com.example.chinago.HotelRecViewAdapter.removeFromRentPressed;

public class HotelActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView HotelName;
    private TextView HotelDescription;
    private TextView HotelRoom;
    private TextView RoomPrice;
    private ImageView HotelImage;
    private Button btnConfirmRent;
    private TextInputEditText roomInput;
    private TextInputLayout roomInputLayout;
    public String rentDate;
    private Button btnDate;

    public static final String HOTEL_ID_KEY = "hotelId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        initView();

        Intent intent = getIntent();                                                //
        if (null != intent) {                                                       // check null
            int hotelId = intent.getIntExtra(HOTEL_ID_KEY, -1);        //
            if (hotelId != -1) {                                                    //
                Hotel incomingHotel = Utils.getInstance().getHotelById(hotelId);
                if (null != incomingHotel) {
                    setData(incomingHotel);

                    // handle user input (room)
                    roomInput.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() == 0 || Integer.parseInt(String.valueOf(s)) == 0) {
                                roomInputLayout.setError("Vui lòng nhập số phòng hợp lệ");
                            } else if (Integer.parseInt(String.valueOf(s)) > incomingHotel.getRoom()) {
                                roomInputLayout.setError("Vượt quá số phòng còn lại");
                            } else {
                                roomInputLayout.setError(null);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    handleOutOfRoom(incomingHotel);

                    if (removeFromRentPressed) {
                        rollbackRentedRoom(incomingHotel);
                        removeFromRentPressed = false;
                    }
                }
            }
        }
    }

    //handling out of room, if not out of room => proceed to rent rooms
    private void handleOutOfRoom(Hotel hotel) {
        boolean outOfRoom = false;

        if (hotel.getRoom() == 0) {
            outOfRoom = true;
        }

        if (outOfRoom) {
            btnConfirmRent.setText("Đã hết phòng");
            btnConfirmRent.setEnabled(false);
            btnDate.setEnabled(false);
            roomInput.setEnabled(false);
        } else {
            btnConfirmRent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (roomInput == null || rentDate == null) {
                        Toast.makeText(HotelActivity.this, "Hãy nhập số phòng và ngày trả phòng", Toast.LENGTH_SHORT).show();
                    } else if (roomInputLayout.getError() != null) {
                        Toast.makeText(HotelActivity.this, "Hãy nhập số phòng phù hợp", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Utils.getInstance().addToRentHistory(hotel)) {
                            // Add hotel to history list and update room
                            Toast.makeText(HotelActivity.this, "Đã đặt phòng", Toast.LENGTH_SHORT).show();
                            setRentedRoom(hotel);
                            setRentedDate(hotel);

                            // Refresh page
                            finish();
                            Intent intent = getIntent();
                            intent.getIntExtra(HOTEL_ID_KEY, -1);
                            startActivity(intent);
                        }
                    }
                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    private void setData(Hotel hotel) {
        HotelName.setText(hotel.getName());
        HotelDescription.setText(hotel.getDescription());
        HotelRoom.setText("Chỉ còn " + String.valueOf(hotel.getRoom()) + " phòng");
        RoomPrice.setText(hotel.getRoomPrice() + " mỗi phòng");
        Glide.with(this).asBitmap().load(hotel.getImageURl()).into(HotelImage);
    }

    private void setRentedRoom(Hotel hotel) {
        int availableRoom = hotel.getRoom();
        int userInputRoom = Integer.parseInt(String.valueOf(roomInput.getText()));
        hotel.setRentRoom(String.valueOf(userInputRoom));
        hotel.setRoom(availableRoom - userInputRoom);
    }

    private void setRentedDate(Hotel hotel) {
        hotel.setRentDate(rentDate);
    }

    private void rollbackRentedRoom(Hotel hotel) {
        if (hotel.getRoom() != 0 || hotel.getRentRoom() != null) {
            int rentedRoom = Integer.parseInt(hotel.getRentRoom());
            int availableRoom = hotel.getRoom();
            hotel.setRoom(rentedRoom + availableRoom);
        }
    }

    private void initView() {
        HotelName = findViewById(R.id.HotelNameDetail);
        HotelDescription = findViewById(R.id.HotelDescriptionDetail);
        HotelRoom = findViewById(R.id.hotelRoom);
        RoomPrice = findViewById(R.id.roomPrice);
        HotelImage = findViewById(R.id.HotelImageDetail);
        btnConfirmRent = findViewById(R.id.confirmRent);
        roomInput = findViewById(R.id.roomInput);
        roomInputLayout = findViewById(R.id.roomInputLayout);
        btnDate = findViewById(R.id.btnDate);
    }

    public void chooseCheckoutDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month += 1;
        rentDate = dayOfMonth + "/" + month + "/" + year;
        btnDate.setText("Ngày trả: " + rentDate);
    }
}