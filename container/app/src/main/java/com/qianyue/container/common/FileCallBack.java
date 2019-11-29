package com.qianyue.container.common;

import android.app.ProgressDialog;
import android.content.Context;

public abstract class FileCallBack implements CallBack {
    private ProgressDialog mDialog;

    public FileCallBack() {
    }

    public FileCallBack(Context context) {
        showDialog(context);
    }

    @Override
    public void onFinish() {
        dismissDialog();
    }

    private void showDialog(Context context) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(context);
            mDialog.setMessage("加载中...");
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
        }
        if (!mDialog.isShowing())
            mDialog.show();
    }

    private void dismissDialog() {
        if (mDialog != null &&
                mDialog.isShowing())
            mDialog.dismiss();
        mDialog = null;
    }
}
