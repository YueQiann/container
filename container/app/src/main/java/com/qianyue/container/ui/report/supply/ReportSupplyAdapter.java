package com.qianyue.container.ui.report.supply;

import android.graphics.Color;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyue.container.R;
import com.qianyue.container.db.SupplyInfo;
import com.qianyue.container.utils.CommonUtil;

import java.util.List;

public class ReportSupplyAdapter extends BaseQuickAdapter<SupplyInfo, BaseViewHolder> {
    public ReportSupplyAdapter(@Nullable List<SupplyInfo> data) {
        super(R.layout.item_supply_report, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplyInfo item) {
        ((TextView) helper.getView(R.id.item_supply_time_tv)).setTextSize(12.0f);
        helper.setText(R.id.item_supply_time_tv, CommonUtil.timeStamp2Date(item.getTime(), "yyyy-MM-dd HH:mm:ss"));
        helper.setText(R.id.item_supply_name_tv, item.getGoodsName());
        helper.setText(R.id.item_supply_cargo_tv, String.valueOf(item.getChannel()));
        helper.setText(R.id.item_supply_user_tv, item.getUserName());
        helper.setText(R.id.item_supply_count_tv, String.valueOf(item.getCount()));
        helper.itemView.setBackgroundColor(mData.indexOf(item) % 2 == 0 ? Color.WHITE :
                ContextCompat.getColor(mContext, R.color.colorGray));
    }
}
