package com.qianyue.container.ui.product.info;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.qianyue.container.common.UIhelper;
import com.qianyue.container.common.db.DBBaseActivity;
import com.qianyue.container.db.GoodsInfo;
import com.qianyue.container.db.NeedleInfo;
import com.qianyue.container.utils.ImageLoadUtil;

public class GoodsInfoActivity extends DBBaseActivity implements GoodsInfoView {
    private GoodsInfoPresenter mPresenter = new GoodsInfoPresenter(this, this);

    @Override
    protected String getTitleName() {
        return "产品编辑";
    }

    @Override
    protected void initsView(@Nullable Bundle savedInstanceState) {
        mPresenter.setData(getIntent());
        leftBtn.setText("保存");
        rightBtn.setText("返回");
        delBtn.setVisibility(View.VISIBLE);
        delBtn.setText("删除");
        delBtn.setOnClickListener(v -> mPresenter.delGoodsInfo());
        ivContrast.setOnClickListener(v -> UIhelper.gotoChoosePhoto(this, 200));
        ivImg.setOnClickListener(V -> UIhelper.gotoChoosePhoto(this, 300));
        rightBtn.setOnClickListener(v -> finishActivity());
        leftBtn.setOnClickListener(v -> mPresenter.upData(
                etName.getText().toString().trim(),
                etNum.getText().toString().trim(),
                etLen.getText().toString().trim(),
                etColor.getText().toString().trim(),
                etLattice.getText().toString().trim()));
    }

    @Override
    public void showGoodsInfo(GoodsInfo info) {
        etName.setText(info.getName());
        etNum.setText(String.valueOf(info.getStock()));
        etLattice.setText(String.valueOf(info.getLattice()));
        ImageLoadUtil.loadImage(ivImg, info.getImg());
        NeedleInfo _needInfo = info.getNeedleInfo();
        if (_needInfo != null) {
            etLen.setText(_needInfo.getLen());
            etColor.setText(_needInfo.getColor());
            ImageLoadUtil.loadImage(ivContrast, _needInfo.getImg());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void loadContrastPic(Uri uri) {
        ImageLoadUtil.loadImage(ivContrast, uri);
    }

    @Override
    public void loadGoodsPic(Uri uri) {
        ImageLoadUtil.loadImage(ivImg, uri);
    }
}
