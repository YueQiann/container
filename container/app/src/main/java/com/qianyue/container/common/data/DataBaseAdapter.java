package com.qianyue.container.common.data;

import android.util.Log;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyue.container.R;
import com.qianyue.container.db.GoodsInfo;
import com.qianyue.container.utils.ImageLoadUtil;

import java.util.List;

public class DataBaseAdapter extends BaseQuickAdapter<GoodsInfo, BaseViewHolder> {

    public DataBaseAdapter(@Nullable List<GoodsInfo> data) {
        super(R.layout.item_goods, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsInfo item) {
        Log.i(getClass().getName(), "第" + mData.indexOf(item) + "更新啦");
        ImageLoadUtil.loadTopRoundImage(
                helper.getView(R.id.goods_iv), item.getImg(), 8);
        helper.setText(R.id.item_goods_name_tv, item.getName());
        helper.setText(R.id.item_goods_stock_tv, item.getStock() + "");
        helper.setText(R.id.item_goods_position_tv, item.getLattice() + "");
    }

}
