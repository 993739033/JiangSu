package com.wyw.jiangsu.runnable;

import com.wyw.jiangsu.bean.BaseArrayObjectEntity;
import com.wyw.jiangsu.bean.BaseObjectEntity;
import com.wyw.jiangsu.bean.KanYanbean;

import java.util.List;

/**
 * Created by wyw on 2016/12/22.
 * 返回状态码的异常
 */

public class StatusException {

    BaseArrayObjectEntity baseObjectArr;

    BaseObjectEntity baseObjectEntity;


    public StatusException(BaseArrayObjectEntity baseObjectArr) {
        this.baseObjectArr = baseObjectArr;
    }

    public StatusException(BaseObjectEntity baseObjectEntity) {
        this.baseObjectEntity = baseObjectEntity;
    }

    public <T> T getObjectData(Class<T> clazz) throws IllegalArgumentException {
        if (baseObjectEntity != null) {
            if (baseObjectEntity.getErrorCode() != 0) {
                throw new IllegalArgumentException(baseObjectEntity.getErrorMsg());
            } else {
                return (T) baseObjectEntity.getData();
            }
        }
        return null;
    }

    public <T> List<T> getObjectDataList(Class<T> clazz) throws IllegalArgumentException {
        if (baseObjectArr != null) {
            if (baseObjectArr.getErrorCode() != 0) {
                throw new IllegalArgumentException(baseObjectArr.getErrorMsg());
            } else {
                return (List<T>) baseObjectArr.getData();
            }
        }
        return null;
    }

    public <T> List<T> getObjectRefreshDataList(Class<T> clazz) throws IllegalArgumentException, RefreshException {
        if (baseObjectArr.getData() != null) {
            if (baseObjectArr.getErrorCode() != 0) {
                throw new RefreshException(baseObjectArr.getErrorMsg());
            } else {
                return (List<T>) baseObjectArr.getData();
            }
        }
        return null;
    }


}
