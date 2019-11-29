package com.qianyue.container.ui.replenishment;

import com.qianyue.container.base.BaseView;
import com.qianyue.container.db.GoodsInfo;

public interface ReplenishmentView extends BaseView {
    void showDialog(GoodsInfo info);
}
