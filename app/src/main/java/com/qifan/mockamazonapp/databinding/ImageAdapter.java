package com.qifan.mockamazonapp.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.qifan.mockamazonapp.R;

public class ImageAdapter {
    @BindingAdapter("imageAdapter")
    public static void setImage(ImageView view,int imageURL){
        Context context = view.getContext();
        RequestOptions requestOptions=new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(imageURL)
                .into(view);
    }

    @BindingAdapter("imageAdapter")
    public static void setImage(ImageView view,String imageURL){
        Context context = view.getContext();
        RequestOptions requestOptions=new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(imageURL)
                .into(view);
    }


    @BindingAdapter({"imageResource","requestListener"})
    public static void bindRequestListener(ImageView view, int imageURL, RequestListener requestListener){
        Context context = view.getContext();
        RequestOptions requestOptions=new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(imageURL)
                .listener(requestListener)
                .into(view);
    }

}
