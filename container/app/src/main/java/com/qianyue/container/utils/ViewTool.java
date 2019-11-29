package com.qianyue.container.utils;

import android.app.Activity;
import android.view.View;

public class ViewTool {
    public static <T extends View> T findById(Activity activity,int resId){
        return activity.findViewById(resId);
    }

    public static <T extends View> T findById(View view,int id){
        return view.findViewById(id);
    }
}
