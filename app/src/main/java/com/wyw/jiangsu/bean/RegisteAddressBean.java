package com.wyw.jiangsu.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */
public class RegisteAddressBean {
    private List<AdressBean.DataListBean> dataList;
    private Map<String, List<AdressBean.DataListBean>> countries;
    private List<AdressBean.DataListBean> cities;
    private Map<String, List<AdressBean.DataListBean>> streets;
    private List<String> province;
    public RegisteAddressBean(List<AdressBean.DataListBean> cities, Map<String, List<AdressBean.DataListBean>> countries,
                              Map<String, List<AdressBean.DataListBean>> streets) {
        this.cities = cities;
        this.countries = countries;
        this.streets = streets;
    }

    public List<String> getProvince() {
        return province;
    }

    public void setProvince(List<String> province) {
        this.province = province;
    }

    public RegisteAddressBean(List<AdressBean.DataListBean> dataList, List<AdressBean.DataListBean> cities,
                              Map<String, List<AdressBean.DataListBean>> countries, Map<String, List<AdressBean.DataListBean>> streets) {
        this.dataList = dataList;
        this.cities = cities;
        this.countries = countries;
        this.streets = streets;
    }

    public List<AdressBean.DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<AdressBean.DataListBean> dataList) {
        this.dataList = dataList;
    }

    public List<AdressBean.DataListBean> getCities() {
        return cities;
    }

    public void setCities(List<AdressBean.DataListBean> cities) {
        this.cities = cities;
    }

    public Map<String, List<AdressBean.DataListBean>> getCountries() {
        return countries;
    }

    public void setCountries(Map<String, List<AdressBean.DataListBean>> countries) {
        this.countries = countries;
    }

    public Map<String, List<AdressBean.DataListBean>> getStreets() {
        return streets;
    }

    public void setStreets(Map<String, List<AdressBean.DataListBean>> streets) {
        this.streets = streets;
    }
}
