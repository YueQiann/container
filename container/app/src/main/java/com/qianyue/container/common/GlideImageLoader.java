package com.qianyue.container.common;

import android.content.Context;
import android.widget.ImageView;

import com.qianyue.container.utils.ImageLoadUtil;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageLoadUtil.loadImage(imageView,path);
    }
}
