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
import com.example.petshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Category> mCategory;
    private ItemClickListener itemClickListener;
    int selectedPosition=-1;

    public CategoryAdapter(Context mContext,ItemClickListener itemClickListener,ArrayList<Category> mCategory) {
        this.mContext = mContext;
        this.mCategory = mCategory;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View categoryView = inflater.inflate(R.layout.item_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = mCategory.get((position));

        Picasso.get().load(category.getImageCategory()).into(holder.imgCategory);

        holder.txtCategory.setText(category.getNameCategory());
    }

    @Override
    public int getItemCount() {
        return mCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCategory;
        private TextView txtCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(v,mCategory.get(getLayoutPosition()),mCategory.get(getLayoutPosition()).getIdCategory());

                }
            });
        }
    }
    public interface ItemClickListener{
        void onClick(View v,Category category,String idCategory);
    }
}