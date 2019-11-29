package com.qianyue.container.ui.system;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.common.UIhelper;
import com.qianyue.container.utils.InitTopView;
import com.qianyue.container.utils.ViewTool;
import com.qianyue.container.widget.LazyRecyclerView;

public class SystemActivity extends BaseLayoutActivity implements View.OnClickListener {
    private Button btnProduct, btnPersonnel, btnDevice, btnReplenishment, btnForm, btnCargo;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        InitTopView.initTitle("系统设置", this);
        leftBtn.setVisibility(View.GONE);
        rightBtn.setText("退出");

        View view = LayoutInflater.from(this).inflate(R.layout.item_system_layout, null);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
        linearLayoutCompat.addView(view);
        findView(view);
        pageLayout();
    }

    private void findView(View view) {
        btnProduct = ViewTool.findById(view, R.id.system_product_btn);
        btnPersonnel = ViewTool.findById(view, R.id.system_personnel_btn);
        btnReplenishment = ViewTool.findById(view, R.id.system_replenishment_btn);
        btnDevice = ViewTool.findById(view, R.id.system_device_btn);
        btnForm = ViewTool.findById(view, R.id.system_form_btn);
        btnCargo = ViewTool.findById(view, R.id.system_cargo_way);
        btnProduct.setOnClickListener(this);
        btnPersonnel.setOnClickListener(this);
        btnReplenishment.setOnClickListener(this);
        btnDevice.setOnClickListener(this);
        btnForm.setOnClickListener(this);
        btnCargo.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
    }

    private void pageLayout() {
        int type = getIntent().getIntExtra("type", 1);
        if (type == 1) { // 运维人员
            btnPersonnel.setVisibility(View.GONE);
            btnForm.setVisibility(View.GONE);
            btnCargo.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.system_product_btn:
                UIhelper.gotoProductActivity(this);
                break;
            case R.id.system_personnel_btn:
                UIhelper.gotoPersonnelActivity(this);
                break;
            case R.id.system_replenishment_btn:
                UIhelper.gotoReplenishmentActivity(this);
                break;
            case R.id.system_device_btn:
                UIhelper.gotoDeviceActivity(this);
                break;
            case R.id.system_form_btn:
                UIhelper.gotoReportActivity(this);
                break;
            case R.id.system_cargo_way:
                UIhelper.gotoAisleActivity(this);
                break;
            case R.id.layout_right_btn:
                finish();
                break;
        }
    }
}
