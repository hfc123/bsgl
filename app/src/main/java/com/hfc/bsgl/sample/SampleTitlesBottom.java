package com.hfc.bsgl.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.hfc.libs.banner.jakewhartonindicator.TitlePageIndicator;
import com.hfc.bsgl.R;
public class SampleTitlesBottom extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles_bottom);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}