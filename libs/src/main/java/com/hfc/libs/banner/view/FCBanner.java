package com.hfc.libs.banner.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hfc.libs.R;
import com.hfc.libs.banner.adapter.HFCViewAdapter;
import com.hfc.libs.banner.imageloader.DefaultImageView;
import com.hfc.libs.banner.interfaces.HFCPagerTransformer;
import com.hfc.libs.banner.interfaces.ViewCreator;
import com.hfc.libs.banner.jakewhartonindicator.CirclePageIndicator;
import com.hfc.libs.banner.jakewhartonindicator.PageIndicator;
import com.hfc.libs.banner.transformer.CubeTransformer;
import com.hfc.libs.dputils.DimUtil;
import com.hfc.libs.toast.IToast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hongfuchang
 * @description:
 * @email 284424243@qq.com
 * @date :2022/1/11 14:32
 **/
public class FCBanner extends RelativeLayout implements ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private PageIndicator pageIndicator ;//
    private List<View> views = new ArrayList<>();
    private PagerAdapter pagerAdapter;
    private boolean isLoop;
    private boolean canLoop;
    private AutoRunable autoRunable = new AutoRunable(this);
    private float delaytime=2;//秒
    private ViewCreator viewCreator;
    public  FCBanner(Context context) {
        this(context,null);
    }
    public  FCBanner(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FCBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FCBanner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        initViewPager();
        initIndicator(new CirclePageIndicator(getContext()));
        startLoop();

    }
    public  void  startLoop(){
        if (views.size()>1)
        postDelayed(autoRunable, (long) (delaytime*1000));
    }
    private void initIndicator(PageIndicator pageIndicator) {

        if (this.pageIndicator!=null){
        //已添加过
            removeView((View) this.pageIndicator);
        }
        View  pageIndicatorV= (View) pageIndicator;
        this.pageIndicator =pageIndicator;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        //代码接收的是Pixel 需抓换成px
        params.setMargins(0,0,0, (int) DimUtil.dipToPx(30));
        pageIndicatorV.setLayoutParams(params);
        addView(pageIndicatorV);
        if (viewPager!=null)
        pageIndicator.setViewPager(viewPager);
    }
    private void initViewPager() {
        if (viewPager==null)
        viewPager =new ViewPager(getContext(),null);//默认的viewpager
        initViewPager(viewPager);

    }

    private void initViewPager(ViewPager viewpager){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        viewpager.setLayoutParams(params);
        viewpager.addOnPageChangeListener(this);
        addView(viewpager);
        viewpager.setAdapter(getPagerAdapter());
        setViewPagerTransformer();
    }
    public PagerAdapter getPagerAdapter(){
        if (pagerAdapter==null)
            pagerAdapter=new HFCViewAdapter(getContext(),views);
        return pagerAdapter;
    }

    public void setPagerAdapter(PagerAdapter pagerAdapter) {
        this.pagerAdapter = pagerAdapter;
    }

    public ViewPager getViewPager() {
        if (viewPager==null){
            initViewPager();;
        }
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.viewPager!=null)
        removeView(this.viewPager);//移除后添加
        initViewPager(viewPager);
        pageIndicator.setViewPager(viewPager);
        this.viewPager = viewPager;
    }

    public PageIndicator getPageIndicator() {
        return pageIndicator;
    }

    public void setPageIndicator(PageIndicator pageIndicator) {
        this.pageIndicator = pageIndicator;
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        Log.e("onPageScroll1: ", i+"");
    }

    @Override
    public void onPageSelected(int i) {
        Log.e("onPageScroll2: ", i+"");
    }
    /*
    * （0，1，2）。arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
    * */
    @Override
    public void onPageScrollStateChanged(int state) {
        Log.e("onPageScroll3: ", state+"");
        switch (state) {
            case 0:
            case 1:
                if (viewPager.getCurrentItem() == 0) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 2, false);
                } else if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1) {
                    viewPager.setCurrentItem(1, false);
                }
                break;
            case 2:
                break;
        }
    }


    class  AutoRunable implements Runnable {
        private final WeakReference<FCBanner> mBanner;

        private AutoRunable(FCBanner banner) {
            mBanner = new WeakReference<>(banner);
        }

        @Override
        public void run() {
            FCBanner banner = mBanner.get();
            if (banner != null&&canLoop) {
                isLoop=true;
                postDelayed(autoRunable, (long) (delaytime*1000));
                int currentpage= viewPager.getCurrentItem();
                if (currentpage==1)
                banner.getViewPager().setCurrentItem(++currentpage%viewPager.getAdapter().getCount(),false);
                else
                banner.getViewPager().setCurrentItem(++currentpage%viewPager.getAdapter().getCount(),true);
            }else {
                isLoop =false;
            }
        }
    }


    boolean oldautorun;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldautorun = canLoop;
                canLoop =false;
                isLoop =false;
                break;
            case MotionEvent.ACTION_UP:
                canLoop =oldautorun;
                removeCallbacks(autoRunable);
                postDelayed(autoRunable, (long) (delaytime*1000));
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public void setBannerList(List<Object> list){
        setBannerList(list,false);
    }
    public void setBannerViewList(List<View> list){
        setBannerViewList(list,true);
    }
    //图片
    public void setBannerList(List<Object> list,boolean needLoop){
        canLoop =needLoop;
        if (list==null||list.size()<=0){
            IToast.getInstance(getContext()).showI("列表为空");
            return;
        }
        if (list.size()>=2){
            Object indexFirst =list.get(0);
            Object indexLast  =list.get(list.size()-1);
            list.add(0,indexLast);
            list.add(list.size(),indexFirst);
        }

        views.clear();
        for (int i = 0; i < list.size(); i++) {
             views.add( getViewCreator().createView(getContext(),list.get(i)));

        }
        if (views.size()==1){
            notifyDataSetChanged(0);
        }else {
            notifyDataSetChanged(1);
        }

    }
    //设置自定义的控件
    public void setBannerViewList(List<View> list,boolean needLoop){
        canLoop =needLoop;
        if (list==null||list.size()<=0){
            IToast.getInstance(getContext()).showI("列表为空");
            return;
        }
        if (list.size()>=2){
            View indexFirst =list.get(0);
            View indexLast  =list.get(list.size()-1);
            list.add(0,indexLast);
            list.add(list.size(),indexFirst);
        }

        views.clear();
        views.addAll(list);
        if (views.size()==1){
            notifyDataSetChanged(0);
        }else {
            notifyDataSetChanged(1);
        }
    }

    public void notifyDataSetChanged(int currentItem) {
        viewPager.setFocusable(true);
        viewPager.setAdapter(getPagerAdapter());
        pagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(currentItem);

        if (canLoop){
            postDelayed(autoRunable, (long) (delaytime*1000));
        }
    }

    public ViewCreator getViewCreator() {
        if (viewCreator==null){
            viewCreator = new DefaultImageView();
        }
        return viewCreator;
    }

    public void setViewCreator(ViewCreator viewCreator) {
        this.viewCreator = viewCreator;
    }

    public boolean isCanLoop() {
        return canLoop;
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }

    public void  setViewPagerTransformer(){
        getViewPager().setPageTransformer(true,getPagerTransformer());
    }
    HFCPagerTransformer  transformer ;

    public HFCPagerTransformer getTransformer() {
        return transformer;
    }

    public void setTransformer(HFCPagerTransformer transformer) {
        this.transformer = transformer;
        setViewPagerTransformer();
    }

    public HFCPagerTransformer getPagerTransformer(){
        return transformer;
    }
}
