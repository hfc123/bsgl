package com.hfc.libs.banner.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hfc.libs.R;
import com.hfc.libs.banner.adapter.HFCImagerAdapter;

/**
 * @author hongfuchang
 * @description:
 * @email 284424243@qq.com
 * @date :2022/1/11 14:32
 **/
public class HFCBanner1 extends RelativeLayout {

    ViewPager viewPager;
    public HFCBanner1(Context context) {
        this(context,null);
    }

    public HFCBanner1(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HFCBanner1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HFCBanner1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        initViewPager();
        initIndicator();
    }

    private void initIndicator() {

    }

    private void initViewPager() {
        ViewPager viewPager =new ViewPager(getContext(),null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(params);
        addView(viewPager);
        initViewPager(viewPager);

    }
   private void initViewPager(ViewPager viewpager){
        viewpager.setAdapter(new HFCImagerAdapter(getContext()));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
