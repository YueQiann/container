package com.qianyue.container.common.excel.call;

public interface CallBack {
    void onSucc();

    void onFail(String msg);

    void onFinish();
}
