package com.qianyue.common;

import java.io.FileDescriptor;
import java.lang.reflect.Field;

public class FileDescriptorPath {

    static {
        System.loadLibrary("FileDescriptorPath");
    }

    public static String getPath(FileDescriptor fileDescriptor) {
        String _path = null;
        try {
            Field _field = FileDescriptor.class.getDeclaredField("descriptor");
            _field.setAccessible(true);
            final int fd = (int) _field.get(fileDescriptor);
            _path = getPath(fd);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return _path;
    }

    public static native String getPath(int fd);
}
