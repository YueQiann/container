package com.qianyue.container.utils;

import android.os.SystemClock;
import android.util.SparseLongArray;

public class ClickUtil {
    private static SparseLongArray sparseArray = new SparseLongArray();

    /**
     * 为每个控件设置点击间隔
     *
     * @param key
     * @param second
     * @return
     */
    public static boolean canClick(int key, long second) {
        Long _time = sparseArray.get(key);
        long _center = SystemClock.elapsedRealtime();
        if (_time == null) {
            sparseArray.put(key, _center);
            return true;
        }
        if (_center - sparseArray.get(key) >= second) {
            sparseArray.put(key, _center);
            return true;
        }
        return false;
    }

    /**
     * 设置一秒点击间隔
     *
     * @param key
     * @return
     */
    public static boolean defaultClick(int key) {
        return canClick(key, 1000);
    }
}
