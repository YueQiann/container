package com.qianyue.container.ui.splash;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.qianyue.container.R;
import com.qianyue.container.base.BaseActivity;
import com.qianyue.container.common.GlideImageLoader;
import com.qianyue.container.common.UIhelper;
import com.qianyue.container.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

import butterknife.BindView;

public class SplashActivity extends BaseActivity implements SplashView {
    @BindView(R.id.splash_banner)
    Banner banner;

    private SplashPresenter presenter = new SplashPresenter(this, this);

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initPermissions();
        presenter.init();
        presenter.getData();
        banner.setImageLoader(new GlideImageLoader());
        banner.setOnBannerListener(position -> UIhelper.gotoMainActivity(mContext));
        banner.start();
    }

    private void initPermissions() {
        String permissions[] = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE};
        RxPermissions _rxPermissions = new RxPermissions(this);
        _rxPermissions.request(permissions)
                .subscribe(granted -> {
                    if (!granted) {
                        ToastUtil.showShort("您不同意权限将影响使用");
                    }
                });
    }

    @Override
    protected void initCountTimer() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_splash;
    }

    @Override
    public void setData(List data) {
        banner.update(data);
    }
}
