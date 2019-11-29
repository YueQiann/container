package com.qianyue.container.ui.product.add;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.qianyue.container.R;
import com.qianyue.container.common.UIhelper;
import com.qianyue.container.common.db.DBBaseActivity;
import com.qianyue.container.utils.ImageLoadUtil;

public class AddGoodsActivity extends DBBaseActivity implements AddGoodsView {
    private AddGoodsPresenter mPresenter = new AddGoodsPresenter(this, this);

    @Override
    protected String getTitleName() {
        return "添加商品";
    }

    @Override
    protected void initsView(@Nullable Bundle savedInstanceState) {
        mPresenter.initPresenter();
        leftBtn.setText("添加");
        rightBtn.setText("返回");
        rightBtn.setOnClickListener(v -> finishActivity());
        ivContrast.setOnClickListener(v -> UIhelper.gotoChoosePhoto(this, 200));
        ivImg.setOnClickListener(v -> UIhelper.gotoChoosePhoto(this, 300));
        leftBtn.setOnClickListener(v ->
                mPresenter.saveData(etName.getText().toString().trim(),
                        etNum.getText().toString().trim(),
                        etLen.getText().toString().trim(),
                        etColor.getText().toString().trim(),
                        etLattice.getText().toString().trim()));
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
