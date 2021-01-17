package com.example.petshop.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.petshop.Activity.HomeActivity;
import com.example.petshop.Activity.ListProductActivity;
import com.example.petshop.Activity.ProductDetailActivity;
import com.example.petshop.Adapter.CategoryAdapter;
import com.example.petshop.Adapter.HotProductAdapter;
import com.example.petshop.Adapter.NavigationAdapter;
import com.example.petshop.Adapter.ProductAdapter;
import com.example.petshop.Adapter.ProductInCategoryAdapter;
import com.example.petshop.Class.Category;
import com.example.petshop.Class.Product;
import com.example.petshop.R;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;


public class HomeFragment extends Fragment implements CategoryAdapter.ItemClickListener, HotProductAdapter.ItemClickListener {
    private View viewRoot;

    private RecyclerView homeCategoryRcv, homeProductRcv;
    private ArrayList<Category> listCategory = new ArrayList<>();
    private Category category;
    private CategoryAdapter adapter = null;


    private ArrayList<Product> productArrayList = new ArrayList<>();
    private Product product;
    private HotProductAdapter productAdapter = null;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_home, container, false);

        Init();
        return viewRoot;
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

    public void getDataProduct() {
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
                        for (QueryDocumentSnapshot doc : value) {
                            productArrayList.add(new Product(doc.getId().toString(),
                                    doc.get("name").toString(),
                                    doc.get("description").toString(),
                                    doc.get("imgUrl").toString(),
                                    Integer.parseInt(doc.get("stock").toString()),
                                    Float.parseFloat(doc.get("unitPrice").toString()),
                                    Integer.parseInt( doc.get("counter").toString())));
                            adapter.notifyDataSetChanged();
                        }
                        Log.d("TAG", "Current cites in CA: ");
                    }
                });
    }

    private void Init() {
        homeCategoryRcv = (RecyclerView) viewRoot.findViewById(R.id.homeCategoryRcv);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        getDataCategory();
        Log.d("hehe", String.valueOf(listCategory.size()));
        adapter = new CategoryAdapter(getContext(), this, listCategory);
        homeCategoryRcv.setLayoutManager(layoutManager1);
        homeCategoryRcv.setAdapter(adapter);

        homeProductRcv = (RecyclerView) viewRoot.findViewById(R.id.homeProductRcv);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        getDataProduct();
//        Log.d("hehe", String.valueOf(listCategory.size()));
        productAdapter = new HotProductAdapter(getContext(), this::onProductClick, productArrayList);
        homeProductRcv.setLayoutManager(layoutManager2);
        homeProductRcv.setAdapter(productAdapter);

    }

    @Override
    public void onClick(View v, Category category, String idCategory) {

        }

    @NonNull
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CategoryFragment();
            case 2:
                return new SearchViewFragment();
            default:
                return new AccountFragment();
        }
    }


    @Override
    public void onProductClick(View v, Product product, String idProduct) {
        Intent intent=new Intent(getActivity(),ProductDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable ("Object", product);
        intent.putExtras(bundle);
        startActivity (intent);

    }
}