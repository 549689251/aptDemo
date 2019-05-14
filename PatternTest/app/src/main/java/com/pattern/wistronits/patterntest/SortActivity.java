package com.pattern.wistronits.patterntest;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SortActivity extends BaseActivity {


    private TextView mContentTv;
    private int[] mSortArrays = {1, 12, 9, 11, 7, 33, 1999, 334, 0, 23, 22, 51, 44, 99, 11};

    @Override
    protected void initData() {
        selectSort();
        mContentTv = findViewById(R.id.tv_content);


        String dateStr = "2019-1-23";
        String dateStr2 = "2019-5-1 ";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date2 = format.parse(dateStr2);
            Date date = format.parse(dateStr);

            System.out.println("两个日期的差距：" + differentDaysByMillisecond(date, date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.gdd.brocast");
        this.registerReceiver(new MyBrocastReceiver(), intentFilter);

        Intent intent = new Intent(this, MyBrocastReceiver.class);
        intent.setAction("com.gdd.brocast");
        this.sendBroadcast(intent);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_sort;
    }

    private void showDubble() {
        int temp;
        for (int i = 0; i < mSortArrays.length; i++) {
            for (int j = i + 1; j < mSortArrays.length; j++) {
                if (mSortArrays[i] > mSortArrays[j]) {
                    temp = mSortArrays[j];
                    mSortArrays[j] = mSortArrays[i];
                    mSortArrays[i] = temp;
                }
            }
        }

        Log.d("SortActivity", mSortArrays.toString());
    }


    private void selectSort() {

        int temp;
        int index;
        for (int i = 0; i < mSortArrays.length; i++) {
            index = i;
            for (int j = i + 1; j < mSortArrays.length; j++) {
                if (mSortArrays[j] < mSortArrays[index]) {
                    index = j;
                }
            }

            temp = mSortArrays[index];
            mSortArrays[index] = mSortArrays[i];
            mSortArrays[i] = temp;
        }

        Log.d("SortActivity2", mSortArrays.toString());


    }


    public int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }
}
