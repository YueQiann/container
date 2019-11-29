package com.qianyue.container.ui.report.supply;

import com.qianyue.container.base.BaseView;

public interface ReportSupplyView extends BaseView {
    void showDialog();

    void toast(String msg);

    void dismissDialog();
}
