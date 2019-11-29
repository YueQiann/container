package com.qianyue.face.util;

import android.app.Activity;
import android.content.Context;

import com.arcsoft.face.ErrorInfo;
import com.arcsoft.face.FaceEngine;
import com.qianyue.face.R;
import com.qianyue.face.common.Constants;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FaceUtil {
    private static Context context;

    public static void init(Context context) {
        if (context == null)
            throw new NullPointerException("context 不能为空");
        FaceUtil.context = context;
    }

    public static Context getContext() {
        return FaceUtil.context;
    }

    /**
     * 激活系统引擎
     *
     * @param activity
     */
    public static void activeEngine(final Activity activity) {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            FaceEngine faceEngine = new FaceEngine();
            int activeCode = faceEngine.active(activity, Constants.APP_ID, Constants.SDK_KEY);
            emitter.onNext(activeCode);
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer activeCode) {
                        if (activeCode == ErrorInfo.MOK) {
                            ToastUtil.showMsg(FaceUtil.getContext().getString(R.string.active_success));
                        } else if (activeCode == ErrorInfo.MERR_ASF_ALREADY_ACTIVATED) {

                        } else {
                            ToastUtil.showMsg(FaceUtil.getContext().getString(R.string.active_failed, activeCode));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
