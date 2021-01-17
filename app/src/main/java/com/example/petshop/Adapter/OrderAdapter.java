package com.example.petshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.Class.Product;
import com.example.petshop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Product> products;
    private CartItemsAdapter.ItemClickListener itemClickListener;

    public OrderAdapter(Context context, ArrayList<Product> products, CartItemsAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.products = products;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View cartView = inflater.inflate(R.layout.order_item, parent, false);
        OrderAdapter.ViewHolder viewHolder = new OrderAdapter.ViewHolder(cartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        Picasso.get().load(product.getImgUrl()).into(holder.imageCart);

        holder.cartItemName.setText(product.getNameProduct());

        Integer price = (int)product.getUnitPrice();

        String str = String.format("%,d", price);

        holder.cartItemPrice.setText(String.valueOf(str + " vnđ"));
        holder.cartItemCounter.setText("Số lượng: " + String.valueOf(product.getCounter()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageCart;
        private TextView cartItemName;
        private TextView cartItemPrice;
        private EditText cartItemCounter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCart = itemView.findViewById(R.id.imageCart);
            cartItemName = itemView.findViewById(R.id.cartItemName);
            cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            cartItemCounter = itemView.findViewById(R.id.cartItemCounter);
        }
    }

    public interface ItemClickListener {

    }
}
