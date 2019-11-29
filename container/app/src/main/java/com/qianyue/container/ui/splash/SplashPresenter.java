package com.qianyue.container.ui.splash;

import android.util.Log;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.qianyue.container.R;
import com.qianyue.container.application.App;
import com.qianyue.container.base.BasePresenter;
import com.qianyue.container.common.ActionHelper;
import com.qianyue.container.utils.ConstantUtil;
import com.qianyue.container.utils.RxSPTool;
import com.qianyue.container.work.ReceiveFormDelWork;
import com.qianyue.face.common.Constants;
import com.qianyue.face.util.FaceUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class SplashPresenter extends BasePresenter<SplashView, SplashActivity> {
    public SplashPresenter(SplashView view, SplashActivity activity) {
        super(view, activity);
    }

    public void getData() {
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.banner);
        list.add(R.mipmap.banner);
        list.add(R.mipmap.banner);
        ActionHelper.UnNullAction(mView, () -> mView.setData(list));
    }

    public void init() {
//        String _num = RxSPTool.getString(App.getContext(), ConstantUtil.DEF_NUM);
//        String _pwd = RxSPTool.getString(App.getContext(), ConstantUtil.DEF_PWD);
//        if (!TextUtils.isEmpty(_num) && !TextUtils.isEmpty(_pwd)) {
//            return;
//        }
        RxSPTool.putString(App.getContext(), ConstantUtil.DEF_NUM, "admin");
        RxSPTool.putString(App.getContext(), ConstantUtil.DEF_PWD, "123456");

        Log.i(getClass().getSimpleName(), System.currentTimeMillis() + "");

        PeriodicWorkRequest request = new PeriodicWorkRequest
                .Builder(ReceiveFormDelWork.class, 15, TimeUnit.MINUTES)
                .build();
        WorkManager.getInstance().enqueue(request);

        FaceUtil.activeEngine(mAcitity);
    }
}
