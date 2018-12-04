package com.allen.auto.speak.utils;

import com.jacob.com.Dispatch;

public class TSWindow extends TSPlug {

    private TSWindow() {};

    /**
     * 查找窗口
     * 查找符合类名或者标题名的顶层可见窗口
     * @param clazz 窗口类名，如果为空，则匹配所有. 这里的匹配是模糊匹配.
     * @param title 窗口标题,如果为空，则匹配所有.这里的匹配是模糊匹配.
     * @return 整型数表示的窗口句柄，没找到返回0
     */
    public static Integer find(String clazz, String title) {
        return Dispatch.call(com, "FindWindow", clazz, title).getInt();
    }

    /**
     * 查找窗口
     * 根据指定的进程名字，来查找可见窗口.
     * @param processName   进程名. 比如(notepad.exe).这里是精确匹配,但不区分大小写.
     * @param clazz 窗口类名，如果为空，则匹配所有. 这里的匹配是模糊匹配.
     * @param title 窗口标题,如果为空，则匹配所有.这里的匹配是模糊匹配.
     * @return  整型数表示的窗口句柄，没找到返回0
     */
    public static Integer findByProcess(String processName, String clazz, String title) {
        return Dispatch.call(com, "FindWindowByProcess", processName, clazz, title).getInt();
    }

    /**
     * 获取窗口区域
     * 获取窗口在屏幕上的位置
     * @param hwnd  指定的窗口句柄
     * @param x1    返回窗口左上角X坐标
     * @param y1    返回窗口左上角Y坐标
     * @param x2    返回窗口右下角X坐标
     * @param y2    返回窗口右下角Y坐标
     * @return
     */
    public static boolean getRect(int hwnd, int x1,int y1,int x2,int y2) {
        return Dispatch.call(com, "GetWindowRect", hwnd,x1,y1,x2,y2).getInt()==1;
    }

    /**
     * 获取窗口状态
     * 获取指定窗口的一些属性
     * @param hwnd  指定的窗口句柄
     * @param flag  0是否存在、1是否处于激活、2是否可见、3是否最小化、4是否最大化、5是否置顶、6是否无响应、7是否可用
     * @return
     */
    public static boolean getState(int hwnd, int flag) {
        return Dispatch.call(com, "GetWindowState", hwnd, flag).getInt()==1;
    }


    /**
     * 设置窗口状态
     * @param hwnd  指定的窗口句柄
     * @param flag  0关闭指定窗口、1激活指定窗口、2最小化指定窗口,但不激活、3最小化指定窗口,并释放内存、4最大化指定窗口,同时激活窗口、5恢复指定窗口 ,但不激活、6隐藏指定窗口、7显示指定窗口、8置顶指定窗口、9取消置顶指定窗口、10禁止指定窗口、11取消禁止指定窗口、12恢复并激活指定窗口、13强制结束窗口所在进程、14闪烁指定的窗口、15使指定的窗口获取输入焦点
     * @return
     */
    public static boolean setState(int hwnd, int flag) {
        return Dispatch.call(com, "GetWindowState", hwnd, flag).getInt()==1;
    }

    /**
     * 设置窗口的标题
     * @param hwnd  设置窗口的标题
     * @param title 标题
     * @return
     */
    public static boolean setTitle(int hwnd, String title) {
        return Dispatch.call(com, "SetWindowText", hwnd, title).getInt()==1;
    }

    /**
     *
     * 绑定指定的窗口,并指定这个窗口的屏幕颜色获取方式,鼠标仿真模式,键盘仿真模式,以及模式设定
     * @param hwnd
     * @param display
     * @param mouse
     * @param keypad
     * @param mode
     * @return
     */
    public static boolean bind(int hwnd, String display, String mouse, String keypad, int mode) {
        return Dispatch.call(com, "BindWindow", hwnd, display, mouse, keypad, mode).getInt()==1;
    }

    /**
     * 是否绑定
     * @param hwnd
     * @return
     */
    public static boolean isBind(int hwnd) {
        return Dispatch.call(com, "IsBind", hwnd).getInt()==1;
    }

    public static boolean unBind() {
        return Dispatch.call(com, "UnBindWindow").getInt()==1;
    }

    public static boolean lockInput(int lock) {
        return Dispatch.call(com, "LockInput", lock).getInt()==1;
    }

}
