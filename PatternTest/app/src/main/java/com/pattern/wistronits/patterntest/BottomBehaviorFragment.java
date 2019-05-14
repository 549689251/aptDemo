package com.pattern.wistronits.patterntest;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;


/**
 * A simple {@link Fragment} subclass.
 *
 * @author wistronits
 */
public class BottomBehaviorFragment extends BottomSheetDialogFragment {

    private Context mContext;

    public BottomBehaviorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bottom_behavior, container, false);

        rootView.findViewById(R.id.tv_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mContext = getContext();

        rootView.findViewById(R.id.tv_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(mContext);
                dialog.setContentView(R.layout.view_dialog);
                dialog.show();
                EventBus.getDefault().post(new EventAction(true));
            }
        });
        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        return dialog;
    }
}
