package com.qianyue.container.widget.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qianyue.container.R;
import com.qianyue.container.db.GoodsInfo;
import com.qianyue.container.widget.dialog.layout.SupplyLayout;

public class SupplyDialog extends BaseDialogFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private GoodsInfo mGoodsInfo;
    private SupplyLayout mLayout;
    private OnClickListener listener;

    public static SupplyDialog newInstance(GoodsInfo info) {
        SupplyDialog _dialog = new SupplyDialog();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, info);
        _dialog.setArguments(args);
        return _dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGoodsInfo = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    protected void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {
        addToView();
        initShow();
        initEvent();
    }

    private void addToView() {
        mLayout = new SupplyLayout(context);
        layout.addView(mLayout.getRootView());
    }

    private void initShow() {
        if (mGoodsInfo == null) {
            return;
        }
        titleTv.setText(String.format("补货数量编辑  商品名%s", mGoodsInfo.getName()));
        mLayout.getTvCurrent().setText(String.format("目前存量：%d", mGoodsInfo.getStock()));
    }

    private void initEvent() {
        mLayout.getBtnBack().setOnClickListener(this);
        mLayout.getBtnDefine().setOnClickListener(this);
    }

    public interface OnClickListener {
        void onClick(View view, GoodsInfo info, int increase);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    private void onClickEvent(View view) {
        if (listener != null) {
            String _numstr = mLayout.getEtNumber().getText().toString();
            int _num = 0;
            if (TextUtils.isEmpty(_numstr)) {
                listener.onClick(view, mGoodsInfo, _num);
            } else {
                try {
                    _num = Integer.parseInt(_numstr);
                } catch (NumberFormatException e) {
                    _num = 0;
                    e.printStackTrace();
                } finally {
                    listener.onClick(view, mGoodsInfo, _num);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_supply_yes_btn:
                onClickEvent(view);
                break;
            case R.id.item_supply_no_btn:
                break;
        }
        dismissAllowingStateLoss();
    }
}
