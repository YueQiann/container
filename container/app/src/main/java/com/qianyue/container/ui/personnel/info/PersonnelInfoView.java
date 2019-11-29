package com.qianyue.container.ui.personnel.info;

import android.net.Uri;

import com.qianyue.container.base.BaseView;
import com.qianyue.container.db.UserInfo;

public interface PersonnelInfoView extends BaseView {
    void showPersonnelInfo(UserInfo info);

    void finishActivity();

    void loadPic(Uri uri);

    void changeStyle();

    void addStyle();
}
