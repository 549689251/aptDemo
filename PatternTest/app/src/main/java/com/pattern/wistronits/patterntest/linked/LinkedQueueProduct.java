package com.pattern.wistronits.patterntest.linked;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

public class LinkedQueueProduct implements Runnable {
    private ConcurrentLinkedQueue mLinkedTransferQueue;


    public LinkedQueueProduct(ConcurrentLinkedQueue mLinkedTransferQueue) {
        this.mLinkedTransferQueue = mLinkedTransferQueue;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            Log.d("LinkedQueueProduct", i + "----" );
            try {
                mLinkedTransferQueue.add(i);
                Thread.sleep(0);

            } catch (Exception e) {

            }
        }
    }
}
