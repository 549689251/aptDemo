package com.pattern.wistronits.aptdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pattern.wistronits.apt_annotion.BindView;
import com.pattern.wistronits.apt_library.BindViewTools;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView mContentTv;

    @BindView(R.id.iv_content)
    ImageView mIv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindViewTools.bind(this);
        mContentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
