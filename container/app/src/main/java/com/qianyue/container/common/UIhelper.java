package com.qianyue.container.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

import com.qianyue.container.db.GoodsInfo;
import com.qianyue.container.db.UserInfo;
import com.qianyue.container.ui.aisle.AisleActivity;
import com.qianyue.container.ui.device.DeviceActivity;
import com.qianyue.container.ui.device.DeviceChildActivity;
import com.qianyue.container.ui.device.imei.ImeiActivity;
import com.qianyue.container.ui.device.item.face.FaceActivity;
import com.qianyue.container.ui.home.MainActivity;
import com.qianyue.container.ui.personnel.PersonnelActivity;
import com.qianyue.container.ui.personnel.admin.AdminActivity;
import com.qianyue.container.ui.personnel.info.PersonnelInfoActivity;
import com.qianyue.container.ui.product.ProductActivity;
import com.qianyue.container.ui.product.add.AddGoodsActivity;
import com.qianyue.container.ui.product.info.GoodsInfoActivity;
import com.qianyue.container.ui.replenishment.ReplenishmentActivity;
import com.qianyue.container.ui.replenishment.ReplenishmentAdapter;
import com.qianyue.container.ui.report.ReportActivity;
import com.qianyue.container.ui.report.receive.ReceiveActivity;
import com.qianyue.container.ui.report.supply.ReportSupplyActivity;
import com.qianyue.container.ui.splash.SplashActivity;
import com.qianyue.container.ui.system.SystemActivity;

public class UIhelper {

    /**
     * 前往广告页
     */
    public static void gotoSplashActivity(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往主页
     *
     * @param context
     */
    public static void gotoMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 去往系统设置
     * type 0 普通人员没有权利进入
     * 1 运维人员
     * 2 管理员
     */
    public static void gotoSystemActivity(Context context, int type) {
        Intent intent = new Intent(context, SystemActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    /**
     * 前往产品编辑页面
     */
    public static void gotoProductActivity(Context context) {
        Intent intent = new Intent(context, ProductActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往产品添加页面
     */
    public static void gotoAddGoodsActivity(Context context) {
        Intent intent = new Intent(context, AddGoodsActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往产品详情页面
     */
    public static void gotoGoodsInfoActivity(Context context, GoodsInfo goodsInfo) {
        Intent intent = new Intent(context, GoodsInfoActivity.class);
        intent.putExtra("goods", goodsInfo);
        context.startActivity(intent);
    }

    /**
     * 前往图片选择页面
     */
    public static void gotoChoosePhoto(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 前往人员管理
     * * 0 普通人员没有权利进入
     * * 1 运维人员
     * * 2 管理员
     */
    public static void gotoPersonnelActivity(Context context) {
        Intent intent = new Intent(context, PersonnelActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往人员管理子界面
     * * type 0 普通人员
     * * 1 运维人员
     * * 2 管理员
     */
    public static void gotoPersonnelChildActivity(Context context, int type, String title) {
        Intent intent = new Intent(context, AdminActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("name", title);
        context.startActivity(intent);
    }

    /**
     * 前往人员信息页面
     *
     * @param context
     * @param info    人员信息详情
     */
    public static void gotoPersonnelInfoActivity(Context context, UserInfo info, String title, int type) {
        Intent intent = new Intent(context, PersonnelInfoActivity.class);
        intent.putExtra("user", info);
        intent.putExtra("name", title);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    /**
     * 前往货道设置页面
     *
     * @param context
     */
    public static void gotoAisleActivity(Context context) {
        Intent intent = new Intent(context, AisleActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往查看报表
     *
     * @param context
     */
    public static void gotoReportActivity(Context context) {
        Intent intent = new Intent(context, ReportActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往领取报表
     *
     * @param context
     */
    public static void gotoReceiveActivity(Context context) {
        Intent intent = new Intent(context, ReceiveActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往补货页面
     *
     * @param context
     */
    public static void gotoReplenishmentActivity(Context context) {
        Intent intent = new Intent(context, ReplenishmentActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往补货报表页面
     *
     * @param context
     */
    public static void gotoReportSupplyActivity(Context context) {
        Intent intent = new Intent(context, ReportSupplyActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往设备管理页面
     *
     * @param context
     */
    public static void gotoDeviceActivity(Context context) {
        Intent intent = new Intent(context, DeviceActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往设备调试页面
     *
     * @param context
     */
    public static void gotoDeviceChildActivity(Context context) {
        Intent intent = new Intent(context, DeviceChildActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往人脸识别调试页面
     *
     * @param context
     */
    public static void gotoFaceActivity(Context context) {
        Intent intent = new Intent(context, FaceActivity.class);
        context.startActivity(intent);
    }

    /**
     * 前往IMEI页面
     */
    public static void gotoIMEIActivity(Context context) {
        Intent intent = new Intent(context, ImeiActivity.class);
        context.startActivity(intent);
    }
}
