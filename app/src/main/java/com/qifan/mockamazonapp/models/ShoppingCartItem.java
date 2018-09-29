package com.qifan.mockamazonapp.models;

public class ShoppingCartItem {
    private Product mProduct;
    private int mquantity;

    public ShoppingCartItem(Product product, int mquantity) {
        mProduct = product;
        this.mquantity = mquantity;
    }

    public ShoppingCartItem() {
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product product) {
        mProduct = product;
    }

    public int getQuantity() {
        return mquantity;
    }

    public void setQuantity(int mquantity) {
        this.mquantity = mquantity;
    }
}
