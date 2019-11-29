package com.qianyue.container.ui.replenishment;

import androidx.annotation.Nullable;

import com.qianyue.container.common.data.DataBaseAdapter;
import com.qianyue.container.db.GoodsInfo;

import java.util.List;

public class ReplenishmentAdapter extends DataBaseAdapter {
    public ReplenishmentAdapter(@Nullable List<GoodsInfo> data) {
        super(data);
    }
}
