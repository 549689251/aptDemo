package com.pattern.wistronits.patterntest.linked;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

public class LinkedQueueConsume implements Runnable {


    private ConcurrentLinkedQueue mLinkedTransferQueue;

    private String tag;

    public LinkedQueueConsume(ConcurrentLinkedQueue mLinkedTransferQueue, String tag) {
        this.mLinkedTransferQueue = mLinkedTransferQueue;
        this.tag = tag;

    }

    @Override
    public void run() {


        while (true) {
            try {
                if (!mLinkedTransferQueue.isEmpty()) {
                    int position = (int) mLinkedTransferQueue.poll();
                    Log.d("LinkedQueueConsume", "消费了----" + tag + "--------" + position);
                    Thread.sleep(10);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
