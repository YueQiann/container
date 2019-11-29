package com.qianyue.container.ui.product.info;

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

import org.litepal.LitePal;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class GoodsInfoPresenter extends BasePresenter<GoodsInfoView, GoodsInfoActivity>
        implements FileOperateHelper.OnFilePathListenter {
    private GoodsInfo mInfo;
    private String imgPath;
    private String contrastPath;
    private FileOperateHelper mFileHelper;
    private int type;

    public GoodsInfoPresenter(GoodsInfoView view, GoodsInfoActivity activity) {
        super(view, activity);
    }

    public void setData(Intent intent) {
        if (intent != null) {
            mInfo = intent.getParcelableExtra("goods");
            mFileHelper = new FileOperateHelper(mAcitity, "goods");
            mFileHelper.setOnFilePathListenter(this);
            Log.i(getClass().getSimpleName(), mInfo.toString());
            ActionHelper.UnNullAction(mView, () -> mView.showGoodsInfo(mInfo));
        }
    }

    public void delGoodsInfo() {
        if (mInfo != null) {
            NeedleInfo _info = mInfo.getNeedleInfo();
            if (_info != null) {
                int _i = _info.delete();
                Log.i(getClass().getSimpleName(), "针信息删除条数=" + _i);
            }
            int _a = LitePal.delete(GoodsInfo.class, mInfo.getId());
            Log.i(getClass().getSimpleName(), "商品信息删除条数=" + _a);
            ToastUtil.showShort("删除成功");
            ActionHelper.UnNullAction(mView, () -> mView.finishActivity());
        } else {
            ToastUtil.showShort("内容为空！");
        }
    }

    /**
     * 更新数据
     *
     * @param name    商品名
     * @param num     数量
     * @param len     长度
     * @param color   颜色
     * @param lattice 货柜编号
     */
    public void upData(String name, String num,
                       String len, String color,
                       String lattice) {

        NeedleInfo needleInfo = mInfo.getNeedleInfo();

        if (!TextUtils.isEmpty(name)) {
            mInfo.setName(name);
        }

        if (!TextUtils.isEmpty(num)) {
            mInfo.setStock(Integer.parseInt(num));
        }

        if (!TextUtils.isEmpty(lattice)) {
            mInfo.setLattice(Integer.parseInt(lattice));
        }

        if (!TextUtils.isEmpty(imgPath)) {
            mInfo.setImg(imgPath);
        }

        if (needleInfo != null) {

            if (!TextUtils.isEmpty(len)) {
                needleInfo.setLen(len);
            }

            if (!TextUtils.isEmpty(color)) {
                needleInfo.setLen(color);
            }

            if (!TextUtils.isEmpty(contrastPath)) {
                needleInfo.setImg(contrastPath);
            }

            needleInfo.save();
        }
        mInfo.update(mInfo.getId());
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
