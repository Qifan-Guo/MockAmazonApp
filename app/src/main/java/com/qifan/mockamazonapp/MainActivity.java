package com.qifan.mockamazonapp;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qifan.mockamazonapp.databinding.ActivityMainBinding;
import com.qifan.mockamazonapp.models.Product;
import com.qifan.mockamazonapp.util.IMainActivity;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private ActivityMainBinding mActivityMainBinding;
    private static final String PRODUCT_KEY="product_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        initView();
    }

    private void initView(){
        MainFragment mainFragment=new MainFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,mainFragment).commit();
    }

    @Override
    public void inflateDetailView(Product product) {
        ViewProductFragment viewProductFragment=new ViewProductFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable(PRODUCT_KEY,product);
        viewProductFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(getString(R.string.fragment_view_product));
        fragmentTransaction.replace(R.id.fragmentContainer,viewProductFragment).commit();

    }
}
