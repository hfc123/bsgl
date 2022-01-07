package com.hfc.bsgl.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.hfc.libs.banner.jakewhartonindicator.TitlePageIndicator;
import com.hfc.libs.banner.jakewhartonindicator.TitlePageIndicator.IndicatorStyle;
import com.hfc.bsgl.R;
public class SampleTitlesTriangle extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        TitlePageIndicator indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setFooterIndicatorStyle(IndicatorStyle.Triangle);
        mIndicator = indicator;
    }
}