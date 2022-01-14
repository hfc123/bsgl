package com.hfc.libs.banner.interfaces;

import android.content.Context;
import android.view.View;

/**
 * @author hongfuchang
 * @description:
 * @email 284424243@qq.com
 * @date :2022/1/13 17:38
 **/
public interface ViewCreator<T extends View> {

    public T createView(Context context , Object url);
}
