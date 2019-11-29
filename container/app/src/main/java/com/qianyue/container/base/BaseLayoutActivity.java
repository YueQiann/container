package com.qianyue.container.base;

import android.widget.Button;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.qianyue.container.R;

import butterknife.BindView;

public abstract class BaseLayoutActivity extends BaseActivity {
    @BindView(R.id.layout_linerlayout)
    protected LinearLayoutCompat linearLayoutCompat;
    @BindView(R.id.layout_left_btn)
    protected Button leftBtn;
    @BindView(R.id.layout_right_btn)
    protected Button rightBtn;
    @BindView(R.id.layout_del_btn)
    protected Button delBtn;
    @BindView(R.id.layout_next_btn)
    protected Button otherBtn;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }
}
