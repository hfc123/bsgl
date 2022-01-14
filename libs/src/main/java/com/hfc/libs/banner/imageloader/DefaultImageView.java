package com.hfc.libs.banner.imageloader;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hfc.libs.banner.interfaces.ViewCreator;

/**
 * @author hongfuchang
 * @description:
 * @email 284424243@qq.com
 * @date :2022/1/13 17:40
 **/
public class DefaultImageView implements ViewCreator<ImageView> {

    @Override
    public ImageView createView(Context context, Object url) {
        ImageView imageView  = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(url).into(imageView);
        return imageView;
    }
}
