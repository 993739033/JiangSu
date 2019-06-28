package com.wyw.jiangsu.bean.local;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by wyw on 2016/12/23.
 * view_add_anim 的实体类
 */

public class LocalAnimTypeBean implements Parcelable{
    private String animType;
    private String deadthNumber;
    private String insuredNumber;

    public LocalAnimTypeBean() {
    }

    public LocalAnimTypeBean(String animType, String deadthNumber, String insuredNumber) {
        this.animType = animType;
        this.deadthNumber = deadthNumber;
        this.insuredNumber = insuredNumber;
    }

    protected LocalAnimTypeBean(Parcel in) {
        animType = in.readString();
        deadthNumber = in.readString();
        insuredNumber = in.readString();
    }

    public static final Creator<LocalAnimTypeBean> CREATOR = new Creator<LocalAnimTypeBean>() {
        @Override
        public LocalAnimTypeBean createFromParcel(Parcel in) {
            return new LocalAnimTypeBean(in);
        }

        @Override
        public LocalAnimTypeBean[] newArray(int size) {
            return new LocalAnimTypeBean[size];
        }
    };

    public String getAnimType() {
        return animType;
    }

    public void setAnimType(String animType) {
        this.animType = animType;
    }

    public String getDeadthNumber() {
        return deadthNumber;
    }

    public void setDeadthNumber(String deadthNumber) {
        this.deadthNumber = deadthNumber;
    }

    public String getInsuredNumber() {
        return insuredNumber;
    }

    public void setInsuredNumber(String insuredNumber) {
        this.insuredNumber = insuredNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(animType);
        dest.writeString(deadthNumber);
        dest.writeString(insuredNumber);
    }
}
