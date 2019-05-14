package com.pattern.wistronits.patterntest;

import android.app.Dialog;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class BottomSheeltBehaviorActivity extends BaseActivity {

    private FrameLayout mContentFl;


    @Override
    protected void initData() {
        mContentFl = findViewById(R.id.share_view);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.share_view));
        bottomSheetBehavior.setPeekHeight(0);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

        findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment();
            }
        });
    }

    @Override
    public int getContentView() {
        return R.layout.activity_bottom_sheelt_behavior;
    }


    private void showFragment() {
        BottomBehaviorFragment fragment = new BottomBehaviorFragment();
        fragment.show(getSupportFragmentManager(), "");
    }



    @Subscribe
    public void onActionEvent(EventAction action, EventAction action2) {

        Toast.makeText(this, "receive action2", Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onActionEvent(EventAction action) {

        Toast.makeText(this, "receive action1", Toast.LENGTH_SHORT).show();
    }
}
