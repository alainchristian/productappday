package com.example.productapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowProducts extends AppCompatActivity {
    RecyclerView productRV;
    ProductDBHelper dbHelper;
    ArrayList<Product> productsList;
    ProductRVAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);
        productRV=findViewById(R.id.productRV);
        productsList =new ArrayList<>();
        dbHelper=new ProductDBHelper(this);
        productsList= dbHelper.getProducts();
        adapter = new ProductRVAdapter(productsList,this);
        productRV.setLayoutManager(new GridLayoutManager(this,3));
        productRV.setAdapter(adapter);

    }
}