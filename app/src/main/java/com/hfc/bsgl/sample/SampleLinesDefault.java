package com.hfc.bsgl.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.hfc.libs.banner.jakewhartonindicator.LinePageIndicator;
import com.hfc.bsgl.R;
public class SampleLinesDefault extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_lines);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (LinePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}