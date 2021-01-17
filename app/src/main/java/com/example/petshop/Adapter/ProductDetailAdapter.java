package com.example.petshop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.Activity.ProductDetailActivity;
import com.example.petshop.Class.ListProduct;
import com.example.petshop.Class.ProductDetails;
import com.example.petshop.R;

import java.util.List;

public class ProductDetailAdapter extends BaseAdapter{
    Activity context;
    //int item_layout;
    int img;
    String name;
    float price;
    String danhmuc;
    String hang;
    int soluong;
    String mota;

    public ProductDetailAdapter(Activity context,int img, String name, float Price,
                                String danhmuc, String hang, int soluong, String mota) {
        this.context = context;
        //this.item_layout = item_layout;
        this.img = img;
        this.name=name;
        this.price= Price;
        this.danhmuc=danhmuc;
        this.hang=hang;
        this.soluong=soluong;
        this.mota=mota;

    }

    @Override
    public int getCount() {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            // Link item layout and binding data
            holder = new ViewHolder ();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            holder.img = convertView.findViewById(R.id.bigImg);
            holder.txtName = convertView.findViewById(R.id.productname);
            holder.txtPrice = convertView.findViewById(R.id.productprice);
            holder.txtsoluong = convertView.findViewById (R.id.txtsoluong);
            holder.txtmota = convertView.findViewById (R.id.txtmota);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ProductDetails Details = null;

        holder.img.setImageResource(Details.getBigImg ());
        holder.txtName.setText(Details.getProductname ());
        holder.txtPrice.setText(String.valueOf(Details.getProductprice ()) + " VND");
        holder.txtsoluong.setText(Details.getTxtsoluong ());
        holder.txtmota.setText(Details.getTxtmota ());



        return convertView;
    }

    public static class ViewHolder{
        ImageView img;
        TextView txtName, txtPrice,txtdanhmuc,txthangsx, txtsoluong, txtmota;
        Button btnAddCart;
    }

}
