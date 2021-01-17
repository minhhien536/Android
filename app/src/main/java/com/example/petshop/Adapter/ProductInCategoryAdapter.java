package com.example.petshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.petshop.Class.ChildCategory;
import com.example.petshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductInCategoryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ChildCategory> childCategories;

    private int positionSelect = -1;

    public ProductInCategoryAdapter(Context context, int layout, List<ChildCategory> childCategories ) {
        this.context = context;
        this.layout = layout;
        this.childCategories = childCategories;
    }

    @Override
    public int getCount() {
        if (childCategories.size() != 0 && !childCategories.isEmpty()) {
            return childCategories.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgProduct;
        TextView txtProduct;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            convertView=inflater.inflate(layout,null);
            holder.imgProduct=(ImageView) convertView.findViewById(R.id.imgProductitem);
            holder.txtProduct=(TextView) convertView.findViewById(R.id.tvProductitem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ChildCategory child=childCategories.get(position);
        holder.txtProduct.setText(child.getChildCategoryName());
        Picasso.get().load(child.getImgChildCategory()).into(holder.imgProduct);



        return convertView;
    }
}