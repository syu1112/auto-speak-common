package com.allen.auto.speak.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;

//键鼠操作
public interface DD extends Library {
    DD INSTANCE = (DD) Native.loadLibrary("ddxoft/DD64", DD.class);
    //64位JAVA调用DD64.dll, 32位调用DD32.dll 。与系统本身位数无关。
    int DD_mov(int x, int y);
    int DD_movR(int dx, int dy);
    int DD_btn(int btn);
    int DD_whl(int whl);
    int DD_key(int ddcode, int flag);
    int DD_str(String s);
}
