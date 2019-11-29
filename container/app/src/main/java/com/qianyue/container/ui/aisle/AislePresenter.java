package com.qianyue.container.ui.aisle;

import android.text.TextUtils;
import android.util.Log;

import com.qianyue.container.application.App;
import com.qianyue.container.base.BasePresenter;
import com.qianyue.container.common.ActionHelper;
import com.qianyue.container.db.GoodsInfo;
import com.qianyue.container.utils.ConstantUtil;
import com.qianyue.container.utils.RxSPTool;
import com.qianyue.container.utils.ToastUtil;

import org.litepal.LitePal;

import java.util.List;

public class AislePresenter extends BasePresenter<AisleView, AisleActivity> {
    public AislePresenter(AisleView view, AisleActivity activity) {
        super(view, activity);
    }

    public void save(String needle, String recover, int type) {
        Log.i(getClass().getSimpleName(), "货到类型 = " + type);
        if (TextUtils.isEmpty(needle)) {
            ToastUtil.showShort("请填入机针仓门!");
            return;
        }
        if (TextUtils.isEmpty(recover)) {
            ToastUtil.showShort("请填入回收仓门!");
            return;
        }
        if (TextUtils.equals(needle, recover)) {
            ToastUtil.showShort("机针仓门不能和回收仓门相同!");
            return;
        }
        List<GoodsInfo> _goods = LitePal.where("lattice = ?", needle).find(GoodsInfo.class);
        if (_goods.size() == 0) {
            ToastUtil.showShort("没有此机针仓门请重新设置!");
            return;
        }
        List<GoodsInfo> _goodss = LitePal.where("lattice = ?", recover).find(GoodsInfo.class);
        if (_goodss.size() == 0) {
            ToastUtil.showShort("没有此回收仓门请重新设置!");
            return;
        }
        RxSPTool.putString(App.getContext(), ConstantUtil.NEEDLE_CHANNEL, needle);
        RxSPTool.putString(App.getContext(), ConstantUtil.RECOVER_CHANNEL, recover);
        RxSPTool.putInt(App.getContext(), ConstantUtil.DEVICE_TYPE, type);
        ToastUtil.showShort("设置成功!");
        ActionHelper.UnNullAction(mView, () -> mView.finishActivity());
    }
}
