package com.wyw.jiangsu.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.wyw.jiangsu.view.recyclerview.entity.MultiItemEntity;

/**
 * Created by wyw on 2016/12/26.
 * 无害化动物种类
 */

public class NetworkAnimType extends MultiItemEntity implements Parcelable{
    private String Fbsdwzl;//种类
    private String Fsws;//死亡数
    private String Fcbs;//参保数
    private String SWZL;//重量

    public NetworkAnimType() {
    }

    public NetworkAnimType(Parcel in) {
        Fbsdwzl = in.readString();
        Fsws = in.readString();
        Fcbs = in.readString();
        SWZL = in.readString();
    }

    public static final Creator<NetworkAnimType> CREATOR = new Creator<NetworkAnimType>() {
        @Override
        public NetworkAnimType createFromParcel(Parcel in) {
            return new NetworkAnimType(in);
        }

        @Override
        public NetworkAnimType[] newArray(int size) {
            return new NetworkAnimType[size];
        }
    };

    public String getFbsdwzl() {
        return Fbsdwzl;
    }

    public void setFbsdwzl(String fbsdwzl) {
        this.Fbsdwzl = fbsdwzl;
    }

    public String getFsws() {
        return Fsws;
    }

    public void setFsws(String fsws) {
        this.Fsws = fsws;
    }

    public String getFcbs() {
        return Fcbs;
    }

    public void setFcbs(String fcbs) {
        this.Fcbs = fcbs;
    }

    public String getSWZL() {
        return SWZL;
    }

    public void setSWZL(String SWZL) {
        this.SWZL = SWZL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Fbsdwzl);
        dest.writeString(Fsws);
        dest.writeString(Fcbs);
        dest.writeString(SWZL);
    }
}
