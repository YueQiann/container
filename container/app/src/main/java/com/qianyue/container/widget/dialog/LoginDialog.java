package com.qianyue.container.widget.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qianyue.container.common.UIhelper;
import com.qianyue.container.db.UserInfo;
import com.qianyue.container.utils.CommonUtil;
import com.qianyue.container.widget.dialog.layout.LoginLayout;

import java.util.List;

public class LoginDialog extends BaseDialogFragment {
    @Override
    protected void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {
        titleTv.setText("系统设置");
        LoginLayout vLayout = new LoginLayout(context);
        layout.addView(vLayout.getView());
        vLayout.getLeftBtn().setOnClickListener(v -> {
            String num = vLayout.getNumEt().getText().toString().trim();
            String pwd = vLayout.getPwdEt().getText().toString().trim();
            UserInfo user = CommonUtil.loginUser(num, pwd);
            if (user != null) {
                UIhelper.gotoSystemActivity(context, user.getType());
                dismissAllowingStateLoss();
            }
        });
        vLayout.getRightBtn().setOnClickListener(v -> dismissAllowingStateLoss());
    }
}
