package com.qianyue.container.ui.report.supply;

import androidx.recyclerview.widget.RecyclerView;

import com.qianyue.container.base.BasePresenter;
import com.qianyue.container.common.excel.call.CallBack;
import com.qianyue.container.common.excel.client.ExcleClient;
import com.qianyue.container.common.excel.supply.SupplyExcel;
import com.qianyue.container.db.SupplyInfo;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ReportSupplyPresenter extends BasePresenter<ReportSupplyView, ReportSupplyActivity> {
    private List<SupplyInfo> mData = new ArrayList<>();
    private ReportSupplyAdapter mAdapter;

    public ReportSupplyPresenter(ReportSupplyView view, ReportSupplyActivity activity) {
        super(view, activity);
    }


    public void bindView(RecyclerView view) {
        mAdapter = new ReportSupplyAdapter(mData);
        mAdapter.bindToRecyclerView(view);
    }

    public void getData() {
        if (LitePal.findFirst(SupplyInfo.class) == null) {
            for (int i = 0; i < 10; i++) {
                SupplyInfo _info = new SupplyInfo();
                _info.setTime(System.currentTimeMillis());
                _info.setChannel(1);
                _info.setGoodsName("2");
                _info.setGoodsImg("/storage/emulated/0/sina/weibo/weibo/img-2a5b8572479ac5650690b8d2cf614331.jpg");
                _info.setUserName("乱世狂刀" + i);
                _info.setCount(i + "");
                _info.save();
            }
        }
        LitePal.findAllAsync(SupplyInfo.class)
                .listen(list -> mAdapter.replaceData(list));
    }

    /**
     * 导出到U盘
     */
    public void export() {
        if (mView != null)
            mView.showDialog();
        ExcleClient client = new ExcleClient.Builder()
                .build();
        SupplyExcel excel = new SupplyExcel();
        client.newCall(excel).enqueue(new CallBack() {
            @Override
            public void onSucc() {
                if (mView != null)
                    mView.toast("导出成功");
            }

            @Override
            public void onFail(String msg) {
                if (mView != null)
                    mView.toast(msg);
            }

            @Override
            public void onFinish() {
                if (mView != null)
                    mView.dismissDialog();
            }
        });
    }
}
