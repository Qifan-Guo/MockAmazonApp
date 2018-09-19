package com.qifan.mockamazonapp;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.qifan.mockamazonapp.models.Product;

public class ProductViewModel extends BaseObservable {
    private Product mProduct;
    private int mQuantity;
    private boolean Visibility= false;


    @Bindable
    public boolean isVisibility() {
        return Visibility;
    }
    @Bindable
    public int getQuantity() {
        return mQuantity;
    }
    @Bindable
    public Product getProduct() {
        return mProduct;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }
    public void setProduct(Product product) {
        mProduct = product;
        notifyPropertyChanged(BR.product);
    }
    public void setVisibility(boolean visibility) {
        Visibility = visibility;
        notifyPropertyChanged(BR.visibility);
    }
    public RequestListener getCustomRequestListener(){
        return new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
               setVisibility(true);
                return false;
            }
        };
    }

}
