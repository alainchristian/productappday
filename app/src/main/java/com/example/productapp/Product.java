package com.example.productapp;

import android.graphics.Bitmap;

public class Product {
    String prodName;
    float prodPrice;
    String prodDescr;
    Bitmap prdImage;


    public Product(String prodName, float prodPrice, String prodDescr, Bitmap prdImage) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodDescr = prodDescr;
        this.prdImage = prdImage;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public float getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(float prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdDescr() {
        return prodDescr;
    }

    public void setProdDescr(String prodDescr) {
        this.prodDescr = prodDescr;
    }

    public Bitmap getPrdImage() {
        return prdImage;
    }

    public void setPrdImage(Bitmap prdImage) {
        this.prdImage = prdImage;
    }
}
