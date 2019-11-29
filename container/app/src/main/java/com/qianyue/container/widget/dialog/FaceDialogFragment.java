package com.qianyue.container.widget.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.qianyue.container.fragment.*;

import com.qianyue.container.widget.dialog.layout.FaceLayout;

public class FaceDialogFragment extends BaseDialogFragment implements ViewTreeObserver.OnGlobalLayoutListener {
    private Fragment faceFragment;
    private FaceLayout vLayout;

    @Override
    protected void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vLayout = new FaceLayout(context);
        layout.addView(vLayout.getView());
//        vLayout.getFrameLayout().getViewTreeObserver().addOnGlobalLayoutListener(this);
        faceFragment = new FaceFragment();
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(vLayout.getFrameLayoutId(), faceFragment);
        ft.commit();
    }

    @Override
    public void onGlobalLayout() {
//        vLayout.getFrameLayout().getViewTreeObserver().removeOnGlobalLayoutListener(this);

    }
}
