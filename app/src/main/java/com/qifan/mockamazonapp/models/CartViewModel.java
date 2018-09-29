package com.qifan.mockamazonapp.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.qifan.mockamazonapp.util.BigDecimalUtil;
import com.qifan.mockamazonapp.util.Prices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends BaseObservable{
    private List<ShoppingCartItem> cart = new ArrayList<>();
    private boolean isCartVisible;


    @Bindable
    public boolean isCartVisible() {
        return isCartVisible;
    }

    @Bindable
    public List<ShoppingCartItem> getCart() {
        return cart;
    }

    public void setCartVisible(boolean cartVisible) {
        isCartVisible = cartVisible;
        notifyChange();
    }

    public void setCart(List<ShoppingCartItem> cart) {
        this.cart = cart;
        notifyChange();
    }
    public String getProductQuantitiesString(){
        int totalItem = 0 ;
        for(ShoppingCartItem cartItem : cart){
            totalItem += cartItem.getQuantity();
        }
        return String.valueOf(totalItem);
    }
    public String getTotalCost(){
        double totalCost = 0;
        for(ShoppingCartItem cartItem : cart){
            int quantity = cartItem.getQuantity();

            double cost = quantity * (Prices.getPrices().get(String.valueOf(cartItem.getProduct().getSerial_number())).doubleValue());
            totalCost += cost;
        }
        return "$"+ BigDecimalUtil.getValue(new BigDecimal(totalCost));
    }
}
