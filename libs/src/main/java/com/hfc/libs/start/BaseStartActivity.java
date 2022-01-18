package com.hfc.libs.start;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.hfc.libs.R;
import com.hfc.libs.fullscreen.FullScreenUtils;
import com.hfc.libs.toast.IToast;
import com.ydtx.jobmanage.library.dialog.baseDialog.CountDownBaseDialog;

/**
 * @author hongfuchang
 * @description:
 * @email 284424243@qq.com
 * @date :2022/1/18 15:18
 **/
public class BaseStartActivity extends Activity {
    ViewStub viewStub;
    int TOTAL_TIME =5;
    int ONECE_TIME =1;
    TextView downview;
    private CountDownTimer countDownTimer ;
    boolean needCountDown;
    private  void startCountDown(){
        if (!needCountDown){
            return;
        }
        countDownTimer= new CountDownTimer((TOTAL_TIME+1)*1000, ONECE_TIME*1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                if (downview!=null){
                    downview.setText( (TOTAL_TIME)--+"");
                }
            }

            @Override
            public void onFinish() {
                IToast.getInstance(BaseStartActivity.this).showI("Finish");
            }
        };
        countDownTimer.start();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.TranslucentTheme);
        super.onCreate(savedInstanceState);
        FullScreenUtils.setFullScreen(this);

        setContentView(R.layout.startlayout);
        viewStub =findViewById(R.id.skipview);
        setSkipView(R.id.skiplayout,R.layout.skipview);
        startCountDown();
    }

    //该方法只能替换一次
    public void  setSkipView(@IdRes int inflatedId, @LayoutRes int layoutResource){
    /*    if (viewStub.getParent()!=null)
        ((ViewGroup) viewStub.getParent()).removeView(viewStub);*/
        viewStub.setInflatedId(inflatedId);
        viewStub.setLayoutResource(layoutResource);
        View view = viewStub.inflate();
        downview =  (view.findViewById(R.id.skip));
        FullScreenUtils.fitsAllPadding(this,view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSkipClick(v);
            }
        });

    }

    private void onSkipClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        canCelCountDown();
        startCountDown();
    }

    @Override
    protected void onPause() {
        super.onPause();
        canCelCountDown();
    }
    private void canCelCountDown(){
        if (countDownTimer!=null)
        countDownTimer.cancel();
    }
}
