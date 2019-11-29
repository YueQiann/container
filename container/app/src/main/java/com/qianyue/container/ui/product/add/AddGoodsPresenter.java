package com.qianyue.container.ui.product.add;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.qianyue.container.base.BasePresenter;
import com.qianyue.container.common.ActionHelper;
import com.qianyue.container.common.FileOperateHelper;
import com.qianyue.container.db.GoodsInfo;
import com.qianyue.container.db.NeedleInfo;
import com.qianyue.container.utils.ToastUtil;
import com.qianyue.container.utils.Uri2FileUtil;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class AddGoodsPresenter extends BasePresenter<AddGoodsView, AddGoodsActivity>
        implements FileOperateHelper.OnFilePathListenter {
    private String contrastPath;
    private String imgPath;
    private FileOperateHelper mFileHelper;
    private int type;

    public AddGoodsPresenter(AddGoodsView view, AddGoodsActivity activity) {
        super(view, activity);
    }

    public void initPresenter() {
        mFileHelper = new FileOperateHelper(mAcitity, "goods");
        mFileHelper.setOnFilePathListenter(this);
    }

    private boolean checkIsEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    public void saveData(String name, String num,
                         String len, String color,
                         String lattice) {
        if (checkIsEmpty(name) || checkIsEmpty(num) ||
                checkIsEmpty(len) || checkIsEmpty(color) ||
                checkIsEmpty(lattice)) {
            ToastUtil.showShort("请完善数据");
            return;
        }

        if (checkIsEmpty(contrastPath) || checkIsEmpty(imgPath)) {
            ToastUtil.showShort("请选择图片");
            return;
        }

        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setName(name);
        goodsInfo.setStock(Integer.parseInt(num));
        goodsInfo.setLattice(Integer.parseInt(lattice));
        goodsInfo.setImg(imgPath);
        NeedleInfo needleInfo = new NeedleInfo();
        needleInfo.setLen(len);
        needleInfo.setImg(contrastPath);
        needleInfo.setColor(color);
        needleInfo.save();
        goodsInfo.setNeedleInfo(needleInfo);
        goodsInfo.save();
        ToastUtil.showShort("保存成功");
        ActionHelper.UnNullAction(mView, () -> mView.finishActivity());
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        Uri _uri = data.getData();
        type = requestCode;
        switch (requestCode) {
            case 200:
                contrastPath = Uri2FileUtil.uri2FilePath(_uri);
                mFileHelper.copy(new File(contrastPath));
                ActionHelper.UnNullAction(mView, () -> mView.loadContrastPic(_uri));
                Log.i(this.getClass().getSimpleName(), "对比图片地址:" + contrastPath);
                break;
            case 300:
                imgPath = Uri2FileUtil.uri2FilePath(_uri);
                mFileHelper.copy(new File(imgPath));
                ActionHelper.UnNullAction(mView, () -> mView.loadGoodsPic(_uri));
                Log.i(this.getClass().getSimpleName(), "产品图片地址:" + imgPath);
                break;
        }
    }

    @Override
    public void onFilePath(String path) {
        if (type == 200) {
            contrastPath = path;
            Log.i(this.getClass().getSimpleName(), "保存后得对比图片地址:" + contrastPath);
        } else if (type == 300) {
            imgPath = path;
            Log.i(this.getClass().getSimpleName(), "保存后产品图片地址:" + imgPath);
        }
    }
}
