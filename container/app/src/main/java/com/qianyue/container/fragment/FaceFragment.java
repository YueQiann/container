package com.qianyue.container.fragment;

import com.qianyue.container.utils.ToastUtil;
import com.qianyue.face.faceserver.CompareResult;
import com.qianyue.face.fragment.RecognizeFragment;

public class FaceFragment extends RecognizeFragment {
    @Override
    protected void onSucc(CompareResult compareResult) {
        super.onSucc(compareResult);
        ToastUtil.showShort("人脸识别成功");
    }

    @Override
    protected void onFail(Object object) {
        super.onFail(object);
        ToastUtil.showShort("人脸识别失败");
    }
}
