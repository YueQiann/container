package com.qianyue.container.common;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;

import java.lang.ref.WeakReference;

public class TouchCountTimer extends CountDownTimer {
    private WeakReference<Context> mContext;

    public TouchCountTimer(long millisInFuture, long countDownInterval, Context context) {
        super(millisInFuture, countDownInterval);
        mContext = new WeakReference<>(context);
    }

    @Override
    public void onTick(long l) {

    }

    @Override
    public void onFinish() {
        Context context = mContext.get();
        if (context != null)
            UIhelper.gotoSplashActivity(context);
    }
}
