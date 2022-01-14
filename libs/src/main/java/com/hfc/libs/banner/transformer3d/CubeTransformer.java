package com.hfc.libs.banner.transformer3d;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.hfc.libs.banner.interfaces.HFCPagerTransformer;

/**
 * @author hongfuchang
 * @description:
 * @email 284424243@qq.com
 * @date :2022/1/14 10:38
 **/
public class CubeTransformer  implements ViewPager.PageTransformer, HFCPagerTransformer {

        @Override
        public void transformPage(View view, float position) {
            //设置中心点缩放
            view.setPivotX(position < 0f ? view.getWidth() :0f);
            view.setPivotY(view.getHeight()*0.5f);
            view.setRotationY(position * 45f);
        }

}
