package com.qianyue.container.ui.product.info;

import android.net.Uri;

import com.qianyue.container.base.BaseView;
import com.qianyue.container.db.GoodsInfo;

public interface GoodsInfoView extends BaseView {
    void showGoodsInfo(GoodsInfo info);

    void finishActivity();

    void loadContrastPic(Uri uri);

    void loadGoodsPic(Uri uri);
}
