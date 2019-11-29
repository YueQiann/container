package com.qianyue.container.utils;

import android.app.Activity;
import android.widget.TextView;

import com.qianyue.container.R;

public class InitTopView {
    public static void initTitle(String title, Activity activity) {
        ((TextView) ViewTool.findById(activity, R.id.title)).setText(title);
    }
}
