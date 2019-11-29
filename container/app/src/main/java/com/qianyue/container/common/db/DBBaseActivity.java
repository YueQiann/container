package com.qianyue.container.common.db;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.utils.InitTopView;
import com.qianyue.container.utils.ViewTool;

public abstract class DBBaseActivity extends BaseLayoutActivity {
    protected View mRootView;
    protected EditText etName, etNum, etLen, etColor, etLattice;
    protected ImageView ivContrast, ivImg;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        InitTopView.initTitle(getTitleName(), this);
        initDBView();
        initsView(savedInstanceState);
    }

    private void initDBView() {
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.item_goods_info, null);
        etName = ViewTool.findById(mRootView, R.id.item_goods_info_name_et);
        etNum = ViewTool.findById(mRootView, R.id.item_goods_info_num_et);
        etLen = ViewTool.findById(mRootView, R.id.item_goods_info_len_et);
        etColor = ViewTool.findById(mRootView, R.id.item_goods_info_color_et);
        etLattice = ViewTool.findById(mRootView, R.id.item_goods_info_lattice_et);
        ivContrast = ViewTool.findById(mRootView, R.id.item_goods_info_contrast_iv);
        ivImg = ViewTool.findById(mRootView, R.id.item_goods_info_img_iv);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRootView.setLayoutParams(lp);
        linearLayoutCompat.addView(mRootView);
    }

    protected abstract String getTitleName();

    protected abstract void initsView(@Nullable Bundle savedInstanceState);
}
