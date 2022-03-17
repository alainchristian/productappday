package com.example.productapp;

public class Product {
    String prodName;
    float prodPrice;
    String prodDescr;

    public Product(String prodName, float prodPrice, String prodDescr) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodDescr = prodDescr;
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
}
