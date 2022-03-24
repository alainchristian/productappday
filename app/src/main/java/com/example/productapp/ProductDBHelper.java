package com.example.productapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ProductDBHelper extends SQLiteOpenHelper {
    Context context;
    byte [] byteImage;
    Coms coms;
    ByteArrayOutputStream outputStream;
    public ProductDBHelper(@Nullable Context context) {
        super(context, "productDB.db", null, 1);
        this.context=context;
        coms=new Coms(context);
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
    public ArrayList<Product> getProducts(){
        try {
            SQLiteDatabase database = getWritableDatabase();
            ArrayList<Product> productList=new ArrayList<>();
            Cursor cursor =database.rawQuery("select * from prdTable",null);
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    String name=cursor.getString(1);
                    Float price =cursor.getFloat(2);
                    String desr =cursor.getString(3);
                    byte[] imageByte=cursor.getBlob(4);
                    Bitmap imageBtmp = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                    productList.add(new Product(name,price,desr,imageBtmp));
                }
                return productList;
            }
            else {
                coms.messages("Database error","Database is empty");
                return null;
            }

        } catch (Exception e) {
            coms.messages("Database error",e.getMessage());
            return null;
        }
    }

}
