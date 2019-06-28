package com.wyw.jiangsu.dialog;

/**
 * Created by wyw on 2016/10/17.
 * 耳标号adapter中viewholder的数据
 */
public class ErBiaoEntity {
    private boolean isSelect;
    private String preSeven; //前七位
    private String middleFive;//中五位
    private String start;//后三位开始
    private String end = "";//后三位结束


    public ErBiaoEntity(boolean isSelect, String preSeven, String middleFive, String start, String end) {
        this.isSelect = isSelect;
        this.preSeven = preSeven;
        this.middleFive = middleFive;
        this.start = start;
        this.end = end;
    }

    public ErBiaoEntity(boolean isSelect, String preSeven, String middleFive, String start) {
        this.isSelect = isSelect;
        this.preSeven = preSeven;
        this.middleFive = middleFive;
        this.start = start;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getPreSeven() {
        return preSeven;
    }

    public void setPreSeven(String preSeven) {
        this.preSeven = preSeven;
    }

    public String getMiddleFive() {
        return middleFive;
    }

    public void setMiddleFive(String middleFive) {
        this.middleFive = middleFive;
    }

    public String getStart() {
        return start;
    }

    public int getIntStart() {
        return Integer.valueOf(start);
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public int getIntEnd() {
        return Integer.valueOf(end);
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
