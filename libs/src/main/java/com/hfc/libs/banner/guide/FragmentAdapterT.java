package com.hfc.libs.banner.guide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author hongfuchang
 * @description:
 * @email 284424243@qq.com
 * @date :2022/1/14 18:14
 **/
public class FragmentAdapterT extends FragmentPagerAdapter {
    List<GuideFragment>fs;

    public FragmentAdapterT(FragmentManager fm, List<GuideFragment> fs) {
        super(fm);
        this.fs = fs;
    }

    public FragmentAdapterT(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fs.get(i);
    }

    @Override
    public int getCount() {
        return fs.size();
    }
}
