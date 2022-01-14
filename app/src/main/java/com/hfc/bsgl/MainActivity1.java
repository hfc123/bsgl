package com.hfc.bsgl;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hfc.libs.banner.interfaces.HFCBanner;
import com.hfc.libs.banner.transformer.CubePageTransformer;
import com.hfc.libs.banner.transformer.CubeTransformer;
import com.hfc.libs.banner.view.FCBanner;
import com.hfc.libs.test.TestActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongfuchang
 * @description:
 * @email 284424243@qq.com
 * @date :2022/1/11 15:28
 **/
public class MainActivity1 extends TestActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Object> iis= new ArrayList<>();
        iis.add(com.hfc.libs.R.drawable.bg1);
        iis.add(com.hfc.libs.R.drawable.bg2);
        iis.add(com.hfc.libs.R.drawable.bg3);
        iis.add(com.hfc.libs.R.drawable.bg4);
        FCBanner bn= findViewById(R.id.hfcbanner);
        bn.setBannerList(iis);
        bn.setTransformer(new CubeTransformer());
    }
}
