package com.qianyue.container.ui.personnel.info;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.qianyue.container.base.BasePresenter;
import com.qianyue.container.common.ActionHelper;
import com.qianyue.container.common.FileOperateHelper;
import com.qianyue.container.db.UserInfo;
import com.qianyue.container.utils.ToastUtil;
import com.qianyue.container.utils.Uri2FileUtil;

import org.litepal.LitePal;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class PersonnelInfoPresenter extends BasePresenter<PersonnelInfoView, PersonnelInfoActivity>
        implements FileOperateHelper.OnFilePathListenter {
    private UserInfo info;
    private int type;
    private String imgPath;
    private FileOperateHelper mFileHelper;

    public PersonnelInfoPresenter(PersonnelInfoView view, PersonnelInfoActivity activity) {
        super(view, activity);
    }

    public void setData(Intent intent) {
        info = intent.getParcelableExtra("user");
        if (info != null) {
            ActionHelper.UnNullAction(mView, () -> {
                mView.changeStyle();
                mView.showPersonnelInfo(info);
            });
        } else {
            info = new UserInfo();
            type = intent.getIntExtra("type", 0);
            ActionHelper.UnNullAction(mView, () -> mView.addStyle());
        }
    }

    public void addInfo(String name, String remarks, String num, String pwd) {
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showShort("请填入姓名");
            return;
        }

        if (TextUtils.isEmpty(remarks)) {
            ToastUtil.showShort("请填入备注");
            return;
        }

        if (TextUtils.isEmpty(num)) {
            ToastUtil.showShort("请填入账号");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showShort("请填入密码");
            return;
        }

        if (TextUtils.isEmpty(imgPath)) {
            ToastUtil.showShort("请选择照片");
            return;
        }
        info.setName(name);
        info.setRemarks(remarks);
        info.setNumber(num);
        info.setPassword(pwd);
        info.setImg(imgPath);
        info.setType(type);
        info.save();
        ToastUtil.showShort("保存成功");
        ActionHelper.UnNullAction(mView, () -> mView.finishActivity());
    }

    public void deleteInfo() {
        LitePal.delete(UserInfo.class, info.getId());
        ToastUtil.showShort("删除成功");
        ActionHelper.UnNullAction(mView, () -> mView.finishActivity());
    }

    public void saveDate(String name, String remarks, String num, String pwd) {
        if (!TextUtils.isEmpty(name)) {
            info.setName(name);
        }

        if (!TextUtils.isEmpty(remarks)) {
            info.setRemarks(remarks);
        }

        if (!TextUtils.isEmpty(num)) {
            info.setNumber(num);
        }

        if (!TextUtils.isEmpty(pwd)) {
            info.setPassword(pwd);
        }

        if (!TextUtils.isEmpty(imgPath)) {
            info.setImg(imgPath);
        }
        info.update(info.getId());
        ToastUtil.showShort("保存成功");
        ActionHelper.UnNullAction(mView, () -> mView.finishActivity());
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (mFileHelper == null) {
            mFileHelper = new FileOperateHelper(mAcitity, "face");
            mFileHelper.setOnFilePathListenter(this);
        }
        Uri _uri = data.getData();
        switch (requestCode) {
            case 200:
                imgPath = Uri2FileUtil.uri2FilePath(_uri);
                mFileHelper.copy(new File(imgPath));
                ActionHelper.UnNullAction(mView, () -> mView.loadPic(_uri));
                Log.i(this.getClass().getSimpleName(), "人脸图片地址:" + imgPath);
                break;
        }
    }

    @Override
    public void onFilePath(String path) {
        imgPath = path;
    }
}
