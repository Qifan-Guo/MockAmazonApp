package com.qifan.mockamazonapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.qifan.mockamazonapp.databinding.ActivityLogInBinding;


public class LogInActivity extends AppCompatActivity {
    ActivityLogInBinding mBinding;
    String userName = "admin";
    String password = "admin123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_log_in);
        mBinding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mUserName = mBinding.userName.getText().toString();
                String mPassword = mBinding.password.getText().toString();
                if(!mUserName.equals(userName)){
                    Toast.makeText(getBaseContext(),"User does not exsit",Toast.LENGTH_SHORT).show();
                }else if(!mPassword.equals(password)){
                    Toast.makeText(getBaseContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();

                }
            }
        });

    }
}
