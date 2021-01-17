package com.example.petshop.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.example.petshop.Activity.LoginActivity;
import com.example.petshop.Activity.ProfileEditActivity;
import com.example.petshop.Class.Customer;
import com.example.petshop.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class AccountFragment extends Fragment {

    private View viewRoot;
    LinearLayout layoutUpdate, layoutLogout;
    TextView txtName, txtEmail;
    SharedPreferences sharedPreferences;
    String nameUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewRoot = inflater.inflate(R.layout.fragment_account, container, false);

        Init();
        addEvents();
        return viewRoot;
    }

    private void addEvents() {
        sharedPreferences = this.getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String idUser = sharedPreferences.getString("idUser", "");
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
                    txtName.setText(data.get("displayName").toString());
                } else {
                    Log.d("3", "Current data: null");
                }
            }
        });
        txtEmail.setText("abc@gmail.com");

        layoutUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
                startActivity(intent);
            }
        });

        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }


    private void Init() {
        txtName = viewRoot.findViewById(R.id.txtName);
        txtEmail = viewRoot.findViewById(R.id.txtEmail);
        layoutUpdate = viewRoot.findViewById(R.id.layoutUpdate);
        layoutLogout = viewRoot.findViewById(R.id.layoutLogout);

    }

}