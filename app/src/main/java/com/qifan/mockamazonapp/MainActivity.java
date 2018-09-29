package com.qifan.mockamazonapp;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.qifan.mockamazonapp.databinding.ActivityMainBinding;
import com.qifan.mockamazonapp.models.CartViewModel;
import com.qifan.mockamazonapp.models.Product;
import com.qifan.mockamazonapp.models.ShoppingCartItem;
import com.qifan.mockamazonapp.util.IMainActivity;
import com.qifan.mockamazonapp.util.PreferenceKeys;
import com.qifan.mockamazonapp.util.Products;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private ActivityMainBinding mActivityMainBinding;
    private static final String PRODUCT_KEY="product_key";

    //vars
    private boolean mClickToExit = false;
    private Runnable mCheckoutRunnable;
    private Handler mCheckoutHandler;
    private int mCheckoutTimer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mActivityMainBinding.cart.setOnTouchListener(new CartTouchListener());
        mActivityMainBinding.proceedToCheckout.setOnClickListener(mCheckOutListener);
        getNumCartItem();
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
        fragmentTransaction.replace(R.id.fragmentContainer,viewProductFragment,"View Product").commit();

    }

    @Override
    public void showDialog() {
        QuantityDialog quantityDialog = new QuantityDialog();
        quantityDialog.show(getSupportFragmentManager(),"quantity chooser");
    }


    @Override
    public void setQuantity(int x) {
        Log.d("test", "setQuantity: ");
     ViewProductFragment fragment=(ViewProductFragment) getSupportFragmentManager().findFragmentByTag("View Product");
     if(fragment != null){
         Log.d("test", "not null ");
         fragment.mBinding.getProductView().setQuantity(x);
     }
    }

    public void getNumCartItem(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart,new HashSet<String>());
        // Retrieve the quantities of each item from the cart
        Products products = new Products();
        List<ShoppingCartItem> cartItems = new ArrayList<>();
        for(String serialNumber : serialNumbers){
            int quantity = preferences.getInt(serialNumber, 0);

            cartItems.add(new ShoppingCartItem(products.PRODUCT_MAP.get(serialNumber), quantity));
        }

        CartViewModel viewModel = new CartViewModel();
        viewModel.setCart(cartItems);
        try{
            viewModel.setCartVisible(mActivityMainBinding.getCartView().isCartVisible());
        }catch (NullPointerException e){
            Log.e("main", "getShoppingCart: NullPointerException: " + e.getMessage() );
        }
        mActivityMainBinding.setCartView(viewModel);

    }
    public static class CartTouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                view.setBackgroundColor(view.getContext().getResources().getColor(R.color.blue4));
                view.performClick();

                IMainActivity iMainActivity = (IMainActivity)view.getContext();
                iMainActivity.inflateViewCartFragment();
            }
            else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                view.setBackgroundColor(view.getContext().getResources().getColor(R.color.blue6));
            }

            return true;
        }
    }

    @Override
    public void addToShoppingCart(Product product, int quantity) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart,new HashSet<String>());
        serialNumbers.add(String.valueOf(product.getSerial_number()));
        editor.putStringSet(PreferenceKeys.shopping_cart,serialNumbers);
        editor.commit();
        int currentQuantity = preferences.getInt(String.valueOf(product.getSerial_number()),0);
        editor.putInt(String.valueOf(product.getSerial_number()),(currentQuantity+quantity));
        editor.commit();
        //reset the quantity and notify the user
        setQuantity(1);
        Toast.makeText(this,"Added to Cart",Toast.LENGTH_SHORT).show();
        getNumCartItem();
    }

    @Override
    public void inflateViewCartFragment() {
        ViewCartFragment fragment = (ViewCartFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_cart));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fragment == null){
            fragment = new ViewCartFragment();
            transaction.replace(R.id.fragmentContainer, fragment, getString(R.string.fragment_view_cart));
            transaction.addToBackStack(getString(R.string.fragment_view_cart));
            transaction.commit();
        }
    }

    @Override
    public void setCartVisibility(boolean visibility) {
        mActivityMainBinding.getCartView().setCartVisible(visibility);
    }

    @Override
    public void updateQuantity(Product product, int quantity) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        //add the quantity
        int currentQuantity = preferences.getInt(String.valueOf(product.getSerial_number()), 0);

        //commit the updated quantity
        editor.putInt(String.valueOf(product.getSerial_number()), (currentQuantity + quantity));
        editor.commit();

        getNumCartItem();

    }

    @Override
    public void removeCartItem(ShoppingCartItem cartItem) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.remove(String.valueOf(cartItem.getProduct().getSerial_number()));
        editor.commit();

        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart, new HashSet<String>());
        if(serialNumbers.size() == 1){
            editor.remove(PreferenceKeys.shopping_cart);
            editor.commit();
        }
        else{
            serialNumbers.remove(String.valueOf(cartItem.getProduct().getSerial_number()));
            editor.putStringSet(PreferenceKeys.shopping_cart, serialNumbers);
            editor.commit();
        }

        getNumCartItem();

        //remove the item from the list in ViewCartFragment
        ViewCartFragment fragment = (ViewCartFragment)getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_cart));
        if(fragment != null){
            fragment.updateCartItems();
        }

    }
    public void checkout(){

        mActivityMainBinding.progressBar.setVisibility(View.VISIBLE);

        mCheckoutHandler = new Handler();
        mCheckoutRunnable  = new Runnable() {
            @Override
            public void run() {
                mCheckoutHandler.postDelayed(mCheckoutRunnable, 200);
                mCheckoutTimer += 200;
                if(mCheckoutTimer >= 1600){
                    emptyCart();
                    mActivityMainBinding.progressBar.setVisibility(View.GONE);
                    mCheckoutHandler.removeCallbacks(mCheckoutRunnable);
                    mCheckoutTimer = 0;
                }
            }
        };
        mCheckoutRunnable.run();
    }

    private void emptyCart(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> serialNumbers = preferences.getStringSet(PreferenceKeys.shopping_cart, new HashSet<String>());
        SharedPreferences.Editor editor = preferences.edit();

        for(String serialNumber : serialNumbers){
            editor.remove(serialNumber);
            editor.commit();
        }

        editor.remove(PreferenceKeys.shopping_cart);
        editor.commit();
        Toast.makeText(this, "thanks for shopping!", Toast.LENGTH_SHORT).show();
        removeViewCartFragment();
        getNumCartItem();
    }

    public View.OnClickListener mCheckOutListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            checkout();
        }
    };

    public void removeViewCartFragment(){
        getSupportFragmentManager().popBackStack();
        ViewCartFragment fragment = (ViewCartFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_view_cart));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fragment != null){
            transaction.remove(fragment);
            transaction.commit();
        }
    }

}
