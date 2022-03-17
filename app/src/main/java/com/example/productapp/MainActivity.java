package com.example.productapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    private void showProductForm() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Product recording");
        builder.setMessage("Record all products as reuired");
        builder.setIcon(R.drawable.ic_record);
        View view = LayoutInflater.from(this).inflate(R.layout.productform,null);
        builder.setView(view);
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });
        builder.show();
    }
}