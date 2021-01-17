package com.example.petshop.Class;

import android.app.Activity;

public class ProductDetails extends Activity {



    private String idProduct;
    private int bigImg;
    private String productname;
    private double productprice;
    private String txtdanhmuc;
    private String txthangsx;
    private int txtsoluong;
    private String txtmota;

    public ProductDetails(String idProduct, int img, String productname, double productprice, String txtdanhmuc,
                          String txthangsx, int txtsoluong, String txtmota) {
        this.idProduct = idProduct;
        this.bigImg = img;
        this.productname = productname;
        this.productprice = productprice;
        this.txtdanhmuc = txtdanhmuc;
        this.txtdanhmuc = txthangsx;
        this.txtsoluong = txtsoluong;
        this.txtmota = txtmota;
    }
    public int getBigImg() {
        return bigImg;
    }

    public void setBigImg(int bigImg) {
        this.bigImg = bigImg;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public double getProductprice() {
        return productprice;
    }

    public void setProductprice(double productprice) {
        this.productprice = productprice;
    }

    public String getTxtdanhmuc() {
        return txtdanhmuc;
    }

    public void setTxtdanhmuc(String txtdanhmuc) {
        this.txtdanhmuc = txtdanhmuc;
    }

    public String getTxthangsx() {
        return txthangsx;
    }

    public void setTxthangsx(String txthangsx) {
        this.txthangsx = txthangsx;
    }

    public int getTxtsoluong() {
        return txtsoluong;
    }

    public void setTxtsoluong(int txtsoluong) {
        this.txtsoluong = txtsoluong;
    }

    public String getTxtmota() {
        return txtmota;
    }

    public void setTxtmota(String txtmota) {
        this.txtmota = txtmota;
    }
    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }
}
