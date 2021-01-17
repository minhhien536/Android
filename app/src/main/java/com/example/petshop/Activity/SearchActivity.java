package com.example.petshop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petshop.Adapter.ProductAdapter;
import com.example.petshop.Adapter.ProductDetailAdapter;
import com.example.petshop.Class.ListProduct;
import com.example.petshop.Class.Product;
import com.example.petshop.Class.ProductDetails;
import com.example.petshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class SearchActivity extends AppCompatActivity {
    ScrollView prddetal;
    ImageView img;
    TextView txtName, txtPrice, txtsoluong, txtmota;
    Button btnAddCart;
    ImageView cart;
    ProductDetailAdapter productDetailAdapter;
    Product product;
    private Object Tag;
    String productName;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        getIntent();
        linkViews();

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("BUNDLE");

        productName = (String) bundle.getString("productName","");

     //   getDataIntent();

        getData();


    }

//    private void getDataIntent() {
//        Bundle bundle = getIntent().getExtras();
//        product = new Product();
//        product = (Product) bundle.getSerializable("Object");
//
//        Picasso.get().load(product.getImgUrl()).into(img);
//        txtName.setText(String.valueOf(product.getNameProduct()));
//        txtPrice.setText(String.valueOf(product.getUnitPrice()));
//        txtmota.setText(String.valueOf(product.getDescription()));
//        txtsoluong.setText("Số lượng: " + String.valueOf(product.getStock()));
//    }


    private void getDataProduct(String name) {
        db.collection("product")
                .whereEqualTo("name", name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("DATAAA", document.getId() + " => " + document.getData());
                                product = new Product(
                                        document.getId(),
                                        document.getData().get("name").toString(),
                                        document.getData().get("description").toString(),
                                        document.getData().get("imgUrl").toString(),
                                        Integer.valueOf(document.getData().get("stock").toString()),
                                        Integer.valueOf(document.getData().get("unitPrice").toString()),
                                        1
                                );
                                Log.d("22222", document.getData().toString());
                            }
                            Picasso.get().load(product.getImgUrl()).into(img);
                            Log.d("DATA", String.valueOf(product));
                            txtName.setText(String.valueOf(product.getNameProduct()));
                            txtPrice.setText(String.valueOf(product.getUnitPrice()));
                            txtmota.setText(String.valueOf(product.getDescription()));
                            txtsoluong.setText("      Số lượng: " + String.valueOf(product.getStock()));

                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });


    }


    public void linkViews() {
        img = findViewById(R.id.bigImg);
        txtName = findViewById(R.id.productname);
        txtPrice = findViewById(R.id.productprice);
        txtsoluong = findViewById(R.id.txtsoluong);
        btnAddCart = findViewById(R.id.btnAddCart);
        txtmota = findViewById(R.id.txtmota);
        cart = findViewById(R.id.cart);
    }


    private void getData() {

        getDataProduct(productName);

    }


}