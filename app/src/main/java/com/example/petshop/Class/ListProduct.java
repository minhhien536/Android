package com.example.petshop.Class;

public class ListProduct {
    private String idProduct;
    private int img;
    private String name;
    private  double price;

    //public  ListProduct(){}


    public ListProduct(int img,String idProduct, String name, double price) {
        this.idProduct = idProduct;
        this.img = img;
        this.name = name;
        this.price = price;
    }
    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }
    public int getImg() {
        return img;
    }
    public void setImg(int img) {
        this.img = img;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

}
