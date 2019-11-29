package com.qianyue.container.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.util.TypedValue;

import androidx.annotation.RequiresApi;

import com.qianyue.container.application.App;
import com.qianyue.container.db.UserInfo;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonUtil {

    /**
     * 获取u盘挂在路径
     */
    public static List getUSBPaths(Context con) {//反射获取路径
        String[] paths = null;
        List data = new ArrayList();    // include sd and usb devices
        StorageManager storageManager = (StorageManager) con.getSystemService(Context.STORAGE_SERVICE);
        try {
            paths = (String[]) StorageManager.class.getMethod("getVolumePaths", null).invoke(storageManager, null);
            for (String path : paths) {
                Log.i("getUSBPaths", "getUSBPaths == path === 筛选前" + path);
                String state = (String) StorageManager.class.getMethod("getVolumeState", String.class).invoke(storageManager, path);
                if (state.equals(Environment.MEDIA_MOUNTED) && !path.contains("internal") && !path.contains("emulated")) {
                    data.add(path);
                    Log.i("getUSBPaths", "getUSBPaths == path === 筛选后" + path);
                }
            }
        } catch (Exception e) {
            ToastUtil.showShort(e.getMessage());
            e.printStackTrace();
        }
        return data;
    }

    //dp转px
    public static int dip2px(float dpValue) {
        final float scale = App.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //px转dp
    public static int px2dip(int pxValue) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, App.getContext().getResources().getDisplayMetrics()));
    }

    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
//            format = "yyyy-MM-dd HH:mm:ss";
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

    public static String currentTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sDateFormat.format(new Date());
    }

    /**
     * =========== 用户登录 ==========
     */
    public static List<UserInfo> getUsers(String number, String pwd) {
        return LitePal.where("number = ? and password = ?", number, pwd).find(UserInfo.class);
    }

    public static List<UserInfo> getUsersType(String number, String pwd) {
        return LitePal.where("number = ? and password = ? and (type = 1 or type = 2)", number, pwd).find(UserInfo.class);
    }

    public static UserInfo loginUser(String number, String pwd) {
        List<UserInfo> userInfos = getUsersType(number, pwd);
        if (userInfos != null && userInfos.size() != 0) {
            return userInfos.get(0);
        }
        String num = RxSPTool.getContent(App.getContext(), ConstantUtil.DEF_NUM);
        String password = RxSPTool.getContent(App.getContext(), ConstantUtil.DEF_PWD);
        if (num.equals(number) && pwd.equals(password)) {
            UserInfo info = new UserInfo();
            info.setType(2); // 管理员
            return info;
        }
        return null;
    }

    public static boolean isLogin(String number, String pwd) {
        return loginUser(number, pwd) != null;
    }
}
