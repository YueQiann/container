package com.qianyue.container.ui.home;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Size;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qianyue.common.utils.CommonUtils;
import com.qianyue.container.common.ActionHelper;
import com.qianyue.container.common.data.DataBaseAdapter;
import com.qianyue.container.common.data.DataBasePresenter;
import com.qianyue.container.db.GoodsInfo;
import com.qianyue.container.utils.FileUtil;
import com.qianyue.container.utils.ToastUtil;
import com.qianyue.seriaport.UsbService;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;

public class MainPresenter extends DataBasePresenter<MainView, MainActivity>
        implements BaseQuickAdapter.OnItemClickListener {
    private ImageCapture imageCapture;
    private ImageCaptureConfig config;
    private MyHandler mHandler;
    private UsbService usbService;

    public MainPresenter(MainView view, MainActivity activity) {
        super(view, activity);
    }

    @Override
    protected DataBaseAdapter getAdapter(List<GoodsInfo> data) {
        DataBaseAdapter _adapter = new DataBaseAdapter(data);
        _adapter.setOnItemClickListener(this);
        return _adapter;
    }

    protected void init() {
        initCamera();
        mHandler = new MyHandler(this);
    }

    /**
     * 相机初始化
     */
    private void initCamera() {
        config = new ImageCaptureConfig.Builder()
                .setTargetRotation(mAcitity.getWindowManager().getDefaultDisplay().getRotation())
                .setTargetResolution(new Size(1280, 720))
                .setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
                .build();
    }

    /**
     * 拍摄图片
     */
    public void takePic() {
        imageCapture = new ImageCapture(config);
        CameraX.bindToLifecycle(mAcitity, imageCapture);
        String _name = String.format("img%d.jpg", System.currentTimeMillis());
        File file = new File(mAcitity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/takePic", _name);
        FileUtil.checkAndCreate(file);
        imageCapture.takePicture(file, new ImageCapture.OnImageSavedListener() {
            @Override
            public void onImageSaved(@NonNull File file) {
                takeNextPic(file);
            }

            @Override
            public void onError(@NonNull ImageCapture.ImageCaptureError imageCaptureError,
                                @NonNull String message, @Nullable Throwable cause) {
                CameraX.unbind(imageCapture);
            }
        });
    }

    /**
     * 拍摄第二张图片
     */
    public void takeNextPic(File file) {
        imageCapture.takePicture(file, new ImageCapture.OnImageSavedListener() {
            @Override
            public void onImageSaved(@NonNull File file) {
                CameraX.unbind(imageCapture);
            }

            @Override
            public void onError(@NonNull ImageCapture.ImageCaptureError imageCaptureError, @NonNull String message, @Nullable Throwable cause) {
                CameraX.unbind(imageCapture);
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        takePic();
    }

    /**
     * =========================== ================================ ============================= =======================
     */

//    public void open() {
//        if (usbService != null)
//            usbService.write(CommonUtils.hexToByteArray(""));
//    }

    private void startService(Class<?> service, ServiceConnection serviceConnection, Bundle extras) {
        if (!UsbService.SERVICE_CONNECTED) {
            Intent startService = new Intent(mAcitity, service);
            if (extras != null && !extras.isEmpty()) {
                Set<String> keys = extras.keySet();
                for (String key : keys) {
                    String extra = extras.getString(key);
                    startService.putExtra(key, extra);
                }
            }
            mAcitity.startService(startService);
        }
        Intent bindingIntent = new Intent(mAcitity, service);
        mAcitity.bindService(bindingIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void setFilters() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbService.ACTION_USB_PERMISSION_GRANTED);
        filter.addAction(UsbService.ACTION_NO_USB);
        filter.addAction(UsbService.ACTION_USB_DISCONNECTED);
        filter.addAction(UsbService.ACTION_USB_NOT_SUPPORTED);
        filter.addAction(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED);
        filter.addAction(UsbService.ACTION_USB_READY);
        mAcitity.registerReceiver(mUsbReceiver, filter);
    }

    public void startService() {
        setFilters();
        startService(UsbService.class, usbConnection, null);
    }

    public void unregister() {
        ActionHelper.UnNullAction(mAcitity, new ActionHelper.onActionListener() {
            @Override
            public void onAction() {
                mAcitity.unregisterReceiver(mUsbReceiver);
                mAcitity.unbindService(usbConnection);
            }
        });
    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case UsbService.ACTION_USB_PERMISSION_GRANTED: // USB PERMISSION GRANTED
                    Toast.makeText(context, "USB Ready", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_PERMISSION_NOT_GRANTED: // USB PERMISSION NOT GRANTED
                    Toast.makeText(context, "USB Permission not granted", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_NO_USB: // NO USB CONNECTED
                    Toast.makeText(context, "No USB connected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_DISCONNECTED: // USB DISCONNECTED
                    Toast.makeText(context, "USB disconnected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_NOT_SUPPORTED: // USB NOT SUPPORTED
                    Toast.makeText(context, "USB device not supported", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_READY:
                    Toast.makeText(context, "USB 可以开始测试", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private final ServiceConnection usbConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            usbService = ((UsbService.UsbBinder) arg1).getService();
            usbService.setHandler(mHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            usbService = null;
        }
    };

    private static class MyHandler extends Handler {
        private final WeakReference<MainPresenter> mPresenter;

        public MyHandler(MainPresenter presenter) {
            mPresenter = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UsbService.MESSAGE_FROM_SERIAL_PORT:
                    //返回的信息
                    String data = (String) msg.obj;
                    break;
                case UsbService.CTS_CHANGE:
                    ToastUtil.showLong("CTS_CHANGE");
                    break;
                case UsbService.DSR_CHANGE:
                    ToastUtil.showLong("DSR_CHANGE");
                    break;
            }
        }
    }
}
