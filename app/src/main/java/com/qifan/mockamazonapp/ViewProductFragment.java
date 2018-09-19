package com.qifan.mockamazonapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.mockamazonapp.databinding.FragmentViewProductBinding;
import com.qifan.mockamazonapp.models.Product;
import com.qifan.mockamazonapp.util.Products;


/**
 * Created by User on 2/6/2018.
 */

public class ViewProductFragment extends Fragment {

    private static final String TAG = "ViewProductFragment";
    private static final String PRODUCT_KEY="product_key";
    // Data binding
    FragmentViewProductBinding mBinding;
    private Product mProduct;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=this.getArguments();
        if(bundle != null){
            mProduct = bundle.getParcelable(PRODUCT_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentViewProductBinding.inflate(inflater);
        ProductViewModel productViewModel=new ProductViewModel();
        productViewModel.setProduct(mProduct);
        productViewModel.setQuantity(1);
        mBinding.setProductView(productViewModel);

        return mBinding.getRoot();
    }

}














