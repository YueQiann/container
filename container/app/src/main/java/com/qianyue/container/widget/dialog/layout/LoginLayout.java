package com.qianyue.container.widget.dialog.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.qianyue.container.R;
import com.qianyue.container.utils.ViewTool;

public class LoginLayout {
    private Context context;
    private Button leftBtn;
    private Button rightBtn;
    private EditText numEt;
    private EditText pwdEt;
    private View view;

    public LoginLayout(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        view = LayoutInflater.from(context).inflate(R.layout.item_login,null);
        leftBtn = ViewTool.findById(view, R.id.item_login_btn);
        rightBtn = ViewTool.findById(view, R.id.item_login_back_btn);
        numEt = ViewTool.findById(view, R.id.item_login_num_et);
        pwdEt = ViewTool.findById(view, R.id.item_login_pwd_et);
    }

    public Button getLeftBtn() {
        return leftBtn;
    }

    public Button getRightBtn() {
        return rightBtn;
    }

    public EditText getNumEt() {
        return numEt;
    }

    public EditText getPwdEt() {
        return pwdEt;
    }

    public View getView() {
        return view;
    }
}
