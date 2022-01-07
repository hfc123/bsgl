package com.hfc.libs.exception;

/**
 * @description:状态栏-无法获取到子view时抛出的异常
 * @author hongfuchang
 * @email 284424243@qq.com
 * @date :2022/1/7 10:35
 **/
public class NoDecorChildViewException extends NullPointerException {

    public static final  String NoDecorChildViewExceptionMessage ="需要在setcontentView之后调用，不然会获取不到布局";
    public NoDecorChildViewException() {
        this(NoDecorChildViewExceptionMessage);
    }

    public NoDecorChildViewException(String s) {
        super(s);
    }
}
