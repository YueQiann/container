package com.qianyue.container.ui.report.receive;

import androidx.recyclerview.widget.RecyclerView;

import com.qianyue.container.application.App;
import com.qianyue.container.base.BasePresenter;
import com.qianyue.container.common.excel.call.CallBack;
import com.qianyue.container.common.excel.client.ExcleClient;
import com.qianyue.container.common.excel.receive.ReceiveExcel;
import com.qianyue.container.db.ReceiveInfo;
import com.qianyue.container.utils.CommonUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ReceivePresenter extends BasePresenter<ReceiveView, ReceiveActivity> {
    private List<ReceiveInfo> mData = new ArrayList<>();
    private ReceiveAdapter mAdapter;

    public ReceivePresenter(ReceiveView view, ReceiveActivity activity) {
        super(view, activity);
    }

    public void bindView(RecyclerView view) {
        mAdapter = new ReceiveAdapter(mData);
        mAdapter.bindToRecyclerView(view);
    }

    public void getData() {
        mData.clear();

        if (LitePal.findFirst(ReceiveInfo.class) == null) {
            for (int i = 0; i < 10; i++) {
                ReceiveInfo _info = new ReceiveInfo();
                _info.setTime(System.currentTimeMillis());
                _info.setChannle(1);
                _info.setContrastLen("2");
                _info.setContrastPic("/storage/emulated/0/sina/weibo/weibo/img-2a5b8572479ac5650690b8d2cf614331.jpg");
                _info.setIsFace(0);
                _info.setItemName("乱世狂刀" + i);
                _info.setItemPic("/storage/emulated/0/sina/weibo/weibo/img-2a5b8572479ac5650690b8d2cf614331.jpg");
                _info.setLen("3");
                _info.setReceiveNotes("领取人" + i);
                _info.setRecipients("领取人" + i);
                _info.setStatus(0);
                _info.save();
            }
        }
        LitePal.findAllAsync(ReceiveInfo.class)
                .listen(list -> {
                    mData.addAll(list);
                    mAdapter.notifyDataSetChanged();
                });
    }

    /**
     * 导出到U盘
     */
    public void export() {
        if (mView != null)
            mView.showDialog();
        ExcleClient client = new ExcleClient.Builder()
                .build();
        ReceiveExcel excel = new ReceiveExcel();
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
