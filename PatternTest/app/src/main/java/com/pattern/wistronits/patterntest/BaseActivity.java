package com.pattern.wistronits.patterntest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initData();
    }

    protected abstract void initData();

    public abstract int getContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
