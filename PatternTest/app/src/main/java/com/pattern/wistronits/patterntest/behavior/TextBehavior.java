package com.pattern.wistronits.patterntest.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author wistronits
 */
public class TextBehavior extends CoordinatorLayout.Behavior<TextView> {

    public TextBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {
        return dependency instanceof Button;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {

        child.setX(dependency.getX());
        child.setY(dependency.getY());
        child.setText(dependency.getX()+"---"+dependency.getY());
        return true;
    }
}
