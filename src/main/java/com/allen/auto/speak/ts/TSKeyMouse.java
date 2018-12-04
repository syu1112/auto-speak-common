package com.allen.auto.speak.ts;

import com.jacob.com.Dispatch;

public class TSKeyMouse extends TSPlug{

    private TSKeyMouse() {
    }

    public static boolean pressChar(String charStr) {
        return Dispatch.call(TSPlug.com, "KeyPressChar", charStr).getInt()==1;
    }

    public static boolean press(int vkCode) {
        return Dispatch.call(TSPlug.com, "KeyPress", vkCode).getInt()==1;
    }

    public static boolean keyDown(int vkCode) {
        return Dispatch.call(TSPlug.com, "KeyDown", vkCode).getInt()==1;
    }
}
