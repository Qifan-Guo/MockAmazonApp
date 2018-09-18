package com.qifan.mockamazonapp.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qifan.mockamazonapp.adapters.ProductsAdapter;
import com.qifan.mockamazonapp.models.Product;

import java.util.List;

public class MainFragmentBindingAdapter {
@BindingAdapter("productsList")
    public static void setProductList(RecyclerView view, List<Product> products){
    if(products==null){
        return;
    }
    RecyclerView.LayoutManager layoutManager=view.getLayoutManager();
    if(layoutManager==null){
        view.setLayoutManager(new GridLayoutManager(view.getContext(),2));
    }
    ProductsAdapter adapter=(ProductsAdapter)view.getAdapter();
    if(adapter == null){
        adapter= new ProductsAdapter(view.getContext(),products);
        view.setAdapter(adapter);
    }
}


}
