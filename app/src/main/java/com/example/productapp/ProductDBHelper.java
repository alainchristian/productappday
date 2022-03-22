package com.example.productapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class ProductDBHelper extends SQLiteOpenHelper {
    Context context;
    byte [] byteImage;
    ByteArrayOutputStream outputStream;
    public ProductDBHelper(@Nullable Context context) {
        super(context, "productDB.db", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table prdTable(prdId INTEGER PRIMARY KEY AUTOINCREMENT,prdName TEXT NOT NULL,prdPrice FLOAT,PrdDesc TEXT,prdImage BLOB)");
        } catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try {
            db.execSQL("drop table if exists prdTable ");
        }catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void insertProduct(Product product){
        try {
            Bitmap imageBtmp=product.getPrdImage();
            outputStream=new ByteArrayOutputStream();
            imageBtmp.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            byteImage=outputStream.toByteArray();

            SQLiteDatabase db=getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("prdName",product.getProdName());
            values.put("prdPrice",product.getProdPrice());
            values.put("PrdDesc",product.getProdDescr());
            values.put("prdImage",byteImage);
            long result=db.insert("prdTable",null,values);
            if (result==-1){
                Toast.makeText(context, "Data enty failed", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "Data entry successful", Toast.LENGTH_SHORT).show();
            }
        }catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
