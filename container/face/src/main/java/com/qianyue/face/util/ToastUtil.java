package com.qianyue.face.util;

import android.widget.Toast;

public class ToastUtil {
    public static Toast mToast;

    public static void showMsg(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(FaceUtil.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
