package com.cfox.circleimageviewdemo.imageview;

import android.content.Context;
import android.util.AttributeSet;

import com.cfox.circleimageviewdemo.R;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : CircleImageViewDemo
 * <br/>PACKAGE_NAME : com.cfox.circleimageviewdemo.imageview
 * <br/>AUTHOR : CFOX
 * <br/>DATA : 2016/5/31 0031
 * <br/>TIME : 9:39
 * <br/>MSG :
 * <br/>************************************************
 */
public class CircleImageView extends CircleImageViewBase {

    public CircleImageView(Context paramContext) {
        super(paramContext);
    }

    public CircleImageView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public CircleImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    /**
     * 设置默认值
     */
    @Override
    protected void initConfig() {
        setUseDefaultStyle(false);
        setIsFill(true);
        setBorderColor(R.color.colorPrimaryDark);
        setBorderWidth(15);
        setText("hello");
        setTextBackgroundColorRes(R.color.colorPrimaryDark);

        setTextColorRes(R.color.colorAccent);
    }
}
