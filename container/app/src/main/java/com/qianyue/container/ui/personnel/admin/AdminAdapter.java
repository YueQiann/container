package com.qianyue.container.ui.personnel.admin;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyue.container.R;
import com.qianyue.container.db.UserInfo;

import java.util.List;

public class AdminAdapter extends BaseQuickAdapter<UserInfo, BaseViewHolder> {
    public AdminAdapter(@Nullable List<UserInfo> data) {
        super(R.layout.item_personnel_top, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserInfo item) {
        Log.i(getClass().getName(), "第" + mData.indexOf(item) + "更新");
        helper.setText(R.id.item_personnel_top_name_tv, item.getName());
        helper.setText(R.id.item_personnel_top_remarks_tv, item.getRemarks());
        helper.itemView.setBackgroundColor(mData.indexOf(item) % 2 == 0 ? Color.WHITE : Color.GRAY);
    }

    public void setData(List data) {
        mData = data;
    }

}
