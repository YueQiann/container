package com.qianyue.container.ui.personnel.info;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.common.UIhelper;
import com.qianyue.container.db.UserInfo;
import com.qianyue.container.utils.ImageLoadUtil;
import com.qianyue.container.utils.InitTopView;
import com.qianyue.container.utils.ViewTool;

public class PersonnelInfoActivity extends BaseLayoutActivity implements PersonnelInfoView {
    private PersonnelInfoPresenter mPresenter = new PersonnelInfoPresenter(this, this);
    private EditText etName, etRemarks, etNum, etPwd;
    private ImageView ivImg;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        InitTopView.initTitle(getIntent().getStringExtra("name"), this);
        addToView();
        mPresenter.setData(getIntent());
    }

    private void addToView() {
        View _rootView = LayoutInflater.from(mContext).inflate(R.layout.item_personnel_info, null);
        ViewGroup.LayoutParams _lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        _rootView.setLayoutParams(_lp);
        linearLayoutCompat.addView(_rootView);
        findView(_rootView);
    }

    private void findView(View view) {
        etName = ViewTool.findById(view, R.id.item_personnel_info_name_et);
        etRemarks = ViewTool.findById(view, R.id.item_personnel_info_remarks_et);
        etNum = ViewTool.findById(view, R.id.item_personnel_info_num_et);
        etPwd = ViewTool.findById(view, R.id.item_personnel_info_pwd_et);
        ivImg = ViewTool.findById(view, R.id.item_personnel_info_iv);
        ivImg.setOnClickListener(v -> UIhelper.gotoChoosePhoto(this, 200));
    }

    private void initBtn() {
        leftBtn.setText("保存");
        rightBtn.setText("返回");
        delBtn.setText("删除");
        delBtn.setVisibility(View.VISIBLE);
        leftBtn.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String remarks = etRemarks.getText().toString().trim();
            String num = etNum.getText().toString().trim();
            String pwd = etPwd.getText().toString().trim();
            mPresenter.saveDate(name, remarks, num, pwd);
        });
        rightBtn.setOnClickListener(v -> finishActivity());
        delBtn.setOnClickListener(v -> mPresenter.deleteInfo());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showPersonnelInfo(UserInfo info) {
        etName.setText(info.getName());
        etRemarks.setText(info.getRemarks());
        etNum.setText(info.getNumber());
        etPwd.setText(info.getPassword());
        ImageLoadUtil.loadImage(ivImg, info.getImg());
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void loadPic(Uri uri) {
        ImageLoadUtil.loadImage(ivImg, uri);
    }

    @Override
    public void changeStyle() {
        initBtn();
    }

    @Override
    public void addStyle() {
        leftBtn.setText("确认");
        rightBtn.setText("返回");
        leftBtn.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String remarks = etRemarks.getText().toString().trim();
            String num = etNum.getText().toString().trim();
            String pwd = etPwd.getText().toString().trim();
            mPresenter.addInfo(name, remarks, num, pwd);
        });
        rightBtn.setOnClickListener(v -> finishActivity());
    }
}
