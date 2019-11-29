package com.qianyue.container.work;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.qianyue.container.db.ReceiveInfo;

import org.litepal.LitePal;

public class ReceiveFormDelWork extends Worker {

    public ReceiveFormDelWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        long _time = System.currentTimeMillis();
        Log.i(getClass().getName(), "时间：" + _time);
        LitePal.deleteAll(ReceiveInfo.class, "time - ? >=  2592000000", _time + "");
        return Result.success();
    }
}
