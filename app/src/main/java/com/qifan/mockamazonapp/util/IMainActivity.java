package com.qifan.mockamazonapp.util;

import com.qifan.mockamazonapp.models.Product;
import com.qifan.mockamazonapp.models.ShoppingCartItem;

public interface IMainActivity {
    void inflateDetailView(Product product);
    void showDialog();
    void setQuantity(int x);
    void addToShoppingCart(Product product, int quantity);

    void inflateViewCartFragment();

    void setCartVisibility(boolean visibility);

    void updateQuantity(Product product, int quantity);

    void removeCartItem(ShoppingCartItem cartItem);
}
