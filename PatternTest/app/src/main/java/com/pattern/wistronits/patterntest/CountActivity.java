package com.pattern.wistronits.patterntest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountActivity extends AppCompatActivity {

    private int[] values = {60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 6, 16, 26, 36, 46, 56, 76, 86, 96};

    private Handler mHandler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);


        new Thread(new Runnable() {
            @Override
            public void run() {
                show6();
            }
        }).start();
    }

    private List mList = new ArrayList();

    private void showData() {

        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 1000; j = j + 3) {
                for (int k = 0; k < 1000; k = k + 7) {

                    if (1 * i + j * 3 + k * 7 == 1000) {
                        Log.d("----", "i==" + i + ",j==" + j + ",k==" + k);
                        mList.add(i + j + k);
                    }
                }
            }
        }

        Collections.sort(mList);
        Log.d("Collections----", mList.toString());

    }


    private void show6() {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                for (int k = 0; k < values.length; k++) {

                    if (values[i] + values[j] + values[k] == 100) {
                        Log.e("-------", values[i] + "--" + values[j] + "--" + values[k]);

                    }
                }
            }
        }
    }


}
