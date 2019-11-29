package com.qianyue.container.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {

    /**
     * 文件拷贝
     *
     * @param source 源文件
     * @param dest   目标文件
     */
    public static void copyFile(File source, File dest) throws IOException {
        checkAndCreate(dest);
        FileChannel sourceCh = new FileInputStream(source).getChannel();
        FileChannel destCh = new FileOutputStream(dest).getChannel();
        MappedByteBuffer byteBuffer = sourceCh.map(
                FileChannel.MapMode.READ_ONLY, 0, sourceCh.size());
        destCh.write(byteBuffer);
        sourceCh.close();
        destCh.close();
    }

    /**
     * 文件拷贝
     *
     * @param source 源文件
     * @param dest   目标文件
     */
    public static void copyFile(String source, String dest) throws IOException {
        File _source = new File(source);
        File _dest = new File(dest);
        if (!checkFile(_source))
            copyFile(_source, _dest);
    }

    /**
     * 删除文件
     *
     * @param source
     */
    public static boolean deleteFile(File source) {
        if (source != null && source.exists() && source.isFile()) {
            return source.delete();
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param path
     */
    public static boolean deleteFile(String path) {
        File _file = new File(path);
        return deleteFile(_file);
    }

    /**
     * 检查文件是否存在
     * 不存在则创建
     *
     * @param file
     */
    public static void checkAndCreate(File file) {
        if (!checkFile(file)) {
            if (file != null) {
                File _parent = file.getParentFile();
                _parent.mkdirs();
            } else {
                throw new NullPointerException("文件不能为空!");
            }
        }
    }

    /**
     * 检查文件是否存在
     * flase 不存在 or 空
     * true 存在
     *
     * @param file
     * @return
     */
    public static boolean checkFile(File file) {
        return file != null && file.exists();
    }

}
