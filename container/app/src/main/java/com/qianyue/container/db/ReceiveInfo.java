package com.qianyue.container.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

/**
 * 领取
 */
public class ReceiveInfo extends LitePalSupport implements Parcelable {
    private long id;
    // 货物名称
    private String itemName;
    // 货物图片
    private String itemPic;
    // 货道
    private int channle;
    // 出货状态 0: 出货失败 1：出货成功
    private int status;
    // 是否为人脸认证 0: 人脸 1:账号密码登录
    private int isFace;
    // 时间
    private long time;
    // 对比长度
    private String len;
    // 拍摄物体长度
    private String contrastLen;
    // 拍摄物体照片
    private String contrastPic;
    // 领取人
    private String recipients;
    // 领取人备注
    private String receiveNotes;

    public ReceiveInfo() {
    }

    protected ReceiveInfo(Parcel in) {
        id = in.readLong();
        itemName = in.readString();
        itemPic = in.readString();
        channle = in.readInt();
        status = in.readInt();
        isFace = in.readInt();
        time = in.readLong();
        len = in.readString();
        contrastLen = in.readString();
        contrastPic = in.readString();
        recipients = in.readString();
        receiveNotes = in.readString();
    }

    public static final Creator<ReceiveInfo> CREATOR = new Creator<ReceiveInfo>() {
        @Override
        public ReceiveInfo createFromParcel(Parcel in) {
            return new ReceiveInfo(in);
        }

        @Override
        public ReceiveInfo[] newArray(int size) {
            return new ReceiveInfo[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public int getChannle() {
        return channle;
    }

    public void setChannle(int channle) {
        this.channle = channle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsFace() {
        return isFace;
    }

    public void setIsFace(int isFace) {
        this.isFace = isFace;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    public String getContrastLen() {
        return contrastLen;
    }

    public void setContrastLen(String contrastLen) {
        this.contrastLen = contrastLen;
    }

    public String getContrastPic() {
        return contrastPic;
    }

    public void setContrastPic(String contrastPic) {
        this.contrastPic = contrastPic;
    }

    public String getReceiveNotes() {
        return receiveNotes;
    }

    public void setReceiveNotes(String receiveNotes) {
        this.receiveNotes = receiveNotes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(itemName);
        parcel.writeString(itemPic);
        parcel.writeInt(channle);
        parcel.writeInt(status);
        parcel.writeInt(isFace);
        parcel.writeLong(time);
        parcel.writeString(len);
        parcel.writeString(contrastLen);
        parcel.writeString(contrastPic);
        parcel.writeString(recipients);
        parcel.writeString(receiveNotes);
    }
}
