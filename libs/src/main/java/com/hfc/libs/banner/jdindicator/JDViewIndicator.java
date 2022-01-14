package com.hfc.libs.banner.jdindicator;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.ColorRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hfc.libs.R;
import com.hfc.libs.banner.jakewhartonindicator.PageIndicator;


public class JDViewIndicator extends LinearLayout implements PageIndicator {

    public JDViewIndicator(Context context) {
        this(context, null);
    }

    public JDViewIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JDViewIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
        setSize(5);
        setDrawableColorResources(R.color.white_20, R.color.white);
    }

    private int drawableWidth;
    private int drawableHeight;
    private int drawableSelectWidth;
    private int drawableSelectHeight;
    private ShapeDrawable drawableNormal;
    private ShapeDrawable drawableSelect;
    private int alphaNormal = 255;
    private int alphaSelect = 255;

    public void setSize(int size) {
        if (this.drawableWidth != size || this.drawableHeight != size) {
            setSize(size, size);
        }
    }

    public void setSize(int drawableWidth, int drawableHeight) {
        if (this.drawableWidth != drawableWidth || this.drawableHeight != drawableHeight) {
            this.drawableWidth = drawableWidth;
            this.drawableHeight = drawableHeight;
            notifyDataSetChanged();
        }
    }

    public void setSizeSelectResources(@IntegerRes int width, @IntegerRes int height) {
        setSizeSelect(getResources().getInteger(width), getResources().getInteger(height));
    }

    public void setSizeSelect(int drawableWidth, int drawableHeight) {
        boolean change = false;
        if (this.drawableSelectWidth != drawableWidth || this.drawableSelectHeight != drawableHeight) {
            this.drawableSelectWidth = drawableWidth;
            this.drawableSelectHeight = drawableHeight;
            change = true;
        }
        if (drawableSelect != null && drawableWidth != drawableHeight) {
            int width = dp2px(drawableWidth);
            int height = dp2px(drawableHeight);
            int size;
            if (width > height) {
                size = height;
            } else {
                size = width;
            }
            drawableSelect.setShape(new RoundRectShape(new float[]{size, size, size, size, size, size, size, size}, null, null));
        }
        if (change) {
            notifyDataSetChanged();
        }
    }

    public void setColorResourcesForAlpha(@ColorRes int colorSelect) {
        setDrawableColor(getResources().getColor(colorSelect), getResources().getColor(colorSelect),
                96, this.alphaSelect);
    }

    public void setDrawableColorResources(@ColorRes int colorNormal, @ColorRes int colorSelect) {
        setDrawableColor(getResources().getColor(colorNormal), getResources().getColor(colorSelect),
                this.alphaNormal, this.alphaSelect);
    }

    public void setDrawableColor(int colorNormal, int colorSelect) {
        setDrawableColor(colorNormal, colorSelect, this.alphaNormal, this.alphaSelect);
    }

    public void setDrawableColor(int colorNormal, int colorSelect, int alphaNormal, int alphaSelect) {
        boolean change = false;
        if (drawableNormal == null) {
            drawableNormal = new ShapeDrawable(new OvalShape());
            drawableNormal.getPaint().setStyle(Paint.Style.FILL);
            change = true;
        }
        if (drawableSelect == null) {
            drawableSelect = new ShapeDrawable(new OvalShape());
            drawableSelect.getPaint().setStyle(Paint.Style.FILL);
            change = true;
        }
        if (drawableNormal.getPaint().getColor() != colorNormal) {
            drawableNormal.getPaint().setColor(colorNormal);
            change = true;
        }
        if (drawableSelect.getPaint().getColor() != colorSelect) {
            drawableSelect.getPaint().setColor(colorSelect);
            change = true;
        }
        if (this.alphaNormal != alphaNormal) {
            this.alphaNormal = alphaNormal;
            drawableNormal.setAlpha(this.alphaNormal);
        }
        if (this.alphaSelect != alphaSelect) {
            this.alphaSelect = alphaSelect;
            drawableSelect.setAlpha(this.alphaSelect);
        }
        if (change) {
            notifyDataSetChanged();
        }
    }

    private int mCount;
    private int mPosition;

    public void setCount(int count) {
        if (this.mCount != count) {
            this.mCount = count;
            notifyDataSetChanged();
        }
    }

    public void to(int position) {
        if (this.mPosition != position) {
            this.mPosition = position;
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return mCount;
    }

    public int getPosition() {
        return mPosition;
    }
    ViewPager viewPager ;
    @Override
    public void setViewPager(ViewPager view) {
        viewPager =view;
        view.setOnPageChangeListener(this);
        setCount(view.getAdapter().getCount()-2);
    }

    @Override
    public void setViewPager(ViewPager view, int initialPosition) {

    }

    @Override
    public void setCurrentItem(int item) {

    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {

    }

    public void notifyDataSetChanged() {
        if (getVisibility() == GONE || getVisibility() == INVISIBLE) {
            return;
        }
        setVisibility(VISIBLE);

        int count = getCount();

        if (count <= 1) {
            removeAllViews();
            return;
        }

        if (getChildCount() > count) {
            for (int i = 0; i < getChildCount() - count; i++) {
                removeView(getChildAt(i));
                i--;
            }
        }

        for (int i = 0; i < count; i++) {
            if (getChildCount() <= i) {
                LayoutParams params = new LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                ImageView view = new ImageView(getContext());
                addView(view, params);
            }
            View view = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int drawableWidth = dp2px(this.drawableWidth);
            int drawableHeight = dp2px(this.drawableHeight);
            if (i == getPosition()
                    && this.drawableSelectHeight > 0 && this.drawableSelectWidth > 0) {
                int drawableSelectWidth = dp2px(this.drawableSelectWidth);
                int drawableSelectHeight = dp2px(this.drawableSelectHeight);
                layoutParams.width = drawableSelectWidth;
                layoutParams.height = drawableSelectHeight;
            } else {
                layoutParams.width = drawableWidth;
                layoutParams.height = drawableHeight;
            }
            layoutParams.leftMargin = drawableWidth / 3;
            layoutParams.rightMargin = drawableWidth / 3;
            view.setVisibility(VISIBLE);
            if (view instanceof ImageView) {
                view.setLayoutParams(layoutParams);
                view.setBackground(
                        i == getPosition() ?
                                this.drawableSelect : this.drawableNormal);
            }
        }
    }

    private int dp2px(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    float mPageOffset;
    @Override
    public void onPageScrolled(int i, float positionOffset, int i1) {
        //需要变换则需要用ondraw方法否则控件容易变形
     /*  Log.e("onPageScrolled: ", +viewPager.getCurrentItem()+","+i);
        int topositon;
        if (viewPager.getCurrentItem()>i){
            //左边滑动
            mPageOffset = 1-positionOffset;
            topositon=viewPager.getCurrentItem()-1;
        }else {
            //右边滑动
            mPageOffset =  positionOffset;
            topositon=viewPager.getCurrentItem()+1;
        }
        Log.e("onPageScrolled: ",mPosition+","+i+","+mPageOffset+"," +i1);
        if (topositon==0){
            reSetViewSize(mPosition,mCount-1);
        }else if (topositon==mCount+1){
            reSetViewSize(mPosition,0);
        }else {
            reSetViewSize(mPosition,topositon-1);
        }*/
    }
    private void reSetViewSize(int a1, int a2){
        int drawableSelectWidth = dp2px(this.drawableSelectWidth);
        int drawableWidth = dp2px(this.drawableWidth);

        View v1=getChildAt(a1);
            View v2= getChildAt(a2);
        if (v1==null||v2==null){
            return;
        }
        ViewGroup.LayoutParams p1 = v1.getLayoutParams();
        ViewGroup.LayoutParams p2 = v2.getLayoutParams();
        p1.width= (int) (drawableSelectWidth-(drawableSelectWidth-drawableWidth)*mPageOffset);
        p2.width= (int) (drawableWidth+(drawableSelectWidth-drawableWidth)*mPageOffset);
        Log.e("reSetViewSize: ",alphaSelect-mPageOffset*(alphaSelect-alphaNormal)+"" );
        if (v1 instanceof ImageView) {
//            v1.getBackground().setAlpha((int) (alphaSelect-mPageOffset*(alphaSelect-alphaNormal)));
//            v2.getBackground().setAlpha((int) (alphaNormal+mPageOffset*(alphaSelect-alphaNormal)));
        }

        v1.setLayoutParams(p1);
        v2.setLayoutParams(p2);

    }
    @Override
    public void onPageSelected(int i) {
        Log.e( "onPageSelected: ",i+"" );
      if (i==0){
          to(mCount-1);
      }else if (i==mCount+1){
          to(0);
      }else {
          to(i-1);
      }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}