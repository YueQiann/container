package com.qianyue.container.ui.personnel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseLayoutActivity;
import com.qianyue.container.common.UIhelper;
import com.qianyue.container.utils.InitTopView;

public class PersonnelActivity extends BaseLayoutActivity implements View.OnClickListener {

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        InitTopView.initTitle("人员管理", this);
        leftBtn.setVisibility(View.INVISIBLE);
        rightBtn.setVisibility(View.INVISIBLE);
        addToView();
    }

    private void findView(View view) {
        view.findViewById(R.id.item_personnel_admin_btn).setOnClickListener(this);
        view.findViewById(R.id.item_personnel_maintain_btn).setOnClickListener(this);
        view.findViewById(R.id.item_personnel_recipient_btn).setOnClickListener(this);
        view.findViewById(R.id.item_personnel_out_btn).setOnClickListener(this);
    }

    private void addToView() {
        View rootVeiw = LayoutInflater.from(mContext).inflate(R.layout.item_personnel, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootVeiw.setLayoutParams(lp);
        linearLayoutCompat.addView(rootVeiw);
        findView(rootVeiw);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_personnel_admin_btn:
                UIhelper.gotoPersonnelChildActivity(mContext, 2, "总管理员");
                break;
            case R.id.item_personnel_maintain_btn:
                UIhelper.gotoPersonnelChildActivity(mContext, 1, "运维人员管理");
                break;
            case R.id.item_personnel_recipient_btn:
                UIhelper.gotoPersonnelChildActivity(mContext, 0, "领取人员管理");
                break;
            case R.id.item_personnel_out_btn:
                finish();
                break;
        }
    }
}
