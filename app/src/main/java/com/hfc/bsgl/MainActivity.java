package com.hfc.bsgl;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.hfc.libs.banner.jakewhartonindicator.CirclePageIndicator;
import com.hfc.libs.banner.jakewhartonindicator.IconPageIndicator;
import com.hfc.libs.banner.jakewhartonindicator.IconPagerAdapter;
import com.hfc.libs.fullscreen.FullScreenUtils;
import com.hfc.libs.fullscreen.StatusBarsUtil;

public class MainActivity extends AppCompatActivity {
    ImageView[]imageViews = new ImageView[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*恢复全屏的主题*/
//        setTheme(R.style.TranslucentTheme);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FullScreenUtils.setFullScreen(this,R.color.transtport,true,true);
       /* StatusBarsUtil.setStatusBarColor(this, R.color.colorPrimary);

        StatusBarsUtil.setStatusBarWordColor(this);*/
//       getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewPager viewPager = findViewById(R.id.viewpager);
        IconPageIndicator circlePageIndicator = findViewById(R.id.circlepageindicator);
        ImageView iv = new ImageView(this);
        ImageView iv2 = new ImageView(this);
        ImageView iv3 = new ImageView(this);
        iv.setImageDrawable(getResources().getDrawable(R.drawable.bg2));
        iv2.setImageDrawable(getResources().getDrawable(R.drawable.bg3));
        iv3.setImageDrawable(getResources().getDrawable(R.drawable.bg4));
        imageViews[0]=iv;
        imageViews[1]=iv2;
        imageViews[2]=iv3;
        viewPager.setAdapter(new MyPagerAdapter());
        circlePageIndicator.setViewPager(viewPager);
    }

    class MyPagerAdapter extends PagerAdapter implements IconPagerAdapter {
        int []imgs=new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        protected  final int[] ICONS = new int[] {
                R.drawable.perm_group_calendar,
                R.drawable.perm_group_camera,
                R.drawable.perm_group_device_alarms
        };
        @Override
        public int getIconResId(int index) {
            return ICONS[index];
        }

        @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view==o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(imageViews[position]);
                return imageViews[position];
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(imageViews[position]);
            }

    }
}
