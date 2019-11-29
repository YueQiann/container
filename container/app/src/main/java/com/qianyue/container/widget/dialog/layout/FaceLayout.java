package com.qianyue.container.widget.dialog.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qianyue.container.R;
import com.qianyue.container.utils.ViewTool;

public class FaceLayout {
    private FrameLayout frameLayout;
    private TextView tvTitle;
    private Context context;
    private View view;

    public FaceLayout(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        view = LayoutInflater.from(context).inflate(R.layout.item_face, null);
        frameLayout = ViewTool.findById(view, R.id.item_face_fl);
        tvTitle = ViewTool.findById(view, R.id.item_face_tv);
    }

    public int getFrameLayoutId() {
        return R.id.item_face_fl;
    }

    public FrameLayout getFrameLayout() {
        return frameLayout;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public Context getContext() {
        return context;
    }

    public View getView() {
        return view;
    }
}
