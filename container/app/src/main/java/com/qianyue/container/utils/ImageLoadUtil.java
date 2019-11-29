package com.qianyue.container.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.qianyue.container.common.RoundedCornersTransform;

public class ImageLoadUtil {

    /**
     * 加载中心扩张版图片
     *
     * @param view
     * @param url
     */
    public static void loadImage(ImageView view, Object url) {
        RequestOptions options = RequestOptions.centerCropTransform();
        Glide.with(view.getContext())
                .load(url)
                .apply(options)
                .into(view);
    }

    /**
     * 加载扩张版图片
     *
     * @param view
     * @param url
     */
    public static void loadFitCenterImage(ImageView view, Object url) {
        RequestOptions options = RequestOptions.fitCenterTransform();
        Glide.with(view.getContext())
                .load(url)
                .apply(options)
                .into(view);
    }


    /**
     * 加载top圆角效果
     */
    public static void loadTopRoundImage(ImageView view, Object url, int roundingRadius) {
        Context context = view.getContext();
        float radius = CommonUtil.dip2px(roundingRadius);
        RoundedCornersTransform cornersTransform = new RoundedCornersTransform(context, radius);
        cornersTransform.setNeedCorner(true, true, false, false);
        MultiTransformation transformation = new MultiTransformation(new CenterCrop(), cornersTransform);
        RequestOptions op = RequestOptions.bitmapTransform(transformation);
        Glide.with(context).load(url).apply(op).into(view);
    }
}
