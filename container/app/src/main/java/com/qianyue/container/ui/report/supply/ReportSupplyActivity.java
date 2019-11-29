package com.qianyue.container.ui.report.supply;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.utils.InitTopView;
import com.qianyue.container.utils.ToastUtil;

public class ReportSupplyActivity extends BaseLayoutActivity implements ReportSupplyView {
    private ReportSupplyPresenter mPresenter = new ReportSupplyPresenter(this, this);
    private RecyclerView mRecyclerView;
    private ProgressDialog mDialog;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        InitTopView.initTitle("补货报表", this);
        initBtn();
        addToView();
        mPresenter.getData();
    }

    private void addToView() {
        View _view = LayoutInflater.from(mContext).inflate(R.layout.item_supply_report, null);
        mRecyclerView = new RecyclerView(mContext);
        ViewGroup.LayoutParams _lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRecyclerView.setLayoutParams(_lp);
        linearLayoutCompat.addView(_view);
        linearLayoutCompat.addView(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mPresenter.bindView(mRecyclerView);
    }

    private void initBtn() {
        leftBtn.setText("导出");
        rightBtn.setText("返回");
        rightBtn.setOnClickListener(v -> finish());
        leftBtn.setOnClickListener(v -> mPresenter.export());
    }

    @Override
    public void showDialog() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(mContext);
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
        }
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void toast(String msg) {
        runOnUiThread(() -> ToastUtil.showShort(mContext, msg));
    }

    @Override
    public void dismissDialog() {
        runOnUiThread(() -> {
            if (mDialog != null)
                mDialog.dismiss();
        });
    }
}
