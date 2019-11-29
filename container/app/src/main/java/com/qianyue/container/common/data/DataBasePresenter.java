package com.qianyue.container.common.data;

import androidx.recyclerview.widget.RecyclerView;

import com.qianyue.container.R;
import com.qianyue.container.base.BasePresenter;
import com.qianyue.container.base.BaseView;
import com.qianyue.container.db.GoodsInfo;
import com.qianyue.container.db.NeedleInfo;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public abstract class DataBasePresenter<V extends BaseView, T extends DataBaseActivity> extends BasePresenter<V, T> {
    protected List<GoodsInfo> data = new ArrayList<>();
    protected DataBaseAdapter adapter;

    public DataBasePresenter(V view, T activity) {
        super(view, activity);
    }

    public void bindRecyclerView(RecyclerView view) {
        adapter = getAdapter(data);
        if (adapter != null)
            adapter.bindToRecyclerView(view);
    }

    /**
     * 查询数据
     */
    public void queryDataBase() {
        if (adapter == null) {
            return;
        }
        if (LitePal.findFirst(GoodsInfo.class) == null) {
            for (int i = 1; i <= 60; i++) {
                GoodsInfo info = new GoodsInfo();
                info.setImg("/storage/emulated/0/sina/weibo/weibo/img-2a5b8572479ac5650690b8d2cf614331.jpg");
                info.setLattice(i);
                info.setName("商品" + i);
                NeedleInfo needleInfo = new NeedleInfo();
                needleInfo.setColor("#333333");
                needleInfo.setImg(String.valueOf(R.mipmap.banner));
                needleInfo.setLen("100");
                needleInfo.save();
                info.setNeedleInfo(needleInfo);
                info.setStock(8);
                info.save();
            }
        }
        List<GoodsInfo> _newData = LitePal.findAll(GoodsInfo.class);
        adapter.replaceData(_newData);
//        DiffUtil.DiffResult _diffResult = DiffUtil.calculateDiff(new DataBaseDiff(data, _newData));
//        data = _newData;
//        adapter.setData(_newData);
//        _diffResult.dispatchUpdatesTo(adapter);
    }

    protected abstract DataBaseAdapter getAdapter(List<GoodsInfo> data);
}
