package com.example.petshop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.petshop.Adapter.ProductAdapter;
import com.example.petshop.Class.ChildCategory;
import com.example.petshop.Class.ListProduct;
import com.example.petshop.Class.Product;
import com.example.petshop.R;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.Nullable;

public class ListProductActivity extends AppCompatActivity {

    GridView grvlistProduct;
    ProductAdapter productAdapter;
    ArrayList<Product> listProducts;
    Intent Intent;
    FirebaseFirestore db = FirebaseFirestore.getInstance ();
    String idCHild;
    ImageView backpro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_list_product);

        linkViews ();
        Bundle bundle = getIntent().getExtras();
        idCHild= bundle.getString ("IdChild");
        setAdapterListView ();
        addEvents ();
        getIntent ();
    }

    private void getData(String idCHild) {
        db = FirebaseFirestore.getInstance ();
        db.collection ("product")
                .whereEqualTo ("categoryId",idCHild)
                .addSnapshotListener (new EventListener<QuerySnapshot> () {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w ("Fail", "Listen failed.", e);
                            return;
                        }
                        listProducts.clear ();
                        for (QueryDocumentSnapshot doc : value) {
                            listProducts.add (new Product (
                                    doc.getId (),
                                    doc.get ("name").toString (),
                                    doc.get ("description").toString (),
                                    doc.get ("imgUrl").toString (),
                                    Integer.valueOf (doc.get ("stock").toString ()),
                                    Integer.valueOf (doc.get ("unitPrice").toString ()),
                                    1
                            ));
                            productAdapter.notifyDataSetChanged ();
                        }
                    }
                });
    }



    private void addEvents() {

        grvlistProduct.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (ListProductActivity.this, ProductDetailActivity.class);

                Bundle bundle = new Bundle();
                Product p=listProducts.get (position);
                bundle.putSerializable ("Object", p);
                intent.putExtras(bundle);
                startActivity (intent);
            }
        });

        backpro.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (backpro.getBaseline () == View.VISIBLE) {
                    backpro.setVisibility (View.INVISIBLE); //or GONE

                } else {
                    finish ();
                }
            }
        });
    }

//    @Override
//    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
//        super.onPostCreate (savedInstanceState);
//        setContentView (R.layout.activity_product_detail);
//        grvlistProduct.setOnItemClickListener (new AdapterView.OnItemClickListener (){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent (ListProductActivity.this,  ProductDetailActivity.class);
//                startActivity (Intent);
//            }
//        });
//    }

    private void setAdapterListView() {
        listProducts = new ArrayList<Product> ();
        getData(idCHild);
        productAdapter = new ProductAdapter (ListProductActivity.this, R.layout.item_product_gridview, listProducts);
        grvlistProduct.setAdapter (productAdapter);
    }

    private void linkViews() {
        grvlistProduct = findViewById (R.id.grvlistProduct);
        backpro =findViewById (R.id.backproduct);

    }
}

