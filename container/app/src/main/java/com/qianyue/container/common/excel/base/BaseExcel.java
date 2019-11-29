package com.qianyue.container.common.excel.base;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.qianyue.container.application.App;
import com.qianyue.container.common.excel.call.CallBack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public abstract class BaseExcel implements Excel {
    // 工作簿
    protected WritableWorkbook writableWorkbook;
    // sheet
    protected WritableSheet workbookSheet;
    // 回调
    protected CallBack mCallBack;

    private static final int MIN_WIDTH = 9;
    private static final int CELL_INTERVAL = 1;

    @Override
    public void create() {
        File filePath = getFilePath();
        String fileName = getFileName();
        if (TextUtils.isEmpty(fileName)) {
            mCallBack.onFail("文件名不能为空!");
            return;
        }
        if (filePath == null) {
            return;
        }
        createWorkbook(filePath, fileName);
    }

    @Override
    public void close() {
        try {
            if (writableWorkbook != null) {
                writableWorkbook.write();
                writableWorkbook.close();
                mCallBack.onSucc();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            if (mCallBack != null)
                mCallBack.onFinish();
        }
    }

    protected abstract File getFilePath();

    protected abstract String getFileName();

    @Override
    public void setCallBack(CallBack callBack) {
        this.mCallBack = callBack;
    }

    /**
     * sheet titles
     *
     * @param page
     * @param name
     */
    protected void createSheetSetTitle(WritableCellFormat format, int page, String name, String... titles) throws WriteException {
        workbookSheet = writableWorkbook.createSheet(name, page);
        Label label;
        for (int x = 0; x < titles.length; x++) {
            if (format == null) {
                label = new Label(x, 0, titles[x]);
            } else {
                label = new Label(x, 0, titles[x], format);
            }
            workbookSheet.setColumnView(x, getStringLength(titles[x]) + CELL_INTERVAL);
            workbookSheet.addCell(label);
        }
    }

    /**
     * 得到 label
     *
     * @param c
     * @param r
     * @param cont
     * @param st
     * @return
     */
    protected Label getLabel(int c, int r, String cont, CellFormat st) {
        return st != null ? new Label(c, r, cont, st) : new Label(c, r, cont);
    }

    protected void addCall(int c, int r, String cont, CellFormat st) {
        addCall(c, getLabel(c, r, cont, st));
    }

    protected void addImg(int c, int r, File file) throws RowsExceededException {
        WritableImage image = new WritableImage(c, r, 1, 1, getImageByte(file));
        workbookSheet.setRowView(r, 600, false); //设置行高
        // 把图片插入到sheet
        workbookSheet.addImage(image);
    }

    public static byte[] getImageByte(File file) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(file);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    /**
     * 添加 call
     *
     * @param c
     * @param label
     */
    protected void addCall(int c, Label label) {
        String _str = label.getContents();
        int width = getStringLength(_str);
        workbookSheet.setColumnView(c, (width > MIN_WIDTH ? width : MIN_WIDTH));
        try {
            workbookSheet.addCell(label);
        } catch (WriteException e) {
            e.printStackTrace();
            mCallBack.onFail(e.getMessage());
        }
    }

    /**
     * 创建工作簿
     *
     * @param path
     * @param name
     */
    protected void createWorkbook(File path, String name) {
        checkFilePathIsExist(path);
        File _file = new File(path, name + ".xls");
        try {
            writableWorkbook = Workbook.createWorkbook(_file);
        } catch (IOException e) {
            e.printStackTrace();
            mCallBack.onFail(e.getMessage());
        }
    }

    /**
     * 如果路径不存在就创建
     *
     * @param filePath
     */
    private void checkFilePathIsExist(File filePath) {
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
    }

    /**
     * 根据字符串返回字符数,解决中文显示不全的问题
     *
     * @param str
     * @return
     */
    private int getStringLength(String str) {
        int count = 0;
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            String len = Integer.toBinaryString(c[i]);
            if (len.length() > 8) {
                count += 2;
            } else {
                count++;
            }
        }
        return count;
    }

    /**
     * 保存路径
     */
    protected File getRootPath() {
        File _file = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            File files[] = App.getContext().getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS);
            for (File file : files) {
                if (file == null) {
                    continue;
                }
                if (Environment.isExternalStorageRemovable(file)) {
                    _file = file;
                }
            }
        }
        return _file;
    }
}
