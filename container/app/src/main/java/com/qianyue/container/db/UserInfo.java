package com.qianyue.container.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.LitePalSupport;

public class UserInfo extends LitePalSupport implements Parcelable {
    private int id;
    // 姓名
    private String name;
    // 备注
    private String remarks;
    // 账号
    private String number;
    // 密码
    private String password;
    // 图片
    private String img;
    // type 0 普通用户 1 运营人员 2 管理员
    private int type;

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        remarks = in.readString();
        number = in.readString();
        password = in.readString();
        img = in.readString();
        type = in.readInt();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(remarks);
        parcel.writeString(number);
        parcel.writeString(password);
        parcel.writeString(img);
        parcel.writeInt(type);
    }
}
