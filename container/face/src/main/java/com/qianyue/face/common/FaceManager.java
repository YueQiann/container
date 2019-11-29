package com.qianyue.face.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import com.qianyue.face.faceserver.FaceServer;
import com.qianyue.face.util.FaceUtil;
import com.qianyue.face.util.ImageUtil;
import com.qianyue.face.widget.ProgressDialog;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FaceManager {
    private static FaceManager INSTANCE;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ProgressDialog mDialog;
    private Handler handler = new Handler(Looper.getMainLooper());

    public FaceManager getInstance() {
        if (null == INSTANCE) {
            synchronized (FaceManager.class) {
                if (null == INSTANCE)
                    INSTANCE = new FaceManager();
            }
        }
        return INSTANCE;
    }

    public void init(Context context) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(context);
        }
        executorService.execute(() ->
                FaceServer.getInstance().init(context));
    }

    public void doRegister(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            return;
        }
        if (!dir.isDirectory()) {
            return;
        }
        final File[] jpgFiles = dir.listFiles((dir1, name) -> name.endsWith(FaceServer.IMG_SUFFIX));
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final int totalCount = jpgFiles.length;

                int successCount = 0;
                handler.post(() -> {
                    if (mDialog != null) {
                        mDialog.setMaxProgress(totalCount);
                        mDialog.show();
                    }
                });
                for (int i = 0; i < totalCount; i++) {
                    final int finalI = i;
                    handler.post(() -> {
                        if (mDialog != null) {
                            mDialog.refreshProgress(finalI);
                        }
                    });
                    final File jpgFile = jpgFiles[i];
                    Bitmap bitmap = BitmapFactory.decodeFile(jpgFile.getAbsolutePath());
                    if (bitmap == null) {
                        continue;
                    }
                    bitmap = ImageUtil.alignBitmapForNv21(bitmap);
                    if (bitmap == null) {
                        continue;
                    }
                    byte[] nv21 = ImageUtil.bitmapToNv21(bitmap, bitmap.getWidth(), bitmap.getHeight());
                    boolean success = FaceServer.getInstance().register(FaceUtil.getContext(), nv21,
                            bitmap.getWidth(), bitmap.getHeight(),
                            jpgFile.getName().substring(0, jpgFile.getName().lastIndexOf(".")));
                    if (!success) {
                    } else {
                        successCount++;
                    }
                }
                final int finalSuccessCount = successCount;
                handler.post(() -> {
                    if (mDialog != null && mDialog.isShowing())
                        mDialog.dismiss();
                });
            }
        });
    }

    public void cloes() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdownNow();
        }
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        FaceServer.getInstance().unInit();
    }
}
