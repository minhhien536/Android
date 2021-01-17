package com.example.petshop.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.petshop.Activity.ListProductActivity;
import com.example.petshop.Activity.ProductDetailActivity;
import com.example.petshop.Adapter.CategoryAdapter;
import com.example.petshop.Adapter.ProductInCategoryAdapter;
import com.example.petshop.Class.Category;
import com.example.petshop.Class.ChildCategory;
import com.example.petshop.Class.Product;
import com.example.petshop.R;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

import javax.annotation.Nullable;

public class CategoryFragment extends Fragment implements CategoryAdapter.ItemClickListener {
    private View viewRoot;
    private LinearLayout layoutCategory;
    private RecyclerView rvcCategory;
    private ArrayList<Category> listCategory = new ArrayList<>();
    private Category category;
    private CategoryAdapter adapter = null;

    private GridView gvProduct;
    private ArrayList<ChildCategory> listChild = new ArrayList<>();
    ;
    private Product product;
    private ProductInCategoryAdapter adapterChild = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_category, container, false);

        Init();

        return viewRoot;
    }

    @Override
    public void onResume() {
        getFullChildCategory();
        super.onResume();
    }

    public void getDataCategory() {
        db = FirebaseFirestore.getInstance();
        db.collection("category")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("Fail", "Listen failed.", e);
                            return;
                        }
                        for (QueryDocumentSnapshot doc : value) {
                            listCategory.add(new Category(doc.getId().toString(), doc.get("categoryName").toString(), doc.get("imageCategory").toString()));
                            adapter.notifyDataSetChanged();
                        }
                        Log.d("TAG", "Current cites in CA: ");
                    }
                });
    }

    public void getDataProduct(String id) {
        db = FirebaseFirestore.getInstance();
        db.collection("childCategory")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("Fail", "Listen failed.", e);
                            return;
                        }
                        listChild.clear();
                        for (QueryDocumentSnapshot doc : value) {
                            if (doc.get("parentCatId").toString().equals(id)) {
                                listChild.add(new ChildCategory(
                                        doc.getId(),
                                        doc.get("childCategoryName").toString(),
                                        doc.get("imgChildCategory").toString(),
                                        doc.get("parentCatId").toString()
                                ));
                                adapterChild.notifyDataSetChanged();
                            }

                        }
                    }
                });
    }

    public void getFullChildCategory() {
        db = FirebaseFirestore.getInstance();
        db.collection("childCategory")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("Fail", "Listen failed.", e);
                            return;
                        }
                        listChild.clear();
                        for (QueryDocumentSnapshot doc : value) {

                            listChild.add(new ChildCategory(
                                    doc.getId(),
                                    doc.get("childCategoryName").toString(),
                                    doc.get("imgChildCategory").toString(),
                                    doc.get("parentCatId").toString()
                            ));
                            adapterChild.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void Init() {
        rvcCategory = (RecyclerView) viewRoot.findViewById(R.id.rcwCategory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        getDataCategory();
        adapter = new CategoryAdapter(getContext(), this, listCategory);
        rvcCategory.setLayoutManager(layoutManager);
        rvcCategory.setAdapter(adapter);

        gvProduct = (GridView) viewRoot.findViewById(R.id.grvProduct);
        adapterChild = new ProductInCategoryAdapter(getContext(), R.layout.item_category_gridview, listChild);
        gvProduct.setAdapter(adapterChild);
        gvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ListProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("IdChild", listChild.get(position).getIdChildCategory());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v, Category category, String idCategory) {
        getDataProduct(idCategory);
    }
}