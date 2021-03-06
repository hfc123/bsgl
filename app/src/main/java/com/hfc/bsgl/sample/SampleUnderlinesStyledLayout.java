package com.hfc.bsgl.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.hfc.libs.banner.jakewhartonindicator.UnderlinePageIndicator;
import com.hfc.bsgl.R;
public class SampleUnderlinesStyledLayout extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themed_underlines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (UnderlinePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}