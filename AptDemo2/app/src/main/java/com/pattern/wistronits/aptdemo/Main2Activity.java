package com.pattern.wistronits.aptdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pattern.wistronits.apt_annotion.BindView;

public class Main2Activity extends AppCompatActivity {
    @BindView(R.id.tv_content)
    TextView mContentTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
