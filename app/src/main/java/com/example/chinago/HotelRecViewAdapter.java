package com.example.chinago;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.chinago.HotelActivity.HOTEL_ID_KEY;

public class HotelRecViewAdapter extends RecyclerView.Adapter<HotelRecViewAdapter.ViewHolder> {

    private ArrayList<Hotel> hotels = new ArrayList<>();
    private Context context;

    public HotelRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HotelRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelRecViewAdapter.ViewHolder holder, int position) {
        holder.HotelName.setText(hotels.get(position).getName());
        holder.HotelDescription.setText(hotels.get(position).getDescription());

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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            HotelName = itemView.findViewById(R.id.HotelName);
            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.HotelImage);
            HotelDescription = itemView.findViewById(R.id.HotelDescription);
        }
    }

}
