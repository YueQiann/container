package com.qianyue.container.ui.product;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.common.UIhelper;
import com.qianyue.container.common.data.DataBaseActivity;

import butterknife.OnClick;

public class ProductActivity extends DataBaseActivity implements ProductView {
    private ProductPresenter presenter = new ProductPresenter(this, this);

    @Override
    protected String getTitleName() {
        return "产品编辑";
    }

    @Override
    protected void initDataView(@Nullable Bundle savedInstanceState) {
        rightBtn.setText("返回");
        leftBtn.setText("添加商品");
        presenter.bindRecyclerView(recyclerView);
        leftBtn.setOnClickListener(v -> UIhelper.gotoAddGoodsActivity(this));
        rightBtn.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.queryDataBase();
    }
}
