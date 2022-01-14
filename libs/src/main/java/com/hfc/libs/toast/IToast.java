package com.hfc.libs.toast;

import android.content.Context;
import android.os.Build;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hfc.libs.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * @author hongfuchang
 * @description:仿iostoast
 * @email 284424243@qq.com
 * @date :2022/1/13 17:08
 **/
public class IToast {
        private static IToast iToast ;
        private static Context mContext;
        private Toast mToastI;
        private Toast mToastIA;
        private int mDuration;
        public static IToast getInstance(Context mContext2){
            mContext=mContext2;
            if (iToast==null){
                return  new IToast();
            }
            return  iToast;
        }
        //仿iostoast
        public void showI(String msg){
            if (mContext != null) {
                if (TextUtils.isEmpty(msg)) {
                    msg = "接口异常，msg为空";
                }
                if (Build.VERSION.SDK_INT == 28) {
                    mToastI = Toast.makeText(mContext, msg, LENGTH_SHORT);
                }
                if (mToastI == null) {
                        mToastI = Toast.makeText(mContext, msg, LENGTH_SHORT);
                        View toastRoot = LayoutInflater.from(mContext).inflate(R.layout.ios_toast, null);
                        TextView iostoastView =  toastRoot.findViewById(R.id.iostoast);
                        iostoastView.setText(msg);
                        mToastI = new Toast(mContext);
                        mToastI.setView(toastRoot);
                        mToastI.setGravity(Gravity.CENTER, 0, 0);
                }
                mToastI.setDuration(mDuration==0?LENGTH_SHORT:mDuration);
                mToastI.show();
            }
        }


        //安卓toast
        public void showA(String msg){
            if (mContext != null) {
                if (TextUtils.isEmpty(msg)) {
                    msg = "msg为空";
                }
                if (Build.VERSION.SDK_INT == 28) {
                    mToastIA = Toast.makeText(mContext, msg, LENGTH_SHORT);
                }
                if (mToastIA == null) {
                    mToastIA = Toast.makeText(mContext,msg, LENGTH_SHORT);
                }
                mToastIA.setDuration(mDuration==0?LENGTH_SHORT:mDuration);
                mToastIA.show();
            }
        }

        public @interface IntDef {
            String[] prefix() default "";
            int[] value() default {};

            boolean flag() default false;
        }
        /**
         * Show the view or text notification for a short period of time.  This time
         * could be user-definable.  This is the default.
         * @see #setDuration
         */
        public static final int LENGTH_SHORT = 0;

        /**
         * Show the view or text notification for a long period of time.  This time
         * could be user-definable.
         * @see #setDuration
         */
        public static final int LENGTH_LONG = 1;
        /** @hide */
        @IntDef( prefix= { "LENGTH_" }, value = {
                LENGTH_SHORT,
                LENGTH_LONG
        })
        @Retention(RetentionPolicy.SOURCE)
        public @interface Duration {}
        /**
         * Set how long to show the view for.
         * @see #LENGTH_SHORT
         * @see #LENGTH_LONG
         */
        public void setDuration(@Duration int duration) {
            mDuration = duration;
        }
}
