package com.qianyue.container.ui.device;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.common.UIhelper;
import com.qianyue.container.utils.InitTopView;

import static android.view.Gravity.CENTER_HORIZONTAL;

public class DeviceActivity extends BaseLayoutActivity implements View.OnClickListener {
    private boolean hasFocus;
    private boolean hasFirst;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        hasFirst = true;
        InitTopView.initTitle("设备管理", this);
        initBtn();
        loadView();
    }

    private void addToView() {
        linearLayoutCompat.setWeightSum(2.0f);
        linearLayoutCompat.addView(btnStyle1(false, "设备调试", R.id.item_device_child_device_btn));
        linearLayoutCompat.addView(btnStyle1(true, "设置IMEI", R.id.item_device_child_imei_btn));
    }

    private Button btnStyle1(boolean isTop, String text, int id) {
        Button _btn = new Button(new ContextThemeWrapper(mContext, R.style.sysbtn), null, R.style.sysbtn);
        int width = linearLayoutCompat.getWidth() / 3;
        Log.i(getClass().getName(), "宽度=" + width);
        LinearLayoutCompat.LayoutParams _lp = new LinearLayoutCompat.LayoutParams(width, 0, 1.0f);
        _lp.gravity = CENTER_HORIZONTAL;
        if (isTop) {
            _lp.setMargins(0, (int) getResources().getDimension(R.dimen.default_half_dp), 0, 0);
        } else {
            _lp.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.default_half_dp));
        }
        _btn.setLayoutParams(_lp);
        _btn.setText(text);
        _btn.setId(id);
        _btn.setOnClickListener(this);
        return _btn;
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
            addToView();
            hasFirst = false;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_device_child_imei_btn:
                UIhelper.gotoIMEIActivity(this);
                break;
            case R.id.item_device_child_device_btn:
                UIhelper.gotoDeviceChildActivity(this);
                break;
        }
    }
}
