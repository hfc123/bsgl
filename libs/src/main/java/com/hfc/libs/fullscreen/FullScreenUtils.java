package com.hfc.libs.fullscreen;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.hfc.libs.exception.NoDecorChildViewException;
import static com.hfc.libs.fullscreen.StatusBarsUtil.getNavigationBarHeight;
import static com.hfc.libs.fullscreen.StatusBarsUtil.getStatusBarHeight;

/**
 * @description:状态栏-沉浸式控制工具
 * @author hongfuchang
 * @email 284424243@qq.com
 * @date :2022/1/7 10:35
 **/
public class FullScreenUtils {

    public static  void setFullScreen(@NonNull Activity activity, @ColorRes int color,boolean bgfitwindow,boolean viewfitwindow){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setFullScreen21(activity,color,bgfitwindow,viewfitwindow);
        }else if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)){
            setFullScreen19(activity,color,viewfitwindow);
        }
    }

    // 代码设置4.4-5.0
    @TargetApi(19)
    public static void setFullScreen19(Activity activity,@ColorRes int color,boolean fitwindow){
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //orxml  <item name="android:windowTranslucentStatus" tools:ignore="NewApi">true</item>
        setDecorView(activity, activity.getResources().getColor(color),fitwindow);
    }

    @TargetApi(21)
    public static void setFullScreen21(@NonNull Activity activity, @ColorRes int color,boolean bgfitwindow,boolean viewfitwindow){
        //在Android 5.0使图片延伸到状态栏，只需设置windowTranslucentStatus,将 statusBarColor 设置为透明即可
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS|WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag 则背景不延申到状态栏
        if (bgfitwindow){
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (viewfitwindow){
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            //rootview不会为状态栏留出状态栏空间
            if (rootView==null){
                throw new NoDecorChildViewException();
            }
            ViewCompat.setFitsSystemWindows(rootView,true);
            rootView.setClipToPadding(true);
        }
        activity.getWindow().setStatusBarColor(activity.getResources().getColor(color));
        //or style code
        /*
        <style name="MDTheme" parent="Theme.Design.Light.NoActionBar">
        <item name="android:windowTranslucentStatus">false</item>
        <item name="android:windowDrawsSystemBarBackgrounds">true</item>
        <item name="android:statusBarColor">@android:color/holo_red_light</item>
        </style>
        */
    }


    public static void setDecorView(Activity activity,@ColorInt int color,boolean fitWindow){
        //获取windowphone下的decorView
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        int count  = decorView.getChildCount();
        //判断是否已经添加了statusBarView
        if (count > 0 && decorView.getChildAt(count - 1) instanceof StatusBarView) {
            decorView.getChildAt(count - 1).setBackgroundColor(color);
        } else {
            //新建一个和状态栏高宽的view
            StatusBarView statusView = createStatusBarView(activity,color );
            decorView.addView(statusView);
        }
        //是否延申到状态栏
        //次方法需在setcontentview之后使用
        if (fitWindow){
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            //rootview不会为状态栏留出状态栏空间
            if (rootView==null){
                throw new NoDecorChildViewException();
            }
            ViewCompat.setFitsSystemWindows(rootView,true);
            rootView.setClipToPadding(true);
        }

    }


    private static StatusBarView createStatusBarView(Activity activity, int color) {
        // 绘制一个和状态栏一样高的矩形
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(color);
        return statusBarView;
    }
    static class StatusBarView extends View{

        public StatusBarView(Context context) {
            super(context);
        }

        public StatusBarView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }
    }

    /**
     * 适应系统窗口（PaddingTop & PaddingBottom）
     *
     * @param activity           Activity对象
     * @param isFitStatusBar     是否适应状态栏
     * @param isFitNavigationBar 是否适应导航栏
     * @param views              适应窗口的View
     */
    private static void fitsPadding(Activity activity, boolean isFitStatusBar, boolean isFitNavigationBar, boolean reset, View... views) {
        if (views == null || views.length < 1) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = 0;
            if (isFitStatusBar) {
                statusBarHeight = getStatusBarHeight(activity);
            }
            int navigationBarHeight = 0;
            if (isFitNavigationBar) {
                navigationBarHeight = getNavigationBarHeight(activity);
            }
            for (View view : views) {
                if (view != null) {
                    fitsPadding(view, 0, statusBarHeight, 0, navigationBarHeight, reset);
                }
            }
        }
    }
    /**
     * Padding适应配置
     *
     * @param view   目标View
     * @param left   PaddingLeft
     * @param top    PaddingTop
     * @param right  PaddingRight
     * @param bottom PaddingBottom
     * @param reset  是否重置原有Padding
     */
    public static void fitsPadding(View view, int left, int top, int right, int bottom, boolean reset) {
        if (view == null) {
            return;
        }
        if (reset) {
            view.setPadding(left, top, right, bottom);
        } else {
            view.setPadding(view.getPaddingLeft() + left, view.getPaddingTop() + top, view.getPaddingRight() + right, view.getPaddingBottom() + bottom);
        }
    }

}
