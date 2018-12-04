package com.allen.auto.speak.utils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

/**
 * 天使插件
 * 插件接口:ts.tssoft
 */
public class TSPlug {
    private static ActiveXComponent ts = new ActiveXComponent("ts.tssoft");
    public static Dispatch com =  ts.getObject();

    /**
     * 版本
     * 返回当前插件版本号
     * @return  当前插件的版本描述字符串
     */
    public static String ver() {
        return ts.invoke("Ver").getString();
    }


    /**
     * 发送字符串
     * 向指定窗口发送文本数据
     * @param hwnd  指定的窗口句柄
     * @param str   发送的文本数据
     * @return
     */
    public static boolean sendString(int hwnd, String str) {
        return Dispatch.call(com, "SendString", hwnd, str).getInt()==1;
    }

    /**
     * 发送字符串2
     * 向指定窗口发送文本数据
     * @param hwnd  指定的窗口句柄
     * @param str   发送的文本数据
     * @return
     */
    public static boolean sendString2(int hwnd, String str) {
        return Dispatch.call(com, "SendString2", hwnd, str).getInt()==1;
    }

    /**
     * 发送字符串IME模式
     * 向绑定的窗口发送文本数据
     * @param str   发送的文本数据
     * @return
     */
    public static boolean sendStringIme(String str) {
        return Dispatch.call(com, "SendStringIme", str).getInt()==1;
    }

}
