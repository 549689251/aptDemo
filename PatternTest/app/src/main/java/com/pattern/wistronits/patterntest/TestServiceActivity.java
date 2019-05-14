package com.pattern.wistronits.patterntest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pattern.wistronits.patterntest.bean.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestServiceActivity extends Activity {


    private boolean mIsBinded;
    private TextView mContentTv;
    private MyService.ShowMsgBinder mShowMsgBinder;
    private LocalBroadcastManager mLocalBroadcastManager;
    private PatternBroadCast mPatternBroadCast = new PatternBroadCast();
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mShowMsgBinder = (MyService.ShowMsgBinder) service;
            mShowMsgBinder.showMsg(3, new MyService.ProgressCallBack() {
                @Override
                public int progress(int progress) {

                    mContentTv.setText(progress + "");
                    return progress;
                }
            });
            mShowMsgBinder.showProgress();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);
        mContentTv = findViewById(R.id.tv_content);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsBinded = !mIsBinded;
                Intent intent = new Intent(TestServiceActivity.this, MyService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);

                Intent intent1 = new Intent(TestServiceActivity.this, SecondActivity.class);
                startActivity(intent1);
            }
        });
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsBinded = !mIsBinded;
                unbindService(serviceConnection);
            }
        });


        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.pattern.wistronits.patterntest");
        mLocalBroadcastManager.registerReceiver(mPatternBroadCast, intentFilter);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gank.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        Call<ResponseData> call = retrofitService.loadData();
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                List<ResponseData.ResultsBean> list = response.body().getResults();
                Log.d("----", list.toString());
                Log.d("----", "onResponse");

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("----", "");

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceConnection != null && mIsBinded) {
            unbindService(serviceConnection);
        }
        mLocalBroadcastManager.unregisterReceiver(mPatternBroadCast);
    }


    public class PatternBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("---PatternBroadCast", "来自service的local 通知");
        }
    }


}
