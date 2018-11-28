package com.allen.auto.speak;

import com.allen.auto.speak.utils.DD;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class AutoSpeakTask extends TimerTask {
    private Timer timer = null;
    private static AutoSpeakTask autoSpeakTask;
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

    public void start(int s) {
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
        //回车-输入文本
        DD.INSTANCE.DD_key(313,1);
        DD.INSTANCE.DD_key(313,2);
        //粘贴
        DD.INSTANCE.DD_key(600,1);
        DD.INSTANCE.DD_key(504,1);
        DD.INSTANCE.DD_key(600,2);
        DD.INSTANCE.DD_key(504,2);
        //回车-发送文本
        DD.INSTANCE.DD_key(313,1);
        DD.INSTANCE.DD_key(313,2);
    }

    public void destroyed(){
        System.out.println("destroyed...");
        timer.cancel();
        flag = true;
    }
}
