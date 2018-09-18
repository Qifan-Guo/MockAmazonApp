package com.qifan.mockamazonapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.mockamazonapp.databinding.FragmentMainBinding;


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
        return mFragmentMainBinding.getRoot();
    }

    @Override
    public void onRefresh() {
        onItemsLoadComplete();
    }

    public void onItemsLoadComplete(){
        (mFragmentMainBinding.recyclerView.getAdapter()).notifyDataSetChanged();
        mFragmentMainBinding.swipeRefreshLayout.setRefreshing(false);
    }
}

