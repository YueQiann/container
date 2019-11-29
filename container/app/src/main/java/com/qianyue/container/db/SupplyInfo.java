package com.qianyue.container.db;

import org.litepal.crud.LitePalSupport;

public class SupplyInfo extends LitePalSupport {
    private long id;
    // 货物名
    private String goodsName;
    // 补货人
    private String userName;
    // 货物图片
    private String goodsImg;
    // 货道
    private int channel;
    // 时间
    private long time;
    // 补货的数量
    private String count;

    public long getId() {
        return id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
