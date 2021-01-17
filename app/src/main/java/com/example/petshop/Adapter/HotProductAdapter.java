package com.example.petshop.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.petshop.Class.Category;
import com.example.petshop.Class.Product;
import com.example.petshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotProductAdapter extends RecyclerView.Adapter<HotProductAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Product> mProduct;
    private ItemClickListener itemClickListener;
    int selectedPosition=-1;

    public HotProductAdapter(Context mContext,ItemClickListener itemClickListener,ArrayList<Product> mProduct) {
        this.mContext = mContext;
        this.mProduct = mProduct;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View HotproductView = inflater.inflate(R.layout.item_product_gridview, parent, false);
        ViewHolder viewHolder = new ViewHolder(HotproductView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = mProduct.get((position));

        Picasso.get().load(product.getImgUrl()).into(holder.imgProductItem);

        holder.txtProduct.setText(product.getNameProduct());

        Integer productUnitPrice = (int)product.getUnitPrice();
        String str = String.format("%,d", productUnitPrice);

        holder.txtPrice.setText(str + " VND");

    }

    @Override
    public int getItemCount() {
        return mProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgProductItem;
        private TextView txtProduct, txtPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProductItem = itemView.findViewById(R.id.imgProductitem);
            txtProduct = itemView.findViewById(R.id.Productitemname);
            txtPrice = itemView.findViewById(R.id.PriProductitem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onProductClick(v,mProduct.get(getLayoutPosition()),mProduct.get(getLayoutPosition()).getIdProduct());

                }
            });
        }
    }
    public interface ItemClickListener{
        void onProductClick(View v,Product product,String idProduct);
    }
}