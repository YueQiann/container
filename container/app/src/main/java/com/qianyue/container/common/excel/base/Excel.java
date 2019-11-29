package com.qianyue.container.common.excel.base;

import com.qianyue.container.common.excel.call.CallBack;

public interface Excel {
    void create();

    void fillData();

    void close();

    void setCallBack(CallBack callBack);
}
