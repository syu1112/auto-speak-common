package com.allen.auto.speak;

import com.allen.auto.speak.utils.ClipboardUtil;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 自动喊话器_通用版v1.0.0
 * CreateBy Allen 2018.11.27
 */
public class Main extends JFrame implements ActionListener {

    private static final int GLOBAL_HOT_KEY_START = 0;
    private static final int GLOBAL_HOT_KEY_STOP = 1;

    private static final int F1 = 112;
    private static final int F2 = 113;

    private JTextArea jTextArea;
    private JTextField jTextField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }

    public Main() {
        init();
    }

    //初始化程序
    private void init() {
        final Main main = this;

        //注册热键
        JIntellitype.getInstance().registerHotKey(GLOBAL_HOT_KEY_START, JIntellitype.MOD_SHIFT + JIntellitype.MOD_CONTROL, F1);
        JIntellitype.getInstance().registerHotKey(GLOBAL_HOT_KEY_STOP, JIntellitype.MOD_SHIFT + JIntellitype.MOD_CONTROL, F2);
        //准备界面
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(0, 10, 10));

        JLabel jLabel = new JLabel("Hot key: Start[Shift+Ctrl+F1]  Stop[Shift+Ctrl+F2]");
        jLabel.setForeground(Color.red);
        jPanel.add(jLabel);

        JLabel jLabel2 = new JLabel("Wait Time:");
        jPanel.add(jLabel2);
        jTextField = new JTextField();
        jTextField.setColumns(5);
        jTextField.setText("10");
        jTextField.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if(!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)){
                    e.consume(); //关键，屏蔽掉非法输入
                }
            }
        });
        jPanel.add(jTextField);
        JLabel jLabel3 = new JLabel("s");
        jPanel.add(jLabel3);

        jTextArea = new JTextArea(8, 26);
        jTextArea.setText("Please input contents...");
        jTextArea.setLineWrap(true); //激活自动换行功能
        jTextArea.setWrapStyleWord(true);    // 激活断行不断字功能
        JScrollPane sp = new JScrollPane(jTextArea);
        sp.setBounds(5, 45, 650, 400);
        jPanel.add(sp);

        JLabel jLabel4 = new JLabel(" ");
        jPanel.add(jLabel4);

        // 添加热键监听器
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
            public void onHotKey(int markCode) {
                switch (markCode) {
                    case GLOBAL_HOT_KEY_START:
                        if(!AutoSpeakTask.flag) {
                            System.err.println("invalid operation! because the program is running");
                            break;
                        }
                        //开始自动喊话
                        main.setTitle("[STATUS]: runing...");  //更改窗口标题为当前状态
                        jTextArea.setEnabled(false); //设置不可修改
                        jTextField.setEnabled(false); //设置不可修改
                        ClipboardUtil.setClipbordContents(jTextArea.getText());  //将喊话内容复制到粘贴板
                        System.out.println(Integer.parseInt(jTextField.getText()));
                        AutoSpeakTask.getInstance().start(Integer.parseInt(jTextField.getText()));  //根据喊话间隔，定时执行喊话内容
                        break;
                    case GLOBAL_HOT_KEY_STOP:
                        if(AutoSpeakTask.flag) {
                            System.err.println("invalid operation! because the program is stop");
                            break;
                        }
                        main.setTitle("[STATUS]: stop!"); //更改窗口标题为当前状态
                        jTextArea.setEnabled(true); //设置可修改
                        jTextField.setEnabled(true); //设置可修改
                        AutoSpeakTask.getInstance().destroyed();    //销毁定时任务
                        break;
                }
            }
        });
        main.setTitle("AS");
        main.setSize(315, 240);
        main.setVisible(true);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setResizable(false);
        main.add(jPanel);
    }

    //监听事件
    public void actionPerformed(ActionEvent e) {
    }

}


//备忘按键的数值:
//
//public static final int F1 = 112;
//
//public static final int F2 = 113;
//
//public static final int F3 = 114;
//
//public static final int F4 = 115;
//
//public static final int F5 = 116;
//
//public static final int F6 = 117;
//
//public static final int F7 = 118;
//
//public static final int F8 = 119;
//
//public static final int F9 = 120;
//
//public static final int F10 = 121;
//
//public static final int F11 = 122;
//
//public static final int F12 = 123;
//
//public static final int ESC = 27;
//
//public static final int TAB = 9;
//
//public static final int CAPSLOCK = 20;
//
//public static final int SHIFT = 16;
//
//public static final int CTRL = 17;
//
//public static final int START_LEFT = 91;
//
//public static final int START_RIGHT = 92;
//
//public static final int CONTEXT_MENU = 93;
//
//public static final int ALT = 18;
//
//public static final int SPACE = 32;
//
//public static final int CARRIAGE_RETURN = 13;
//
//public static final int LINE_FEED = 10;
//
//public static final int BACK_SLASH = 220;
//
//public static final int BACK_SPACE = 8;
//
//public static final int INSERT = 45;
//
//public static final int DEL = 46;
//
//public static final int HOME = 36;
//
//public static final int END = 35;
//
//public static final int PAGE_UP = 33;
//
//public static final int PAGE_DOWN = 34;
//
//public static final int PRINT_SCREEN = 44;
//
//public static final int SCR_LK = 145;
//
//public static final int PAUSE = 19;
//
//public static final int LEFT_ARROW_KEY = 37;
//
//public static final int UP_ARROW_KEY = 38;
//
//public static final int RIGHT_ARROW_KEY = 39;
//
//public static final int DOWN_ARROW_KEY = 40;