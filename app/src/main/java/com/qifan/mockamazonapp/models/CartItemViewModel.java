package com.qifan.mockamazonapp.models;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.qifan.mockamazonapp.util.IMainActivity;


/**
 * Created by User on 2/9/2018.
 */

public class CartItemViewModel extends BaseObservable {

    private static final String TAG = "CartItemViewModel";

    private ShoppingCartItem cartItem;

    @Bindable
    public ShoppingCartItem getCartItem(){
        return cartItem;
    }

    public void setCartItem(ShoppingCartItem cartItem){
        Log.d(TAG, "setQuantity: updating cart");
        this.cartItem = cartItem;
        notifyChange();
    }

    public void increaseQuantity(Context context){
        ShoppingCartItem cartItem = getCartItem();
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        setCartItem(cartItem);
        IMainActivity iMainActivity = (IMainActivity) context;
        iMainActivity.updateQuantity(cartItem.getProduct(), 1);
    }

    public void decreaseQuantity(Context context){
        ShoppingCartItem cartItem = getCartItem();
        IMainActivity iMainActivity = (IMainActivity) context;
        if(cartItem.getQuantity() > 1){
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            setCartItem(cartItem);
            iMainActivity.updateQuantity(cartItem.getProduct(), -1);
        }
        else if(cartItem.getQuantity() == 1){
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            setCartItem(cartItem);
            iMainActivity.removeCartItem(cartItem);
        }
    }

    public String getQuantityString(ShoppingCartItem cartItem){
        return ("Qty: " + String.valueOf(cartItem.getQuantity()));
    }

}



























