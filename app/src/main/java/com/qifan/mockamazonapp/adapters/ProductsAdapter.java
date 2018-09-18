package com.qifan.mockamazonapp.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.mockamazonapp.R;
import com.qifan.mockamazonapp.databinding.ProductItemBinding;
import com.qifan.mockamazonapp.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.BindingHolder> {


    private List<Product> mProducts=new ArrayList<>();
    private Context mContext;

    public ProductsAdapter(Context context,List<Product> products){
        mContext=context;
        mProducts=products;
    }

    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ProductItemBinding binding=DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.product_item,viewGroup,false);

        return new BindingHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BindingHolder bindingHolder, int i) {
        Product product=mProducts.get(i);
        bindingHolder.mProductItemBinding.setProduct(product);
        bindingHolder.mProductItemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        ProductItemBinding mProductItemBinding;
        public BindingHolder(@NonNull View itemView) {
            super(itemView);
            mProductItemBinding= DataBindingUtil.bind(itemView);
        }
    }
}
