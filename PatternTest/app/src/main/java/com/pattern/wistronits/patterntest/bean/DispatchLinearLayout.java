package com.pattern.wistronits.patterntest.bean;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class DispatchLinearLayout extends LinearLayout {
    public DispatchLinearLayout(Context context) {
        super(context);
    }

    public DispatchLinearLayout(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("---DispatchLinearLayout","onTouchEvent");

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("-onInterceptTouchEvent","onInterceptTouchEvent");

        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("---DispatchLinearLayout","dispatchTouchEvent");

        return super.dispatchTouchEvent(ev);
    }
}
