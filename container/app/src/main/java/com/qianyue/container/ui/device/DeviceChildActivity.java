package com.qianyue.container.ui.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.common.UIhelper;
import com.qianyue.container.utils.InitTopView;

public class DeviceChildActivity extends BaseLayoutActivity implements View.OnClickListener {
    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        InitTopView.initTitle("设备调试", this);
        initBtn();
        addToView();
    }

    private void initBtn() {
        leftBtn.setVisibility(View.GONE);
        rightBtn.setText("退出");
        rightBtn.setOnClickListener(v -> finish());
    }

    private void findView(View view) {
        view.findViewById(R.id.item_device_cargo_btn).setOnClickListener(this);
        view.findViewById(R.id.item_device_face_btn).setOnClickListener(this);
        view.findViewById(R.id.item_device_goods_btn).setOnClickListener(this);
        view.findViewById(R.id.item_device_all_btn).setOnClickListener(this);
    }

    private void addToView() {
        View rootVeiw = LayoutInflater.from(mContext).inflate(R.layout.item_device, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootVeiw.setLayoutParams(lp);
        linearLayoutCompat.addView(rootVeiw);
        findView(rootVeiw);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_device_cargo_btn:

                break;
            case R.id.item_device_face_btn:
                UIhelper.gotoFaceActivity(this);
                break;
            case R.id.item_device_goods_btn:

                break;
            case R.id.item_device_all_btn:

                break;
        }
    }
}
