package com.qianyue.container.widget.dialog.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qianyue.container.R;
import com.qianyue.container.utils.ViewTool;

public class SupplyLayout {
    private View mRootView;
    private TextView tvCurrent;
    private EditText etNumber;
    private Button btnDefine;
    private Button btnBack;

    public SupplyLayout(Context context) {
        findView(context);
    }

    private void findView(Context context) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.item_supply, null);
        tvCurrent = ViewTool.findById(mRootView, R.id.item_supply_current_sum_tv);
        etNumber = ViewTool.findById(mRootView, R.id.item_supply_et);
        btnDefine = ViewTool.findById(mRootView, R.id.item_supply_yes_btn);
        btnBack = ViewTool.findById(mRootView, R.id.item_supply_no_btn);
    }

    public View getRootView() {
        return mRootView;
    }

    public TextView getTvCurrent() {
        return tvCurrent;
    }

    public EditText getEtNumber() {
        return etNumber;
    }

    public Button getBtnDefine() {
        return btnDefine;
    }

    public Button getBtnBack() {
        return btnBack;
    }
}
