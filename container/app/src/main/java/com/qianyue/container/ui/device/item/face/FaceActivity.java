package com.qianyue.container.ui.device.item.face;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.utils.InitTopView;
import com.qianyue.container.widget.dialog.FaceDialogFragment;

public class FaceActivity extends BaseLayoutActivity {
    private boolean hasFocus;
    private boolean hasFirst;
    private FaceDialogFragment mDialog;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        hasFirst = true;
        InitTopView.initTitle("设备管理", this);
        initBtn();
    }

    private void initBtn() {
        leftBtn.setVisibility(View.GONE);
        rightBtn.setText("返回");
        rightBtn.setOnClickListener(v -> finish());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.hasFocus = hasFocus;
        loadView();
    }

    private void loadView() {
        if (hasFirst && hasFocus) {
            showDialog();
            hasFirst = false;
        }
    }

    private void showDialog() {
        mDialog = new FaceDialogFragment();
        mDialog.show(getSupportFragmentManager(), "FaceDialogFragment");
    }
}
