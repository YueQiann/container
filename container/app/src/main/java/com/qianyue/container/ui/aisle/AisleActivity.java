package com.qianyue.container.ui.aisle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.utils.ConstantUtil;
import com.qianyue.container.utils.InitTopView;
import com.qianyue.container.utils.RxSPTool;
import com.qianyue.container.utils.ViewTool;

public class AisleActivity extends BaseLayoutActivity implements AisleView {
    private EditText etNeedle, etRecover;
    private Spinner mSpinner;
    private AislePresenter mPresenter = new AislePresenter(this, this);

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        InitTopView.initTitle("设置货道", this);
        addToView();
        showPage();
        initBtn();
    }

    private void addToView() {
        View _view = LayoutInflater.from(mContext).inflate(R.layout.item_aisle, null);
        ViewGroup.LayoutParams _lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        _view.setLayoutParams(_lp);
        linearLayoutCompat.addView(_view);
        findView(_view);
    }

    private void findView(View view) {
        etNeedle = ViewTool.findById(view, R.id.item_aisle_needle_et);
        etRecover = ViewTool.findById(view, R.id.item_aisle_recover_et);
        mSpinner = ViewTool.findById(view, R.id.item_aisle_spinner);
    }

    private void showPage() {
        etNeedle.setText(RxSPTool.getString(this, ConstantUtil.NEEDLE_CHANNEL));
        etRecover.setText(RxSPTool.getString(this, ConstantUtil.RECOVER_CHANNEL));
        mSpinner.setSelection(RxSPTool.getInt(mContext, ConstantUtil.DEVICE_TYPE, 0));
    }

    private void initBtn() {
        leftBtn.setText("确定");
        rightBtn.setText("返回");
        rightBtn.setOnClickListener(v -> finish());
        leftBtn.setOnClickListener(v ->
                mPresenter.save(etNeedle.getText().toString().trim(),
                        etRecover.getText().toString().trim(),
                        mSpinner.getSelectedItemPosition()));
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
