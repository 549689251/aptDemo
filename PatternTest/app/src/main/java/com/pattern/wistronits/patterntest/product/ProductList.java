package com.pattern.wistronits.patterntest.product;

import android.util.Log;

public class ProductList {

    private int mCount = 0;


    public synchronized  void increase() {
        if (mCount >= 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        mCount++;
        Log.d("increase", "(" + mCount + ")");
        notify();

    }


    public synchronized void decrease() {
        if (mCount <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mCount--;
        Log.d("decrease", "(" + mCount + ")");
        notify();
    }

}
