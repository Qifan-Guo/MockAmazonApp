package com.qifan.mockamazonapp.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qifan.mockamazonapp.R;
import com.qifan.mockamazonapp.databinding.CartItemBinding;
import com.qifan.mockamazonapp.models.CartItemViewModel;
import com.qifan.mockamazonapp.models.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;



public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.BindingHolder>{

    private static final String TAG = "CartItemAdapter";

    private List<ShoppingCartItem> mCartItems = new ArrayList<>();
    private Context mContext;

    public CartItemAdapter(Context context, List<ShoppingCartItem> cartItems) {
        mCartItems = cartItems;
        mContext = context;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CartItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.cart_item, parent, false);

        return new CartItemAdapter.BindingHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ShoppingCartItem cartItem = mCartItems.get(position);
        CartItemViewModel viewModel = new CartItemViewModel();
        viewModel.setCartItem(cartItem);
        holder.binding.setCartItemView(viewModel);
        holder.binding.executePendingBindings();
    }

    public void updateCartItems(List<ShoppingCartItem> cartItems){
        mCartItems.clear();
        mCartItems.addAll(cartItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCartItems.size();
    }


    public class BindingHolder extends RecyclerView.ViewHolder{

       CartItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}



















