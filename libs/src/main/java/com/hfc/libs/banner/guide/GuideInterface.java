package com.hfc.libs.banner.guide;

import android.content.DialogInterface;
import android.view.View;

/**
 * @author hongfuchang
 * @description:
 * @email 284424243@qq.com
 * @date :2022/1/14 17:58
 **/
public interface GuideInterface {
    void onSkipClick(View v);
    void initBanner();
    void setTransformer();
    void setViewPager();
    void setViewPagerAdapter();

}
