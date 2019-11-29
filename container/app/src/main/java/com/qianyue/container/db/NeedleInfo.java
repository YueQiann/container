package com.qianyue.container.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.LitePalSupport;

public class NeedleInfo extends LitePalSupport implements Parcelable {
    private int id;
    // 颜色
    private String color;
    // 长度
    private String len;
    // 图片
    private String img;

    public NeedleInfo() {
    }

    protected NeedleInfo(Parcel in) {
        id = in.readInt();
        color = in.readString();
        len = in.readString();
        img = in.readString();
    }

    public static final Creator<NeedleInfo> CREATOR = new Creator<NeedleInfo>() {
        @Override
        public NeedleInfo createFromParcel(Parcel in) {
            return new NeedleInfo(in);
        }

        @Override
        public NeedleInfo[] newArray(int size) {
            return new NeedleInfo[size];
        }
    };

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(color);
        parcel.writeString(len);
        parcel.writeString(img);
    }
}
