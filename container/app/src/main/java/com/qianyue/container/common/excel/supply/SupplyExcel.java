package com.qianyue.container.common.excel.supply;

import com.qianyue.container.common.excel.base.BaseExcel;
import com.qianyue.container.db.ReceiveInfo;
import com.qianyue.container.db.SupplyInfo;
import com.qianyue.container.utils.CommonUtil;

import org.litepal.LitePal;

import java.io.File;
import java.util.List;

import jxl.format.Alignment;
import jxl.write.WritableCellFormat;
import jxl.write.WriteException;

public class SupplyExcel extends BaseExcel {
    private String[] titles = {"时间", "货物名称", "货道", "补货人", "补货数量", "货物图片"};

    @Override
    protected File getFilePath() {
        return getRootPath();
    }

    @Override
    protected String getFileName() {
        String _time = CommonUtil.currentTime();
        return String.format("补货报表%s", _time);
    }

    @Override
    public void fillData() {
        if (writableWorkbook == null) {
            mCallBack.onFail("excel创建失败!");
            return;
        }

        List<SupplyInfo> _data = LitePal.findAll(SupplyInfo.class);
        try {
            createSheetSetTitle(null, 0, "领取列表", titles);
            fill(_data);
        } catch (WriteException e) {
            e.printStackTrace();
            mCallBack.onFail(e.getMessage());
        }
    }

    // {"时间", "货物名称", "货道", "补货人", "补货数量", "货物图片"};
    private void fill(List<SupplyInfo> data) throws WriteException {
        int size = data.size();
        WritableCellFormat dataFormat = new WritableCellFormat();
        dataFormat.setAlignment(Alignment.RIGHT);
        for (int i = 1; i <= size; i++) {
            SupplyInfo info = data.get(i - 1);
            addCall(0, i, CommonUtil.timeStamp2Date(info.getTime(), "yyyy-MM-dd HH:mm:ss"), dataFormat);
            addCall(1, i, info.getGoodsName(), dataFormat);
            addCall(2, i, info.getChannel() + "", dataFormat);
            addCall(3, i, info.getUserName(), dataFormat);
            addCall(4, i, String.valueOf(info.getCount()), dataFormat);
            // 图片
            addImg(5, i, new File(info.getGoodsImg()));
        }
    }
}
