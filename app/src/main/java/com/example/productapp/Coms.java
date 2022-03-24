package com.example.productapp;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class Coms {
    Context context;

    public Coms(Context context) {
        this.context = context;
    }
    public void messages(String title,String mess){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(mess);
        builder.setIcon(R.drawable.ic_baseline_warning_24);
        builder.setPositiveButton("OK",null);
        builder.show();

    }
}
