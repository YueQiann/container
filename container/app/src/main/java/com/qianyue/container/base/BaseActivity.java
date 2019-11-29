package com.qianyue.container.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.qianyue.container.common.TouchCountTimer;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected Unbinder unbinder;
    private LifeCycleListener listener;
    // 记时
    protected TouchCountTimer mCountTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 强制横屏显示
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(getLayoutResID());
        if (listener != null)
            listener.onCreate(savedInstanceState);
        mContext = this;
        unbinder = ButterKnife.bind(this);
        initCountTimer();
        initView(savedInstanceState);
    }

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    protected abstract int getLayoutResID();

    public void setLifeCycleListener(LifeCycleListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                countTimerStart();
                break;
            default:
                countTimerCancel();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (listener != null)
            listener.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        countTimerStart();
        if (listener != null)
            listener.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countTimerCancel();
        if (listener != null)
            listener.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null)
            listener.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countTimerClear();
        if (listener != null)
            listener.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (listener != null)
            listener.onRestart();
    }

    protected void initCountTimer() {
        mCountTimer = new TouchCountTimer(120000, 1000, mContext);
    }

    protected void countTimerStart() {
        if (mCountTimer != null)
            mCountTimer.start();
    }

    protected void countTimerCancel() {
        if (mCountTimer != null)
            mCountTimer.cancel();
    }

    protected void countTimerClear() {
        if (mCountTimer != null)
            mCountTimer.cancel();
        mCountTimer = null;
    }
}
