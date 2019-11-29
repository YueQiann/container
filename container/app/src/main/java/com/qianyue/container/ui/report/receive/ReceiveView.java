package com.qianyue.container.ui.report.receive;

import com.qianyue.container.base.BaseView;

public interface ReceiveView extends BaseView {
    void showDialog();

    void toast(String msg);

    void dismissDialog();
}
