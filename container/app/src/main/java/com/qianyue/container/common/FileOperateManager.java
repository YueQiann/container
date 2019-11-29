package com.qianyue.container.common;


import android.os.Handler;
import android.os.Looper;

import com.qianyue.container.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FileOperateManager {
    private static FileOperateManager instance;
    private static final Executor executor = new ThreadPoolExecutor(0 /* corePoolSize */,
            Integer.MAX_VALUE /* maximumPoolSize */, 60L /* keepAliveTime */, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    private final static Android _android = new Android();

    private FileOperateManager() {
    }

    public static FileOperateManager getInstance() {
        if (null == instance) {
            synchronized (FileOperateManager.class) {
                if (null == instance) {
                    instance = new FileOperateManager();
                }
            }
        }
        return instance;
    }

    public void copy(String source, String dest, CallBack callBack) {
        executor.execute(() -> {
            try {
                FileUtil.copyFile(source, dest);
                _android.defaultCallbackExecutor().execute(() -> callBack.onSucc());
            } catch (IOException e) {
                e.printStackTrace();
                _android.defaultCallbackExecutor().execute(() -> callBack.onFail(e.getMessage()));
            } finally {
                _android.defaultCallbackExecutor().execute(() -> callBack.onFinish());
            }
        });
    }

    public void copy(File source, File dest, CallBack callBack) {
        executor.execute(() -> {
            try {
                FileUtil.copyFile(source, dest);
                _android.defaultCallbackExecutor().execute(() -> callBack.onSucc());
            } catch (IOException e) {
                e.printStackTrace();
                _android.defaultCallbackExecutor().execute(() -> callBack.onFail(e.getMessage()));
            } finally {
                _android.defaultCallbackExecutor().execute(() -> callBack.onFinish());
            }
        });
    }

    public void delete(String path, CallBack callBack) {
        executor.execute(() -> {
            try {
                boolean _b = FileUtil.deleteFile(path);
                if (_b)
                    _android.defaultCallbackExecutor().execute(() -> callBack.onSucc());
                else
                    _android.defaultCallbackExecutor().execute(() -> callBack.onFail("删除失败"));
            } catch (Exception e) {
                _android.defaultCallbackExecutor().execute(() -> callBack.onFail(e.getMessage()));
            } finally {
                _android.defaultCallbackExecutor().execute(() -> callBack.onFinish());
            }
        });
    }

    public void delete(File path, CallBack callBack) {
        executor.execute(() -> {
            try {
                boolean _b = FileUtil.deleteFile(path);
                if (_b)
                    _android.defaultCallbackExecutor().execute(() -> callBack.onSucc());
                else
                    _android.defaultCallbackExecutor().execute(() -> callBack.onFail("删除失败"));
            } catch (Exception e) {
                _android.defaultCallbackExecutor().execute(() -> callBack.onFail(e.getMessage()));
            } finally {
                _android.defaultCallbackExecutor().execute(() -> callBack.onFinish());
            }
        });
    }


    static class Android {
        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        static class MainThreadExecutor implements Executor {
            private final Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void execute(Runnable r) {
                handler.post(r);
            }
        }
    }
}
