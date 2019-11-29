package com.qianyue.container.ui.product;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qianyue.container.common.UIhelper;
import com.qianyue.container.common.data.DataBaseAdapter;
import com.qianyue.container.common.data.DataBasePresenter;
import com.qianyue.container.db.GoodsInfo;

import java.util.List;

public class ProductPresenter extends DataBasePresenter<ProductView, ProductActivity> {
    public ProductPresenter(ProductView view, ProductActivity activity) {
        super(view, activity);
    }

    @Override
    protected DataBaseAdapter getAdapter(List<GoodsInfo> data) {
        DataBaseAdapter adapter = new DataBaseAdapter(data);
        adapter.setOnItemClickListener((adapter1, view, position) ->
                UIhelper.gotoGoodsInfoActivity(mAcitity, adapter.getData().get(position)));
        return adapter;
    }
}
