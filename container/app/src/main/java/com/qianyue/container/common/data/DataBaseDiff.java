package com.qianyue.container.common.data;

import android.text.TextUtils;

import androidx.recyclerview.widget.DiffUtil;

import com.qianyue.container.db.GoodsInfo;

import java.util.List;

public class DataBaseDiff extends DiffUtil.Callback {
    private List<GoodsInfo> oldDatas;
    private List<GoodsInfo> newDatas;

    public DataBaseDiff(List<GoodsInfo> oldDatas, List<GoodsInfo> newDatas) {
        this.oldDatas = oldDatas;
        this.newDatas = newDatas;
    }

    @Override
    public int getOldListSize() {
        return oldDatas != null ? oldDatas.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newDatas != null ? newDatas.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        int oldId = oldDatas.get(oldItemPosition).getId();
        int newId = newDatas.get(newItemPosition).getId();
        return oldId == newId;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        GoodsInfo oldInfo = oldDatas.get(oldItemPosition);
        GoodsInfo newInfo = newDatas.get(newItemPosition);
        return TextUtils.equals(oldInfo.getImg(), newInfo.getImg()) &&
                TextUtils.equals(oldInfo.getName(), newInfo.getImg()) &&
                TextUtils.equals(String.valueOf(oldInfo.getLattice()), String.valueOf(newInfo.getLattice())) &&
                TextUtils.equals(String.valueOf(oldInfo.getStock()), String.valueOf(newInfo.getStock()));
    }
}
