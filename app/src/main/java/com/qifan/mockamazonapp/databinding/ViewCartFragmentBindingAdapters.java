package com.qifan.mockamazonapp.databinding;


import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.qifan.mockamazonapp.adapters.CartItemAdapter;
import com.qifan.mockamazonapp.models.ShoppingCartItem;

import java.util.List;





public class ViewCartFragmentBindingAdapters {

    private static final String TAG = "ViewCartFragmentBinding";


    @BindingAdapter("cartItems")
    public static void setCartItems(RecyclerView view, List<ShoppingCartItem> cartItems){
        Log.d(TAG, "setCartItems: Binding work");
        if(cartItems == null){
            Log.d(TAG, "setCartItems: No item");
            return;
        }
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        if(layoutManager == null){
            view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }
        CartItemAdapter adapter = (CartItemAdapter) view.getAdapter();
        if(adapter == null){
            Log.d(TAG, "setCartItems: new");
            adapter = new CartItemAdapter(view.getContext(), cartItems);
            view.setAdapter(adapter);
        }
        else{
            Log.d(TAG, "setCartItems: new");
            adapter.updateCartItems(cartItems);
        }
    }

}





















