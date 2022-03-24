package com.example.productapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView prodImage;
    Uri imagePath;
    Bitmap imageTostore;
    public final static int MYREQ_CODE=1001;
    TextInputEditText prodNameEt,prodPriceEt,prodDescriptionEt;
    Button btnSend;
    ProductDBHelper dbHelper;
    Coms coms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coms=new Coms(this);

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,MYREQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==MYREQ_CODE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            try {
                imagePath=data.getData();

                imageTostore= MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                prodImage.setImageBitmap(imageTostore);
            } catch (IOException e) {
                coms.messages("DB issues",e.getMessage());
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuProdAdd:
                showProductForm();
                break;
            case R.id.mnuProdView:
                showProducts();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showProducts() {
        startActivity(new Intent(MainActivity.this,ShowProducts.class));

    }
    Dialog dialog;
    private void showProductForm() {
        dbHelper = new ProductDBHelper(this);
        dialog = new Dialog(this);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.productform,null);
        builder.setView(view);
        dialog=builder.create();
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        prodImage=view.findViewById(R.id.prdImage);
        prodNameEt=view.findViewById(R.id.prdName);
        prodPriceEt=view.findViewById(R.id.prdPrice);
        prodPriceEt.setText("0");
        prodDescriptionEt=view.findViewById(R.id.prdDescription);
        btnSend=view.findViewById(R.id.btnSend);

        prodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send dat to database
                sendData();
            }
        });
        dialog.show();
    }
    private void sendData() {
        try {
        String name=prodNameEt.getText().toString();
        Float price = Float.valueOf(prodPriceEt.getText().toString());
        String descr = prodDescriptionEt.toString();
        if (TextUtils.isEmpty(name)){
            prodNameEt.setError("Product name is required");
            prodNameEt.requestFocus();
            return;
        }
        if (price<=0){
            prodPriceEt.setError("Please provide the price");
            prodPriceEt.requestFocus();
            return;
        }
        if (imagePath==null){
            coms.messages("Add image","Image cannot null, please selec an image");
        }
        dbHelper.insertProduct(new Product(name,price,descr,imageTostore));
            prodNameEt.setText("");
            prodPriceEt.setText("0");
            prodDescriptionEt.setText("");
            prodImage.setImageResource(R.drawable.ic_baseline_add_a_photo_24);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    public void dismissDialog(View view){

        dialog.dismiss();

    }
}