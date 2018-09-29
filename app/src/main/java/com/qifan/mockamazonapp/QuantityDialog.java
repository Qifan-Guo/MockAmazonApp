package com.qifan.mockamazonapp;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.qifan.mockamazonapp.databinding.FragmentQuantityDialogBinding;
import com.qifan.mockamazonapp.util.IMainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuantityDialog extends DialogFragment {

FragmentQuantityDialogBinding mBinding;
    public QuantityDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= FragmentQuantityDialogBinding.inflate(inflater);
        mBinding.setIMainActivity((IMainActivity)getActivity());
        Log.d("Dialog", "onCreateView: Dialog");
        mBinding.listView.setOnItemClickListener(mOnItemClickListener);
        mBinding.closeDialog.setOnClickListener(mOnClickListener);
        return mBinding.getRoot();
    }

    public AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            mBinding.getIMainActivity().setQuantity(Integer.parseInt((String)adapterView.getItemAtPosition(i)));
            getDialog().dismiss();
        }
    };
    public View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    };
}
