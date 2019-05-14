package com.pattern.wistronits.patterntest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.pattern.wistronits.patterntest.bean.Child;
import com.pattern.wistronits.patterntest.bean.ObjBean;
import com.pattern.wistronits.patterntest.bean.Parent;
import com.pattern.wistronits.patterntest.bean.Student;
import com.pattern.wistronits.patterntest.imgeload.LucImageLoader;

public class MainActivity extends Activity {
    private EventBtn mContentTv;
    private Context mContext;

    private ImageView mContentImageTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mContentTv = findViewById(R.id.tv_content);
        mContentTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("----------", "onTouch");
                return false;
            }
        });

        findViewById(R.id.drag_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "drag_view", Toast.LENGTH_SHORT).show();
            }
        });
        mContentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "show msg", Toast.LENGTH_SHORT).show();
                Log.d("----------", "onclick ");

            }
        });

        findViewById(R.id.dll_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CountActivity.class);
                mContext.startActivity(intent);
            }
        });

        test();

        mContentImageTv = findViewById(R.id.iv_content);
        LucImageLoader.displayImage("https://cdn2.jianshu.io/assets/web/qingteng-be381813e92784a4c01c608834f76eb2.png", mContentImageTv);
    }

    private void test() {
        PatternImpl pattern = new PatternImpl();
        ObjBean objBean = pattern.loadOnlineData();

        PatternBean<Student> patternBean = new PatternBean<>(new Student());

        showIntegerData(patternBean);

        PatternBean<Parent> intPatternBean = new PatternBean<>(new Parent());

        showStringData(intPatternBean);
        // Log.d("-----", objBean.toString());
        showPartData(new PatternBean(new Student()));

    }


    public void showPatternData(PatternBean<?> dPatternBean) {
        Log.d("----", dPatternBean.getD().toString());
    }


    public void showIntegerData(PatternBean<? extends Parent> integerData) {

    }


    public void showStringData(PatternBean<? super Child> stringData) {

    }


    public <T, D> T showPatternDatas(PatternBean<T> data, SecondBean<D> dSecondBean) {

        return data.getD();
    }


    public <T extends Parent> void showPartData(PatternBean<T> t) {
    }

    public <T> T getRealData(T t) {
        return t;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("---MainActivity", "onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("---MainActivity", "dispatchTouchEvent");

        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            Toast.makeText(this, "save data", Toast.LENGTH_SHORT).show();
            outState.putString("outState", "save instance data");
        }
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            String data = (String) savedInstanceState.get("outState");

            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        }
    }
}
