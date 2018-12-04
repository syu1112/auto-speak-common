package com.allen.auto.speak;

import com.allen.auto.speak.ts.TSKeyMouse;
import com.allen.auto.speak.ts.TSPlug;
import com.allen.auto.speak.ts.TSWindow;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class AutoSpeakTask extends TimerTask {
    private Timer timer = null;
    private static AutoSpeakTask autoSpeakTask;
    private int hwnd;
    private String str;
    public static boolean flag = true;

    private AutoSpeakTask() {
    }

    //单例模式，保持这个对象
    public static AutoSpeakTask getInstance(){
        if (flag) {
            autoSpeakTask = new AutoSpeakTask();
            flag = false;
        }
        return autoSpeakTask;
    }

    public void start(int hwnd, String str, int s) {
        this.hwnd = hwnd;
        this.str = str;
        if (timer == null) {
            timer = new Timer();
        } else {
            //从此计时器的任务队列中移除所有已取消的任务。
            timer.purge();
        }
        timer.scheduleAtFixedRate(this, new Date(), s * 1000);
        System.out.println("start...waittime "+s +"s");
    }

    public void run() {
        //激活
        TSWindow.setState(hwnd, 1);
        //置顶
        TSWindow.setState(hwnd, 8);
        //恢复
        TSWindow.setState(hwnd, 12);
        //获取焦点
        TSWindow.setState(hwnd, 15);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TSKeyMouse.pressChar("enter");
        System.out.println("input:"+str+";result:"+TSPlug.sendString(hwnd, str));
        TSKeyMouse.pressChar("enter");
        TSWindow.setState(hwnd, 9); //取消置顶
    }

    public void destroyed(){
        System.out.println("destroyed...");
        timer.cancel();
        flag = true;
    }
}
