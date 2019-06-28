package com.wyw.jiangsu.bean;


public class RegistryRecordBean {
    /**
     * LuId用户id
     * LLandDate登录时间
     * LIsOnline登录状态 1为登录 2为登出
     * FLIP  网络ip地址
     * DeviceId  手机设备id
     */
    private int LuId;
    private String LLandDate;
    private String LIsOnline;
    private String FLIP;
    private String DeviceId;

    public int getLuId() {
        return LuId;
    }

    public void setLuId(int luId) {
        LuId = luId;
    }

    public String getLLandDate() {
        return LLandDate;
    }

    public void setLLandDate(String LLandDate) {
        this.LLandDate = LLandDate;
    }

    public String getLIsOnline() {
        return LIsOnline;
    }

    public void setLIsOnline(String LIsOnline) {
        this.LIsOnline = LIsOnline;
    }

    public String getFLIP() {
        return FLIP;
    }

    public void setFLIP(String FLIP) {
        this.FLIP = FLIP;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }
}
