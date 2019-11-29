package com.qianyue.container.common.data;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gcssloop.widget.PagerGridLayoutManager;
import com.gcssloop.widget.PagerGridSnapHelper;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.utils.InitTopView;
import com.qianyue.container.widget.GridLayoutManager;
import com.qianyue.container.widget.LazyRecyclerView;

public abstract class DataBaseActivity extends BaseLayoutActivity {
    protected RecyclerView recyclerView = null;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        InitTopView.initTitle(getTitleName(), this);
        initRecyclerView();
        initDataView(savedInstanceState);
    }

    private void initRecyclerView() {
        recyclerView = new LazyRecyclerView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        recyclerView.setLayoutParams(lp);
        linearLayoutCompat.addView(recyclerView);
        // 1.水平分页布局管理器
        PagerGridLayoutManager layoutManager = new PagerGridLayoutManager(
                2, 4, PagerGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
//        GridLayoutManager manager = new GridLayoutManager(2,4);
        // 2.设置滚动辅助工具
        PagerGridSnapHelper pageSnapHelper = new PagerGridSnapHelper();
        pageSnapHelper.attachToRecyclerView(recyclerView);
    }

    protected abstract String getTitleName();

    protected abstract void initDataView(@Nullable Bundle savedInstanceState);
}
