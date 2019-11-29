package com.qianyue.container.ui.replenishment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.qianyue.container.common.data.DataBaseActivity;
import com.qianyue.container.db.GoodsInfo;
import com.qianyue.container.widget.dialog.SupplyDialog;

public class ReplenishmentActivity extends DataBaseActivity implements ReplenishmentView {
    private ReplenishmentPresenter mPresenter = new ReplenishmentPresenter(this, this);

    @Override
    protected String getTitleName() {
        return "补货管理";
    }

    @Override
    protected void initDataView(@Nullable Bundle savedInstanceState) {
        mPresenter.bindRecyclerView(recyclerView);
        initBtn();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryDataBase();
    }

    private void initBtn() {
        leftBtn.setText("开锁");
        rightBtn.setText("返回");
        leftBtn.setOnClickListener(v -> {
        });
        rightBtn.setOnClickListener(view -> finish());
    }

    @Override
    public void showDialog(GoodsInfo info) {
        SupplyDialog _dialog = SupplyDialog.newInstance(info);
        _dialog.setOnClickListener((view, infos, num) -> mPresenter.save(infos, num));
        _dialog.show(getSupportFragmentManager(), "");
    }
}
