package com.qianyue.container.ui.device.imei;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.utils.ConstantUtil;
import com.qianyue.container.utils.InitTopView;
import com.qianyue.container.utils.RxSPTool;
import com.qianyue.container.utils.ViewTool;

public class ImeiActivity extends BaseLayoutActivity {
    private EditText etImei;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        InitTopView.initTitle("设置IMEI", this);
        initBtn();
        addToView();
    }

    private void initBtn() {
        rightBtn.setText("返回");
        leftBtn.setText("确定");
        rightBtn.setOnClickListener(v -> finish());
        leftBtn.setOnClickListener(v -> saveImei(etImei.getText().toString().trim()));
    }

    private void addToView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_imei, linearLayoutCompat, true);
        etImei = ViewTool.findById(view, R.id.item_imei_et);
    }

    private void saveImei(String imei) {
        RxSPTool.putString(this, ConstantUtil.IMEI, imei);
    }
}
