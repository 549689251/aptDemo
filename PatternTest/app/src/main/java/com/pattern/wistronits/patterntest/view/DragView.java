package com.pattern.wistronits.patterntest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Scroller;

/**
 * @author wistronits
 */
public class DragView extends Button {
    private int mLastX;
    private int mLastY;
    private Scroller mScroller;

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                int offSet = mScroller.getCurrY();
                mScroller.startScroll(getScrollX(), getScrollY(), 0, 40 - offSet);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - mLastX;
                int offsetY = y - mLastY;

                //   layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                ((ViewGroup) getParent()).scrollBy(-offsetX, -offsetY);

                break;
            case MotionEvent.ACTION_UP:
                int offset = mScroller.getCurrY() - 40;
                mScroller.startScroll(getScrollX(), getScrollY(), 0, -40 - offset);
                invalidate();
                break;

            default:
                break;
        }


        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }


}
