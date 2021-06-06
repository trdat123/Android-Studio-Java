package com.example.chinago;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import static com.example.chinago.HotelActivity.HOTEL_ID_KEY;

public class HotelRecViewAdapter extends RecyclerView.Adapter<HotelRecViewAdapter.ViewHolder> {

    private ArrayList<Hotel> hotels = new ArrayList<>();
    private Context context;
    private String parentActivity;
    public static boolean removeFromRentPressed = false; // check if user press the "hủy đơn" button

    public HotelRecViewAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public HotelRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HotelRecViewAdapter.ViewHolder holder, int position) {
        holder.HotelName.setText(hotels.get(position).getName());
        holder.HotelDescription.setText(hotels.get(position).getDescription());
        holder.RentDate.setText("Ngày trả phòng: " + hotels.get(position).getRentDate());
        holder.RentRoom.setText("Số phòng đã đặt: " + hotels.get(position).getRentRoom());

        if (parentActivity.equals("Main")) {
            holder.btnDelete.setVisibility(View.GONE);
            holder.RentDate.setVisibility(View.GONE);
            holder.RentRoom.setVisibility(View.GONE);
        } else if (parentActivity.equals("RentHotel")) {
            holder.HotelDescription.setVisibility(View.GONE);
            holder.RentDate.setVisibility(View.VISIBLE);
            holder.RentRoom.setVisibility(View.VISIBLE);
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Bạn có chắc muốn hủy đơn đặt phòng?");
                    builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Utils.getInstance().removeFromRentHistory(hotels.get(position))) {
                                Toast.makeText(context, "Đã hủy đơn đặt phòng", Toast.LENGTH_SHORT).show();
                                removeFromRentPressed = true;
                            } else {
                                Toast.makeText(context, "Có lỗi xảy ra xin vui lòng thử lại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                        }
                    });
                    builder.create().show();
                }
            });
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HotelActivity.class);
                intent.putExtra(HOTEL_ID_KEY, hotels.get(position).getId());
                context.startActivity(intent);
            }
        });

        Glide.with(context)
                .asBitmap()
                .load(hotels.get(position).getImageURl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView HotelName;
        private CardView parent;
        private ImageView image;
        private TextView HotelDescription;
        private TextView RentDate;
        private TextView RentRoom;
        private Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            HotelName = itemView.findViewById(R.id.HotelName);
            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.HotelImage);
            HotelDescription = itemView.findViewById(R.id.HotelDescription);
            RentDate = itemView.findViewById(R.id.RentDate);
            RentRoom = itemView.findViewById(R.id.RentRoom);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

}
