package com.qianyue.container.ui.personnel.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.common.UIhelper;
import com.qianyue.container.utils.InitTopView;

public class AdminActivity extends BaseLayoutActivity implements AdminView {
    private RecyclerView mRecyclerView;
    private AdminPresenter mPresenter = new AdminPresenter(this, this);

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mPresenter.initPresenter(getIntent());
        addView();
    }

    private void addView() {
        View _view = LayoutInflater.from(this).inflate(R.layout.item_personnel_top, null);
        linearLayoutCompat.addView(_view);
        mRecyclerView = new RecyclerView(this);
        LinearLayout.LayoutParams _lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRecyclerView.setLayoutParams(_lp);
        linearLayoutCompat.addView(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.bindVeiw(mRecyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getData();
    }

    private void initBtn() {
        rightBtn.setText("返回");
        rightBtn.setOnClickListener(v -> finish());
        leftBtn.setText("添加");
        leftBtn.setOnClickListener(v ->
                UIhelper.gotoPersonnelInfoActivity(this, null,
                        mPresenter.getName(), mPresenter.getType()));
    }

    private void otherBtn() {
        otherBtn.setVisibility(View.VISIBLE);
        otherBtn.setText("导入");
        otherBtn.setOnClickListener(view -> {
        });
    }

    @Override
    public void titleBar(String title) {
        InitTopView.initTitle(title, this);
    }

    @Override
    public void btnStyle(int type) {
        initBtn();
        switch (type) {
            case 0: // 领取人员页面
                otherBtn();
                break;
        }
    }
}
