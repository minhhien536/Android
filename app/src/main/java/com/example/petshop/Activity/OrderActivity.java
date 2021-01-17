package com.example.petshop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petshop.Adapter.CartItemsAdapter;
import com.example.petshop.Adapter.OrderAdapter;
import com.example.petshop.Class.Product;
import com.example.petshop.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    private ArrayList<Product> products;
    private RecyclerView rcvOrderItems;
    private OrderAdapter orderAdapter;
    private TextView totalMoney;
    private Button btnInsertOrder;
    private ImageButton btnCloseOrder;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        products = new ArrayList<>();

        linkViews();
        getData();

        orderAdapter = new OrderAdapter(this, products, null);
        rcvOrderItems.setLayoutManager(new LinearLayoutManager(this));
        rcvOrderItems.setAdapter(orderAdapter);

        addEvents();
    }

    private void addEvents() {
        btnInsertOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String formatted = df.format(new Date());
                Map<String, String> data = new HashMap<>();
                Map<String, ArrayList<Product>> listPro = new HashMap<>();

                data.put("totalMoney", total);
                data.put("createAt", formatted);
                data.put("customerId", "37yx9CWrBEfh9SZ2Cr2n");
                data.put("listProduct", "");

                db.collection("order")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                String idOrder = documentReference.getId();
                                listPro.put("listProduct",products);
                                db.collection("order").document(idOrder).set(listPro);

                                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                                builder.setIcon(R.drawable.ic_check);
                                builder.setTitle("Thông báo!");
                                builder.setMessage("Đặt hàng thành công.");
                                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        OrderActivity.this.finish ();
                                        Intent intent = new Intent (OrderActivity.this, HomeActivity.class);
                                        startActivity (intent);
                                    }
                                });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("FAIL", "Error adding document", e);
                            }
                        });
            }
        });

        btnCloseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void linkViews() {
        btnInsertOrder = findViewById(R.id.btnInsertOrder);
        rcvOrderItems = findViewById(R.id.rcvOrderItems);
        totalMoney = findViewById(R.id.totalMoneyOrder);
        btnCloseOrder = findViewById(R.id.btnCloseOrder);
    }

    private void getData() {
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("BUNDLE");
        ArrayList<Product> object = (ArrayList<Product>) bundle.getSerializable("cartItems");

        total = (String) bundle.getSerializable("totalMoney");

        totalMoney.setText(String.valueOf(total));

        products = object;
    }
}