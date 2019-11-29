package com.qianyue.container.common.excel.call;

import com.qianyue.container.common.excel.base.Excel;

import java.io.IOException;

public interface Call extends Cloneable {

    void execute() throws IOException;

    void enqueue(CallBack callback);

    Call clone();

    interface Factory {
        Call newCall(Excel excel);
    }
}
