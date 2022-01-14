package com.hfc.libs.banner.guide;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.hfc.libs.R;
import com.hfc.libs.banner.transformer3d.CubeTransformer;
import com.hfc.libs.banner.view.FCBanner;
import com.hfc.libs.fullscreen.FullScreenUtils;
import com.hfc.libs.toast.IToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hongfuchang
 * @description:引导页框架（倒计时功能未加）
 * @email 284424243@qq.com
 * @date :2022/1/14 17:22
 **/
public  class GuideActivity extends FragmentActivity implements GuideInterface {
    FCBanner hfcbanner;
    ViewStub viewStub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.guidelayout);
        FullScreenUtils.setFullScreen(this);

        hfcbanner =findViewById(R.id.hfcbanner);
        viewStub =findViewById(R.id.skipview);

        setSkipView(R.id.skip,R.layout.skipview);
        initBanner();
        setViewPagerAdapter();
//        setSkipView(R.id.skip,R.layout.skipview);
    }

    //该方法只能替换一次
    public void  setSkipView(@IdRes int inflatedId,@LayoutRes int layoutResource){
    /*    if (viewStub.getParent()!=null)
        ((ViewGroup) viewStub.getParent()).removeView(viewStub);*/
        viewStub.setInflatedId(inflatedId);
        viewStub.setLayoutResource(layoutResource);
        View view = viewStub.inflate();
        FullScreenUtils.fitsAllPadding(this,view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSkipClick(v);
            }
        });
    }

    @Override
    public void onSkipClick(View v) {
        //todo
        IToast.getInstance(this).showI("skip");
    }

    @Override
    public void initBanner() {
        List<Object> iis= new ArrayList<>();
        iis.add(com.hfc.libs.R.drawable.bg1);
        iis.add(com.hfc.libs.R.drawable.bg2);
        iis.add(com.hfc.libs.R.drawable.bg3);
        iis.add(com.hfc.libs.R.drawable.bg4);
        hfcbanner.setBannerList(iis);
//        hfcbanner.setTransformer(new CubeTransformer());
        hfcbanner.setNeedIndicator(false);
    }

    @Override
    public void setTransformer() {
//        hfcbanner.setTransformer(new CubeTransformer());

    }

    @Override
    public void setViewPager() {

    }

    @Override
    public void setViewPagerAdapter() {
        List<GuideFragment>fragments= Arrays.asList(new GuideFragment(),new GuideFragment(),new GuideFragment(),new GuideFragment(),new GuideFragment());
        hfcbanner.getViewPager().setId(R.id.viewpager);
        FragmentAdapterT adapterT =   new FragmentAdapterT(getSupportFragmentManager(),fragments);
        hfcbanner.setNeedLoop(false);
        hfcbanner.setcanAutoLoop(true);
        hfcbanner.getViewPager()
                .setAdapter(adapterT);
        adapterT.notifyDataSetChanged();
    }
}
