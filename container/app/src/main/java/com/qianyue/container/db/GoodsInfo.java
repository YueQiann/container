package com.qianyue.container.db;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class GoodsInfo extends LitePalSupport implements Parcelable {
    private int id;
    // 名称
    private String name;
    // 余量
    private int stock;
    // 格子
    private int lattice;
    // 图片
    private String img;
    // 对应的针
    private NeedleInfo needleInfo;

    public GoodsInfo() {
    }

    protected GoodsInfo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        stock = in.readInt();
        lattice = in.readInt();
        img = in.readString();
    }

    public static final Creator<GoodsInfo> CREATOR = new Creator<GoodsInfo>() {
        @Override
        public GoodsInfo createFromParcel(Parcel in) {
            return new GoodsInfo(in);
        }

        @Override
        public GoodsInfo[] newArray(int size) {
            return new GoodsInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getLattice() {
        return lattice;
    }

    public void setLattice(int lattice) {
        this.lattice = lattice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public NeedleInfo getNeedleInfo() {
        List<NeedleInfo> _list = LitePal.where("goodsinfo_id = ?", String.valueOf(id)).find(NeedleInfo.class);
        return _list != null && _list.size() != 0 ? _list.get(0) : null;
    }

    public void setNeedleInfo(NeedleInfo needleInfo) {
        this.needleInfo = needleInfo;
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
        parcel.writeString(name);
        parcel.writeInt(stock);
        parcel.writeInt(lattice);
        parcel.writeString(img);
    }

    @Override
    public String toString() {
        return "name:" + name +
                "stock" + stock +
                "lattice" + lattice +
                "img" + img;
    }
}
