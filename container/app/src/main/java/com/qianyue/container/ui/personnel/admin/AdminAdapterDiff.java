package com.qianyue.container.ui.personnel.admin;

import android.text.TextUtils;

import androidx.recyclerview.widget.DiffUtil;

import com.qianyue.container.db.GoodsInfo;
import com.qianyue.container.db.UserInfo;

import java.util.List;

public class AdminAdapterDiff extends DiffUtil.Callback {
    private List<UserInfo> oldDatas;
    private List<UserInfo> newDatas;

    public AdminAdapterDiff(List<UserInfo> oldDatas, List<UserInfo> newDatas) {
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
        UserInfo oldUser = oldDatas.get(oldItemPosition);
        UserInfo newUser = newDatas.get(newItemPosition);
        return TextUtils.equals(oldUser.getName(), newUser.getName()) &&
                TextUtils.equals(oldUser.getRemarks(), newUser.getRemarks());
    }
}
