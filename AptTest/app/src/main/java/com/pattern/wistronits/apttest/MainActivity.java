package com.pattern.wistronits.apttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pattern.wistronits.apt_annotation.BindView;
import com.pattern.wistronits.apt_library.BindViewTools;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    TextView mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindViewTools.bind(this);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("=----","kkk");
            }
        });
    }

}
