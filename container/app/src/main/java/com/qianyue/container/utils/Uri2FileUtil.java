package com.qianyue.container.utils;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.qianyue.common.FileDescriptorPath;
import com.qianyue.container.application.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Uri2FileUtil {

    public static File uri2File(Uri uri) {
        String _path = uri2FilePath(uri);
        return TextUtils.isEmpty(_path) ? null : new File(_path);
    }

    public static String uri2FilePath(Uri uri) {
        if (uri == null)
            return null;
        switch (uri.getScheme()) {
            case ContentResolver.SCHEME_CONTENT:
                return getFilePathContentUri(uri);
            case ContentResolver.SCHEME_FILE:
                return uri.getPath();
            default:
                return null;
        }
    }

    public static File getFileFromContentUri(Uri uri) {
        String _path = getFilePathContentUri(uri);
        return TextUtils.isEmpty(_path) ? null : new File(_path);
    }

    public static String getFilePathContentUri(Uri uri) {
        ContentResolver resolver = App.getContext().getContentResolver();
        String _file = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ParcelFileDescriptor _fileDescriptor = null;
            try {
                _fileDescriptor = resolver.openFileDescriptor(uri, "rw");
                _file = FileDescriptorPath.getPath(_fileDescriptor.getFileDescriptor());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (_fileDescriptor != null)
                        _fileDescriptor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Cursor cursor = resolver.query(uri, new String[]{MediaStore.MediaColumns.DATA},
                    null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int index = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
                _file = cursor.getString(index);
            }
            cursor.close();
        }
        return _file;
    }

}
