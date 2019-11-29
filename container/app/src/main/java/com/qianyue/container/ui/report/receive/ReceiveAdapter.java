package com.qianyue.container.ui.report.receive;

import android.graphics.Color;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyue.container.R;
import com.qianyue.container.db.ReceiveInfo;
import com.qianyue.container.utils.CommonUtil;
import com.qianyue.container.utils.ImageLoadUtil;

import java.util.List;

public class ReceiveAdapter extends BaseQuickAdapter<ReceiveInfo, BaseViewHolder> {

    public ReceiveAdapter(@Nullable List<ReceiveInfo> data) {
        super(R.layout.item_receive, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReceiveInfo item) {
        ((TextView) helper.getView(R.id.item_receive_time_tv)).setTextSize(12.0f);
        helper.setText(R.id.item_receive_time_tv, CommonUtil.timeStamp2Date(item.getTime(), "yyyy-MM-dd HH:mm:ss"));
        helper.setText(R.id.item_receive_name_tv, item.getItemName());
        helper.setText(R.id.item_receive_cargo_tv, item.getChannle() + "");
        helper.setText(R.id.item_receive_user_tv, item.getRecipients());
        ImageLoadUtil.loadFitCenterImage(helper.getView(R.id.item_receive_iv), item.getItemPic());
        helper.setVisible(R.id.item_receive_iv, true);
        helper.setGone(R.id.item_receive_msg_tv, false);
        helper.itemView.setBackgroundColor(mData.indexOf(item) % 2 == 0 ? Color.WHITE : Color.DKGRAY);
    }
}
