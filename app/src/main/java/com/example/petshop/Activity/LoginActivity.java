package com.example.petshop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petshop.Class.Customer;
import com.example.petshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText et_username, et_password;
    Button bt_submit;
    TextView tv_signup;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Customer user = new Customer();
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Password = "passwordKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_signup = findViewById(R.id.tv_signup);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        bt_submit = findViewById(R.id.bt_submit);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        handleLogin();
        tv_signup.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                                         }
                                     }
        );
    }

    private void handleLogin() {
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdatauser();
            }
        });
    }


    public class Session {

        private SharedPreferences prefs;

        public Session(Context cntx) {
            // TODO Auto-generated constructor stub
            prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
        }

        public void setusename(String usename) {
            prefs.edit().putString("usename", usename).commit();
        }

        public String getusename() {
            String usename = prefs.getString("usename", "");
            return usename;
        }
    }

    public void getdatauser() {
        String username = et_username.getText().toString();
        db.collection("customer")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (et_username.getText().toString().equals(document.getData().get("username").toString())
                                        && et_password.getText().toString().equals(document.getData().get("password").toString())) {

                                    SharedPreferences.Editor editor = sharedpreferences.edit();

                                    editor.putString("displayName", document.getData().get("displayName").toString());
                                    editor.putString("idUser", document.getId());

                                    editor.commit();
                                    Toast.makeText (LoginActivity.this, "Success", Toast.LENGTH_SHORT).show ();
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                                }
                                }
                            }


//                        } else {
//                            Log.d("DATA FAIL", "Error getting documents: ", task.getException());

                        }
                    });
                }
    }