package com.qifan.mockamazonapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.mockamazonapp.databinding.FragmentMainBinding;
import com.qifan.mockamazonapp.models.Product;
import com.qifan.mockamazonapp.util.Products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String Tag="MainFragment";
    FragmentMainBinding mFragmentMainBinding;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    mFragmentMainBinding=FragmentMainBinding.inflate(inflater);
    mFragmentMainBinding.swipeRefreshLayout.setOnRefreshListener(this);
    setUpProducts();
        return mFragmentMainBinding.getRoot();
    }

    @Override
    public void onRefresh() {
        onItemsLoadComplete();
    }

    public void setUpProducts(){
        Products products=new Products();
        List<Product> productList=new ArrayList<>();
        productList.addAll(Arrays.asList(products.PRODUCTS));
        mFragmentMainBinding.setProducts(productList);
    }
    public void onItemsLoadComplete(){
        (mFragmentMainBinding.recyclerView.getAdapter()).notifyDataSetChanged();
        mFragmentMainBinding.swipeRefreshLayout.setRefreshing(false);
    }
}

