package com.learn.ssm.chapter11.game;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBeanUtil implements InvocationHandler {
    private Object obj ;//被代理对象
    private Interceptor interceptor = null;// 拦截器
    public static <T> Object getBean(T obj, Interceptor interceptor) {

        ProxyBeanUtil _this = new ProxyBeanUtil();
        _this.obj = obj;
        _this.interceptor = interceptor;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),_this);
    }

    /**
     *
     * @param proxy 被代理对象
     * @param method　当前调度方法
     * @param args　参数
     * @return
     * @throws Throwable　异常
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object retObj = null;
        boolean exceptionFlag = false;
        //before  方法
        interceptor.before(obj);
        try{
            retObj = method.invoke(obj,args);
        }catch(Exception ex){
            exceptionFlag = true;
            ex.printStackTrace();
        }finally{
            interceptor.after(obj);
        }
        if(exceptionFlag){
            interceptor.afterThrowing(obj);
        }else{
            interceptor.afterReturning(obj);
        }
        return retObj;
    }
}
