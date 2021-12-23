package com.segunfrancis.payoneerpaymentmethods.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideUtils {

    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView)
                .load(imageUrl)
                .into(imageView);
    }
}
