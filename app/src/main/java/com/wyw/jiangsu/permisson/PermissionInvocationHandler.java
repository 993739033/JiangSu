package com.wyw.jiangsu.permisson;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wyw on 2017/1/10.
 * 动态代理 还有api21以上才会调用
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PermissionInvocationHandler implements InvocationHandler{

    private InvokeListener listener;
    private Permission permission;

    public static PermissionInvocationHandler of(InvokeListener listener) {
        return new PermissionInvocationHandler(listener);
    }

    private PermissionInvocationHandler(InvokeListener listener) {
        this.listener = listener;
    }

    /**
     *
     * @param permission 传入的是 permisson的实体类
     * @return
     */
    public Permission bind(Permission permission) {
        this.permission = permission;
        return (Permission) Proxy.newProxyInstance(permission.getClass().getClassLoader(),
                permission.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PermissionManager.TPermissionType type = listener.invoke(new InvokeParam(proxy, method,args));
        if (proxy instanceof Permission) {
            if (!PermissionManager.TPermissionType.NOT_NEED.equals(type)) {
                ((Permission) proxy).permissionNotify(type);
            }
        }
        Log.e("PermissionInvocationHan", "invoke之前");
        Object invoke = method.invoke(permission, args);
        Log.e("PermissionInvocationHan", "invoke之后");
        return invoke;
    }
}
