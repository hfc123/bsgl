package com.hfc.libs.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hfc.libs.fullscreen.StatusBarsUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*冷启动后需要恢复全屏的主题*/
//        setTheme(R.style.TranslucentTheme);
        super.onCreate(savedInstanceState);

    }
}
