package com.allen.auto.speak;

import com.allen.auto.speak.ts.TSPlug;
import com.allen.auto.speak.ts.TSWindow;
import com.jacob.com.Dispatch;
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
    private JTextField jTextField2;

    private int hwnd = 0;

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

        JLabel jLabel2 = new JLabel("Wait Time(s):");
        jPanel.add(jLabel2);
        jTextField = new JTextField();
        jTextField.setColumns(3);
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

        JLabel jLabelWt = new JLabel("Window Title:");
        jPanel.add(jLabelWt);
        jTextField2 = new JTextField();
        jTextField2.setText("微信");
        jTextField2.setColumns(5);
        jPanel.add(jTextField2);


        jTextArea = new JTextArea(7, 26);
        jTextArea.setText("Please input contents...");
        jTextArea.setLineWrap(true); //激活自动换行功能
        jTextArea.setWrapStyleWord(true);    //激活断行不断字功能
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
                        String windowTitle = jTextField2.getText();
                        if(null == windowTitle || "".equals(windowTitle)) {
                            JOptionPane.showMessageDialog(null,"Please input window title!", "提示", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        //获取句柄
                        hwnd = TSWindow.find("", windowTitle);
                        //判断窗口是否存在
                        if(!TSWindow.getState(hwnd, 0)) {
                            JOptionPane.showMessageDialog(null,"Program not running!", "提示", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        //开始自动喊话
                        main.setTitle("[STATUS]: runing...");  //更改窗口标题为当前状态
                        jTextArea.setEnabled(false); //设置不可修改
                        jTextField.setEnabled(false); //设置不可修改
                        jTextField2.setEnabled(false); //设置不可修改
                        //根据喊话间隔，定时执行喊话内容
                        AutoSpeakTask.getInstance().start(hwnd, jTextArea.getText(), Integer.parseInt(jTextField.getText()));
                        break;
                    case GLOBAL_HOT_KEY_STOP:
                        if(AutoSpeakTask.flag) {
                            System.err.println("invalid operation! because the program is stop");
                            break;
                        }
                        TSWindow.setState(hwnd, 9); //取消置顶
                        main.setTitle("[STATUS]: stop!"); //更改窗口标题为当前状态
                        jTextArea.setEnabled(true); //设置可修改
                        jTextField.setEnabled(true); //设置可修改
                        jTextField2.setEnabled(true); //设置可修改
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