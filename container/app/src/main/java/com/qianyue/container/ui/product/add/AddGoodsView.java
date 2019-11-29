package com.qianyue.container.ui.product.add;

import android.net.Uri;

import com.qianyue.container.base.BaseView;

public interface AddGoodsView extends BaseView {
    void finishActivity();

    void loadContrastPic(Uri uri);

    void loadGoodsPic(Uri uri);
}
