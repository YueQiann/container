package com.qianyue.container.common;

import android.content.Context;
import android.os.Environment;

import com.qianyue.container.application.App;
import com.qianyue.container.utils.FileUtil;
import com.qianyue.container.utils.ToastUtil;

import java.io.File;

public class FileOperateHelper {
    private File mRootFile;
    private Context mContext;
    private OnFilePathListenter listenter;

    public FileOperateHelper(Context mContext) {
        mRootFile = App.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        this.mContext = mContext;
    }

    public FileOperateHelper(Context mContext, String rootPathName) {
        mRootFile = new File(App.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), rootPathName);
        this.mContext = mContext;
    }

    public void copy(File source) {
        if (!source.getAbsolutePath()
                .contains(mRootFile.getAbsolutePath()) && !source.isDirectory()) {
            File _file = new File(mRootFile, source.getName());
            FileOperateManager.getInstance().copy(source, _file, new FileCallBack(mContext) {
                @Override
                public void onSucc() {
                    ActionHelper.UnNullAction(listenter, () -> listenter.onFilePath(_file.getAbsolutePath()));
                }

                @Override
                public void onFail(String msg) {
                    ToastUtil.showShort(msg);
                }
            });
        }
    }

    public void delete(File source) {
        FileOperateManager.getInstance().delete(source, new FileCallBack(mContext) {
            @Override
            public void onSucc() {

            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }

    public void setOnFilePathListenter(OnFilePathListenter listenter) {
        this.listenter = listenter;
    }

    public interface OnFilePathListenter {
        void onFilePath(String path);
    }

}
