package com.example.petshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.petshop.Class.Category;
import com.example.petshop.Class.Product;
import com.example.petshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    ArrayList<Product> list;
    public SearchAdapter( Context mContext, ArrayList<Product> list){
        this.list=list;
        this.mContext=mContext;
        //  this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        return new MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,final int position) {

        holder.itemView.findViewById(R.id.tvProductName);


    }

    @Override
    public int getItemCount() {
       return list.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvProductName, tvPrice, tvQuantity;
        ImageView imgProduct;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName=itemView.findViewById(R.id.tvProductName);

        }
    }
}
