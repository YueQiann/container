package com.qianyue.container.ui.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.qianyue.container.common.data.DataBaseActivity;
import com.qianyue.container.widget.dialog.LoginDialog;

public class MainActivity extends DataBaseActivity implements MainView {
    private MainPresenter presenter = new MainPresenter(this, this);

    @Override
    protected String getTitleName() {
        return "商品主页";
    }

    @Override
    protected void initDataView(@Nullable Bundle savedInstanceState) {
        presenter.init();
        presenter.bindRecyclerView(recyclerView);
        leftBtn.setVisibility(View.GONE);
        rightBtn.setText("系统设置");
        rightBtn.setOnClickListener(v -> new LoginDialog().show(getSupportFragmentManager(), "loginDialog"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.queryDataBase();
        presenter.startService();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unregister();
    }

}
