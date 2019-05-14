package com.pattern.wistronits.patterntest.view;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @author wistronits
 */
public class ScrollLayout extends ViewGroup {
    private int mTouchSlop;
    private int mLeftBorder;
    private int mRightBorder;
    private int mLastX = 0;
    private Scroller mScroller;


    public ScrollLayout(Context context) {
        this(context, null);
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat.getScaledHoverSlop(configuration);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.layout(i * view.getMeasuredWidth(), 0, (i + 1) * (view.getMeasuredWidth() + view.getPaddingLeft()), 0);
        }

        //边界
        mLeftBorder = getChildAt(0).getLeft();
        mRightBorder = getChildAt(getChildCount() - 1).getRight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //测量子view
        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }


    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                int currentX = (int) ev.getX();
                int offset = currentX - mLastX;

                //滑动
                if (Math.abs(offset) > mTouchSlop) {
                    return true;
                }
                break;
            default:
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:

                int currentX = (int) event.getX();
                int mScrollX = currentX - mLastX;

                //边界处理
                if (mScrollX < mLeftBorder) {
                    scrollTo(mLeftBorder, 0);
                } else if (mScrollX > mRightBorder) {
                    scrollTo(mRightBorder, 0);
                }
                scrollBy(currentX, 0);
                break;
            case MotionEvent.ACTION_UP:
                // scroller
                //mScroller.startScroll();
                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }
}
