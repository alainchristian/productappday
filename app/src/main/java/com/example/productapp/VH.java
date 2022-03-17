package com.example.productapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VH extends RecyclerView.ViewHolder {
    TextView tvName,tvPrice,tvDesc;
    ImageView prodImage;
    public VH(@NonNull View itemView) {
        super(itemView);
        tvName=itemView.findViewById(R.id.tvName);
        tvPrice=itemView.findViewById(R.id.tvPrice);
        tvDesc=itemView.findViewById(R.id.tvDescription);
        prodImage=itemView.findViewById(R.id.prodImage);
    }
}
