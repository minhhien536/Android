package com.example.petshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.example.petshop.Adapter.NavigationAdapter;
import com.example.petshop.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class HomeActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<String> listProductName = new ArrayList<>();
    private ArrayList<String> listProductPrice = new ArrayList<> ();
    private ArrayAdapter<String> adapter;
    ImageView shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hone);

        autoCompleteTextView = findViewById(R.id.searchBox);
        getData();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listProductName);
        autoCompleteTextView.setAdapter(adapter);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(new NavigationAdapter(this));
        shoppingCart = findViewById(R.id.shoppingCart);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
// Add tab to navigation bar
        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.ic_baseline_home_24));
        tabLayout.addTab(tabLayout.newTab().setText("Category").setIcon(R.drawable.ic_baseline_category_24));
        tabLayout.addTab(tabLayout.newTab().setText("Account").setIcon(R.drawable.ic_baseline_account_circle_24));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                    viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                parent.getItemAtPosition(position);
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                String productName = parent.getItemAtPosition(position).toString();

                Bundle bundle = new Bundle();

                bundle.putString("productName", productName);
                intent.putExtra("BUNDLE", bundle);

                startActivity(intent);

            }
        });

        addEvents();
    }

    private void addEvents() {
        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }



    private void getData() {
        db = FirebaseFirestore.getInstance();
        db.collection("product")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("Fail", "Listen failed.", e);
                            return;
                        }
                        listProductName.clear();
                        for (QueryDocumentSnapshot doc : value) {

                            listProductName.add(new String(
                                    doc.get("name").toString()
                            ));
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}