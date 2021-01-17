package com.example.petshop.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.petshop.Class.Customer;
import com.example.petshop.Class.Product;
import com.example.petshop.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileEditActivity extends AppCompatActivity {

    EditText edtDisplayName, edtPhone, edtAddress;
    Button btnUpdate;
    ImageView btnCloseEdit;
    Customer customer;
    ArrayList<String> list = new ArrayList();
    SharedPreferences sharedPreferences;
    String idUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        Init();
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        idUser=sharedPreferences.getString("idUser", "").toString();
        getData(idUser);
        addEvents();
    }

    private void addEvents() {
        btnCloseEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> updates = new HashMap<>();
                updates.put("address", edtAddress.getText().toString());
                updates.put("phone", edtPhone.getText().toString());
                updates.put("displayName", edtDisplayName.getText().toString());
                DocumentReference c = db.collection("customer").document(idUser);
                c
                        .update(updates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("3x33", "DocumentSnapshot successfully updated!");
                                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileEditActivity.this);
                                builder.setIcon(R.drawable.ic_check);
                                builder.setTitle("Thông báo!");
                                builder.setMessage("Cập nhật thành công.");
                                builder.setNegativeButton("OK ", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ProfileEditActivity.this.finish ();
                                        dialog.dismiss ();
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error updating document", e);
                            }
                        });

            }
        });
    }

    private void Init() {
        edtDisplayName = findViewById(R.id.edtDisplayName);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        btnUpdate = findViewById(R.id.btnUpdateProfile);
        btnCloseEdit=findViewById(R.id.btnCloseEdit);
    }

    private void onSetData() {
        edtDisplayName.setText(customer.getDisplayName());
        edtPhone.setText(customer.getPhone());
        edtAddress.setText(customer.getAddress());
    }

    private void getData(String idUser) {
        final DocumentReference docRef = db.collection("customer").document(idUser);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("1", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {

                    Map<String, Object> data = new HashMap<>();
                    data = snapshot.getData();

                    customer = new Customer(
                            data.get("username").toString(),
                            data.get("displayName").toString(),
                            data.get("address").toString(),
                            data.get("password").toString(),
                            data.get("phone").toString());
                    Log.d("33333", data.toString());
                    onSetData();
                } else {
                    Log.d("3", "Current data: null");
                }
            }
        });
    }
}