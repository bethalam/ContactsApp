package com.example.dineshb.contactsapp.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.example.dineshb.contactsapp.R;
import com.example.dineshb.contactsapp.customComponents.Constants;
import com.example.dineshb.contactsapp.interfaces.InterfaceSheetShowing;

public class OtpDialogFragment extends BottomSheetDialogFragment {

    View contentView;
    private InterfaceSheetShowing interfaceSheetShowing;
    View.OnClickListener listener;
    TextView message;


    public static OtpDialogFragment newInstance() {
        return new OtpDialogFragment();
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {


        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {

                dismissAllowingStateLoss();
            }


        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    public void showSheet(FragmentManager fragmentManager, InterfaceSheetShowing interfaceSheetShowing, View.OnClickListener listener) {
        this.listener = listener;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(this, getTag());
        ft.commitAllowingStateLoss();
        this.interfaceSheetShowing=interfaceSheetShowing;
        interfaceSheetShowing.notifyShowing(true);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onViewCreated(View contentView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(contentView, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        contentView = View.inflate(getContext(), R.layout.otp_buttom_sheet, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
        message = (TextView) contentView.findViewById(R.id.message);
        message.setText(getString(R.string.your_otp_is) +" " + Constants.OTP(6));
        contentView.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onClick(message);
                }
            }
        });

    }


    @Override
    public void onDestroy() {
        if(interfaceSheetShowing!=null)
            interfaceSheetShowing.notifyShowing(false);
        super.onDestroy();
    }


}