package com.qianyue.container.ui.replenishment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qianyue.container.common.ActionHelper;
import com.qianyue.container.common.data.DataBaseAdapter;
import com.qianyue.container.common.data.DataBasePresenter;
import com.qianyue.container.db.GoodsInfo;

import java.util.List;

public class ReplenishmentPresenter extends DataBasePresenter<ReplenishmentView, ReplenishmentActivity>
        implements BaseQuickAdapter.OnItemClickListener {
    private ReplenishmentAdapter mAdapter;

    public ReplenishmentPresenter(ReplenishmentView view, ReplenishmentActivity activity) {
        super(view, activity);
    }

    @Override
    protected DataBaseAdapter getAdapter(List<GoodsInfo> data) {
        mAdapter = new ReplenishmentAdapter(data);
        mAdapter.setOnItemClickListener(this);
        return mAdapter;
    }

    public void save(GoodsInfo info, int num) {
        if (info != null) {
            int _sum = num + info.getStock();
            info.setStock(_sum);
            info.update(info.getId());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ActionHelper.UnNullAction(mView, () -> mView.showDialog((GoodsInfo) adapter.getData().get(position)));
    }
}
