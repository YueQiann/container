package com.qianyue.container.application;

import android.app.Application;
import android.content.Context;

import com.qianyue.face.util.FaceUtil;

import org.litepal.LitePal;

public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        LitePal.initialize(this);
        FaceUtil.init(this);
    }

    public static Context getContext() {
        return mContext;
    }

}
