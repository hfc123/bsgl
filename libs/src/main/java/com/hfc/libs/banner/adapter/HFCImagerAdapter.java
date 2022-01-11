package com.hfc.libs.banner.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hfc.libs.R;

import java.util.List;

/**
 * @author hongfuchang
 * @description:
 * @email 284424243@qq.com
 * @date :2022/1/11 14:53
 **/
public class HFCImagerAdapter extends PagerAdapter {
    ImageView[] imageViews=new ImageView[5];
    Context mContext ;

    public HFCImagerAdapter(Context mContext) {
        this.mContext = mContext;
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i]=new ImageView(mContext);
        }
        imageViews[0].setImageDrawable(mContext.getResources().getDrawable(R.drawable.bg1));
        imageViews[1].setImageDrawable(mContext.getResources().getDrawable(R.drawable.bg2));
        imageViews[2].setImageDrawable(mContext.getResources().getDrawable(R.drawable.bg3));
        imageViews[3].setImageDrawable(mContext.getResources().getDrawable(R.drawable.bg4));
        imageViews[4].setImageDrawable(mContext.getResources().getDrawable(R.drawable.bg2));
    }

    @Override
    public int getCount() {
        return imageViews.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        imageViews[position].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        container.addView(imageViews[position]);
        return imageViews[position];
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(imageViews[position]);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
