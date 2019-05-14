package com.pattern.wistronits.patterntest;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.pattern.wistronits.patterntest.linked.LinkedQueueConsume;
import com.pattern.wistronits.patterntest.linked.LinkedQueueProduct;
import com.pattern.wistronits.patterntest.product.Consume;
import com.pattern.wistronits.patterntest.product.Product;
import com.pattern.wistronits.patterntest.product.ProductList;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class ConsumeProductActivity extends Activity {

    private int mLastX;
    private int mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_product);


        ExecutorService executors = Executors.newCachedThreadPool();

        ConcurrentLinkedQueue linkedBlockingQueue = new ConcurrentLinkedQueue();
        executors.execute(new LinkedQueueProduct(linkedBlockingQueue));
        executors.execute(new LinkedQueueConsume(linkedBlockingQueue, "1"));
        executors.execute(new LinkedQueueConsume(linkedBlockingQueue, "2"));
        ConcurrentHashMap map = new ConcurrentHashMap();


        findViewById(R.id.btn_behavior).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mLastX = (int) event.getRawX();
                        mLastY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int newX = (int) event.getRawX();
                        int newY = (int) event.getRawY();

                        v.setX(newX);
                        v.setY(newY);
                        mLastX = newX;
                        mLastY = newY;

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


}
